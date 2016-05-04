package SudokuClass;

import java.util.*;
import java.io.*;

/** A data type to represent sudoku boards in Java. Sudokus are stored as an array of byte tiles. A sudoku board of size n contains n^4 tiles.
  * 
  * <br><br>
  * 
  * Sudoku generation from API comes from QQWing's sudoku generator https://qqwing.com
  */
public class Sudoku{
  /** The smallest possible size for a Sudoku board*/
  public static final int MIN_SIZE=2;
  /** The largest possible size for a Sudoku board*/
  public static final int MAX_SIZE=11;
  
  private byte[] tiles;
    
    private static byte[] stringArrayToByteArray(String[] arr){
      byte[] b=new byte[arr.length];
      for(int c=0;c<arr.length;c++){
        b[c]=Byte.parseByte(arr[c]);
      }
      return b;
    }
    
    private static byte[] apiStringToByteArray(String api){
      byte[] b=new byte[api.length()];
      for(int c=0;c<b.length;c++){
        b[c]=(Byte.parseByte(""+api.charAt(c)));
        b[c]--;
      }
      return b;
    }
  
    private static int[] appendToArray(int[] a,int[] b){
      int oldLength=a.length;
      a=Arrays.copyOf(a,a.length+b.length);
      for(int c=0;c<b.length;c++){
        a[oldLength+c]=b[c];
      }
      return a;
    }
    
    private static byte[] appendToArray(byte[] a,byte[] b){
      int oldLength=a.length;
      a=Arrays.copyOf(a,a.length+b.length);
      for(int c=0;c<b.length;c++){
        a[oldLength+c]=b[c];
      }
      return a;
    }
  
    private static byte[] removeElementVal(byte[] a,byte elementVal){
    byte[] b=new byte[0];
    for(int c=0;c<a.length;c++){
      if(a[c]!=elementVal){
        b=Arrays.copyOf(b,b.length+1);
        b[b.length-1]=a[c];
      }
    }
    return b;
  }
    
    private static int[] removeElement(int[] arr,int element){
      int[] b=new int[arr.length-1];
      int subcount=0;
      for(int c=0;c<arr.length;c++){
        if(c!=element){b[subcount]=arr[c];subcount++;}
      }
      return b;
    }
    
    private static byte[] removeByteElement(byte[] arr,int element){
      byte[] b=new byte[arr.length-1];
      int subcount=0;
      for(int c=0;c<arr.length;c++){
        if(c!=element){b[subcount]=arr[c];subcount++;}
      }
      return b;
    }
  
    private static boolean allEmementsUnique(byte[] b){
     if(b.length>256){return false;}
      for(int i=0;i<b.length-1;i++){
        for(int j=i+1;j<b.length;j++){
          if(b[i]==b[j]){return false;}
         }
        }              
    return true;   
  }
    
   private static int[] removeDuplicates(int[] a){
      Arrays.sort(a);
      int[] b=new int[] {a[0]};
      for(int c=1;c<a.length;c++){
        if(a[c]!=a[c-1]){b=Arrays.copyOf(b,b.length+1);b[b.length-1]=a[c];}
      }
      return b;
   }
  
    private static boolean arrayContains(int[] array,int val){
      for(int c=0;c<array.length;c++){
        if(array[c]==val){return true;}
      }
      return false;
    }
    
    private static int[] naturalNumbersUntil(int num){
      int[] nums=new int[num];
      for(int c=1;c<num;c++){
        nums[c]=nums[c-1];
        nums[c]++;
      }
      return nums;
    }
    
