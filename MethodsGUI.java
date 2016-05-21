//Author: Zachary Minuk
//Purpose: contains all methods relevent to the GUI of Sudoku program
//Date created: March 26, 2016
//Date modified: April 26, 2016

import java.io.*;
import java.util.Arrays;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import SudokuClass.Sudoku;

public class MethodsGUI {
  
  //fields that need to be loaded from file
  public static JLabel currentMode = new JLabel ("Difficulty: ", SwingConstants.CENTER);
  public static long timeKeeper = 0;
  public static boolean gameOver = true;
  public static Sudoku originalGame = new Sudoku(3);
  public static Sudoku game = new Sudoku(3);
  public static Sudoku solvedGame = new Sudoku(3);
  
  //graphical fields
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
  public static ImageIcon resumeImage = new ImageIcon ("resume game.png");
  public static ImageIcon gif = new ImageIcon ("logo.gif");
  public static JButton playButton = new JButton (playImage);
  public static JButton helpButton = new JButton (helpImage);
  public static JButton leaderButton = new JButton (leaderImage);
  public static JButton optionButton = new JButton (optionImage);
  public static JButton back = new JButton("Go Back");
  public static JButton back2 = new JButton("Go To Main Screen");
  public static JButton check = new JButton("Show Solution");
  public static JButton resumeButton = new JButton (resumeImage);
  public static JLabel gifLabel = new JLabel (gif);
  public static JLabel title = new JLabel ("Sudoku");
  public static JLabel time = new JLabel("Time: " + (timeKeeper/1000l), SwingConstants.CENTER);
  public static JLabel other = new JLabel ("<html><center>This game was developed by Ely Golden, Zachary Minuk, and Ethan Orlander under " +
                                           "the supervision of Mark Rottmann at Tanenbaum CHAT Wallenberg Campus. All rights reserved. \u00a9");
  public static JLabel helpLabel = new JLabel("<html><center>The Classic Sudoku is a number placing puzzle based on a 9x9 grid with several " + 
                                              "given numbers. The object is to place the numbers 1 to 9 in the empty squares so that each row, each " +
                                              "column and each 3x3 box contains the same number only once. Sudoku puzzles come in endless number " + 
                                              "combinations taking anything from five minutes to several hours to solve. This version, created kindly by " + 
                                              "Zachary Minuk, Ethan Orlander, and Ely Golden (under the supervision of Mark Rottman of course) " + 
                                              "generates a sudoku for you and times you in seconds to complete it. Your time is then saved and can " + 
                                              "be viewed from our leaderboards. So go, play, have fun! Try your best to set a new high score!! <br><br>" + 
                                              "In the options menu you can chose from a range of difficulties to challenge yourself with. "+ 
                                              "<br><br>As you place numbers 1-9 in the sudoku board, you will get live feedback about whether that " + 
                                              "number interferes with another in the same row/column/sub-grid. If it does interfere, it will turn red." + 
                                              "<br><br>Once the game is correctly solved, you will automatically be redirected, there is no button to " + 
                                              " press when you think you have solved it.");
  public static JLabel leaderboardLabel = new JLabel("This feature has been disabled", SwingConstants.CENTER);
  public static JLabel setting = new JLabel ("Choose a dificulty setting:");
  
  //other fields
  public static JTextField [] arrayFields = new JTextField [81];
  public static int selectedField = -1, width, height;
  public static String backupText = "";
  public static String [] difficulty = {"random","simple","easy","intermediate","expert"};
  public static JComboBox <String> difficultySetting = new JComboBox <> (difficulty);
  public static long timestamp=System.currentTimeMillis()-timeKeeper;
  
  //creates timer (must be global to prevent speed issues)
  public static Timer timey = new Timer (50, new ActionListener() {
    public void actionPerformed (ActionEvent e) {  
      timeKeeper=System.currentTimeMillis()-timestamp;
      time.setText("Time: " + (timeKeeper/1000l));//updates JLabel
    }});//end of timer
  
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
    resumeButton.setRolloverIcon(new ImageIcon("resume game hover.png"));
    helpButton.setRolloverIcon(new ImageIcon("help hover.png"));
    leaderButton.setRolloverIcon(new ImageIcon("leaderboard hover.png"));
    optionButton.setRolloverIcon(new ImageIcon("option hover.png"));
    
    //borders of buttons invisible
    playButton.setBorderPainted(false);
    helpButton.setBorderPainted(false);
    leaderButton.setBorderPainted(false);
    optionButton.setBorderPainted(false);
    resumeButton.setBorderPainted(false);
    
