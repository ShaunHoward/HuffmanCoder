HuffmanCoder
============

A huffman encoder project designed to encode text files with the huffman coding algorithm.

READ-ME for the Huffman Encoding Project.
 
@author Shaun Howard
 
To begin, my text files, including the calculated space savings, Huffman encoding character codes,
the text file I used to test my program, and the output binary text file (compressed) are in the 
"textFiles" folder of my zipped file.

My source codes, including test class, are in the "src" folder of the zipped file.
 
I have included a JUnit test class for the HuffmanLibrary class, since this class calls to all the other classes
of the Huffman Encoding Project. This test class mostly prints contents at various steps in making the H Tree
to the console or prompt at which you call it from. You can open and run this by importing my package into
Eclipse as an existing project, for that's where I coded it. Then run "HuffmanLibraryTest.java" as JUnit test. 
***Note: You will have to change the input and output file paths in the JUnit test class before it runs successfully.
The fields for these file paths/names are noted in the JUnit test class.
 
Some things it will print to console are:
-the contents of the input text file
-the unsorted linked list created from the text file, including characters and their frequencies
-the sorted linked list created from the unsorted linked list, including characters, their frequencies, and total frequency
-the tree root node properties, i.e. total frequency of all nodes, for comparison with sum from sorted linked list
-each character read from the input text file and their Huffman codes
-the contents of the binary file from output of encoding
 
I have included a runnable .jar file of my code called "Huffman.jar" in the directory of my zipped file.
To run this .jar, navigate to the directory of the .jar in an elevated command prompt. Then type "java -jar Huffman.jar" and
hit enter. Subsequently, type the path of a text file to read from, including file name,
i.e. (C:\EECS233\Files\inputFile.txt) and hit enter. Then type the path of a blank text file to output to, 
i.e. (C:\EECS233\Files\outputFile.txt) and hit enter. These two paths may be just the file name i.e. (input.txt)
if the files are in the same directory as "Huffman.jar".
 
If the given instructions are followed, a binary-encoded file should be output to the output text file, which can be read
if opened in a text editor.
 
The logic behind my code is as follows:
1. Read text file to String.
2. Read String and input characters and their frequencies to unsorted linked list.
3. Sort linked list by increasing character frequency.
4. Merge two first nodes from sorted linked list until single node as root of tree is reached.
   - Assign code values upon merge (0 for node of left subtree, 1 for node of right subtree, 0 for tree root node)
5. Search through Huffman tree and find each character's code value through recursion. 
   - Add these characters and their codes to a hash table for quick search.
   - The character is used as the key, while the code is used as the value.
6. Read through the input text file by character and find that character in the hash table.
   - Append the character codes to a StringBuilder.
7. Input final builder after entire input text file is read as String into the BinaryFileWriter class.
8. Return output file with binary "zipped" encoding. 
 
That's pretty much all I have to say for this project.
 
I hope you enjoyed browsing my text files and code!
 
This project was very fun, I enjoyed the thought-provoking challenge.
 
Also, I hope your break went well!
 
Until next time... READ_ME.close();
