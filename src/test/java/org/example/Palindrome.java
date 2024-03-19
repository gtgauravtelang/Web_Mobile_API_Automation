package org.example;

import java.util.HashSet;
import java.util.Set;

public class Palindrome {
    public boolean palindrome(int number)
    {
        int i, sum, temp;
        sum=0;
        temp=number;
        while(number>0)
        {
            i=number%10;
            sum=(sum*10)+i;
            number=number/10;
        }
        if (temp==sum) {
            return true;
        }
        else return false;
    }
    public boolean strPalindrome(String str1)
    {
        int length=str1.length();
        String tempStr="";
        length=length-1;
        for(int i=length;i>=0;i--)
        {
            tempStr=tempStr+str1.charAt(i);
        }
        System.out.println("\nOrig: "+str1);
        System.out.println("reversed: "+tempStr);
        if (str1.equals(tempStr.toString())) {
            return true;
        }
        else return false;
    }
    public static void main(String[] args)
    {
        int intnormal = 121;
        Palindrome obj = new Palindrome();
        System.out.println("\nint:    It is Palindrome? : " + obj.palindrome(intnormal));

        String str = "abtba";
        System.out.println("String: It is Palindrome?: "+obj.strPalindrome(str));

        int[] duplicateInt={1, 3, 6, 9, 3, 6, 8, 8,90,17,1, 0};
        Set<Integer> intset = new HashSet<>();
        int i=0;
        while (i<duplicateInt.length)
        {
            intset.add(duplicateInt[i]);
            i++;
        }
        System.out.println("Removed Duplicates: "+intset);
    }
}