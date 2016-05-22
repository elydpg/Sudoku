package ReadWrite;
import java.io.*;
public class FileIO{
    private static long ceilDiv(long x, long y) {
         long r = x / y;
         if ((x ^ y) >= 0 && (r * y != x)) {
         r++;
         }
         return r;
     }
  public static String readFile(FileReader fileToRead) throws Exception{
    BufferedReader br=new BufferedReader(fileToRead);
    String importedFile="";
    String line="";
    do{
     line=br.readLine();
     if(line!=null){importedFile=importedFile+line+"\n";}
    }while(line!=null);
    return importedFile;
  }
  public static void writeFile(String fileToWrite,FileWriter where2WriteIt) throws Exception{
    PrintWriter pw=new PrintWriter(where2WriteIt);
      pw.print(fileToWrite);
    pw.close();
  }
    public static byte[][] readExtendedBinary(RandomAccessFile data)throws Exception{
    data.seek(0);
    long gigabytes=ceilDiv(data.length(),1073741824);
    long subbytes=(data.length()==0)?0:(((data.length()-1)%1073741824)+1);
    byte[][] imported=new byte[(int)gigabytes][(int)subbytes];
    for(int count=0;count<imported.length;count++){
     data.read(imported[count]);
    }
    return imported;
  }
    public static byte[] readBinary(RandomAccessFile data)throws Exception{
    data.seek(0);
    int fileLength=((data.length()>2147483647)?2147483647:(int)data.length());
    byte[] imported=new byte[fileLength];
    data.read(imported);
    return imported;
  }
  public static void writeExtendedBinary(byte[][] data,RandomAccessFile writeTo)throws Exception{
    writeTo.setLength(0);
    for(int count=0;count<data.length;count++){
      writeTo.write(data[count]);
    }
  }
  public static void writeBinary(byte[] data, RandomAccessFile writeTo)throws Exception{
    writeTo.setLength(0);
    writeTo.write(data);
  }
 public static void main(String[] args) throws Exception{
 }
}