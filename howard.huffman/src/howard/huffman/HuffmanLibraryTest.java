package howard.huffman;

import org.junit.Before;
import org.junit.Test;

/**
 * The test class for the Huffman encoding Library class.
 * This test class is vital because the Huffman Library class utilizes all the other classes in the package.
 * I used System.out.print to test these methods because it was the most efficient way to debug, so that's what
 * this class mostly consists of. I also have Huffman_coder create the binary in the output text file from the given
 * input text file.
 * 
 *** Please Note: If you want to use this JUnit for testing, you must update the inputFile and
 * outputFile Strings to the correct file paths for the input and output text files.
 * 
 * @author Shaun Howard
 *
 */
public class HuffmanLibraryTest {
  
  /** 
   * These are the file paths of the files used to test the class. You may change them to
   * work for you.
   */
  private String inputFile = "D:\\EECS233\\howard.huffman\\textFiles\\InputFile.txt";
  private String outputFile = "D:\\EECS233\\howard.huffman\\textFiles\\OutputFile.txt";
  
  /* The input file as a String. */
  private String inputAsString;
  
  /* The output file as a String. */
  private String outputAsString;
  
  /* The initial, unsorted H Linked List created from the input file String. */
  private HLinkedList hList;
  
  /* The sorted H Linked List, sorted with respect to character frequencies. */
  private HLinkedList sortedHList;
  
  /* The H Tree created from the input file. */
  private HTree tree;
  
  /**
   * Creates an H Tree from the given input file for testing.
   */
  @Before
  public void setUp(){
    
    try{
      
      /* Read input file as String. */
      inputAsString = HuffmanLibrary.readFileAsString(inputFile);
      
      /* Read output file as String. */
      outputAsString = HuffmanLibrary.readFileAsString(outputFile);
      
    } catch (Exception e) {
      
      System.out.println("File Not Found.");
      
    }
    
    System.out.println("Please note, these tests do not necessarily run in order.");
    
    /* Create the initial Huffman linked list from the input String. */
    hList = HuffmanLibrary.createHList(inputAsString);
    
    /* Create the sorted Huffman linked list from the unsorted list. */
    sortedHList = HuffmanLibrary.getSortedLinkedList(hList);
    
    /* Create the Huffman tree from the sorted linked list. */
    tree = new HTree(HuffmanLibrary.getSortedLinkedList(hList));
    
  }
  
  /**
   * Test method for readFileAsString().
   * Prints the contents of the input text file to console.
   */
  @Test
  public void testReadFileAsString() {  
    
    System.out.println("Here are the contents of the input file : ");
    System.out.print(HuffmanLibrary.readFileAsString(inputFile));
    System.out.println();
    System.out.println();
    
  }
  
  /**
   * Test method for createHList.
   * Prints the characters and their elements of the unsorted linked list to console.
   */
  @Test
  public void testCreateHList() {
    
    HTreeNode node = hList.getHeadNode();
    System.out.println("These are the contents of the initial unsorted H Linked List.");
    System.out.println("Character : Frequency");
    while (node != null){
      System.out.println(node.getElement() + " : " + node.getFrequency());
      node = node.next();
    }
    System.out.println();
  }
  
  /**
   * Test method for getSortedLinkedList().
   * Prints the characters and their frequencies from the sorted linked list to console.
   */
  @Test
  public void testGetSortedLinkedList() {
    
    HTreeNode node = sortedHList.getHeadNode();
    int sum = 0;
    System.out.println("Here are the contents of the sorted H Linked List: ");
    System.out.println("Character : Frequency");
    
    /* Gets the characters and their frequencies and prints them. */
    while (node != null){
      System.out.println(node.getElement() + " : " + node.getFrequency());
      sum = sum + node.getFrequency();
      node = node.next();
    }
    
    System.out.println();
    System.out.println("Total sum of all frequencies is : " + sum);
    
  }
  
  /**
   * Test method for createHuffmanTree.
   * Prints the root element(nothing) and the root frequency(sum of all character frequencies) to console.
   */
  @Test
  public void testCreateHuffmanTree() {
    System.out.println("The tree root should have no element : " + tree.getRoot().getElement());
    System.out.println("The tree root frequency should equal the sum of all frequencies.");
    System.out.println("The tree root frequency is : " + tree.getRoot().getFrequency());
    
  }
  
  /**
   * Test method for Huffman_coder().
   * Reads the output binary file and prints output String to console.
   */
  @Test
  public void testHuffman_coder() {
    
    /* Tests if this method works by outputFile's contents. */
    HuffmanLibrary.Huffman_coder(inputFile, outputFile);
    System.out.println("Here are the contents of the output binary file:");
    
    /* Output binary file contents to console. */
    System.out.print(outputAsString);
  }
  
}