    private static byte[] naturalBytesUntil(int num){
      byte[] nums=new byte[num];
      for(int c=1;c<num;c++){
        nums[c]=nums[c-1];
        nums[c]++;
      }
      return nums;
    }
    /** Constructs a Sudoku board from the list of tiles.*/
    public Sudoku(byte[] tileset){
      int size=(int)Math.pow(tileset.length,1d/4d);
      if((int)Math.pow(size,4)!=tileset.length||size<MIN_SIZE||size>MAX_SIZE){throw new IllegalArgumentException(tileset.length+" is an invalid number of tiles");}
      for(int c=0;c<tileset.length;c++){
        if(tileset[c]<-1||tileset[c]>=(size*size)){throw new IllegalArgumentException("Tile "+c+" has an invalid value");}
         }
      tiles=new byte[tileset.length];
      for(int c=0;c<tileset.length;c++){
        tiles[c]=tileset[c];
      }
    }
    /** Constucts an empty Sudoku board of size size.*/
    public Sudoku(int size){
      if(size<MIN_SIZE||size>MAX_SIZE){throw new IllegalArgumentException(size+" is an invalid sudoku size");}
      tiles=new byte[(int)Math.pow(size,4)];
      for(int c=0;c<tiles.length;c++){
        tiles[c]=-1;
      }
    }
    /**Constructs a random valid Sudoku. Equivalent to generateSolved(new Sudoku(size),random);*/
    public Sudoku(int size,Random random){
      Sudoku s=generateSolved(new Sudoku(size),random);
      tiles=s.tiles;
    }
    /**Returns a solved version of the sudoku sud. Note that this method returns a distict object and does not modify the original Sudoku. 
      * If there is more than one solution, this method will return a random solution.*/
    public static Sudoku generateSolved(Sudoku sud,Random random){
      if(sud.isValidSudoku()==false){throw new IllegalArgumentException("Sudoku is already invalid");}
      if(sud.isSolvedSudoku()){throw new IllegalArgumentException("Sudoku is already solved");}
      Sudoku s=copy(sud);
      int[] positionsToSolve=s.unsolvedTilePositions();
      byte[][] availableNumbers=new byte[positionsToSolve.length][0];
       for(int c=0;c<positionsToSolve.length;c++){
        availableNumbers[c]=naturalBytesUntil(s.getSize()*s.getSize());
        }
       int ct=0;
       while(ct<positionsToSolve.length){
        if(availableNumbers[ct].length==0){s.tiles[positionsToSolve[ct]]=-1;availableNumbers[ct]=naturalBytesUntil(s.getSize()*s.getSize());ct--;}
        else{
          int rnd=random.nextInt(availableNumbers[ct].length);
          s.tiles[positionsToSolve[ct]]=availableNumbers[ct][rnd];
          availableNumbers[ct]=removeByteElement(availableNumbers[ct],rnd);
          if(s.conflictingTilePositions(positionsToSolve[ct]).length==0){ct++;}
        }
       }
       return s;
      }
    /**Returns a solved version of the sudoku sud. Note that this method returns a distict object and does not modify the original Sudoku. 
      * If there is more than one solution, this method will return a random solution.*/ 
    public static Sudoku generateSolved(Sudoku sud){
      return generateSolved(sud,new Random());
    }
    
    /**Generates amount size 3 Sudokus using the QQWIng API, with difficulty equal to difficulty. 
      * Valid difficulties are "simple","easy","intermediate","expert","unknown",or "random".
      * "random" difficulty chooses one of "simple","easy","intermediate", or "expert" with equal probability. */
    public static Sudoku[] generateFromApi(int amount,String difficulty)throws Exception{
      if(difficulty.equals("random")){difficulty=new String[] {"simple","easy","intermediate","expert"}[(int)(Math.random()*4)];}
      File file = new File(Sudoku.class.getClassLoader().getResource("SudokuClass/qqwing-1.3.4.jar").toURI());
      file.setReadable(true, false);
      file.setExecutable(true, false);
      file.setWritable(true, false);
      String[] command={"java","-jar",file.getCanonicalPath(),"--generate",""+amount, "--difficulty",difficulty,"--one-line"};
      Process proc = Runtime.getRuntime().exec(command);
      proc.waitFor();
      // Then retreive the process output
      InputStream in = proc.getInputStream();
      InputStream err = proc.getErrorStream();
      byte b[]=new byte[in.available()];
      in.read(b,0,b.length);
      String input=new String(b);
      byte c[]=new byte[err.available()];
      err.read(c,0,c.length);
      String error=new String(c);
      if(error.length()>0){throw new RuntimeException("Generator encountered a runtime error at api: "+error);}
      String[] inputs=(input.replaceAll("[\\.]","0")).split("\n");
      Sudoku[] sudokus=new Sudoku[amount];
      for(int d=0;d<amount;d++){
        sudokus[d]=new Sudoku(apiStringToByteArray(inputs[d]));
      }
      return sudokus;
    }
    /**Generates 1 size 3 Sudoku using the QQWIng API, with difficulty equal to difficulty. 
      * Valid difficulties are "simple","easy","intermediate","expert","unknown",or "random".
      * "random" difficulty chooses one of "simple","easy","intermediate", or "expert" with equal probability. */
    public static Sudoku generateFromApi(String difficulty)throws Exception{
      return generateFromApi(1,difficulty)[0];
    }
    
    /**Generates amount size 3 Sudokus using the QQWIng API, with difficulty equal to "unknown". */
    public static Sudoku[] generateFromApi(int amount)throws Exception{
      return generateFromApi(amount,"unknown");
    }
    
    /**Generates 1 size 3 Sudoku using the QQWIng API, with difficulty equal to "unknown". */
    public static Sudoku generateFromApi()throws Exception{
      return generateFromApi(1,"unknown")[0];
    }
    
