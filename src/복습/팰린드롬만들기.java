package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 팰린드롬만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int[] alpha = new int[26];

        for (int i = 0; i < str.length(); i++) {
            alpha[str.charAt(i) - 'A']++;
        }

        int oddCnt = 0, oddIdx = 0;
        for (int i = 0; i < alpha.length; i++) {
            if(alpha[i] % 2 != 0){
                oddCnt++;
                oddIdx = i;
            }
        }

        if((str.length() % 2 == 0) && oddCnt > 0
        || str.length() % 2 == 1 && oddCnt != 1){
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        String answer = "";
        for (int i = 0; i < alpha.length; i++) {
            for (int j = 0; j < alpha[i] / 2; j++) {
                answer += (char)(i + 'A');
            }
        }

        String rev = new StringBuilder(answer).reverse().toString();
        if(oddCnt == 1) answer += (char)(oddIdx + 'A');
        System.out.println(answer + rev);
    }
}