    //sets font of all JLabels
    title.setFont(new Font("American Typewriter", Font.PLAIN, 50));
    other.setFont(new Font("American Typewriter", Font.PLAIN, 14));
    time.setFont(new Font("American Typewriter", Font.PLAIN, 15));
    helpLabel.setFont(new Font("American Typewriter", Font.PLAIN, 15));
    leaderboardLabel.setFont(new Font("American Typewriter", Font.PLAIN, 18));
    setting.setFont(new Font("American Typewriter", Font.PLAIN, 15));
    currentMode.setFont(new Font("American Typewriter", Font.PLAIN, 15));
    
    //gets monitor resolution for window placement
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    width = (int)screenSize.getWidth();
    height = (int)screenSize.getHeight();
    
    //sets location of everything 
    playButton.setBounds(350,22,200,85);
    resumeButton.setBounds(350,122,200,85);
    helpButton.setBounds(350,222,200,85);
    leaderButton.setBounds(350,322,200,85);
    optionButton.setBounds(350,422,200,85);
    gifLabel.setBounds(30,170,250,250);
    title.setBounds(80,50,250,50);
    other.setBounds(2,490,598,100);
    time.setBounds(0,10,200,23);
    helpLabel.setBounds(15,15,570,500);
    back.setBounds(240,520,120,30);
    leaderboardLabel.setBounds(0,0,600,600);
    back2.setBounds(30,50,140,30);
    check.setBounds(40,90,120,30);
    difficultySetting.setBounds(290,200,200,50);
    setting.setBounds(90,200,190,50);
    currentMode.setBounds(0,120,200,40);
    
