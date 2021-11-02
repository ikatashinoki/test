
import java.util.*;
class Main {
  //initializes the Scanner, which allows the user to input through the console
  public static Scanner s=new Scanner(System.in);
  //initializes the graph, turn, win, and xy variables
  public static int [][] graph = new int [6][7];
  public static int turn=1;
  public static boolean win1=false;
  public static boolean win2=false;
  public static int x;
  public static int y;  
  
  public static void main(String[] args) {
    //this contains what is run on the console
    System.out.println("Welcome to Connect Four!!!\nColumns:\n 1 2 3 4 5 6 7");
    while (win1==win2){
      //this is where most of the methods are run
      generator();//runs the graph generation
      userInput();//runs the part where the user inputs
      y=findPlace();//runs code to find where to place coin, and stores it inside variable y.
      graph[y][x]=turn;//changes the value in the graph to the number of the current player
      
      //this if statement runs the method for whether four points are connected, and if it is, sets the boolean win1 or win2 to true, which brings it to the code for what happens when someone wins
      if (turn==1){
        win1=checker(1,1)||checker(1,0)||checker(0,1)||checker(-1,1);
      }
      else if (turn==2){
        win2=checker(1,1)||checker(1,0)||checker(0,1)||checker(-1,1);
      }
      turn=3-turn;
    }
    //this is what happens once someone has won
    generator();
    if(win1){
      System.out.print("\n\n\n\n\nPlayer 1 wins");
    }
    else{
      System.out.print("\n\n\n\n\nPlayer 2 wins");
    }
    
  }
  
  //below are all of the methods that were run
  public static int findPlace(){
    //this method looks for which row the coin should be placed in, by checking for the last open slot
    for (int k=0;k<6;k++){
      if(graph[k][x]!=0){
        return k-1;
      }
    }
    return 5;
  }
  public static void userInput(){
    //this method prints the prompts on the console, and stores the information the user inputs. It also checks if the number is valid.
    if (turn==1){
      System.out.println("Player 1's turn. Enter the # of the column. (1-7)");
    }
    else{
      System.out.println("Player 2's turn. Enter the # of the column. (1-7)");
    }
    x=s.nextInt()-1; //this stores the column number that the user inputed into the variable x
    if(true!=(x>=0&&x<=6)){
      System.out.println("\nInvalid number.\n");
      userInput();
    }
    else if(graph[0][x]!=0){
      System.out.println("\nColumn full.\n");
      userInput();
    }
  }
  public static void generator(){
    //this method contains the code to generate the graph using two for loops to traverse through the rows and columns of the 2d graph array.
    for (int i=0;i<6;i++){
      for (int j=0;j<7;j++){
        System.out.print("|"+graph[i][j]);
      }
      System.out.println("|");
    }
  }
  public static boolean checker(int incY,int incX){
    //this is the code that checks if four points are connected. This is done with loops that count the number of repeating coins, and increases the variable cnt (count) by one each time a connection is found. This checks by adding (1,0),(0,1),(1,1),(-1,1) to the previous point, which check vertical, horizontal, diagonal, and opposite diagonal, respectively.
    int l=0;
    int cnt=0;
    while(y+incY*l<=5&&y+incY*l>=0&&x+incX*l<=6&&x+incX*l>=0){
      if (graph[y+incY*l][x+incX*l]==turn){
        cnt++;
        l++; 
      }
      else{
        break;
      } 
    }
    l=-1;
    while(y+incY*l<=5&&y+incY*l>=0&&x+incX*l<=6&&x+incX*l>=0){
      if(graph[y+incY*l][x+incX*l]==turn){
        cnt++;
        l--;
      }
      else{
        break;
      }
    }
    if(cnt>=4){
      return true; 
    }else{
      return false;
    }
  }
}
