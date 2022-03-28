package 백준.company.정렬2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15688 {
    // 수 정렬하기 5 / 실버 5 / 카운팅 정렬로 했는데 시초나네..
    // 아니다 카운팅 정렬맞네
    static int MXN = 2000000, HALF = MXN / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] cnt = new int[MXN + 2];
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            cnt[num + HALF]++;
        }


        StringBuilder sb = new StringBuilder();
        for (int i=0; i<=MXN; i++)
            while(cnt[i] > 0){
                sb.append(i - HALF).append('\n');
                cnt[i]--;
            }

        System.out.println(sb);
    }
}
