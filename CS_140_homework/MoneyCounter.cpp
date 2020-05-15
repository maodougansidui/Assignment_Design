// bin/bash/author Jianlyu Mao

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <cstdlib>
#include <ctime>

using namespace std;




class Counter{
public:
    Counter();
    // Initialize the Maximum counter to be 9999 default.

    Counter(int count);
    // Initialize the Maximum counter to be user's input.

    ~Counter();

    int prompt();
    // Ask the user to input the maximum value.

    void instruction();
    // give the instruction on how to input the number.

    int result(vector<string> input);

    vector<string> input();

    void message();

    bool getOverflow();

    void output(int result,vector<string> input);

    int getCurrentValue();

private:
    int maxValue;
    int currentValue;
    bool goneOverFlow;
    void testMax();

};

void destruct(Counter* ptr);

int main(){

    vector<string> userInput;
    int result;
    int amount;

    Counter *cnt= new Counter;
    while(1){


        cnt->message();
        while(!cnt->getOverflow()){
            cnt->instruction();
            userInput=cnt->input();
            result=cnt->result(userInput);
            cnt->output(result,userInput);
        }
        char quit;
        cout << "Do you want to shut down the default counter? Y/N" << endl;
        cin >> quit;
        if (quit=='Y'){
            break;
        }else{
            amount=cnt->prompt();
            cnt= new Counter(amount);
        }
    }

    destruct(cnt);



    return 0;
}


Counter::Counter()
{
    maxValue=9999;
    currentValue=0;
    goneOverFlow=false;
}

Counter::Counter(int count){
    maxValue=count;
    currentValue=0;
    goneOverFlow=false;
    testMax();
}

Counter::~Counter(){

}

void Counter::testMax(){
    if (maxValue>=9999){
        cout << "Illegal maximum value. \n";
        exit(1);
    }
}

int Counter::prompt(){
    int integerAmount;
    string sth;
    cout << "Please enter an integer amount less than 9999 to initialize the counter. \n";
    cin.ignore();
    getline(cin,sth);
    integerAmount=stoi(sth);
    return integerAmount;
}

void Counter::instruction(){

    cout << "Enter a key, either a,s,d,f, followed by 1-9. or o for overflow \n"
         << "a -> cents, s -> dimes, d -> dollars, f-> tens of dollars \n";

}


vector<string> Counter::input(){
    vector<string> vct;
    string dollars;
    bool done=false;
    while (!done){
        getline(cin,dollars);
        if (dollars.empty() || dollars=="o"){
            done=true;
            if (dollars=="o"){
                 vct.push_back(dollars);
            }
        }else{
             vct.push_back(dollars);
        }
    }


    return vct;
}

int Counter::result(vector<string> input){
    int calculator=0;
    for (size_t i=0;i<input.size();++i){
        string x=input.at(i);
        int number=x[1]-48;
        if (x.at(0)=='a'){
            calculator+=number*1;
        }else if (x.at(0)=='s'){
            calculator+=number*10;
        }else if (x.at(0)=='d'){
            calculator+=number*100;
        }else if (x.at(0)=='f'){
            calculator+=number*1000;
        }
    }
    return calculator;
}

void Counter::message(){

    cout << "Maximum value of the counter is " << maxValue << endl;

    cout << "Current value of the counter is " << currentValue << endl;
}

bool Counter::getOverflow(){
    return goneOverFlow;
}


void Counter::output(int result,vector<string> input){
    currentValue+=result;
    if (currentValue<=maxValue){
        cout << "There is no overflow. \n";
    }else{
        cout << "You have gone over the amount. \n";
        goneOverFlow=true;
    }
    if (input.back()!="o"){
        cout << "Current value of the counter is " << currentValue <<endl;
    }
}

int Counter::getCurrentValue(){
    return currentValue;
}

void destruct(Counter* ptr){
    delete ptr;
}
