/*This project takes in a public key (p, g, gxmodp) and a cipher (c1, c2)
and extracts the message using ElGamal decryption*/
//By Dean Connell

import java.util.*;

public class ElGamalDecryption {
    public static void main (String args[]){
        Scanner in = new Scanner(System.in);
        long p = in.nextLong();
        long g = in.nextLong();
        in.nextLine();
        long gxmodp = in.nextLong();
        long c1 = in.nextLong();
        long c2 = in.nextLong();

        long current = g;
        long power = 1;
        while(current!=gxmodp){
            power = power+1;
            current = modMult(current,g,p);
            if(current >= p){
                current = current % p;
            }
        }
        long x = power;
        long divisor = modPow(c1, p-1-x, p);
        long quotient = modMult(divisor,c2,p);
        System.out.println(quotient);
    }

    public static long modPow(long number, long power, long modulus){
        if(power==0)
            return 1;
        else if (power%2==0) {
            long halfpower = modPow(number, power/2, modulus);
            return modMult(halfpower,halfpower,modulus);
        }else{
            long halfpower = modPow(number, power/2, modulus);
            long firstbit = modMult(halfpower,halfpower,modulus);
        return modMult(firstbit,number,modulus);
    }
    }
    public static long modMult(long first, long second, long modulus){
        if(second==0)
            return 0;
        else if (second%2==0) {
            long half=modMult(first, second/2, modulus);
            return (half+half)%modulus;
        }else{
            long half=modMult(first, second/2, modulus);
            return (half+half+first)%modulus;
        }
    }
}
