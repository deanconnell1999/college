/*This project takes in a number of words and arranges them into alphabetical order.
If the number of words is odd, it prints out the word in the middle of the order.
If the number of words is even, it prints out "-1"*/
//By Dean Connell

import java.util.Scanner;

public class MiddleWord {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        in.nextLine();
        String[] words = new String[size];

        for (int i = 0; i < size; i++) {
            String word = in.nextLine();
            word = word.toLowerCase();
            words[i] = word;
        }

        for (int a = size - 1; a > 0; a--) {
            for (int b = 0; b < a; b++) {
                if ((words[b].compareTo(words[b + 1])) > 0) {
                    String temp = words[b];
                    words[b] = words[b + 1];
                    words[b + 1] = temp;
                }
            }
        }
        if(size%2!=0) {
            int median = (size / 2);
            System.out.println(words[median]);
        }else{
            System.out.println(-1);
        }
    }
}
