package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class 중복문자제거 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String answer = "";

        String str = sc.next();

        for (int i = 0; i < str.length(); i++) {
            if(str.indexOf(str.charAt(i)) == i) answer += str.charAt(i);
        }

        System.out.println(answer);
    }
}
