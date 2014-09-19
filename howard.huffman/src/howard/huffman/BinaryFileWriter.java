package howard.huffman;

import java.io.File;
import java.io.FileOutputStream;

/**
 * A class that produces a binary file from input text files.
 * This class is included in the Huffman encoding project.
 * 
 * @author Shaun Howard, EECS 233 TAs
 */
public class BinaryFileWriter {
  
  /**
   * Writes a binary file as output.
   * 
   * @param fileName - the file to convert to binary
   * @param byteArray - the input byte code
   * @throws Exception - throws exception if either parameter doesn't exist
   */
  public static void writeBinaryFile(String fileName, byte[] byteArray) throws Exception{
    
    /* The output stream for writing to the file. */
    FileOutputStream output = new FileOutputStream (new File(fileName));
    output.write(byteArray);
    output.close();
  }
  
  /**
   * Creates a new binary file from the given String sequence and text file.
   * 
   * @param seq - the encoded text file
   * @param fileName - the file to output the binary encoding to
   * @throws Exception - throws exception if either parameter doesn't exist
   */
  public static void createBinaryFile(String seq, String fileName) throws Exception{
    writeBinaryFile(fileName,toByteSequence(seq));
  }
  
  /**
   * Converts a String to a byte sequence.
   * 
   * @param data - the String to convert to a byte sequence
   * @return  a new byte sequence from the given String 
   * @throws Exception - throws exception if parameter doesn't exist
   */
  public static byte[] toByteSequence(String data) throws Exception{
    
    /* Counter for making sequence. */
    int j=-1;
    
    /* Size of the byte array. */
    int byteArrSize = data.length()/8;
    
    /* Checks if the byte array size is adequate. */
    if (data.length()%8 != 0)
      byteArrSize++;
    
    /* Byte array for output. */
    byte[] byteSeq = new byte[byteArrSize];
    
    /* Converts the encoded file to byte code. */
    for(int i=0;i< data.length();i++){
      
      if (i%8 == 0){
        j++;
        byteSeq[j] = 0x00;
      }
      
      byte tmp;
      
      if (data.charAt(i) == '1'){
        tmp = 0x01;
      } else if (data.charAt(i) == '0') {
        tmp = 0x00;
      } else
        throw new Exception ("error in format");
      byteSeq[j] = (byte) (tmp | ( byteSeq[j] << 1));
    }
    
    return byteSeq;
  }
  
}