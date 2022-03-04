package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 유효한팰린드롬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        String answer = "NO";

        str = str.toUpperCase().replaceAll("[^A-Z]", "");
        String tmp  = new StringBuilder(str).reverse().toString();

        if(str.equals(tmp)) answer = "YES";

        System.out.println(answer);
    }
}
