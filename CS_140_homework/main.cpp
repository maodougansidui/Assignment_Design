// bin/bash/author    Jianlyu Mao

#include <iostream>
#include <string>
#include <iomanip>
#include <limits>
#include <vector>
#include <algorithm>

using namespace std;

const int MAXSCOOPS = 5;        // This is the constant of maximum of scoops per cone.

const float PRICE=2.00;         // This is the constant of the price per scoops when
                                // number is less than or equal to 2.

const float PRICEAFTER=1.25;    // This is the constant of the price after 2.

const int MAXCONES= 10;         // This is the constant of maximum of cones.


/**
This function do the following:
    display the price list.
**/

void vendorInterface();

/**
This function will present the ice cream
flavors and corresponding character codes.
**/
void iceFlavor();

/**
This function will calculate the total charge
of the ice cream user purchased.
**/
float totalCharge(int *param, int numberOfCones);

/**
This function will prompt for user to input the number of scoops.
**/

int scoopsForCone();

/**
This function will return a vector containing different flavors user choose.
**/
vector<char> flavorForScoop(int scoops);

/**
This function will simply display cone in ASCII
representation.
**/
void coneGraph(vector<char> arr);




int main(){

    int cones[10]= {};              // create an array to calculate total bill.
    int numberOfCones, j=0;
    float total;
    bool done=false;

    vendorInterface();              // These two functions would present users with info.
    iceFlavor();

    // The code below will ask for the input to get number of cones and do the error checking.

    while (!done){
        cout << "Please input the number of cones that you wish to purchase(10 max): \n";
        cin >> numberOfCones;
        if (cin.fail() || numberOfCones<0 || numberOfCones>MAXCONES ){
            cout << "Error, the number is in range of 1 to 10. "<< endl;
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
        }else{
            done=true;
        }
    }


    for (j=0;j<numberOfCones;j++){
        int scoops=scoopsForCone();
        cones[j]=scoops;
        vector<char> flavorPlus = flavorForScoop(scoops);
        coneGraph(flavorPlus);

    }

    total=totalCharge(cones,numberOfCones);
    cout << "Total Charge: $" << total<< endl;


    return 0;
}



void vendorInterface(){
    // basically it just displays information to the users.

    cout << "Welcome to Gordon's Ice Cream! \n"
         <<  "1 scoop-->" << setw(26) << "$2.00 \n"
         <<  "2 scoops-->" << setw(25) << "$4.00 \n"
         <<  "each scoop after 2-->" << setw(13) << "$1.25" << endl;

}

void iceFlavor(){
    // This function will show the flavors of the ice cream.

    cout << "Ice Cream Flavors: Only one input character for each flavor. \n"
         << "Vanilla" << setw(25) << "! \n"
         << "Chocolate" << setw(23) << "@ \n"
         << "Cookies N' Cream" << setw(16) << "# \n"
         << "Mint Chocolate Chip" << setw(13) << "$ \n"
         << "Chocolate Chip Cookie Dough" << setw(5) << "% \n"
         << "Buttered Pecan" << setw(18) << "^ \n"
         << "Cookie Dough" << setw(20) << "& \n"
         << "Strawberry" << setw(22) << "* \n";

}


float totalCharge(int *param, int numberOfCones){
    int i= 0;
    float addend, sum=0;
    int *ptr=param;
    for (i=0;i<numberOfCones;i++){
        if (*ptr>2){                        // If the scoop
            addend = *ptr * PRICEAFTER;
        }else{
            addend = *ptr * PRICE;
        }
        sum+= addend;
        ptr++;
    }

    return sum;
}


int scoopsForCone(){
    int numberOfScoops;
    bool done=false;
    while (!done){
        cout << "How many scoops for cone?(limit 5 scoops per cone): \n";
        cin >> numberOfScoops;
        if (cin.fail() || numberOfScoops<1 || numberOfScoops>MAXSCOOPS){
            cout << "Error, the number is in range of 1 to 5.Try again! "<< endl;
            cin.clear();
            cin.ignore(numeric_limits<streamsize>::max(), '\n');
        }else{
            done=true;
        }
    }

    return numberOfScoops;
}

vector<char> flavorForScoop(int scoops){
    vector<char> flavors{'!','@','#','$','%','^','&','*'};
    vector<char> chosen;
    bool done= false;
    int i=0;
    for (i=0;i<scoops;i++){
        char userInput;
        while (!done){
            cout << "Specify the flavor for scoop " << i+1 << ": ";
            cin >> userInput;
            if (find(flavors.begin(),flavors.end(),userInput)!=flavors.end()){
                chosen.push_back(userInput);
                done=true;
            }else{
                cout << "Error, not the available flavor symbol. Try again!"<< endl;
                cin.clear();
                cin.ignore(numeric_limits<streamsize>::max(), '\n');
            }
        }
        done=false;

    }

    return chosen;

}

void coneGraph(vector<char> arr){
    vector<char> copyOne= arr;
    cout << "Here is your cone! Enjoy!!!\n";
    for (char x: copyOne){
        cout << setw(6) << "( " << x << " ) \n";
    }

    cout << "    \\___/ \n";
    cout << "     | | \n";

}