    //initialize the JTextFields
    for(int i=0;i<arrayFields.length;i++){
      arrayFields[i] = new JTextField(" ");
      arrayFields[i].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createEmptyBorder(10,10,10,10)));
    }//end of for loop
    
    //declares all action listeners
    playButton.addActionListener(new MainClass.play ()); 
    resumeButton.addActionListener(new MainClass.resume());
    helpButton.addActionListener(new MainClass.help());
    leaderButton.addActionListener(new MainClass.leaderboard ());
    optionButton.addActionListener(new MainClass.option ());
    back.addActionListener(new MainClass.back());
    back2.addActionListener(new MainClass.back());
    check.addActionListener(new MainClass.check());
    
    //everything needed is added to all panels and frames
    panel1.add(playButton);
    panel1.add(resumeButton);
    panel1.add(helpButton);
    panel1.add(leaderButton);
    panel1.add(optionButton);
    panel1.add(gifLabel);
    panel1.add(title);
    panel1.add(other);
    panel3.add(time);
    panel3.add(back2);
    panel3.add(check);
    panel3.add(currentMode);
    panel4.add(back);
    panel4.add(helpLabel);
    panel4.add(leaderboardLabel);
    panel4.add(difficultySetting);
    panel4.add(setting);
    frame1.add(panel4);
    frame2.add(panel3);
    frame1.add(panel1);
    frame1.setVisible(true);
    mainScreen();//runs next method
  }//end of intro method
  
  public static void mainScreen () { 
    timey.stop();//stops the clock
    //makes sure only stuff needed is visible (incase user has switched between pages)
    frame1.setLocationRelativeTo(null);//centers main screen incase user has pressed back button from the play section 
    helpLabel.setVisible(false);
    back.setVisible(false);
    leaderboardLabel.setVisible(false);
    difficultySetting.setVisible(false);
    setting.setVisible(false);
    frame2.setVisible(false);
    panel4.setVisible(false);
    panel2.setVisible(false);
    panel1.setVisible(true);
  }//end of main Screen method
  
  public static void gridReveal() {  
    if(gameOver==false){
    //positions both frames in centre of screen (regardless of monitor's resolution)
    frame1.setLocation(((width-810)/2), ((height-650)/2));
    frame2.setLocation(((width-810)/2) + 610, ((height-650)/2) + 100);
    panel1.setVisible(false);
    timestamp=System.currentTimeMillis()-timeKeeper;
    timey.start();//starts the clock
    frame1.add(panel2);
    frame2.setVisible(true);
    panel2.setVisible(true);
    } else {
      JOptionPane option = new JOptionPane();
      option.setMessage("There is no game to resume");
      JDialog dialog = option.createDialog(null);
      dialog.setVisible(true);
    }//end of if
  }//end of grid reveal method
  
  public static void gridDisplay () {
    gameOver = false;
    panel2.removeAll();//resets previous board
    try{
      originalGame = Sudoku.generateFromApi(difficultySetting.getSelectedIndex()==0?difficulty[((int)Math.random()*4)+1]:difficulty[difficultySetting.getSelectedIndex()]);
    }catch(Exception e){System.err.println(e);}
    game=Sudoku.copy(originalGame);
    solvedGame=Sudoku.generateSolved(game);
    currentMode.setText("Difficulty: " + difficulty[difficultySetting.getSelectedIndex()]);//updates JLabel in stat-window
    for (int i = 0; i < arrayFields.length; i++) {
      byte currentTile=game.getTile(i);
      
      //remove relevant focus and key listeners
      FocusListener[] f=arrayFields[i].getFocusListeners();
      for (int j = 0; j < f.length; j++) {
        if(f[j].getClass()==MainClass.locate.class){arrayFields[i].removeFocusListener(f[j]);}
      }
      KeyListener[] k=arrayFields[i].getKeyListeners();
      for (int j = 0; j < k.length; j++) {
        if(k[j].getClass()==MainClass.key.class){arrayFields[i].removeKeyListener(k[j]);}
      }
      
      if (currentTile == -1) {
        arrayFields[i].setText(" ");
        arrayFields[i].addFocusListener(new MainClass.locate ()); 
        arrayFields[i].addKeyListener(new MainClass.key ());
        arrayFields[i].setEditable(true);
        arrayFields[i].setFont(new Font("American Typewriter", Font.PLAIN, 20));
      } else {
        arrayFields[i].setText(""+(currentTile+1));
        arrayFields[i].setForeground(new Color (0,0,0));
        arrayFields[i].setEditable(false);
        arrayFields[i].setFont(new Font("American Typewriter", Font.BOLD, 20));
      }//end of if
      if ((game.columnOf(i) < 3 && game.rowOf(i) < 3)||(game.columnOf(i) < 3 && game.rowOf(i) < 9 && game.rowOf(i) > 5)||(game.rowOf(i) < 6 && game.rowOf(i) > 2 && game.columnOf(i) < 6 && game.columnOf(i) > 2)||(game.columnOf(i) > 5 && game.columnOf(i) < 10 && game.rowOf(i) < 9 && game.rowOf(i) > 5)||(game.columnOf(i) > 5 && game.columnOf(i) < 10 && game.rowOf(i) < 3)) {
        arrayFields[i].setBackground(new Color (216,216,216));//makes sub-grids a different colour
      }//end of if
      panel2.add(arrayFields[i]);                 
    }//end of for loop
    
    //positions both frames in centre of screen (regardless of monitor's resolution)
    frame1.setLocation(((width-810)/2), ((height-650)/2));
    frame2.setLocation(((width-810)/2) + 610, ((height-650)/2) + 100);
    timeKeeper = 0;//resets timer incase it is not the first game being played
    time.setText("Time: 0");//updates timer JLabel
    timestamp=System.currentTimeMillis();
    timey.start();//starts the clock
    frame1.add(panel2);
    panel1.setVisible(false);
    frame2.setVisible(true);
    panel2.setVisible(true);
    check.setEnabled(true);
  }//end of grid display method
  
  public static void solutionDisplay () {
    panel1.setVisible(false);
    gameOver = true;
    int[] tilesToFill=originalGame.unsolvedTilePositions();
    for (int i = 0; i < tilesToFill.length; i++) {
      arrayFields[tilesToFill[i]].setText(""+(solvedGame.getTile(tilesToFill[i])+1));
      arrayFields[tilesToFill[i]].setEditable(false);
      arrayFields[tilesToFill[i]].setForeground(new Color (0,0,0));
    }//end of first for loop
    tilesToFill=originalGame.solvedTilePositions();
    for (int i = 0; i < tilesToFill.length; i++) {
      arrayFields[tilesToFill[i]].setForeground(new Color (0,0,0));
    }//end of second for loop
    frame1.add(panel2);
    timey.stop();
    panel2.setVisible(true);
    check.setEnabled(false);
  }//end of show solution method
  
  public static void helpMethod () {
    panel1.setVisible(false);
    back.setVisible(true);
    helpLabel.setVisible(true);
    panel4.setVisible(true);
    frame1.add(panel4);
  }//end of help method
  
  public static void leaderboardMethod () {
    panel1.setVisible(false);
    back.setVisible(true);
    leaderboardLabel.setVisible(true);
    panel4.setVisible(true);
    frame1.add(panel4);
  }//end of leaderboard method
  
  public static void optionMethod () {
    panel1.setVisible(false);
    back.setVisible(true);
    difficultySetting.setVisible(true);
    setting.setVisible(true);
    panel4.setVisible(true);
    frame1.add(panel4);
  }//end of help method
  
  public static void gameOver () {
    timey.stop();
    gameOver = true;
    String name = JOptionPane.showInputDialog("Congrats on winning!! Please enter your name");
    mainScreen();
  }//end of game over method
  
}//end of class