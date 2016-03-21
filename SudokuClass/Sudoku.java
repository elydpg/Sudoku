import java.util.*;
import java.util.*;
public class Sudoku{
  
  public static final int MIN_SIZE=2;
  public static final int MAX_SIZE=11;
  
  private byte[] tiles;
  
  public Sudoku(byte[] tileset){
    int size=(int)Math.pow(tileset.length,1d/4d);
    if((int)Math.pow(size,4)!=tileset.length||size<MIN_SIZE||size>MAX_SIZE){throw new IllegalArgumentException(tileset.length+" is an invalid number of tiles");}
    tiles=new byte[tileset.length];
    for(int c=0;c<tileset.length;c++){
      tiles[c]=tileset[c];
    }
  }
  public static Sudoku copy(final Sudoku obj){
    return new Sudoku(obj.tiles);
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
  
  public static void main(String[] args){
  }
}