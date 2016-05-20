//Author: Zachary Minuk, Ely Golden, Ethan Orlander
//Purpose: The main class of sudoku game, runs methods from other classes
//Date created: March 26, 2016
//Date modified: May 19, 2016
import java.awt.event.*;
import java.awt.*;
import SudokuClass.Sudoku;

public class MainClass { 
  public static void main (String [] args) throws Exception {
    MethodsGUI.intro();
  }//end of main
  
  //Below are action listeners that run certain methods when certain buttons are pressed
  static class play implements ActionListener {
    public void actionPerformed (ActionEvent e) {
      MethodsGUI.gridDisplay();
    }}//displays the sudoku board and info screen when play button is pressed
  
  static class help implements ActionListener {
    public void actionPerformed (ActionEvent e) {
      MethodsGUI.helpMethod();
    }}//runs the help method when help button is pressed
  
  static class leaderboard implements ActionListener {
    public void actionPerformed (ActionEvent e) {
      MethodsGUI.leaderboardMethod();
    }}//runs the leaderboard method when leaderboard button is pressed
  
  static class option implements ActionListener {
    public void actionPerformed (ActionEvent e) {
      MethodsGUI.optionMethod();
    }}//runs the option method when option button is pressed
  
  static class back implements ActionListener {
    public void actionPerformed (ActionEvent e) {
      MethodsGUI.mainScreen();
    }}//runs the main screen method when the 'return home' button is pressed
  
  static class check implements ActionListener {
    public void actionPerformed (ActionEvent e) {
      MethodsGUI.solutionDisplay();
    }}//runs the solution display method when you press show solution button
  
  //determines when a JTextArea is pressed and unpressed and determines which of the 81 it is
  static class locate implements FocusListener{
    public void focusGained (FocusEvent e) {
      if (MethodsGUI.gameOver==false) {
        MethodsGUI.selectedField=0;
        Component theField=e.getComponent();
        while(MethodsGUI.selectedField<81){
          if(MethodsGUI.arrayFields[MethodsGUI.selectedField]==theField){break;}
          MethodsGUI.selectedField++;
        }//end of if
        MethodsGUI.arrayFields[MethodsGUI.selectedField].selectAll();
        MethodsGUI.backupText=MethodsGUI.arrayFields[MethodsGUI.selectedField].getText().replaceAll("[\u200b]","");//replaces with a zero width space
      }//end of if
    }//end of focus gained
    public void focusLost (FocusEvent e) {
      if (MethodsGUI.gameOver==false) {
      }//end of if
    }//end of focus lost
  }//end of locate
  
  //find out which key is typed in JTextArea and if not 1-9 does not display it. Number will turn red if there is a conflicting number in same sub-grid, row, or column
  static class key implements KeyListener {
    public void keyTyped (KeyEvent e) {
      if((""+e.getKeyChar()).matches("[1-9\\ ]")||e.getKeyChar()==8){
      if(e.getKeyChar()==8){MethodsGUI.arrayFields[MethodsGUI.selectedField].setText(" ");}
      else{MethodsGUI.arrayFields[MethodsGUI.selectedField].setText("");}
      MethodsGUI.game.setTile(MethodsGUI.selectedField,(byte)(e.getKeyChar()==' '||e.getKeyChar()==8?-1:Character.getNumericValue(e.getKeyChar())-1));
      for(int c=0;c<81;c++){
        if(MethodsGUI.game.conflictingTilePositions(c).length==0){MethodsGUI.arrayFields[c].setForeground(new Color (0,0,0));}
        else{MethodsGUI.arrayFields[c].setForeground(new Color (255,0,0));}
      }//end of if
      if(MethodsGUI.game.equals(MethodsGUI.solvedGame)){
        MethodsGUI.gameOver();
      }//end of if
      MethodsGUI.frame1.requestFocusInWindow();
      }//end of if
      else{
        e.setKeyChar('\u200b');
        MethodsGUI.arrayFields[MethodsGUI.selectedField].setText(MethodsGUI.backupText);
        MethodsGUI.frame1.requestFocusInWindow();
      }//end of if
    }//end of key typed
    public void keyPressed (KeyEvent e) {}//must be here to run
    public void keyReleased (KeyEvent e) {}//must be here to run
  }//end of key listener
}//end of class