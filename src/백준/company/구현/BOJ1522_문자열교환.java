package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1522_문자열교환 { // 문제 설명이;;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        // a를 연속적으로, 연속적인 a의 길이 = a의 개수
        // ans : idx ~ a길이 만큼 중 b의 개수가 최소인 것
        int ans = Integer.MAX_VALUE;
        int aCnt = 0;
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) == 'a') aCnt++;
        }

        for(int i=0; i<str.length(); i++){ // 문자열 이어져있다 했으니 끝까지 가능
            int bCnt = 0;
            for(int j=i; j<aCnt + i; j++){
                // 범위넘어가면 안되니 전체 문자열 길이로 나눠주자
                if(str.charAt(j % str.length()) == 'b') bCnt++;
            }
            ans = Math.min(ans, bCnt);
        }

        System.out.println(ans);
    }
}
