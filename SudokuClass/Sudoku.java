import java.util.*;
import java.util.*;
public class Sudoku{
  
  public static final int MIN_SIZE=2;
  public static final int MAX_SIZE=11;
  
  private byte[] tiles;
  
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
  
    private static boolean allEmementsUnique(byte[] b){
     if(b.length>256){return false;}
      for(int i=0;i<b.length-1;i++){
        for(int j=i+1;j<b.length;j++){
          if(b[i]==b[j]){return false;}
         }
        }              
    return true;   
  }
  
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
  
  public static Sudoku copy(final Sudoku obj){
    return new Sudoku(obj.tiles);
  }
  
  public boolean equals(Object obj){
    return Arrays.equals(tiles,((Sudoku)obj).tiles);
  }
  
  public int hashCode(){
    return Arrays.hashCode(tiles);
  }
  
  public byte[] getTiles(){
    byte[] tileset=new byte[tiles.length];
    for(int c=0;c<tiles.length;c++){
      tileset[c]=tiles[c];
    }
    return tileset;
  }
  
  public int getSize(){
    return (int)Math.pow(tiles.length,1d/4d);
  }
  
  public byte getTile(int tileNumber){
    return tiles[tileNumber];
  }
  
  public byte getTile(int row,int column){
    return tiles[getSize()*getSize()*row+column];
  }
  
  public byte getTileInBox(int box,int pos){
    return tiles[getSize()*(box/getSize())*getSize()*getSize()+getSize()*(box%getSize())+getSize()*getSize()*(pos/getSize())+(pos%getSize())];
  }
  
  public void setTile(int tileNumber,byte tileValue){
    if(tileValue<-1||tileValue>=(getSize()*getSize())){throw new IllegalArgumentException("Tile "+tileValue+" has an invalid value");}
    tiles[tileNumber]=tileValue;
  }
  
  public void setTile(int row,int column,byte tileValue){
    if(tileValue<-1||tileValue>=(getSize()*getSize())){throw new IllegalArgumentException("Tile "+tileValue+" has an invalid value");}
    tiles[getSize()*getSize()*row+column]=tileValue;
  }
  
  public void setTileInBox(int box,int pos,byte tileValue){
   if(tileValue<-1||tileValue>=(getSize()*getSize())){throw new IllegalArgumentException("Tile "+tileValue+" has an invalid value");}
   tiles[getSize()*(box/getSize())*getSize()*getSize()+getSize()*(box%getSize())+getSize()*getSize()*(pos/getSize())+(pos%getSize())]=tileValue;
  }
  
  public byte[] getRow(int row){
    byte[] rowTiles=new byte[getSize()*getSize()];
    int offset=getSize()*getSize()*row;
    for(int c=0;c<rowTiles.length;c++){
      rowTiles[c]=tiles[offset+c];
    }
    return rowTiles;
  }
  
  public byte[] getColumn(int column){
    byte[] columnTiles=new byte[getSize()*getSize()];
    for(int c=0;c<columnTiles.length;c++){
      columnTiles[c]=tiles[getSize()*getSize()*c+column];
    }
    return columnTiles;
  }
  
  public byte[] getBox(int box){
    int rowOffset=getSize()*(box/getSize());
    int columnOffset=getSize()*(box%getSize());
    byte[] boxTiles=new byte[getSize()*getSize()];
    for(int c=0;c<boxTiles.length;c++){
      boxTiles[c]=tiles[rowOffset*getSize()*getSize()+columnOffset+getSize()*getSize()*(c/getSize())+(c%getSize())];
    }
    return boxTiles;
  }
  
  public boolean isSolvedSudoku(){
    return tiles.length==removeElementVal(tiles,(byte)-1).length;
  }
  
  public boolean isValidSudoku(){
    for(int c=0;c<getSize()*getSize();c++){
      if((allEmementsUnique(getRow(c))&&allEmementsUnique(getColumn(c))&&allEmementsUnique(getBox(c)))==false){return false;}
    }
    return true;
  }
  
  public boolean isValidSolvedSudoku(){
    return isSolvedSudoku()&&isValidSudoku();
  }
  
  public static void main(String[] args){
  }
}