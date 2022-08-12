package ro.fasttrackit.curs13;

public class ReverseString {
    //TAIL CALL OPTIMIZATION
    public String reverse(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        } else {
            return reverse(str.substring(1)) + str.charAt(0);
        }
    }
}
