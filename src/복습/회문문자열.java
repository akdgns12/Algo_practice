package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class 회문문자열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String answer = "NO";
        String str = br.readLine();

        str = str.toUpperCase();
        String tmp = new StringBuilder(str).reverse().toString();

        if(str.equals(tmp)) answer = "YES";

        System.out.println(answer);
    }
}
