//Author: Zachary Minuk
//Purpose: contains all methods relevent to the GUI of Sudoku program
//Date created: March 26, 2016
//Date modified: April 26, 2016

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import SudokuClass.Sudoku;

public class MethodsGUI {
  public static long timeKeeper = 0;
  public static boolean gameOver = false;
  public static JFrame frame1 = new JFrame("Sudoku"); 
  public static JFrame frame2 = new JFrame("Info:");
  public static JPanel panel1 = new JPanel();
  public static JPanel panel2 = new JPanel (new GridLayout(9,9,-2,-2));
  public static JPanel panel3 = new JPanel();
  public static JPanel panel4 = new JPanel();
  public static ImageIcon playImage = new ImageIcon ("play.png");
  public static ImageIcon helpImage = new ImageIcon ("help.png");
  public static ImageIcon leaderImage = new ImageIcon ("leaderboard.png");
  public static ImageIcon optionImage = new ImageIcon ("option.png");
  public static ImageIcon gif = new ImageIcon ("logo.gif");
  public static JButton playButton = new JButton (playImage);
  public static JButton helpButton = new JButton (helpImage);
  public static JButton leaderButton = new JButton (leaderImage);
  public static JButton optionButton = new JButton (optionImage);
  public static JButton back = new JButton("Go Back");
  public static JButton quit = new JButton("Quit Game");
  public static JButton back2 = new JButton("Go To Main Screen");
  public static JButton check = new JButton("Check Solution");
  public static JLabel gifLabel = new JLabel (gif);
  public static JLabel title = new JLabel ("Sudoku");
  public static JLabel time = new JLabel("Time: " + timeKeeper, SwingConstants.CENTER);
  public static JLabel other = new JLabel ("<html><center>This game was developed by Ely Golden, Zachary Minuk, and Ethan Orlander under " +
                                           "the supervision of Mark Rottmann at Tanenbaum CHAT Wallenberg Campus. All rights reserved. \u00a9");
  public static JLabel helpLabel = new JLabel("<html><u>       How to play:</u><br><center>Using pure logic and requiring no math " + 
                                              "to solve, these fascinating puzzles offer endless fun and intellectual entertainment to puzzle fans " + 
                                              "of all skills and ages. The Classic Sudoku is a number placing puzzle based on a 9x9 grid with several " + 
                                              "given numbers. The object is to place the numbers 1 to 9 in the empty squares so that each row, each " +
                                              "column and each 3x3 box contains the same number only once. Sudoku puzzles come in endless number " + 
                                              "combinations taking anything from five minutes to several hours to solve. This version, created kindly by " + 
                                              "Zachary Minuk, Ethan Orlander, and Ely Golden (under the supervision of Mark Rottman of course) " + 
                                              "generates a sudoku for you and times you in seconds to complete it. Your time is then saved and can " + 
                                              "be viewed from our leaderboards. So go, play, have fun! Try your best to set a new high score!!");
  public static JLabel leaderboardLabel = new JLabel("This feature has been disabled", SwingConstants.CENTER);
  public static JLabel optionLabel = new JLabel("This feature has been disabled", SwingConstants.CENTER);
  public static Sudoku originalGame=new Sudoku(3);
  public static Sudoku game=new Sudoku(3);
  public static Sudoku solvedGame=new Sudoku(3);
  public static JTextField [] arrayFields = new JTextField [81];
  public static String backupText="";
  
  //creates timer (must be global to prevent speed issues)
  public static Timer timey = new Timer (1000, new ActionListener() {
    public void actionPerformed (ActionEvent e) {  
      timeKeeper++;
      time.setText("Time: " + timeKeeper);//updates JLabel
    }});;//end of timer
  
  public static void intro () {
    //frames initialized and set up
    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame1.setSize(600,600);
    frame1.setLocationRelativeTo(null); 
    frame1.setResizable(false);
    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame2.setSize(200,180);
    frame2.setResizable(false);
    frame2.setUndecorated(true);//gets rid of tool bar at top of window
    
    //panels are set up
    panel1.setLayout(null);//main screen
    panel2.setBorder(BorderFactory.createEmptyBorder(15,15,15,15)); //sudoku grid
    panel3.setLayout(null);//stat window
    panel4.setLayout(null);//option, help, leaderboard window
    
    //buttons change when mouse is over them
    playButton.setRolloverIcon(new ImageIcon("play hover.png"));
    helpButton.setRolloverIcon(new ImageIcon("help hover.png"));
    leaderButton.setRolloverIcon(new ImageIcon("leaderboard hover.png"));
    optionButton.setRolloverIcon(new ImageIcon("option hover.png"));
    
    //borders of buttons invisible
    playButton.setBorderPainted(false);
    helpButton.setBorderPainted(false);
    leaderButton.setBorderPainted(false);
    optionButton.setBorderPainted(false);
    
    //sets font of all JLabels
    title.setFont(new Font("American Typewriter", Font.PLAIN, 40));
    other.setFont(new Font("American Typewriter", Font.PLAIN, 14));
    time.setFont(new Font("American Typewriter", Font.PLAIN, 15));
    helpLabel.setFont(new Font("American Typewriter", Font.PLAIN, 15));
    leaderboardLabel.setFont(new Font("American Typewriter", Font.PLAIN, 18));
    optionLabel.setFont(new Font("American Typewriter", Font.PLAIN, 18));
    
    //sets location of everything 
    playButton.setBounds(350,100,200,88);
    helpButton.setBounds(351,200,200,87);
    leaderButton.setBounds(351,300,200,87);
    optionButton.setBounds(351,400,200,87);
    gifLabel.setBounds(30,150,250,250);
    title.setBounds(235,19,200,40);
    other.setBounds(2,490,598,100);
    quit.setBounds(50,90,100,30);
    time.setBounds(0,10,200,23);
    helpLabel.setBounds(15,0,570,280);
    back.setBounds(240,520,120,30);
    leaderboardLabel.setBounds(0,0,600,600);
    optionLabel.setBounds(0,0,600,600);
    back2.setBounds(30,50,140,30);
    check.setBounds(40,125,120,30);
    
    //everything needed is added to all panels
    panel1.add(playButton);
    panel1.add(helpButton);
    panel1.add(leaderButton);
    panel1.add(optionButton);
    panel1.add(gifLabel);
    panel1.add(title);
    panel1.add(other);
    panel3.add(quit);
    panel3.add(time);
    panel3.add(back2);
    panel4.add(back);
    panel4.add(helpLabel);
    panel4.add(leaderboardLabel);
    panel4.add(optionLabel);
    panel3.add(check);
    
    frame1.add(panel4);
    frame2.add(panel3);
    frame1.add(panel1);
    frame1.setVisible(true);
    mainScreen();
  }//end of intro method
  
