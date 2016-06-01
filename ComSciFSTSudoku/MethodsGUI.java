//Author: Zachary Minuk
//Date created: March 26, 2016
//Date modified: June 1, 2016
package ComSciFSTSudoku;
import java.io.*;
import java.util.Arrays;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import ComSciFSTSudoku.org.json.*;
import ComSciFSTSudoku.ReadWrite.FileIO;
import ComSciFSTSudoku.SudokuClass.Sudoku;
/**A class containing the utility methods for the Sudoku GUI. */
public class MethodsGUI {
  //fields that need to be loaded from file, method required to deal with uncaught exception in global declaration
  /**A utility method that gets the file data from gamestate.json*/
  public static JSONObject getFileData(){try{return new JSONObject(new String(FileIO.readBinary(new RandomAccessFile("data/gamestate.json","rw")),"UTF-8"));}catch(Exception e){return new JSONObject();}}
  /**The JSONObject representing the gamestate.*/
  public static JSONObject fileData= getFileData();
  /**An int representing the selected game difficulty in options.*/
  public static int difficultyIndex = fileData.getInt("difficultyIndex");
  /**An int representing the actual difficulty of the current game.*/
  public static int actualDifficulty = fileData.getInt("actualDifficulty");
  /**An int representing the selected invalid tiles setting in options.*/
  public static int invalidTilesIndex = fileData.getInt("invalidTilesIndex");
  /**A long representing the time played.*/
  public static long timeKeeper = fileData.getLong("timeKeeper");
  /**A boolean representing whether or not there is a game being played.*/
  public static boolean gameOver = fileData.getBoolean("gameOver");
  /**A Sudoku representing the original state of the game. Written to file as a String.*/
  public static Sudoku originalGame = Sudoku.constructFromString(fileData.getString("originalGame"));
  /**A Sudoku representing the current state of the game. Written to file as a String.*/
  public static Sudoku game = Sudoku.constructFromString(fileData.getString("game"));
  /**A Sudoku representing the solved state of the game. Written to file as a String.*/
  public static Sudoku solvedGame = Sudoku.constructFromString(fileData.getString("solvedGame"));
  /**A utility method that converts a JSONArray to an int array.*/
  public static int[] jsonArrayToIntArray(JSONArray n){int[] a=new int[n.length()];for(int c=0;c<a.length;c++){a[c]=n.getInt(c);}return a;}
  /**An int array representing the moves made in the current game.*/
  public static int[] moves=jsonArrayToIntArray(fileData.getJSONArray("moves"));
  
