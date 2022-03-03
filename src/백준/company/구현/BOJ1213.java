package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1213 {
    // 팰린드롬 만들기 / 실버 4 / 구현
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int[] alpha = new int[26];
        for (int i = 0; i < str.length(); i++) {
            alpha[str.charAt(i) - 'A']++;
        }

        // 길이가 짝수면 모든 알파벳의 개수도 짝수여야 함
        // 길이가 홀수면 하나의 알파벳 개수만 홀수여야 함

        int oddCnt = 0, oddIdx = 0;

        for (int i = 0; i < alpha.length; i++) {
            if(alpha[i] % 2 != 0){
                oddCnt++; // 홀수번 등장하는 문자 개수 세는 변수
                oddIdx = i; //
            }
        }

        // 문자가 짝수길이인데, 홀수번 등장하는 문자가 있다면 팰린드롬 X
        // 문자가 홀수길이인데, 홀수번 등장하는 문자가 1개가 아니라면 팰린드롬 X
        if((str.length() % 2 == 0 && oddCnt > 0) ||
                (str.length() % 2 == 1 && oddCnt != 1)) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        // 3가지의 경우로 나눔
        // 1. i=0 ~ alpha.length 까지 alpha[i] / 2만큼
        // 2. 전체길이가 홀수일 경우 홍수 개인 알파벳
        // 3. i=alpha.length ~ 0까지 alpha[i] / 2 만큼
        String answer = "";
        for (int i = 0; i < alpha.length; i++) {
            for (int j = 0; j < alpha[i] / 2; j++) {
                answer += ((char)(i + 'A'));
            }
        }

        String rev = new StringBuilder(answer).reverse().toString();
        if(oddCnt == 1) answer += ((char)(oddIdx + 'A'));
        System.out.println(answer + rev);
    }
}
