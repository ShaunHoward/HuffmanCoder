package howard.huffman;

/**
 * A class for the Huffman encoding tree node.
 * This is included in the Huffman encoding project.
 * This class implements the Comparable interface, which allows
 * HTreeNodes to be compared to themselves in regards to frequency, namely. 
 * Such is useful for sorting throughout the Huffman encoding process.
 * 
 * @author Shaun Howard
 * @implements Comparable<HTreeNode> - these nodes are comparable to each other by frequency
 */
public class HTreeNode implements Comparable<HTreeNode>{
  
  /** The character signified by the leaf node. */
  private char character;
  
  /** The frequency of character occurrences in the text. */
  private int frequency;
  
  /** The next node of the HLinkedList. */
  private HTreeNode next;
  
  /** The element of the left subtree leaf node. */
  private HTreeNode left;
  
  /** The element of the right subtree leaf node. */
  private HTreeNode right;
  
  /** The code of the node in the Huffman Tree. */
  private int code;
  
  /**
   * A constructor for the Huffman Tree Node class. 
   * Specifically useful for creating the first, unsorted linked list
   * of characters and frequencies.
   * 
   * @param character - the character signified by the leaf node
   * @param next - the next node in the Huffman Linked List
   */
  public HTreeNode (char character, HTreeNode next){
    this.character = character;
    this.next = next;
    this.frequency = 1;
  }
  
  /**
   * Additional constructor that takes a node and creates a new node from that node's data.
   * Specifically useful for inserting merged nodes back into the linked list.
   * Postcondition - next() link is null, data from node is the copied to this new node.
   * 
   * @param node - the node to duplicate
   */
  public HTreeNode (HTreeNode node) {
    this.character = node.getElement();
    this.frequency = node.getFrequency();
    this.next = null;
    this.left = node.getLeft();
    this.right = node.getRight();
  }
  
  /**
   * Additional constructor for the Huffman Tree Node class. 
   * Specifically useful for creating a new root node when merging nodes.
   * Postcondition - next() link is null, frequency is that of left and right nodes
   * combined.
   * 
   * @param left - the left node of the H Tree
   * @param right = the right node of the H Tree
   */
  public HTreeNode(HTreeNode left, HTreeNode right){
    this.left = left;
    this.right = right;
    this.frequency = left.getFrequency() + right.getFrequency();
    this.next = null;
  }
  
  /** 
   * Link to the next node of the linked list. 
   * 
   * @return  the node following this node in the linked list
   */
  public HTreeNode next(){
    return next;
  }
  
  /**
   * Sets the code of the Huffman tree node.
   * Package protected by default.
   * 
   * @param code - the value to set the code of the node
   */
  void setCode(int intCode){
    this.code = intCode;
  }
  
  /**
   * Changes the node that comes after this node in the list.
   * 
   * @param next - the node that should come after this node in the list; it can be null.
   */
  public void setNext(HTreeNode next) {
    this.next = next;
  }
  
  /**
   * Gets the frequency of the character in the leaf node. 
   * 
   * @return  the frequency of this node's character in the list
   */
  public int getFrequency(){
    return this.frequency;
  }
  
  /**
   * Sets the frequency of this node's character.
   * Package protected by default.
   * 
   * @param freq - the frequency to set for this node
   */
  void setFrequency(int freq){
    this.frequency = freq;
  }
  
  /**
   * Increments the frequency of this character.
   * Package protected by default.
   */
  void incrementFrequency(){
    this.frequency++;
  }
  
  /**
   * Sets the left child of this node in the HTree.
   * Package protected by default.
   * 
   * @param left - the node to set as this node's left child
   */
  void setLeft(HTreeNode left){
    this.left = left;
  }
  
  /**
   * The left child of this node in the HTree.
   * 
   * @return  the left child of this node in the HTree
   */
  public HTreeNode getLeft(){
    return left;
  }
  
  /**
   * Sets the right child of this node in the HTree.
   * Package protected by default.
   * 
   * @param right - the node to set as this node's right child
   */
  void setRight(HTreeNode right){
    this.right = right;
  }
  
  /** 
   * The right child of this node in the HTree.
   * 
   * @return the right child of this node in the HTree
   */
  public HTreeNode getRight(){
    return right;
  }
  
  /**
   * Gets the element of the node. 
   * 
   * @return  the character stored in this leaf node
   */
  public char getElement(){
    return character;
  }
  
  /**
   * Determines if this node is a leaf node. 
   * 
   * @return  whether or not this node is a leaf node
   */
  public boolean isLeafNode(){
    
    return (this.left == null && this.right == null);
    
  }
  
  /**
   * Compares the frequencies of this node and the specified node.
   * Returns -1 if this node's frequency is less than that of the specified node.
   * Returns 0 if the node frequencies are the same.
   * Returns 1 if this node's frequency is greater than that of the specified node.
   * 
   * @param node - the node to compare this node with
   * @return  whether the frequency of the specified node is less than, greater than, or equal to this node
   * @throws NullPointerException - if either or the nodes are null in comparison
   * @Override - overrides the compareTo() method of the Comparable interface
   */
  @Override
  public int compareTo(HTreeNode node) throws NullPointerException {
    
    if (this.getFrequency() < node.getFrequency())
      return -1;
    else if(this.getFrequency() > node.getFrequency())
      return 1;
    else
      return 0;
  }
  
  /**
   * Determines whether this node is equivalent to the specified node.
   * Calls the compareTo() method for consistency.
   * If characters and frequencies are the same, the nodes are the same.
   * 
   * @param node - the node to compare with this node
   * @return  whether or not the two nodes have the same element and frequency
   */
  public boolean equals(HTreeNode node){
    
    return (this.getElement() == node.getElement() && this.compareTo(node) == 0);
    
  }
  
}