  public static void mainScreen () { 
    //makes sure only stuff needed is visible (incase user has switched between pages)
    frame2.setVisible(false);
    frame1.setLocationRelativeTo(null);//centers main screen incase user has pressed back button from the play section 
    helpLabel.setVisible(false);
    back.setVisible(false);
    leaderboardLabel.setVisible(false);
    optionLabel.setVisible(false);
    panel4.setVisible(false);
    panel2.setVisible(false);
    panel1.setVisible(true); 
    playButton.addActionListener(new MainClass.play ());   
    helpButton.addActionListener(new MainClass.help());
    leaderButton.addActionListener(new MainClass.leaderboard ());
    optionButton.addActionListener(new MainClass.option ());
  }//end of main Screen method
  
  public static void gridDisplay () {
    panel1.setVisible(false);
    gameOver = false;
    panel2.removeAll();//resets previous board
    try{
      originalGame=Sudoku.generateFromApi();
    }catch(Exception e){System.err.println(e);}
    game=Sudoku.copy(originalGame);
    solvedGame=Sudoku.generateSolved(game);
    for (int i = 0; i < arrayFields.length; i++) {
      
      byte currentTile=game.getTile(i);
      if (currentTile == -1) {
        arrayFields[i] = new JTextField("");
        arrayFields[i].setFont(new Font("American Typewriter", Font.PLAIN, 20));
        arrayFields[i].addFocusListener(new MainClass.locate ()); 
      } else {
        arrayFields[i] = new JTextField(""+(currentTile+1));
        arrayFields[i].setEditable(false);
        arrayFields[i].setFont(new Font("American Typewriter", Font.BOLD, 20));
      }
      arrayFields[i].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createEmptyBorder(10,10,10,10)));
      if ((game.columnOf(i) < 3 && game.rowOf(i) < 3)||(game.columnOf(i) < 3 && game.rowOf(i) < 9 && game.rowOf(i) > 5)||(game.rowOf(i) < 6 && game.rowOf(i) > 2 && game.columnOf(i) < 6 && game.columnOf(i) > 2)||(game.columnOf(i) > 5 && game.columnOf(i) < 10 && game.rowOf(i) < 9 && game.rowOf(i) > 5)||(game.columnOf(i) > 5 && game.columnOf(i) < 10 && game.rowOf(i) < 3)) {
        arrayFields[i].setBackground(new Color (216,216,216));
      }//end of if
      panel2.add(arrayFields[i]); 
                
      
    }//end of outer for loop
    frame1.add(panel2);
    panel2.setVisible(true);
    frame1.validate();//updates the screen
  }//end of grid display method
 
  
  public static void solutionDisplay () {
    panel1.setVisible(false);
    gameOver = true;
    int[] tilesToFill=originalGame.unsolvedTilePositions();
    for (int i = 0; i < tilesToFill.length; i++) {
      arrayFields[tilesToFill[i]].setText(""+(solvedGame.getTile(tilesToFill[i])+1));
      arrayFields[tilesToFill[i]].setEditable(false);
    }//end of outer for loop
    frame1.add(panel2);
    panel2.setVisible(true);
    frame1.validate();//updates the screen
  }//end of grid display method
  
  public static void infoWindow () {
    //positions both frames in centre of screen (regardless of monitor's resolution)
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int)screenSize.getWidth();
    int height = (int)screenSize.getHeight();
    frame1.setLocation(((width-810)/2), ((height-650)/2));
    frame2.setLocation(((width-810)/2) + 610, ((height-650)/2) + 100);
    timeKeeper = 0;//resets timer incase it is not the first game being played
    frame2.setVisible(true);
    timey.start();//starts the clock
    quit.addActionListener(new MainClass.quit());
    back2.addActionListener(new MainClass.back());
    check.addActionListener(new MainClass.check());
    gridDisplay();
  }//end of info window method
  
  public static void helpMethod () {
    panel1.setVisible(false);
    back.setVisible(true);
    helpLabel.setVisible(true);
    panel4.setVisible(true);
    frame1.add(panel4);
    back.addActionListener(new MainClass.back());
  }//end of help method
  
  public static void leaderboardMethod () {
    panel1.setVisible(false);
    back.setVisible(true);
    leaderboardLabel.setVisible(true);
    panel4.setVisible(true);
    frame1.add(panel4);
    back.addActionListener(new MainClass.back());
  }//end of leaderboard method
  
  public static void optionMethod () {
    panel1.setVisible(false);
    optionLabel.setVisible(true);
    back.setVisible(true);
    panel4.setVisible(true);
    frame1.add(panel4);
    back.addActionListener(new MainClass.back());
  }//end of help method
  
}//end of class