  //graphical fields
  /**the main frame*/
  public static JFrame frame1 = new JFrame("Sudoku"); 
  /**the frame for the mini-stat window*/
  public static JDialog frame2 = new JDialog(new JFrame(),"Info");
  /**the panel for the main screen, holds the buttons, text, and gif*/
  public static JPanel panel1 = new JPanel();
  /**The panel for the sudoku grid, contains a grid layout that is 9x9*/
  public static JPanel panel2 = new JPanel (new GridLayout(9,9,-2,-2));
  /**the panel that holds information in the stat-window (timer and buttons)*/
  public static JPanel panel3 = new JPanel();
  /**panel that holds JLabels and JComboBoxes for help and options page*/
  public static JPanel panel4 = new JPanel();
  /**panel that holds the leaderboard table*/
  public static JPanel panel5 = new JPanel(new GridLayout(19,4));
  /**gets the image 'play.png' into an imageIcon*/
  public static ImageIcon playImage = new ImageIcon ("resources/play.png");
  /**gets the image 'help.png' into an imageIcon*/
  public static ImageIcon helpImage = new ImageIcon ("resources/help.png");
  /**gets the image 'leaderboard.png' into an imageIcon*/
  public static ImageIcon leaderImage = new ImageIcon ("resources/leaderboard.png");
  /**gets the image 'option.png' into an imageIcon*/
  public static ImageIcon optionImage = new ImageIcon ("resources/option.png");
  /**gets the image 'resume game.png' into an imageIcon*/
  public static ImageIcon resumeImage = new ImageIcon ("resources/resume game.png");
  /**gets the gif 'logo.gif' into an imageIcon*/
  public static ImageIcon gif = new ImageIcon ("resources/logo.gif");
  /**creates a button with the image playImage*/
  public static JButton playButton = new JButton (playImage);
  /**creates a button with the image helpImage*/
  public static JButton helpButton = new JButton (helpImage);
  /**creates a button with the image leaderImage*/
  public static JButton leaderButton = new JButton (leaderImage);
  /**creates a button with the image optionImage*/
  public static JButton optionButton = new JButton (optionImage);
  /**creates a button that displays the text: Go back*/
  public static JButton back = new JButton("Go Back");
  /**creates a button that displays the text: Go To Main Screen*/
  public static JButton back2 = new JButton("Go To Main Screen");
  /**creates a button that displays the text: Show Solution*/
  public static JButton check = new JButton("Show Solution");
  /**creates a button with the image resumeImage*/
  public static JButton resumeButton = new JButton (resumeImage);
  /**creates a JLabel with the gif*/
  public static JLabel gifLabel = new JLabel (gif);
  /**creates a JLabel that displays the title of the game*/
  public static JLabel title = new JLabel ("Sudoku");
  /**creates a JLabel that shows you which difficulty you are playing in*/
  public static JLabel currentMode = new JLabel ("Difficulty: ", SwingConstants.CENTER);
  /**creates a JLabel that displays the time*/
  public static JLabel time = new JLabel("Time: " + formatTime(timeKeeper), SwingConstants.CENTER);
  /**creates a JLabel that gives some game info at the bottom of main screen*/
  public static JLabel other = new JLabel ("<html><center>This game was developed by Ely Golden, Zachary Minuk, and Ethan Orlander under " +
                                           "the supervision of Mark Rottmann at Tanenbaum CHAT Wallenberg Campus. All rights reserved. \u00a9");
  /**creates a JLabel with all help information*/
  public static JLabel helpLabel = new JLabel("<html><center>The object is to place the numbers 1 to 9 in the empty squares so that each row, each " +
                                              "column and each 3x3 box contains the same number only once. This version, created kindly by " + 
                                              "Zachary Minuk, Ethan Orlander, and Ely Golden (under the supervision of Mark Rottman of course) " + 
                                              "generates a sudoku for you and times you as you complete it. Your time is then saved and can " + 
                                              "be viewed from our leaderboards. <br><br>" + 
                                              "In the options menu you can chose from a range of difficulties to challenge yourself with. "+ 
                                              "<br><br> If in options you set Invalid Tiles to 'do not show' you will get no feedback on whether your numbers " +
                                              "are correct or not. <br><br> If in options you set Invalid Tiles to 'show currently conflicting', you will get feedback " +
                                              "about whether the number you input is valid in that current situation. For example, if there is a 5 in a certain row " +
                                              "and you input a 5 at any point in that row, both numbers will turn red indicating that it is not valid.<br><br> " + 
                                              "If in options you set Invalid Tiles to 'show conflicting with solution', every number you input will turn red if " +
                                              "it should not be there.<br><br> Press shift + z to undo your previous moves. " + 
                                              "<br><br>Once the game is correctly solved, you will automatically be redirected, there is no button to " + 
                                              " press when you think you have solved it.<br><br>If you're playing a game and for some reason the " + 
                                              "window goes out of focus, the game will pause and take you back to the main screen, where you can press resume" + 
                                              " game to continue.");
  /**creates a JLabel that holds the text: Difficulty*/
  public static JLabel setting = new JLabel ("Difficulty:");
  /**creates a JLabel that holds the text: Invalid Tiles*/
  public static JLabel hintSetting = new JLabel ("Invalid Tiles:");
  /**creates a JOptionPane to display when you try to resume a game when there is no game to resume*/
  public static JOptionPane option = new JOptionPane("<html><font face = 'American Typewriter'>There is no game to resume");
  /**creates a JDialog which displays the JOptionPane*/
  public static JDialog dialog = option.createDialog(null);
  /**creates a JLabel that displays loading when the game is generating a new sudoku */
  public static JLabel load = new JLabel ("");
  /**JLabel array that holds the leaderboard info*/
  public static JLabel [] table = new JLabel[76];
  
