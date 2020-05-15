#include "Board.h"
#include <string>
#include <iostream>
#include <time.h>

using namespace std;


Board::Board(){
    rest=0;
    row=0;
    column=0;
    board.resize(row,vector<char>(column,'0'));
    graph.resize(row,vector<char>(column,'*'));
}

Board::Board(int m, int n){
    rest=0;
    row=m;
    column=n;
    board.resize(row,vector<char>(column,'0'));
    graph.resize(row,vector<char>(column,'*'));
}

void Board::BoardInit(){
    int randRow, randCol;
    srand(time(NULL));
    int i=0;

    while (i<5){
        randRow=rand() % (row-1);
        randCol=rand() % (column-1);
        i++;

        if (board[randRow][randCol]!='B'){
            board[randRow][randCol]='B';
        }else{
            i--;
        }
    }

    for (int i=0; i<row;i++){
        for (int j=0; j<column; j++){
            if (board[i][j]!='B'){
                char c='0'+Calvalue(i,j);
                board[i][j]=c;
            }
        }
    }

}

void Board::printGrid(int x, int y){
    Showadjacent(x,y);
    rest=0;
    for (int i=0; i<row; i++){
        for (int j=0; j<column;j++){
            cout << graph[i][j] << " ";
            if (graph[i][j]=='*'){
                rest++;
            }
        }
        cout << endl;
    }
    cout << endl;
}

char Board::realBoard(int x, int y){
    return board[x-1][y-1];
}

bool Board::Valid(int r, int c){
    return (r>=0) && (r<row) && (c>=0) && (c<column);
}

int Board::Calvalue(int r, int c){
    int countV=0;
    if (Valid(r-1,c-1) && board[r-1][c-1]=='B'){    // North-West
        countV++;
    }
    if (Valid(r-1,c) && board[r-1][c]=='B'){        // North
        countV++;
    }
    if (Valid(r-1,c+1) && board[r-1][c+1]=='B'){    // North-East
        countV++;
    }
    if (Valid(r,c-1) && board[r][c-1]=='B'){        // West
        countV++;
    }
    if (Valid(r,c+1) && board[r][c+1]=='B'){        // East
        countV++;
    }
    if (Valid(r+1,c-1) && board[r+1][c-1]=='B'){    // South-West
        countV++;
    }
    if (Valid(r+1,c) && board[r+1][c]=='B'){        // South
        countV++;
    }
    if (Valid(r+1,c+1) && board[r+1][c+1]=='B'){    // South-East
        countV++;
    }
    return countV;
}

void Board::Showadjacent(int x, int y){
    if (graph[x-1][y-1]=='*'){
        graph[x-1][y-1]=realBoard(x,y);
    }

    if (Valid(x-2,y-2) && realBoard(x-1,y-1)=='0'){     // North-West
        graph[x-2][y-2]=realBoard(x-1,y-1);
    }

    if (Valid(x-2,y-1) && realBoard(x-1,y)=='0'){       // North
        graph[x-2][y-1]=realBoard(x-1,y);
    }

    if (Valid(x-2,y) && realBoard(x-1,y+1)=='0'){       // North-East
        graph[x-2][y]=realBoard(x-1,y+1);
    }

    if (Valid(x-1,y-2) && realBoard(x,y-1)=='0'){       // West
        graph[x-1][y-2]=realBoard(x,y-1);
    }

    if (Valid(x-1,y) && realBoard(x,y+1)=='0'){         // East
        graph[x-1][y]=realBoard(x,y+1);
    }

    if (Valid(x,y-2) && realBoard(x+1,y-1)=='0'){       // South-West
        graph[x][y-2]=realBoard(x+1,y-1);
    }

    if (Valid(x,y-1) && realBoard(x+1,y)=='0'){         // South
        graph[x][y-1]=realBoard(x+1,y);
    }

    if (Valid(x,y) && realBoard(x+1,y+1)=='0'){         // South-East
        graph[x][y]=realBoard(x+1,y+1);
    }
}
