package 코테대비.문자열;

import java.util.HashSet;
import java.util.Scanner;

public class 중복문자제거 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String answer = "";

        String str = sc.next();

        for(int i=0; i<str.length(); i++){
//            System.out.println(str.charAt(i) + " " + i + " " + str.indexOf(str.charAt(i)));
            if(str.indexOf(str.charAt(i)) == i) answer += str.charAt(i);
        }

        System.out.println(answer);
    }
}
