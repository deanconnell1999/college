/*This project uses the recursive quicksort algorithm to sort a given list of words
in order of greatest character in each word (the word art has a greatest character of 't' 
because 't' is the letter furthest along the alphabet in that word), and then words that share
their greatest character are sorted in alphabetical order. The resulting order is printed*/
//By Dean Connell

import java.util.Scanner;

public class QuicksortWords {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt(); //take in length
        in.nextLine();
        String[] words = new String[length];
        for(int a=0;a<length;a++) {
            words[a] = in.nextLine(); //filling words array
        }
        recQuickSort(words, 0, length-1);
        for(int b=0;b<length;b++){
            System.out.println(words[b]);
        }
    }

    public static void recQuickSort(String[] words, int left, int right) {
        if (right - left <= 0) { // if size <= 1,
            return; // already sorted
        } else { // size is 2 or larger
            String pivot = words[right]; // rightmost item
            // partition range
            int partition = partitionIt(words, left, right, pivot);
            recQuickSort(words, left, partition - 1); // sort left side
            recQuickSort(words, partition + 1, right); // sort right side
        }
    }

    public static int partitionIt(String[] words, int left, int right, String pivot) {
        int leftPtr = left - 1; // left (after ++)
        int rightPtr = right; // right-1 (after --)
        while (true) {
            while (comparison(words[++leftPtr], pivot) == true) { // this may be the problem if it doesn't go right)
            } // scan to the right
            while (rightPtr > 0 && comparison(words[--rightPtr], pivot) == false) {
            } // scan to the left
            if (leftPtr >= rightPtr) { // if pointers cross,
                break; // partition done
            } else { // not crossed, so
                swap(words, leftPtr, rightPtr); // swap elements
            }
        }
        swap(words, leftPtr, right); // swap pivot into correct place
        return leftPtr; // return pivot location
    }

    public static void swap(String[] words, int one, int two) { //trivial
        String temp = words[one];
        words[one] = words[two];
        words[two] = temp;
    }

    public static boolean comparison(String one, String two) {
        int onemax = 0;
        int twomax = 0;
        for (int i = 0; i < one.length(); i++) {
            if ((int) one.charAt(i) > onemax) {
                onemax = (int) one.charAt(i); //finding largest char
            }
        }
        for (int i = 0; i < two.length(); i++) {
            if ((int) two.charAt(i) > twomax) {
                twomax = (int) two.charAt(i); //finding largest char
            }
        }
        if (onemax < twomax) { //if largest of 1 is less than 2
            return true; //needs to be swapped
        } else if (onemax > twomax) { //if largest of 2 is less than 1
            return false; //do not swap
        }
        if (one.compareTo(two) < 0) { //if 1 comes before 2 in dictionary
            return true; //needs to be swapped
        } else {
            return false; //do not swap
        }
    }
}