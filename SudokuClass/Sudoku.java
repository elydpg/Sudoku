import java.util.*;
import java.util.*;
public class Sudoku{
  
  public static final int MIN_SIZE=2;
  public static final int MAX_SIZE=11;
  
  private byte[] tiles;
  
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
  
  public static void main(String[] args){
  }
}