  //other fields
  /**creates a JTextField array that represents the text boxes into which the numbers are inserted.*/
  public static JTextField [] arrayFields = new JTextField [81];
  /**An int representing the selected textbox. Used for number insertion.*/
  public static int selectedField = -1;
  /**An int representing the monitor's width.*/
  public static int width;
  /**An int representing the monitor's height.*/
  public static int height;
  /**A string representing backup text. Used for number insertion.*/
  public static String backupText = "";
  /**A String array representing the possibile difficulty settings.*/
  public static String [] difficulty = {"random","simple","easy","intermediate","expert"};
  /**A String array representing the possibile invalid tiles settings.*/
  public static String [] hints = {"do not show","show currently conflicting","show conflicting with solution"};
  /**A JComboBox allowing the selection of the difficulty settings.*/
  public static JComboBox <String> difficultySetting = new JComboBox <> (difficulty);
  /**A JComboBox allowing the selection of the invalid tiles settings.*/
  public static JComboBox <String> hintBox = new JComboBox <> (hints);
  /**A long that is used to calculate time played based on the System Time.*/
  public static long timestamp=System.currentTimeMillis()-timeKeeper;
  /**A boolean representing whether or not the undo function is possible.*/
  public static boolean undoPossible=false;
  
  /**makes time appear in hours:minutes:seconds format*/
  public static String formatTime(long time){
    String seconds=""+Math.floorMod(Math.floorDiv(time,1000l),60);
    String minutes=""+Math.floorMod(Math.floorDiv(time,60000l),60);
    String hours=""+Math.floorDiv(time,3600000l);
    if(seconds.length()==1){seconds="0"+seconds;}
    if(minutes.length()==1){minutes="0"+minutes;}
    return hours+":"+minutes+":"+seconds;
  }//end of format time
  
  /**creates timer (must be global to prevent speed issues)*/
  public static Timer timey = new Timer (50, new ActionListener() {
    public void actionPerformed (ActionEvent e) {  
      timeKeeper=System.currentTimeMillis()-timestamp;
      time.setText("Time: " + formatTime(timeKeeper));//updates JLabel
    }});//end of timer
  
