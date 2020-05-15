// bin/bash/author Jianlyu Mao

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <cstdlib>
#include <ctime>
#include <map>
#include "Board.h"

class Game{
public:
    // default constructor
    Game();
    // Initialize the boolean and board.
    Game(bool value);
    // Game processing.
    void getInput();
    // check the result. If true, then continue, if false, either win or lose,
    // ask user whether play it again or not.
    bool result(int x, int y);

private:
    bool done;
    Board board;
};



int main(){
    Game g(false);
    g.getInput();
    return 0;
}

Game::Game(){// leave blank
}

Game::Game(bool value){
    done=value;
    board= Board(5,5);
    board.BoardInit();
}


void Game::getInput(){
    while (!done){
        int x,y;
        cout << "x-position = ";
        cin >> x;
        cout << "y-position = ";
        cin >> y;
        board.printGrid(x,y);
        if (result(x,y)==true){
            continue;
        }else{
            char answer;
            cout << "DO YOU WANT TO PLAY AGAIN? Y/N" << endl;
            cin >> answer;
            if (answer=='Y'){
                board=Board(5,5);
                board.BoardInit();
            }else{
                done=true;
            }
        }
    }
}

bool Game::result(int x, int y){
    char index=board.realBoard(x,y);
    if (board.rest<=5 && index!='B'){
        cout << "Yeah! You win!" << endl;
        return false;
    }
    if (index=='B'){
        cout << "Boom! You lose!" << endl;
        return false;
    }else{
        return true;
    }
}
