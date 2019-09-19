/*This project prompts the user to enter a sentence and then
prints out the ASCII encoding for each letter and the frequency of
each letter*/
//By Dean Connell


import java.util.Scanner;
public class EncodingAndFrequency {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your sentence:");
        String sentence = in.nextLine();
        System.out.println(" ");
        int length = sentence.length();
        char[] freq = new char[length];
        String[] ASCII = new String[length];
        for (int a = 0; a < length; a++) {
            freq[a] = sentence.charAt(a);
            int num = (int) sentence.charAt(a);
            String bistr = Integer.toBinaryString(num);
            while (bistr.length() != 7) {
                bistr = "0" + bistr;
            }
            ASCII[a] = bistr;
            System.out.println(ASCII[a]);
        }
        System.out.println(" ");
        for (int i = 0; i < length; i++) {
            int count = 1;
            String times = "time";
            for (int j = i + 1; j < length; j++) {
                if (freq[i] == freq[j]) {
                    count++;
                    for (int k = j; k < length - 1; k++) { // move values down
                        freq[k] = freq[k + 1];
                    }
                    length = length-1;
                }
            }
            if (count > 1) {
                times = "times";
            }
            System.out.println("'" + freq[i] + "'" + " appeared " + count + " " + times);
        }
    }
}