package com.company;
import java.util.Scanner;
public class tic_tac_toe {
    public static Scanner sc=new Scanner(System.in);
    public static char COMPUTERMOVE ='X';
    public static char HUMANMOVE= 'O';
    static void showBoard(char board[][])
    {
        System.out.printf("\t\t\t %c | %c | %c \n", board[0][0], board[0][1], board[0][2]);
        System.out.printf("\t\t\t-----------\n");
        System.out.printf("\t\t\t %c | %c | %c \n", board[1][0], board[1][1], board[1][2]);
        System.out.printf("\t\t\t-----------\n");
        System.out.printf("\t\t\t %c | %c | %c \n\n", board[2][0], board[2][1], board[2][2]);
    }
    static void showInstructions()
    {
        System.out.printf("\nChoose a cell numbered from 1 to 9 as below and play\n\n");
        System.out.printf("\t\t\t 1 | 2 | 3 \n");
        System.out.printf("\t\t\t-----------\n");
        System.out.printf("\t\t\t 4 | 5 | 6 \n");
        System.out.printf("\t\t\t-----------\n");
        System.out.printf("\t\t\t 7 | 8 | 9 \n\n");
    }
    static void initialise(char board[][])
    {
// Initially the board to '*' as said
        for (int i=0; i<3; i++)
        {
            for (int j=0; j<3; j++)
                board[i][j] = '*';
        }
    }
    static void declareWinner(int whoseTurn)
    {
        if (whoseTurn == 1)
            System.out.printf("COMPUTER has won\n");
        else
            System.out.printf("HUMAN has won\n");
    }
    static boolean rowCrossed(char board[][])
    {
        for (int i=0; i<3; i++)
        {
            if (board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2] &&
                    board[i][0] != '*')
                return (true);
        }
        return(false);
    }
    static boolean columnCrossed(char board[][])
    {
        for (int i=0; i<3; i++)
        {
            if (board[0][i] == board[1][i] &&
                    board[1][i] == board[2][i] &&
                    board[0][i] != '*')
                return (true);
        }
        return(false);
    }
    static boolean diagonalCrossed(char board[][])
    {
        if (board[0][0] == board[1][1] &&
                board[1][1] == board[2][2] &&
                board[0][0] != '*')
            return(true);
        if (board[0][2] == board[1][1] &&
                board[1][1] == board[2][0] &&
                board[0][2] != '*')
            return(true);
        return(false);
    }
    static boolean gameOver(char board[][])
    {
        return(rowCrossed(board) || columnCrossed(board) || diagonalCrossed(board) );
    }
    static int minimax(char board[][], int depth, boolean isAI)
    {
        int score = 0;
        int bestScore = 0;
        if (gameOver(board) == true)
        {
            if (isAI == true)
                return -10;
            if (isAI == false)
                return +10;
        }
        else
        {
            if(depth < 9)
            {
                if(isAI == true)
                {
                    bestScore = -999;
                    for(int i=0; i<3; i++)
                    {
                        for(int j=0; j<3; j++)
                        {
                            if (board[i][j] == '*')
                            {
                                board[i][j] = COMPUTERMOVE;
                                score = minimax(board, depth + 1, false);
                                board[i][j] = '*';
                                if(score > bestScore)
                                {
                                    bestScore = score;
                                }
                            }
                        }
                    }
                    return bestScore;
                }
                else
                {
                    bestScore = 999;
                    for (int i = 0; i < 3; i++)
                    {
                        for (int j = 0; j < 3; j++)
                        {
                            if (board[i][j] == '*')
                            {
                                board[i][j] = HUMANMOVE;
                                score = minimax(board, depth + 1, true);
                                board[i][j] = '*';
                                if (score < bestScore)
                                {
                                    bestScore = score;
                                }
                            }
                        }
                    }
                    return bestScore;
                }
            }
        }
        return 0;
    }
    static int bestMove(char board[][], int moveIndex)
    {
        int x = -1, y = -1;
        int score = 0, bestScore = -999;
        for (int i = 0; i <3; i++)
        {
            for (int j = 0; j <3; j++)
            {
                if (board[i][j] == '*')
                {
                    board[i][j] = COMPUTERMOVE;
                    score = minimax(board, moveIndex+1, false);
                    board[i][j] = '*';
                    if(score > bestScore)
                    {
                        bestScore = score;
                        x = i;
                        y = j;
                    }
                }
            }
        }
        return x*3+y;
    }
    // A function to play Tic-Tac-Toe
    static void playTicTacToe(int whoseTurn)
    {
        char board[][]=new char[3][3];
        int moveIndex = 0, x = 0, y = 0;
        initialise(board);
        showInstructions();
        while (gameOver(board) == false && moveIndex != 9)
        {
            int n;
            if (whoseTurn == 1)
            {
                n = bestMove(board, moveIndex);
                x = n / 3;
                y = n % 3;
                board[x][y] = COMPUTERMOVE;
                System.out.printf("COMPUTER has put a %c in cell %d\n\n", COMPUTERMOVE,
                        n+1);
                showBoard(board);
                moveIndex ++;
                whoseTurn = 0;
            }
            else if (whoseTurn == 0)
            {
                System.out.printf("You can insert in the following positions : ");
                for(int i=0; i<3; i++)
                    for (int j = 0; j < 3; j++)
                        if (board[i][j] == '*')
                            System.out.printf("%d ", (i * 3 + j) + 1);
                System.out.printf("\n\nEnter the position = ");
                n=sc.nextInt();
                n--;
                x = n / 3;
                y = n %3;
                if(board[x][y] == '*' && n<9 && n>=0)
                {
                    board[x][y] = HUMANMOVE;
                    System.out.printf ("\nHUMAN has put a %c in cell %d\n\n", HUMANMOVE,
                            n+1);
                    showBoard(board);
                    moveIndex ++;
                    whoseTurn = 1;
                }
                else if(board[x][y] != '*' && n<9 && n>=0)
                {
                    System.out.printf("\nPosition is occupied, select any one place from the available places\n\n");
                }
                else if(n<0 || n>8)
                {
                    System.out.printf("Invalid position\n");
                }
            }
        }
        if (gameOver(board) == false && moveIndex == 9)
            System.out.printf("It's a draw\n");
        else
        {
            if (whoseTurn == 1)
                whoseTurn = 0;
            else if (whoseTurn == 0)
                whoseTurn = 1;
            declareWinner(whoseTurn);
        }
    }
    public static void main(String[] args) {

        System.out.println("Tic-Tac-Toe");

        char count='Y';
        do {
            char choice;
            System.out.println("Do you want to start first?(Y/N) : ");
            choice=sc.next().charAt(0);
            if (choice=='N'){
                playTicTacToe(1);
            }
            else if (choice=='Y'){
                playTicTacToe(0);
            }
            else {
                System.out.println("Invalid Choice");
            }
            System.out.println("Do you want to quit?(Y/N) : ");
            count=sc.next().charAt(0);
        }while (count=='N');


        }
}