  /** Returns this Sudoku board as a String*/
  public String toString(){
    String str="";
    for(int c=0;c<tiles.length;c++){
      str=str+tiles[c];
      if(c!=tiles.length-1){str=str+"|";}
    }
    return str;
  }
   /** Constructs a Sudoku from String stringForm*/ 
  public static Sudoku constructFromString(String stringForm){
    return new Sudoku(stringArrayToByteArray(stringForm.split("\\|")));
  }
  /** Returns this Sudoku board as a String in technical format*/
  public String toTechnicalString(){
    String str="";
    for(int c=0;c<tiles.length;c++){
      str=str+tiles[c];
      if(c!=tiles.length-1){str=str+"\u001f";}
    }
    return str;
  }
  /** Constructs a Sudoku from String in technical format stringForm*/ 
  public static Sudoku constructFromTechnicalString(String stringForm){
    return new Sudoku(stringArrayToByteArray(stringForm.split("\u001f")));
  }
  /**Creates a copy of this Sudoku obj*/
  public static Sudoku copy(final Sudoku obj){
    return new Sudoku(obj.tiles);
  }
  /**Tests this and obj for equality. If this and obj are equal, reutrns true. Else, returns false. */
  public boolean equals(Object obj){
    return Arrays.equals(tiles,((Sudoku)obj).tiles);
  }
  /**Returns a hashCode for this Sudoku.*/
  public int hashCode(){
    return Arrays.hashCode(tiles);
  }
  /**Returns this Sudoku's list of tiles*/
  public byte[] getTiles(){
    byte[] tileset=new byte[tiles.length];
    for(int c=0;c<tiles.length;c++){
      tileset[c]=tiles[c];
    }
    return tileset;
  }
  /**Returns the size of this Sudoku board.*/
  public int getSize(){
    return (int)Math.pow(tiles.length,1d/4d);
  }
  /**Returns the byte tile of this Sudoku board at tile position tilePosition.*/
  public byte getTile(int tilePosition){
    return tiles[tilePosition];
  }
  /**Returns the byte tile of this Sudoku board at row row and column column.*/
  public byte getTile(int row,int column){
    return tiles[getTilePosition(row,column)];
  }
  /**Returns the byte tile of this Sudoku board at box box and box position pos.*/
  public byte getTileAtBox(int box,int pos){
    return tiles[getTilePositionAtBox(box,pos)];
  }
  /**Sets the tile at tile position tileNumber to tileValue.*/
  public void setTile(int tileNumber,byte tileValue){
    if(tileValue<-1||tileValue>=(getSize()*getSize())){throw new IllegalArgumentException("Tile "+tileValue+" has an invalid value");}
    tiles[tileNumber]=tileValue;
  }
  /**Sets the tile at tile row row and column column to tileValue.*/
  public void setTile(int row,int column,byte tileValue){
    if(tileValue<-1||tileValue>=(getSize()*getSize())){throw new IllegalArgumentException("Tile "+tileValue+" has an invalid value");}
    tiles[getTilePosition(row,column)]=tileValue;
  }
  /**Sets the tile at tile box box and box position pos to tileValue.*/
  public void setTileAtBox(int box,int pos,byte tileValue){
   if(tileValue<-1||tileValue>=(getSize()*getSize())){throw new IllegalArgumentException("Tile "+tileValue+" has an invalid value");}
   tiles[getTilePositionAtBox(box,pos)]=tileValue;
  }
  /**Returns the tile position at row row and column column.*/
    public int getTilePosition(int row,int column){
    return getSize()*getSize()*row+column;
  }
  /**Returns the tile position at box box and box position pos.*/
  public int getTilePositionAtBox(int box,int pos){
    return getSize()*(box/getSize())*getSize()*getSize()+getSize()*(box%getSize())+getSize()*getSize()*(pos/getSize())+(pos%getSize());
  }
  /**Returns the row at tile position tilePosition.*/
  public int rowOf(int tilePosition){
    return tilePosition/(getSize()*getSize());
  }
  /**Returns the column at tile position tilePosition.*/
  public int columnOf(int tilePosition){
    return tilePosition%(getSize()*getSize());
  }
  /**Returns the box at tile position tilePosition.*/
  public int boxOf(int tilePosition){
    return (tilePosition/(getSize()*getSize()*getSize()))*getSize()+(tilePosition%(getSize()*getSize()))/getSize();
  }
  /**Returns the box position at tile position tilePosition.*/
  public int boxPositionOf(int tilePosition){
    return ((tilePosition/(getSize()*getSize()))%getSize())*getSize()+(tilePosition%getSize());
  }
  /**Returns a byte array of the tiles in row row.*/
  public byte[] getRow(int row){
    byte[] rowTiles=new byte[getSize()*getSize()];
    for(int c=0;c<rowTiles.length;c++){
      rowTiles[c]=tiles[getTilePosition(row,c)];
    }
    return rowTiles;
  }
  /**Returns a byte array of the tiles in column column.*/
  public byte[] getColumn(int column){
    byte[] columnTiles=new byte[getSize()*getSize()];
    for(int c=0;c<columnTiles.length;c++){
      columnTiles[c]=tiles[getTilePosition(c,column)];
    }
    return columnTiles;
  }
  /**Returns a byte array of the tiles in box box.*/
  public byte[] getBox(int box){
    byte[] boxTiles=new byte[getSize()*getSize()];
    for(int c=0;c<boxTiles.length;c++){
      boxTiles[c]=tiles[getTilePositionAtBox(box,c)];
    }
    return boxTiles;
  }
  /**Checks if this is a solved Sudoku. If so, returns true. Else, returns false.*/
  public boolean isSolvedSudoku(){
    return unsolvedTilePositions().length==0;
  }
  /**Checks if this is a valid Sudoku. If so, returns true. Else, returns false.*/
  public boolean isValidSudoku(){
    return invalidTilePositions().length==0;
  }
  /**Checks if this is a valid Sudoku AND if this is a solved Sudoku. If so, returns true. Else, returns false.*/
  public boolean isValidSolvedSudoku(){
    return isSolvedSudoku()&&isValidSudoku();
  }
  /**Returns an int array of all the tile positions of the blank tiles.*/
  public int[] unsolvedTilePositions(){
    int[] tilepos=new int[0];
    for(int c=0;c<tiles.length;c++){
      if(tiles[c]==-1){tilepos=appendToArray(tilepos,new int[] {c});}
    }
    return tilepos;
  }
  /**Returns an int array of all the tile positions of the non-blank tiles.*/
  public int[] solvedTilePositions(){
    int[] tilepos=new int[0];
    for(int c=0;c<tiles.length;c++){
      if(tiles[c]!=-1){tilepos=appendToArray(tilepos,new int[] {c});}
    }
    return tilepos;
  }
  /**Returns an int array of all the tile positions of the tiles that conflict with another tile on the board.*/
  public int[] invalidTilePositions(){
    int[] tilepos=new int[0];
    for(int c=0;c<tiles.length;c++){
      if(conflictingTilePositions(c).length!=0){tilepos=appendToArray(tilepos,new int[] {c});}
    }
    return tilepos;
  }
  /**Returns an int array of all the tile positions of the tiles that do not conflict with another tile on the board.*/
  public int[] validTilePositions(){
    int[] tilepos=new int[0];
    for(int c=0;c<tiles.length;c++){
      if(conflictingTilePositions(c).length==0){tilepos=appendToArray(tilepos,new int[] {c});}
    }
    return tilepos;
  }
  /**Returns an int array of all the tile positions of the tiles that conflict with the tile at tile position tilePosition.*/
  public int[] conflictingTilePositions(int tilePosition){
    if(tiles[tilePosition]==-1){return new int[0];}
    int[] conflicts=new int[0];
    for(int c=0;c<getSize()*getSize();c++){
      if(getTile(rowOf(tilePosition),c)==tiles[tilePosition]&&getTilePosition(rowOf(tilePosition),c)!=tilePosition){
        int app=getTilePosition(rowOf(tilePosition),c);
        if(arrayContains(conflicts,app)==false){conflicts=appendToArray(conflicts,new int[] {app});}
      }
      if(getTile(c,columnOf(tilePosition))==tiles[tilePosition]&&getTilePosition(c,columnOf(tilePosition))!=tilePosition){
        int app=getTilePosition(c,columnOf(tilePosition));
        if(arrayContains(conflicts,app)==false){conflicts=appendToArray(conflicts,new int[] {app});}
      }
      if(getTileAtBox(boxOf(tilePosition),c)==tiles[tilePosition]&&getTilePositionAtBox(boxOf(tilePosition),c)!=tilePosition){
        int app=getTilePositionAtBox(boxOf(tilePosition),c);
        if(arrayContains(conflicts,app)==false){conflicts=appendToArray(conflicts,new int[] {app});}
      }
    }
    Arrays.sort(conflicts);
    return conflicts;
  }
  /**Returns an int array of all the tile positions of the tiles that conflict with the tile at row row and column column.*/
  public int[] conflictingTilePositions(int row,int column){
    return conflictingTilePositions(getTilePosition(row,column));
  }
  /**Returns an int array of all the tile positions of the tiles that conflict with the tile at box box and box position pos.*/
  public int[] conflictingTilePositionsAtBox(int box,int pos){
    return conflictingTilePositions(getTilePositionAtBox(box,pos));
  }
  
  public static void main(String[] args)throws Exception{
  }
}