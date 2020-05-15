// #/bin/ author: Jianlyu Mao

#include <iostream>
#include <string>

using namespace std;


int main(){

    // these are the constants for MET of activities.
    //
    const int SLEEPING=1;
    const int WALKING=2;
    const int BASKETBALL=8;
    const float ratioKgtoPounds=2.2;

    string activity;
    int minutes;
    int weight;
    double output;
    // first is the sleeping, walking, basketball.
    string calories[3]= {"sleeping","walking", "basketball"};
    string contOrNot;

    while (true){

        cout << "We have 3 types of activities: Sleeping, walking and basketball. \n"
             << "Please input one of the above activities: \n";

        cin >> activity;

        cout << "Please input the number of minutes spent on that activity: \n";
        cin >> minutes;

        cout << "Please input the weight in pounds: \n";
        cin >> weight;

        if (activity.compare(calories[0])==0){
            output=(0.0175*SLEEPING*weight*minutes)/ratioKgtoPounds;
        } else if (activity.compare(calories[1])==0){
            output=0.0175*WALKING*weight/ratioKgtoPounds*minutes;
        } else if (activity.compare(calories[2])==0){
            output=0.0175*BASKETBALL*weight/ratioKgtoPounds*minutes;
        }

        cout << "the calories are " << output << "\n";
        cout << "Do you wanna do it again? Y/N : \n";

        cin >> contOrNot;

        if (contOrNot.compare("Y")==0){
            continue;
        }else{
            break;
        }
    }

    return 0;
}
