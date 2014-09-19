package howard.huffman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.String;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

/** 
 * A class for the Huffman encoding library.
 * This is included in the Huffman encoding project.
 * This class is used to read a file to String, make an unsorted linked list
 * of characters and their frequencies from a String, sort a linked list in 
 * ascending order of character frequencies, create a Huffman encoding tree,
 * update character codes in the tree, and output an encoded file as binary. 
 * 
 * @author Shaun Howard
 */
public class HuffmanLibrary {
  
  /**
   * Reads the input file as a String.
   * 
   * @param fileName - the name of the file to read 
   * @return  the contents of the file as a String
   */
  public static String readFileAsString(String fileName){
    
    /* A new file from the file name. */
    File f = new File(fileName);
    
    /* Read file as bytes and return as String. */
    try {
      
      /* Byte array of file. */
      byte[] bytes = Files.readAllBytes(f.toPath());
      
      /* String returned from byte array of file. */
      return new String(bytes,"UTF-8");
      
    } catch (FileNotFoundException e) {
      System.out.println("File does not exist.");
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("File cannot be opened.");
      e.printStackTrace();
    }
    
    return "";
    
  }
  
  /**
   * Creates an unsorted linked list of characters and their frequencies in
   * the given String for the Huffman tree. This linked list is to be sorted by
   * character frequency later in the encoding process.
   * 
   * @param fileContents - the input file to read as a String
   * @return  the unsorted linked list of the file characters and their frequencies
   */
  public static HLinkedList createHList(String fileContents){
    
    return new HLinkedList(fileContents);
    
  }
  
  /**
   * Fetches a sorted version of the input linked list. The linked list
   * is sorted by ascending character frequency. Since the HTreeNode
   * class implements the Comparable interface, these nodes can be 
   * placed in an array and sorted through the Arrays class of the Collections
   * framework in regards to their frequency.
   * 
   * @param list - the linked list to sort
   * @return  the linked list sorted by character frequencies
   */
  public static HLinkedList getSortedLinkedList(HLinkedList list){
    
    /* If the list is null, return a null list. */
    if (list.getHeadNode() == null) {
      return new HLinkedList();
    }
    
    /* If the list root is the only node, return the same list. */
    if (list.length() == 1){
      return new HLinkedList(list.getHeadNode());
    }
    
    /* An array of the nodes of the unsorted list for sorting. */
    HTreeNode[] nodeArray = new HTreeNode[list.length()];
    
    /* Reference to the head node of the unsorted list for iteration. */
    HTreeNode hNode = list.getHeadNode();
    
    /* The linked list to be sorted by character frequency. */  
    HLinkedList sortedList = new HLinkedList();
    
    /* Copies the nodes of the unsorted list into an array for comparison. */
    for (int i = 0; i < list.length(); i++){
      nodeArray[i] = hNode;
      hNode = hNode.next();
    }
    
    /* Sorts the nodes in ascending order based on their frequency. */
    Arrays.sort(nodeArray);
    
    /* 
     * Checks for and eliminates any duplicates from the sorted node array by checking next node.
     * Not necessarily needed, but can be helpful if reading of string file misses duplicates.
     */
    for (int i = 0; i < nodeArray.length - 1; i++){
      
      /* If node is equal to next node, move nodes back in array. */
      if(nodeArray[i].equals(nodeArray[i+1])) {
        
        /* Moves the nodes back by one in the index. */
        for (int j = i; j + 1 < nodeArray.length; j++) {
          
          nodeArray[j] = nodeArray[j + 1];
          
        }
      }
    }
    
    /* Inserts the sorted nodes into the list, in ascending order of their frequencies. */
    for (int i = 0; i < nodeArray.length; i++){
      sortedList.insertAtEnd(nodeArray[i]);
    }
    
    return sortedList;
    
  }
  
  /**
   * Creates a Huffman tree from the given linked list of characters
   * and their frequencies.
   * Precondition - input linked list does not have to be sorted.
   * 
   * @param list - a linked list of characters and their frequencies
   * @return  the Huffman encoding tree of the given linked list
   */
  public static HTree createHuffmanTree(HLinkedList list){
    
    return new HTree(getSortedLinkedList(list));
    
  }
  
