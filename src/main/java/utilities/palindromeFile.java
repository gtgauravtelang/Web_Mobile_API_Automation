package utilities;

public class palindromeFile {
    public static void main(String[] args) {
        String word = "madam";
        if (isPalindrome(word)) {
            System.out.println(word + " is a palindrome");
        } else {
            System.out.println(word + " is not a palindrome");
        }

        if (isIntegerPalindrome(1221)) {
            System.out.println("Number is a palindrome");
        } else {
            System.out.println("Number is not a palindrome");
        }
    }

    public static boolean isPalindrome(String word) {
        return word.equals(new StringBuilder(word).reverse().toString());
    }

    public static boolean isIntegerPalindrome(int number)
    {
        int finalNumber=0;
        int temp = number;
        int i, remainder, leng;
        i=remainder=0;
        leng = String.valueOf(number).length();
        while(i<leng) {
            remainder=temp%10;
            finalNumber=(finalNumber * 10) + remainder;
            temp=temp/10;
            i++;
        }
        System.out.println("finalNumber: " + finalNumber);
        if(number == finalNumber)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}