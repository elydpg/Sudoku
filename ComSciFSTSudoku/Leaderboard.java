//Author: Ethan Orlander
//Date created: June 1, 2016
//Date modified: June 1, 2016
package ComSciFSTSudoku;
import java.sql.*;
import java.util.Arrays;

public class Leaderboard implements Comparable<Leaderboard>{
  
  public static String url = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5121998";
  public static String user = "sql5121998";
  public static String password = "mbePPU6Mig";
  public static String sql;
  public static int length = 0;
  public static Leaderboard[] theLeaderboard;
  private String name;
  private long time;
  private int invertedDifficulty;
  
  public static void main(String[] args){
    getLeaderboard();
  }
  
  public Leaderboard(String nam, Long tim, int invertedDiff){
    name=nam;
    time=tim;
    invertedDifficulty=invertedDiff;
  }
  
  public int compareTo(Leaderboard l){
    if(this.time==l.time){
      if(this.invertedDifficulty==l.invertedDifficulty){return 0;}
      else if(this.invertedDifficulty>l.invertedDifficulty){return 1;}else{return -1;}
    }
    else if(this.time>l.time){return 1;}else{return -1;}
  }
  
  public static void getLeaderboard(){
    
    try{
     /**Connect to the MySQL database*/
      Connection myConn = DriverManager.getConnection(url, user, password);
      
      /**Create a statement*/
      Statement myStmt = myConn.createStatement();
     
      /**Copy current leaderboard*/
      ResultSet myRs = myStmt.executeQuery("select * from Leaderboard");
      myRs.last();
      length = myRs.getRow() + 1;
      theLeaderboard = new Leaderboard[length];;
      myRs.first();
      do{
        theLeaderboard[myRs.getRow()] = new Leaderboard(myRs.getString("Name"), myRs.getLong("Score"), myRs.getInt("Difficulty")); 
      }while(myRs.next());
      Arrays.sort(theLeaderboard);
    }catch (Exception e){
      
    }
  }
  
  public static void addEntry(String nam, Long tim, int invertedDiff){
    
    length++;
    theLeaderboard=Arrays.copyOf(theLeaderboard,length);
    theLeaderboard[length-1] = new Leaderboard(nam, tim, invertedDiff);
    Arrays.sort(theLeaderboard);
  }
  
  //this method isn't strictly nescessary
  public static void organizeLeaderboard(){
    
    Arrays.sort(theLeaderboard);
    
  }
  
  public static void updateSQLData(){
    try{
     /**Connect to the MySQL database*/
      Connection myConn = DriverManager.getConnection(url, user, password);
      
      /**Create a statement*/
      Statement myStmt = myConn.createStatement();
     
      /**Execute query*/
      sql = "delete from Leaderboard";
      
      myStmt.executeUpdate(sql);
      System.out.println("Delete complete.");
      
      for(int i = 0; i < length; i++){
      /**Execute SQL query*/
      sql = "insert into Leaderboard "
          + " (Name, Score, Difficulty)"
          + " values ('"theLeaderboard[i].nam"', '"theLeaderboard[i].tim"', '"theLeaderboard[i].invertedDiff"')";
      }
      }catch (Exception e){
      
    }
  }
  
}