  /**creates loading timer (must be global to prevent speed issues)*/
  public static Timer loadingTimer = new Timer (50, new ActionListener() {
    public void actionPerformed (ActionEvent e) {
      if(playButton.isEnabled()==false){
        gridDisplay();
      }
    }});//end of timer
  /**The method that runs on game start. Initializes all the graphics and settings. */
  public static void intro () {
    //sets the image icon
    frame1.setIconImage(new ImageIcon("resources/logo.png").getImage());
    try{com.apple.eawt.Application.getApplication().setDockIconImage(new ImageIcon("resources/logo.png").getImage());}catch(Exception e){}
    
    //frames initialized and set up
    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame1.setSize(600,600);
    frame1.setLocationRelativeTo(null); 
    frame1.setResizable(false);
    frame2.setSize(200,180);
    frame2.setResizable(false);
    frame2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//cannot close window
    
    //panels are set up
    panel1.setLayout(null);//main screen
    panel2.setBorder(BorderFactory.createEmptyBorder(15,15,15,15)); //sudoku grid
    panel3.setLayout(null);//stat window
    panel4.setLayout(null);//option and help window
    panel5.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));//leaderboard table
    
    //buttons change when mouse is over them
    playButton.setRolloverIcon(new ImageIcon("resources/play hover.png"));
    resumeButton.setRolloverIcon(new ImageIcon("resources/resume game hover.png"));
    helpButton.setRolloverIcon(new ImageIcon("resources/help hover.png"));
    leaderButton.setRolloverIcon(new ImageIcon("resources/leaderboard hover.png"));
    optionButton.setRolloverIcon(new ImageIcon("resources/option hover.png"));
    
    //sets font of all JLabels
    title.setFont(new Font("American Typewriter", Font.PLAIN, 50));
    other.setFont(new Font("American Typewriter", Font.PLAIN, 14));
    time.setFont(new Font("American Typewriter", Font.PLAIN, 15));
    helpLabel.setFont(new Font("American Typewriter", Font.PLAIN, 14));
    setting.setFont(new Font("American Typewriter", Font.PLAIN, 15));
    hintSetting.setFont(new Font("American Typewriter", Font.PLAIN, 15));
    currentMode.setFont(new Font("American Typewriter", Font.PLAIN, 15));
    load.setFont(new Font("American Typewriter", Font.PLAIN, 18));
    
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
    load.setBounds(130,100,250,50);
    other.setBounds(2,490,598,100);
    time.setBounds(0,10,200,23);
    helpLabel.setBounds(15,0,570,520);
    back.setBounds(240,520,120,30);
    back2.setBounds(30,50,140,30);
    check.setBounds(40,90,120,30);
    difficultySetting.setBounds(210,100,200,50);
    setting.setBounds(90,100,190,50);
    hintBox.setBounds(210,200,250,50);
    hintSetting.setBounds(90,200,190,50);
    currentMode.setBounds(0,120,200,40);
    
    //initializes the JTextFields with a black border and adds them to the panel
    for(int i=0;i<arrayFields.length;i++){
      arrayFields[i] = new JTextField(" ");
      arrayFields[i].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createEmptyBorder(10,10,10,10)));
    }//end of for loop
    addGameToPanel();
    
    //initializes the JLabels for the leaderboard table and adds them to panel5
    for (int i = 0; i < table.length; i++) {
      if (i == 0) {
        table[i] = new JLabel("Name", SwingConstants.CENTER);       
        table[i].setFont(new Font("American Typewriter", Font.BOLD, 12));
      }else if (i == 1) {
        table[i] = new JLabel("Difficulty", SwingConstants.CENTER);
        table[i].setFont(new Font("American Typewriter", Font.BOLD, 12));
      } else if (i == 2) {
        table[i] = new JLabel("Time", SwingConstants.CENTER);
        table[i].setFont(new Font("American Typewriter", Font.BOLD, 12));
      } else if (i == 3) {
        table[i] = new JLabel("Rank", SwingConstants.CENTER);
        table[i].setFont(new Font("American Typewriter", Font.BOLD, 12));
      }else {
        table[i] = new JLabel("");
        table[i].setFont(new Font("American Typewriter", Font.BOLD, 12));
      }//end of if
      panel5.add(table[i]);
      table[i].setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createEmptyBorder(1,1,1,1)));
    }//end of for loop
    
    //sets settings for options to what they were
    difficultySetting.setSelectedIndex(difficultyIndex);
    hintBox.setSelectedIndex(invalidTilesIndex);
    
    //declares all action listeners
    playButton.addActionListener(new MainClass.play ()); 
    resumeButton.addActionListener(new MainClass.resume());
    helpButton.addActionListener(new MainClass.help());
    leaderButton.addActionListener(new MainClass.leaderboard ());
    optionButton.addActionListener(new MainClass.option ());
    back.addActionListener(new MainClass.back());
    back2.addActionListener(new MainClass.back());
    check.addActionListener(new MainClass.check());
    frame1.addKeyListener(new MainClass.undo ());
    frame1.addWindowFocusListener(new MainClass.pause ());
    frame2.setFocusableWindowState(false);
    
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
    panel4.add(difficultySetting);
    panel4.add(hintBox);
    panel1.add(load);
    panel4.add(hintSetting);
    panel4.add(setting);
    frame1.add(panel4);
    frame2.add(panel3);
    frame1.add(panel1);
    frame1.setVisible(true);
    mainScreen();//runs next method
  }//end of intro method
  
  /**Shows the Main Screen.*/
  public static void mainScreen () {
    //makes sure only stuff needed is visible (incase user has switched between pages)
    timey.stop();//stops the clock
    loadingTimer.start();
    undoPossible=false;//makes undoing moves impossible (since no game is being played)
    frame1.setLocationRelativeTo(null);//centers main screen incase user has pressed back button from the play section
    panel5.setVisible(false);
    helpLabel.setVisible(false);
    back.setVisible(false);
    difficultySetting.setVisible(false);
    setting.setVisible(false);
    hintSetting.setVisible(false);
    hintBox.setVisible(false);
    frame2.setVisible(false);
    panel4.setVisible(false);
    panel2.setVisible(false);
    time.setVisible(true);
    check.setVisible(true);
    currentMode.setVisible(true);
    panel1.setVisible(true);
  }//end of main Screen method
  
  /**Shows the Grid from the "resume game" button.*/
  public static void gridReveal() {
    undoPossible=true;
    if(gameOver == false){
      checkInvalidTiles();
      currentMode.setText("Difficulty: " + difficulty[difficultyIndex]);//updates JLabel in stat-window
      frame1.setLocation(((width-810)/2), ((height-650)/2));
      frame2.setLocation(((width-810)/2) + 610, ((height-650)/2) + 100);
      panel1.setVisible(false);
      frame1.add(panel2);
      timestamp=System.currentTimeMillis()-timeKeeper;
      timey.start();//starts the clock
      loadingTimer.stop();
      frame2.setVisible(true);
      panel2.setVisible(true);
    } else {
      dialog.setVisible(true);//displays error message when no game to resume
    }//end of if
  }//end of grid reveal method
  
  /**Creates a new game.*/
  public static void gridDisplay () {
    loadingTimer.stop();
    gameOver = false;
    difficultyIndex = difficultySetting.getSelectedIndex();
    actualDifficulty = difficultyIndex==0?((int)(Math.random()*4))+1:difficultyIndex;
    moves = new int[0];
    undoPossible=true;
    try{
      originalGame = Sudoku.generateFromApi(difficulty[actualDifficulty]);
    }catch(Exception e){e.printStackTrace();}
    game=Sudoku.copy(originalGame);
    solvedGame=Sudoku.generateSolved(game);
    currentMode.setText("Difficulty: " + difficulty[difficultyIndex]);//updates JLabel in stat-window
    addGameToPanel();
    playButton.setEnabled(true);
    load.setText("");
    frame1.setLocation(((width-810)/2), ((height-650)/2));
    frame2.setLocation(((width-810)/2) + 610, ((height-650)/2) + 100);
    timeKeeper = 0;//resets timer incase it is not the first game being played
    time.setText("Time: 0:00:00");//updates timer JLabel
    timestamp=System.currentTimeMillis();
    timey.start();//starts the clock
    frame1.add(panel2);
    panel1.setVisible(false);
    frame2.setVisible(true);
    panel2.setVisible(true);
    check.setEnabled(true);
  }//end of grid display method
  
  /**Shows the solution.*/
  public static void solutionDisplay () {
    gameOver = true;
    undoPossible=false;
    int[] tilesToFill=originalGame.unsolvedTilePositions();
    for (int i = 0; i < tilesToFill.length; i++) {
      arrayFields[tilesToFill[i]].setText(""+(solvedGame.getTile(tilesToFill[i])+1));
      arrayFields[tilesToFill[i]].setEditable(false);
      arrayFields[tilesToFill[i]].setForeground(new Color (0,0,0));
    }//end of for loop that fills in the answers
    tilesToFill=originalGame.solvedTilePositions();
    for (int i = 0; i < tilesToFill.length; i++) {
      arrayFields[tilesToFill[i]].setForeground(new Color (0,0,0));
    }//end of for loop
    frame1.add(panel2);
    timey.stop();
    currentMode.setText("Difficulty: " + difficulty[actualDifficulty]);
    panel2.setVisible(true);
    check.setEnabled(false);
  }//end of show solution method
  
  /**Adds the game to the JTextField grid.*/
  public static void addGameToPanel(){
    panel2.removeAll();//resets previous board
    for (int i = 0; i < arrayFields.length; i++) {
      byte currentTile=originalGame.getTile(i);
      byte currentGameTile=game.getTile(i);
      
      //remove relevant focus and key listeners
      FocusListener[] f=arrayFields[i].getFocusListeners();
      for (int j = 0; j < f.length; j++) {
        if(f[j].getClass()==MainClass.locate.class){arrayFields[i].removeFocusListener(f[j]);}
      }//end of for loop
      KeyListener[] k=arrayFields[i].getKeyListeners();
      for (int j = 0; j < k.length; j++) {
        if(k[j].getClass()==MainClass.key.class||k[j].getClass()==MainClass.undo.class){arrayFields[i].removeKeyListener(k[j]);}
      }//end of for loop
      arrayFields[i].setForeground(new Color (0,0,0));
      if (currentTile == -1) {
        arrayFields[i].addFocusListener(new MainClass.locate ()); 
        arrayFields[i].addKeyListener(new MainClass.key ());
        arrayFields[i].setEditable(true);
        arrayFields[i].setFont(new Font("American Typewriter", Font.PLAIN, 20));
      } else {
        arrayFields[i].setText(""+(currentTile+1));
        arrayFields[i].addKeyListener(new MainClass.undo ());
        arrayFields[i].setEditable(false);
        arrayFields[i].setFont(new Font("American Typewriter", Font.BOLD, 20));
      }//end of if
      arrayFields[i].setText(currentGameTile==-1?" ":""+(currentGameTile+1));
      if ((game.columnOf(i) < 3 && game.rowOf(i) < 3)||(game.columnOf(i) < 3 && game.rowOf(i) < 9 && game.rowOf(i) > 5)||(game.rowOf(i) < 6 && game.rowOf(i) > 2 && game.columnOf(i) < 6 && game.columnOf(i) > 2)||(game.columnOf(i) > 5 && game.columnOf(i) < 10 && game.rowOf(i) < 9 && game.rowOf(i) > 5)||(game.columnOf(i) > 5 && game.columnOf(i) < 10 && game.rowOf(i) < 3)) {
        arrayFields[i].setBackground(new Color (216,216,216));//makes sub-grids a different colour
      }//end of if
      panel2.add(arrayFields[i]);                 
    }//end of for loop
  }//end of add game to panel
  
  /**Checks the current game for invalid tiles and updates the grid accordingly.*/
  public static void checkInvalidTiles(){
    for(int c = 0; c < 81; c++){
      if(invalidTilesIndex>0&&(game.conflictingTilePositions(c).length!=0||(invalidTilesIndex==2&&game.getTile(c)!=solvedGame.getTile(c)))){arrayFields[c].setForeground(new Color (255,0,0));}
      else{arrayFields[c].setForeground(new Color (0,0,0));}
    }//end of for loop
  }//end of check invalid tiles
  
  /**Shows the help screen.*/
  public static void helpMethod () {
    panel1.setVisible(false);
    back.setVisible(true);
    helpLabel.setVisible(true);
    panel4.setVisible(true);
    frame1.add(panel4);
  }//end of help method
  
  /**Shows the leaderboard.*/
  public static void leaderboardMethod () {
    frame1.setLocation(((width-810)/2), ((height-650)/2));
    frame2.setLocation(((width-810)/2) + 610, ((height-650)/2) + 100);
    time.setVisible(false);
    check.setVisible(false);
    currentMode.setVisible(false);
    frame1.add(panel5);
    frame2.setVisible(true);
    panel1.setVisible(false);
    panel5.setVisible(true);
  }//end of leaderboard method
  
  /**Shows the options screen.*/
  public static void optionMethod () {
    panel1.setVisible(false);
    back.setVisible(true);
    difficultySetting.setVisible(true);
    setting.setVisible(true);
    hintSetting.setVisible(true);
    hintBox.setVisible(true);
    panel4.setVisible(true);
    frame1.add(panel4);
  }//end of help method
  
  /**Converts a move, consisting of a position, initial value, and final value, into a single int.*/
  public static int recordMove(int pos,byte init,byte fin){
    int newinit=0xFF&init;
    int newfin=0xFF&fin;
    return ((0x3FFF&pos)<<16)|(newinit<<8)|newfin;
  }//end of record move
  
  /**Gets the position of a converted int move.*/
  public static int getPos(int move){
    return move>>>16;
  }//end of get pos
  
  /**Gets the initial value of a converted int move.*/
  public static byte getInit(int move){
    return (byte)(0xFF&(move>>>8));
  }//end of getInit
  
  /**Gets the final value of a converted int move.*/
  public static byte getFin(int move){
    return (byte)(0xFF&move);
  }//end of getFin
  
  /**Runs when the user has solved the game.*/
  public static void gameOver () {
    timey.stop();
    gameOver = true;
    currentMode.setText("Difficulty: " + difficulty[actualDifficulty]);
    String name = JOptionPane.showInputDialog("Congrats on solving a"+(actualDifficulty==1?" ":"n ")+difficulty[actualDifficulty]+" Sudoku! Please enter your name");
    String diff = difficulty[actualDifficulty];
    int internalDiff=5-actualDifficulty;
    String timeTaken = formatTime(timeKeeper);
    long internalTime=timeKeeper;
    if(name!=null){
      //code for adding values to the leaderboard
    }//end of if
    mainScreen();
  }//end of game over method
}//end of class