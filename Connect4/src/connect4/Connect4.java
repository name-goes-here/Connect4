package connect4;
import java.util.Scanner;
class Connect4 {
    //Board declaration
    static String boardPart = "|";
    static String[][] board = new String[6][7];
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //On board, space is empty, X is player1, O is player2 
        for(int a = 0; a < 7; a++){
            for(int b = 0; b < 6; b++){
                board[b][a] = " ";
            }
        }
        //Defining variables
        boolean ongoingGame = true;
        String column;
        int count = 0;
        System.out.println("Welcome to Connect 4! Make four in a row to win.");
        //Algorithm for an ongoing game
        while(ongoingGame){
            displayBoard();
            boolean turn = true;
            //Algorithm for a player's turn
            while(turn){
                if(checkTie()){
                    ongoingGame = false;
                    turn = false;
                    break;
                }
                if(count % 2 == 0){
                    System.out.print("Player 1, choose a column: ");
                } else {
                    System.out.print("Player 2, choose a column: ");
                }
                column = input.nextLine();
                int num;
                try{
                    num = Integer.parseInt(column) - 1;
                    try{
                        if(board[0][num] == "X" || board[0][num] == "O"){
                            System.out.println("That column is full.");
                        } else {
                            for(int c = 5; c >= 0; c--){
                                if(board[c][num] == " "){
                                    if(count % 2 == 0){
                                        board[c][num] = "X";
                                        if(checkWin(board[c][num], c, num, count)){
                                            displayBoard();
                                            System.out.println("Player 1 wins!");
                                            ongoingGame = false;
                                        }
                                    } else {
                                        board[c][num] = "O";
                                        if(checkWin(board[c][num], c, num, count)){
                                            displayBoard();
                                            System.out.println("Player 2 wins!");
                                            ongoingGame = false;
                                        }
                                    }
                                    break;
                                }
                            }
                            //Turn ends here
                            count++;
                            turn = false;
                        }
                    } catch(ArrayIndexOutOfBoundsException exception){
                        System.out.println("The board does not have that number of columns.");
                    }
                } catch(NumberFormatException nfe){
                    System.out.println("That is not a number.");
                }
            }
        }
    }
    //Displays current board
    static void displayBoard(){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++){
                System.out.print(boardPart);
                System.out.print(board[i][j]);
                if(j == 6){
                    System.out.print(boardPart);
                }
            }
            System.out.print("\n");
        }
    }
    //Check if there is a win
    static boolean checkWin(String w, int x, int y, int z){
        //Vertical
        if(x < 3){
            if(board[x][y] == board[x + 1][y] && board[x][y] == board[x + 2][y] && board[x][y] == board[x + 3][y]){
                return true;
            }
        }
        //Diagonal
        for(int d = 0; d < 3; d++){
            for(int e = 0; e < 4; e++){
                if(board[d][e] == w && board[d][e] == board[d + 1][e + 1] && board[d][e] == board[d + 2][e + 2] && board[d][e] == board[d + 3][e + 3]){
                    return true;
                }
            }
        }
        for(int d = 0; d < 3; d++){
            for(int e = 6; e > 3; e--){
                if(board[d][e] == w && board[d][e] == board[d + 1][e - 1] && board[d][e] == board[d + 2][e - 2] && board[d][e] == board[d + 3][e - 3]){
                    return true;
                }
            }
        }
        //Horizontal
        if(z > 5){
            for(int h = 0; h < 4; h++){
                if(board[x][h] == w && board[x][h] == board[x][1 + h] && board[x][h] == board[x][2 + h] && board[x][h] == board[x][3 + h]){
                    return true;
                }
            }
        }
        return false;
    }
    //Check if there is a tie (board is full)
    static boolean checkTie(){
        int number = 0;
        for(int s = 0; s < 7; s++){
            for(int t = 0; t < 6; t++){
                if(board[t][s] != " "){
                    number++;
                } else {
                    return false;
                }
            }
        }
        System.out.println("It's a tie!");
        return true;
    }
}
