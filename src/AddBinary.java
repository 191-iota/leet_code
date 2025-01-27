import java.math.BigInteger;

public class AddBinary {

    public static void main(String[] args) {
        
    }
    
    
    // attempt 1 - works but it is slow 
    public static String addBinary(String a, String b) {
        BigInteger bigA = new BigInteger(a, 2);
        BigInteger bigB = new BigInteger(b, 2);

        BigInteger sum = bigA.add(bigB);

        return sum.toString(2);
    }
}