// bin/bash/author Jianlyu Mao

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <cstdlib>
#include <ctime>

using namespace std;

/**
This class is called Red_Yellow_Green_Game which will play the game with users.
**/


class Red_Yellow_Green_Game{
public:
    void init(bool var){            // initialize the variables.
        done=var;
        unsigned seed=time(0);
        srand(seed);
        int computerNumber=(rand() % 899) + 100;
        randomNumber=to_string(computerNumber);
    }

    string getNumber(){             // return the randomNumber, getter.
        return randomNumber;
    }

    bool getBool(){                 // return the Boolean done, getter.
        return done;
    }

    void prompt(){                  // ask for user's guess.
        if (firstTime==1){
            cout << "Please enter a guess? \n";
            firstTime++;
        }else{
            cout << "Enter the next guess? \n";
        }
    }

    string input(){                 // returns user's input.
        string inputNumber;
        cin >> inputNumber;
        return inputNumber;
    }

    vector<int> colors(string input){
        vector<int> vct{0,0,0};        // index 0 is red, index 1 is green, index 2 is yellow.
        int i=0;
        if (randomNumber.compare(input)==0){
            vct.at(1)=3;
            done=true;
        }else{
            for (i=0; i<3;i++){
                size_t found=randomNumber.find(input.at(i));
                if (found ==string::npos){          // not found
                    vct.at(0)+=1;
                }else if (input.at(i)==randomNumber.at(i)){
                    vct.at(1)+=1;
                }else{
                    if (found !=string::npos){
                        vct.at(2)+=1;
                    }
                }
            }
        }

        return vct;
    }

    void output(vector<int> colors){                // if the result suits with the random number,
        if (done){                                  // print out the congrat part.
            cout << "CONGRATULATIONS, YOU ARE CORRECT!!!!!!!!!"<< endl;
        }else{
            // if not, print out the corresponding colors.
            cout << "You have: " << colors.at(0) << "red \n"
                 << "          " << colors.at(1) << "green \n"
                 << "          " << colors.at(2) << "yellow \n";
        }
    }


private:            // private variables.
    string randomNumber;
    bool done=false;
    int firstTime=1;

};



int main(){

    Red_Yellow_Green_Game game;         // create an object called game.
    game.init(false);                   // initialize the Boolean done false.4

    bool done=game.getBool();
    string someNumber=game.getNumber();

    while (!done){
        game.prompt();
        string input=game.input();
        vector<int> vect=game.colors(input);
        game.output(vect);
        done=game.getBool();
    }


}
