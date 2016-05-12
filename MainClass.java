//Author: Zachary Minuk, Ely Golden, Ethan Orlander
//Purpose: The main class of sudoku game, runs methods from other classes
//Date created: March 26, 2016
//Date modified: April 26, 2016
import java.awt.event.*;
public class MainClass { 
  public static void main (String [] args) throws Exception {
    MethodsGUI.intro();
  }//end of main
  
  //Below are action listeners that run certain methods when certain buttons are pressed
  static class play implements ActionListener {
    public void actionPerformed (ActionEvent e) {
      MethodsGUI.infoWindow();
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
  
  static class quit implements ActionListener {
    public void actionPerformed (ActionEvent e) {
      System.exit(0);
    }}//quits the game when quit button is pressed
  
    static class check implements ActionListener {
    public void actionPerformed (ActionEvent e) {
      MethodsGUI.solutionDisplay();
    }}
  
}//end of class