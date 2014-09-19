package howard.huffman;

import java.util.Hashtable;

/**
 * The class for the Huffman encoding tree.
 * This is included in the Huffman encoding project.
 * A Huffman encoding tree is created from a sorted linked list of characters and frequencies.
 * Codes are assigned to specific branches, 0 for left, 1 for right.
 * Characters are assigned specific codes through their location in the tree.
 * The characters and codes are then put into a hash table for quick search upon encoding.
 * 
 * @author Shaun Howard
 */
public class HTree {
  
  /** The root node of the Huffman encoding tree. */
  private HTreeNode root;
  
  /**
   * The constructor for the Huffman tree class.
   * A Huffman encoding tree is created from a sorted linked list
   * of characters and frequencies, as read from a text file.
   * Precondition - the input linked list must be sorted in increasing
   * order with respect to character frequencies.
   * 
   * @param list - the list to create the Huffman tree from
   */
  public HTree(HLinkedList list){
    mergeNodes(list);
    this.root = list.getHeadNode();
  }
  
  /**
   * Gets the root node of the Huffman tree. 
   * 
   * @return  the root node of the Huffman tree
   */
  public HTreeNode getRoot(){
    return this.root;
  }
  
  /**
   * Gets the characters and their codes through recursion. 
   * Method is called by getHashtable() to return a hash table of the tree
   * characters and their codes as Strings.
   * 
   * @param root - the root of the H Tree we are searching
   * @param codes - the hash table of this tree's characters and codes
   * @param code - the code of the node we are at
   */
  public static void getCharacterCode(HTreeNode root, Hashtable<String, String> codes, String code){
    
    /* Checks if the root is null. */
    if (root != null)
    {  
      /* Checks if the left subtree is null. */
      if (root.getLeft() != null)
        
        /* Explore the left subtree. */
        getCharacterCode(root.getLeft(), codes, code + "0");
      
      /* Checks if the right subtree is null. */
      if (root.getRight() != null)  
        
        /* Explore the right subtree. */
        getCharacterCode(root.getRight(), codes, code + "1");
      
      /* Checks if both subtrees are null. */
      if (root.isLeafNode()){
        
        /* Prints out the root character and its code as a reference in testing. */
        System.out.println(root.getElement() + ": " + code);
        
        /* Adds the character and its code to the hash table. */
        codes.put(String.valueOf(root.getElement()), code);          
      }      
    }      
  }
  
  /**
   * Merges the nodes of the sorted Huffman Linked List until top is reached.
   * Precondition - the input linked list must be sorted first with getSortedLinkedList()
   * Postcondition - the linked list will have only a root node, and next() link
   * is null for all nodes within tree.
   *
   * @param list - the list to sort into a Huffman Tree
   */
  private void mergeNodes(HLinkedList list){
    
    /* Runs through the nodes and merges them. */
    while(list.getHeadNode().next().next() != null){
      
      /* The node of the left subtree. */
      HTreeNode left = new HTreeNode(list.getHeadNode());
      
      /* The node of the right subtree. */
      HTreeNode right = new HTreeNode(list.getHeadNode().next());
      
      /* Remove the two first nodes from the list. */
      list.removeHead();
      list.removeHead();
      
      /* The new root node with nodes of the left and right subtrees. */
      HTreeNode newRoot = new HTreeNode(left, right);
      
      /* Assign code values. */
      newRoot.getLeft().setCode(0);
      newRoot.getRight().setCode(1);
      
      /* Reference to the list head for iteration. */
      HTreeNode iterator;
      
      /* Tracks insertion of new root node into list. */
      boolean insert = false;
      
      /* Inserts the node into list in ascending order of frequency. */
      for(iterator = list.getHeadNode(); iterator != null && insert == false; iterator = iterator.next()){
        
        /* Compares the iterator with the new root, inserts new root before iterator node. */
        if (iterator.compareTo(newRoot) > 0) {
          list.insertBefore(iterator, newRoot);
          insert = true;
        }
        /* Otherwise inserts at end. */
        else if (iterator.next() == null && insert == false) {
          list.insertAtEnd(newRoot);
          insert = true;
        }
      }
      
    }
    
    /* The node of the left subtree. */
    HTreeNode left = new HTreeNode(list.getHeadNode());
    
    /* The node of the right subtree. */
    HTreeNode right = new HTreeNode(list.getHeadNode().next());
    
    /* Remove the head node from the list to attain Huffman Tree root node. */
    list.removeHead();
    
    /* The Huffman Tree root node with nodes of left and right subtrees. */
    HTreeNode newRoot = new HTreeNode(left, right);
    
    /* Assign code values. */
    newRoot.getLeft().setCode(0);
    newRoot.getRight().setCode(1);
    
    /* Set the root node code to 0. */
    newRoot.setCode(0);
    
    /* Set head node of list to the root of the H Tree. */
    list.setHead(newRoot);
    
  }
  
}