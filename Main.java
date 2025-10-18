import java.util.*;
public class Main{
    class Game{//this game class will hold all the methods and variables of the game
        String p1;
        String p2;
        int grid[][];
        boolean over;
        Game(String p1,String p2){
            this.p1=p1;
            this.p2=p2;
            grid=new int[3][3];
            over=false;
        }
        void show(){// i print the board using this im thinkin of adding colors to it but donnno how yet
            //update figured the stackoverflow is still helpful added the colors(ANSI escape codes)
            System.out.println();
            String r="\u001B[0m";
            String red="\u001B[31m";
            String blue="\u001B[34m";
            String green="\u001B[32m";
            System.out.println(green+"+---+---+---+"+r);

            for(int i=0;i<3;i++){
                System.out.print("|");
                for(int j=0;j<3;j++){
                    int v=grid[i][j];
                    String s=v==1?red+"X"+r:v==2?blue+"O"+r:" ";
                    System.out.print(" "+s+" |");
                }
                System.out.println();
                System.out.println(green+"+---+---+---+"+r);
            }
            System.out.println();
        }
        boolean win(int p){//checks if any row,col or primary or non primary diagboal is filled or not,
            //a fun trick you call this function after adding value this way you don't have to maintain who actually won, i wasted a lot of time of this state management
            for(int i=0;i<3;i++)
            if(grid[i][0]==p&&grid[i][1]==p&&grid[i][2]==p)
            return true;
            for(int j=0;j<3;j++)
            if(grid[0][j]==p&&grid[1][j]==p&&grid[2][j]==p)
            return true;
            if(grid[0][0]==p&&grid[1][1]==p&&grid[2][2]==p)
            return true;
            if(grid[0][2]==p&&grid[1][1]==p&&grid[2][0]==p)
            return true;
            return false;
        }
        boolean full(){
            for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
            if(grid[i][j]==0)return false;
            return true;
        }
    }
    public static void main(String args[]){
        String p1;
        String p2;
        Scanner sc=new Scanner(System.in);
        System.out.println("Hello and welcome to tickky tacccky toiiiee!!! heheTT");
        System.out.println("Enter Player 1 Name: ");
        p1=sc.nextLine();
        System.out.println("Enter Player 2 Name: ");
        p2=sc.nextLine();
        System.out.println("Toh chaliye shuru karte hain(Technical guruji ki awaz mai)!!");
        Main m=new Main();
        Game g=m.new Game(p1,p2);
        int turn=1;
        while(true){
            System.out.println((turn==1?g.p1:g.p2)+"'s turn. enter coordinates row,col (0-2):");
            g.show();
            String line=sc.nextLine().trim();
            if(line.length()==0){
                System.out.println("invalid");
                continue;
            }
            String[] parts=line.contains(",")?line.split(","):line.split("\\s+");//i was testing and mistakengly added , so thought people might do that often hence added coma too
            if(parts.length<2){
                System.out.println("enter two numbers");
                continue;
            }
            int r=0,c=0;
            try{
                r=Integer.parseInt(parts[0].trim());
                c=Integer.parseInt(parts[1].trim());
            }catch(Exception e){
                System.out.println("invalid");//was gonna do it puerly based on flow, but this had too many edge cases hence used a try catch, will improve it in future
                continue;
            }
            if(r<0||r>2||c<0||c>2){
                System.out.println("coordinates 0-2");// index out of bound ke liye
                continue;
            }
            if(g.grid[r][c]!=0){
                System.out.println("already taken");//checks if the cell is occupied
                continue;
            }
            g.grid[r][c]=turn;
            if(g.win(turn)){
                g.show();
                System.out.println((turn==1?g.p1:g.p2)+" wins!!!<3<3");//checking win
                System.out.println("play again? y/n");
                String ans=sc.nextLine().trim().toLowerCase();
                if(ans.length()>0&&ans.charAt(0)=='y'){
                    g=new Main().new Game(g.p1,g.p2);
                    turn=1;
                    continue;
                }
                break;
            }
            if(g.full()){
                g.show();
                System.out.println("Draw!!!");//checking draw cause if it gets filled even if no win, then it's a draw
                System.out.println("play again? y/n");
                String ans=sc.nextLine().trim().toLowerCase();
                if(ans.length()>0&&ans.charAt(0)=='y'){
                    g=new Main().new Game(g.p1,g.p2);
                    turn=1;
                    continue;
                }
                break;
            }
            turn=turn==1?2:1;
        }
    }
}