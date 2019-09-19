/*This project is used to generate a Vertcoin wallet private and public key
The first line printed is the Elliptic Curve Digital Signature Algorithm (ECDSA).
The second line printed is the hexadecimal private key.
The third line printed is the decimal form of the private key.
The final line printed is the public key.
This project uses SHA-256 double hashing to create the private key*/
//By Dean Connell


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.util.Objects;

public class VertcoinKeys {

    public static void main(String[] args) {
        String hash1 = "";
        String hash2 = "";
        String[] rand = new String[64];
        String ECDSA = "";
        String base58 = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
        String sequence = "";
        for (int a = 0; a < 64; a++) {
            String temp = Integer.toString((int) (Math.random() * 16));
            if (temp.equals("10")) {
                rand[a] = "a";
            } else if (temp.equals("11")) {
                rand[a] = "b";
            } else if (temp.equals("12")) {
                rand[a] = "c";
            } else if (temp.equals("13")) {
                rand[a] = "d";
            } else if (temp.equals("14")) {
                rand[a] = "e";
            } else if (temp.equals("15")) {
                rand[a] = "f";
            } else {
                rand[a] = temp;
            }
            ECDSA = ECDSA + rand[a];
        }
        System.out.println(ECDSA);
        String eightystr = "80"+ECDSA;
        String secret = eightystr;
        try {
            hash1 = sha256(secret);
        } catch (NoSuchAlgorithmException e) {
        }
        try {
            hash2 = sha256(hash1);
        } catch (NoSuchAlgorithmException e){
        }
        String eightyend = hash2.substring(0,8);
        String hexprivate = eightystr + eightyend;
        System.out.println(hexprivate);
        String test = hexprivate;
        BigInteger b10 = new BigInteger(test,16);
        System.out.println(b10);
        while(!b10.equals(BigInteger.valueOf(0))) {
            BigInteger num = (b10.mod(BigInteger.valueOf(58)));
            BigInteger top = b10.subtract(num);
            b10 = top.divide(BigInteger.valueOf(58));
            sequence = sequence + base58.charAt(num.intValue() % 58);
        }
        char[] reverse = sequence.toCharArray();
        for (int b = sequence.length()-1; b >= 0; b--) {
            System.out.print(reverse[b]);
        }
    }
    public static String sha256(String input) throws NoSuchAlgorithmException {
        byte[] in = hexStringToByteArray(input);
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = mDigest.digest(in);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}