package howard.huffman;

import java.lang.String;

/**
 * A class for the Huffman encoding tree singly linked list.
 * This is included in the Huffman encoding project.
 * 
 * @author Shaun Howard
 */
public class HLinkedList{
  
  /** The head of the HLinkedList. */
  private HTreeNode head;
  
  /** The length of the HLinkedList. */
  private int length = 0;
  
  /**
   * Constructor for the Huffman Linked List class.
   * Creates an empty linked list.
   */
  public HLinkedList(){
    this.head = null;
    this.length = 0;
  }
  
  /**
   * Constructor for the Huffman Linked List class.
   * Creates a list with a head and length 1.
   * 
   * @param head - the node as the head of the linked list
   */
  public HLinkedList(HTreeNode head){
    this.head = head;
    this.length = 1;
  }
  
  /** 
   * Constructor for the Huffman Linked List class.
   * Specifically useful for creating the initial, unsorted linked list
   * of characters and their frequencies from the read String. 
   * 
   * @param fileContents - the String to read characters from
   */
  public HLinkedList(String fileContents){
    
    /* Inserts characters into the HLinkedList one by one. */
    for (int i = 0; i < fileContents.length(); i++){
      
      /* The node containing the current character, if it exists. */
      HTreeNode contentNode = find(fileContents.charAt(i));
      
      /* If the character is not in the list, add it. */
      if (contentNode == null){
        insertAtEnd(new HTreeNode(fileContents.charAt(i), null));
      } else { //else increment its frequency
        contentNode.incrementFrequency();
      }
    }
    
  }
  
  /**
   * Gets the head of the list.
   * 
   * @return  the head node of the linked list
   */
  public HTreeNode getHeadNode(){
    return this.head;
  }
  
  /**
   * Inserts the node into the given position of the list.
   * Precondition - position is the index, starting at 0,
   * where the node should be inserted. 
   * Postcondition - the nodes from position on are moved forward one
   * place from insertion of node.
   * 
   * @param node - the node to insert into the linked list
   * @param position - where to insert the node in the list
   */
  public void insertIntoPosition(HTreeNode node, int position){
    
    /* Checks if node is null. */
    if (node == null)
      return;
    
    /* Creates a new node from node; designates a null next() link. */
    HTreeNode newNode = new HTreeNode(node);
    
    /* The iterator node for the insertion. */
    HTreeNode iterator = getHeadNode();
    
    /* Checks if position is at front of list. */
    if (position == 0 || iterator == null){
      
      /* Set new node as head. */
      newNode.setNext(iterator);
      setHead(newNode);
      
      /* Increment length of linked list. */
      this.length++;
    }
    
    /* Checks if position is greater than list length. */
    if (position >= length()) {
      
      /* Inserts new node at end of list. */
      insertAtEnd(newNode);
      return;
    }
    
    /* Inserts new node if position is greater than 0 and iterator is not null. */
    if (position > 0 && iterator != null) {
      
      /* Finds the node after position in the linked list. */
      for (int i = 0; i < position + 2; i++) {
        
        /* Handles case if iterator is null after position. */
        if (iterator.next() == null) {
          insertAtEnd(newNode);
          return;
        }
        
        /* Continues traversing if iterator is not null. */
        iterator = iterator.next();
      }
      
      /* Call to method to insert node at position. */
      insertBefore(iterator, node);
      
    }
    
  }
  
  /**
   * Inserts nodeBefore before nodeAfter in the list.
   * Specifically useful when inserting nodes into sorted list.  
   * 
   * @param nodeAfter - the node to insert before in the list
   * @param nodeBefore - the node to insert before nodeAfter in the list
   */
  public void insertBefore(HTreeNode nodeAfter, HTreeNode nodeBefore){
    
    /* Check if any of the nodes are null. */
    if (nodeAfter == null || nodeBefore == null || getHeadNode() == null)
      return;
    
    /* Checks if nodeAfter is head, if so, nodeBefore is inserted as new head. */
    if (getHeadNode().equals(nodeAfter)){
      
      /* Set nodeBefore as head. */
      nodeBefore.setNext(nodeAfter);
      setHead(nodeBefore);
      
      /* Increment length of linked list. */
      this.length++;
      return;
    }
    
    /* Node for iteration. */
    HTreeNode node;
    
    /* Finds node in list before nodeAfter, then inserts new node before nodeAfter in list. */
    for (node = getHeadNode(); node.next() != null; node = node.next()) {
      
      /* Checks if next node is nodeAfter, if so, inserts nodeBefore before nodeAfter in list. */
      if(node.next().equals(nodeAfter)){
        
        /* Insert nodeBefore before nodeAfter. */
        nodeBefore.setNext(node.next());
        node.setNext(nodeBefore);
        
        /* Increment length of linked list. */
        this.length++;
        return;
      }
      
    }
    
  }
  
  /**
   * Inserts the node to the end of the list.
   * Specifically good for creating an H Linked List initially. 
   * 
   * @param node - node to insert at end of list
   */
  public void insertAtEnd(HTreeNode node){
    
    /* Node for iteration. */
    HTreeNode iterator = getHeadNode();
    
    /* Check if iterator is null, then find last node in list. */
    if(iterator != null) {
      
      /* Find last node in linked list. */
      while(iterator.next() != null) {
        iterator = iterator.next(); 
      }
      
      /* Insert node at the end of the list. */
      iterator.setNext(new HTreeNode(node));
      
    } else { //otherwise set node as head of list
      
      setHead(new HTreeNode(node));
    }
    
    /* Increment length of linked list. */
    this.length++;
  }
  
  /**
   * Finds the specified character in the linked list.
   * Precondition - linked list must not be merged to
   * Huffman tree yet.
   * 
   * @param value - character to find in the list
   * @return  the node containing the sought character
   */
  public HTreeNode find(char value){
    
    /* The iterator node for the search. */
    HTreeNode iterator;
    
    /* Searches the linked list for the desired value. */   
    for(iterator = getHeadNode(); iterator != null; iterator = iterator.next()){
      if (iterator.getElement() == value)
        return iterator;
    }
    
    return null;
  }
  
  /**
   * Removes the head node of the HLinkedList.
   * Method is package protected by default.
   */
  void removeHead(){
    
    this.setHead(getHeadNode().next());
    this.length--;
    
  }
  
  /**
   * Sets the head node of the HLinkedList.
   * 
   * @param node - the node to set as head of the list
   */
  public void setHead(HTreeNode node){
    this.head = node;
  }
  
  /**
   * Gets the length of the HLinkedList. 
   * 
   * @return - the length of the HLinkedList
   */
  public int length(){
    return length;
  }
  
  /**
   * Outputs a comma separated sequence of the HLinkedList characters.
   * 
   * @return  the characters of the HLinkedList as a String
   */
  public String toString(){
    
    /* The iterator node for list traversal. */
    HTreeNode iterator;
    
    /* The string to add characters to. */
    String string = "";
    
    /* Adds the characters from the list with commas between to a string. */
    for (iterator = getHeadNode(); iterator != null; iterator = iterator.next()) {
      
      /* Checks if at head node, if so does not print comma. */
      if (iterator.equals(getHeadNode()))
        string = string + iterator.getElement();
      else { //otherwise print comma and space
        string = string + ", " + iterator.getElement();
      }
      
    }
    
    return string;
  }
  
}