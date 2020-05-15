 // bin/bash/author Jianlyu Mao

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <cstdlib>
#include <ctime>
#include <map>


using namespace std;

map<vector<char>,char> consonants={
    {{'b','f','p','v','B','F','P','V'},'1'},
    {{'c','g','j','k','q','s','x','z','C','G','J','K','Q','S','X','Z'},'2'},
    {{'d','t','D','T'},'3'},
    {{'l','L'},'4'},
    {{'m','n','M','N'},'5'},
    {{'r','R'},'6'}
};

class Soundex{
public:
    Soundex();
    Soundex(string input);
    void outputCode();
    string shrink();
    string output(string mutated);

private:
    string name;
    vector<char> to_drop{'a','e','i','o','u','y','h','w'};
};


int main(){
    string input;
    getline(cin,input);

    while (true){
        Soundex userName(input);
        userName.outputCode();
        cout << "Do you want to input again? Y or N" << endl;
        getline(cin,input);
        if (input=="Y"){
            getline(cin,input);
        }else{
            break;
        }
    }

    return 0;
}

Soundex:: Soundex(){
    name="";

}

Soundex::Soundex(string input){
    name=input;
}

void Soundex::outputCode(){
    string mutated=shrink();
    string result=output(mutated);
    cout << result<<endl;
}

string Soundex::shrink(){
    string copy_of_name=name;
    size_t i=0;

    while(i<copy_of_name.length()-1){
        for (auto& x:consonants){
            if (find(x.first.begin(),x.first.end(),copy_of_name[i])!=x.first.end()){
                if (find(x.first.begin(),x.first.end(),copy_of_name[i+1])!=x.first.end()){
                    copy_of_name.erase(i+1,1);
                    i--;
                }
            }
        }
        i++;
    }

    i=0;
    while(i<copy_of_name.length()-2){
        for (auto& x:consonants){
            if (find(x.first.begin(),x.first.end(),copy_of_name[i])!=x.first.end()){
                if (find(x.first.begin(),x.first.end(),copy_of_name[i+2])!=x.first.end()){
                    if (copy_of_name[i+1]==to_drop.at(6) || copy_of_name[i+1]==to_drop.at(7)){
                        copy_of_name.erase(i+2,1);
                        i--;
                    }
                }
            }
        }
        i++;
    }

    for (size_t i=0; i<to_drop.size();i++){
        copy_of_name.erase(remove(copy_of_name.begin(),copy_of_name.end(),to_drop[i]),copy_of_name.end());
    }

    return copy_of_name;
}

string Soundex::output(string mutated){
    if (mutated.length()==1){
        mutated=mutated+"000";
        return mutated;
    }
    for (size_t i=1;i<mutated.length();i++){
        for (auto& x:consonants){
            if (find(x.first.begin(),x.first.end(),mutated[i])!=x.first.end()){
                mutated[i]=x.second;
            }
        }
    }

    if (mutated.length()<4){
        string appending_zero(4-mutated.length(),'0');
        mutated=mutated+appending_zero;
    }else if (mutated.length()>4){
        mutated.resize(4);
    }

    return mutated;

}
