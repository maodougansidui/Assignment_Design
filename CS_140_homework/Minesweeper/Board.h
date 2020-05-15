#ifndef BOARD_H_INCLUDED
#define BOARD_H_INCLUDED
#include <string>
#include <vector>

using namespace std;

class Board{
public:
    int rest;
    // initialize the default constructor.
    Board();
    // initialize the constructor with m=row and n=column
    Board(int m, int n);
    // create a grid of game.
    void BoardInit();
    // print the grid after the user input.
    void printGrid(int x, int y);
    // remain the real Board.
    char realBoard(int x, int y);
    // check the location is valid location.
    bool Valid(int r, int c);
    // calculate the counts of mines.
    int Calvalue(int r, int c);
    // show the location value and adjacent part.
    void Showadjacent(int x, int y);


private:
    int row;
    int column;
    vector <vector <char> > board;
    vector <vector <char> > graph;

};


#endif // BOARD_H_INCLUDED