  /**
   * Updates the codes of nodes in the Huffman tree through recursion.
   * Calls the setCodeValues(root) method, which recursively updates the
   * code value of each node. Sets the left subtree node to 0, the right
   * subtree node to 1. 
   * 
   * @param tree - the tree to update with codes
   */
  public void updateCodeValues(HTree tree){
    
    if (tree.getRoot() != null){
      
      /* Sets the tree root node code to 0. */
      tree.getRoot().setCode(0);
      
      /* Calls a recursive method for setting the code values of the nodes in the tree. */
      setCodeValues(tree.getRoot());
    }
    
  }
  
  /**
   * Recursive method for setting the code values of the tree nodes. 
   * Sets the left subtree node code to 0, the right subtree node code to 1.
   * This method is called by the updateCodeValues(tree) method to update
   * the code values of the entire tree.
   * Package protected by default.
   * 
   * @param root - the root node of the Huffman tree to update with codes
   */
  void setCodeValues(HTreeNode root){
    
    /* Does nothing if root node is null. */
    if (root == null)
      return;
    
    /* Checks if the root is null. */
    if (root != null)
    {  
      /* Checks if the left subtree is null. */
      if (root.getLeft() != null)
        
        /* Set code of left subtree node to 0. */
        root.getLeft().setCode(0);
      
      /* Explore the left subtree. */
      setCodeValues(root.getLeft());
      
      /* Checks if the right subtree is null. */
      if (root.getRight() != null)  
        
        /* Set code of right subtree node to 1. */
        root.getRight().setCode(1);
      
      /* Explore the right subtree. */
      setCodeValues(root.getRight());
      
      /* Checks if both subtrees are null. */
      if (root.isLeafNode())
        return;
    }      
  }
  
  /**
   * Reads an input text file and returns a Huffman encoded binary file. 
   * 
   * @param input_file - the name of the file to be compressed
   * @param output_file - the name of the compressed output file
   */
  public static void Huffman_coder(String input_file, String output_file){
    
    /* The String to read the file to for encoding. */
    String file;
    
    /* The root of the Huffman tree created by the input file. */
    HTreeNode root = createHuffmanTree(createHList(file = readFileAsString(input_file))).getRoot();
    
    /* Hash table of the characters and their frequencies. */
    Hashtable<String, String> codeTable = new Hashtable<String, String>();
    
    /* Call to the recursive method for attaining character-code combinations. */
    HTree.getCharacterCode(root, codeTable, "0");
    
    /* StringBuilder for the encoded String file. */
    StringBuilder builder = new StringBuilder();
    
    /* Traverse through contents of file, writing the encoding to output String. */
    for(int i = 0; i < file.length(); i++)
      builder.append(codeTable.get(String.valueOf(file.charAt(i))));
    
    /* Create a binary file from the output String of encoded characters. */
    try {
      
      /* Creates binary file in place of output_file. */
      BinaryFileWriter.createBinaryFile(builder.toString(), output_file);
      
    } catch (Exception e) {
      
      System.err.println("Problem writing to the binary file.");
      System.err.println(e.getMessage());
    }
  }
  
  /**
   * The main method of the Huffman encoding Library class.
   * Allows user to compress a given text file to binary with Huffman encoding.
   * This method takes an input file location and an output file location.
   * 
   * @param args - the input file and output file locations, separated by a space
   * @throws java.io.IOException - file may not be accessible
   * @throws java.io.FileNotFoundException - file may not exist
   */
  public static void main(String args[]){
    
    Scanner scanner = new Scanner(System.in);
    
    String fileName = scanner.next();
    String file2Name = scanner.next();
    
    scanner.close();
    
    /* Encodes the given file to binary with Huffman encoding. */
    try{
      
      Huffman_coder(fileName, file2Name);
      
    } catch (Exception e) {
      
      System.out.println("Cannot access file.");
      
    }
    
  }
  
}