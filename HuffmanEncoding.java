/*This project generates a Huffman Encoding of a sentence using a Huffman Binary Tree.
The code then prints out the sentence after the Huffman Encoding*/
//By Dean Connell

import java.util.*;

public class HuffmanEncoding{
    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        String sentence = in.nextLine();

        int[] array = new int[256]; //an array to store all the frequencies

        for (int i = 0; i < sentence.length(); i++) { //go through the sentence
            array[(int) sentence.charAt(i)]++; //increment the appropriate frequencies
        }

        PriorityQueue<Tree> PQ = new PriorityQueue<>(); //make a priority queue to hold the forest of trees

        for (int i = 0; i < array.length; i++) { //go through frequency array
            if (array[i] > 0) { //print out non-zero frequencies - cast to a char


                //FILL THIS IN:

                //MAKE THE FOREST OF TREES AND ADD THEM TO THE PQ
                //create a new Tree
                Tree tree = new Tree();
                Node treeroot = new Node();
                tree.root = treeroot;
                //set the cumulative frequency of that Tree
                tree.frequency = array[i];
                //insert the letter as the root node
                tree.root.letter = (char)i;
                tree.root.smallestLetter = (char)i;
                //add the Tree into the PQ
                PQ.add(tree);
            }
        }

        while (PQ.size() > 1) { //while there are two or more Trees left in the forest

            //FILL THIS IN:
            //IMPLEMENT THE HUFFMAN ALGORITHM
            Tree tree1 = PQ.poll();
            Tree tree2 = PQ.poll();
            Tree combtree = new Tree();
            Node combtreeroot = new Node();
            combtree.root = combtreeroot;
            combtree.root.letter = '@';
            combtree.root.leftChild = tree1.root;
            combtree.root.rightChild = tree2.root;
            if (tree1.root.smallestLetter > tree2.root.smallestLetter){
                combtree.root.smallestLetter = tree2.root.smallestLetter;
            }else{
                combtree.root.smallestLetter = tree1.root.smallestLetter;
            }

            combtree.frequency = tree1.frequency + tree2.frequency;
            PQ.add(combtree);
            //when you're making the new combined tree, don't forget to assign a default root node (or else you'll get a null pointer exception)
            //if you like, to check if everything is working so far, try printing out the letter of the roots of the two trees you're combining
            //remember to check the smallest letter to decide which branch to put on the left, and which on the right


        }

        Tree HuffmanTree = PQ.poll(); //now there's only one tree left - get its codes


        //FILL THIS IN:
        for (int a = 0; a < sentence.length(); a++) {
            System.out.print(HuffmanTree.getCode(sentence.charAt(a)));
            //get all the codes for the letters and print them out
            //call the getCode() method on the HuffmanTree Tree object for each letter in the sentence
        }
    }

    static class Node {


        public char letter = '@'; //stores letter
        public char smallestLetter = '@';  //a nice idea it to track the smallest letter in the tree in a special variable like this

        public Node leftChild; // this node's left child
        public Node rightChild; // this node's right child


    } // end class Node


    static class Tree implements Comparable<Tree> {
        public Node root; // first node of tree
        public int frequency = 0;

        public Tree() // constructor
        {
            root = null;
        } // no nodes in tree yet

        //the PriorityQueue needs to be able to somehow rank the objects in it
        //thus, the objects in the PriorityQueue must implement an interface called Comparable
        //the interface requires you to write a compareTo() method so here it is:

        public int compareTo(Tree object) {
            if (frequency - object.frequency > 0) { //compare the cumulative frequencies of the tree
                return 1;
            } else if (frequency - object.frequency < 0) {
                return -1; //return 1 or -1 depending on whether these frequencies are bigger or smaller
            } else {
                // Sort based on letters
                char a = this.root.smallestLetter;
                char b = object.root.smallestLetter;

                if (a > b) {
                    return 1;
                } else if (a < b) {
                    return -1;
                }
                return 0;
            }
        }

        String path = "error"; //this variable will track the path to the letter we're looking for

        public String getCode(char letter) { //we want the code for this letter

            return this._getCode(letter, this.root, ""); //return the path that results
        }

        public String _getCode(char letter, Node current, String path) {
            if (current == null) {
                return null;
            }
            if (current.letter == letter) {
                return path;
            }

            String leftPath = this._getCode(letter, current.leftChild, path + "0");
            if (leftPath != null) {
                return leftPath;
            }

            String rightPath = this._getCode(letter, current.rightChild, path + "1");
            return rightPath;
        }

    } // end class Tree
}
