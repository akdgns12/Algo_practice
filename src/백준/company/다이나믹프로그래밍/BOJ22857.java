package 백준.company.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ22857 {
    // 가장 긴 짝수 연속한 부분 수열(small) / 실버 3 / 투 포인터
    // 수열 S에서 최대 K번 원소를 삭제한 수열에서 짝수로 이루어져 있는 연속한 부분 수열 중 가장 긴 길이
    static int n, k;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        int cnt = 0; // 홀수 제거 횟수
        int lt = 0;
        for (int rt = 0; rt < n; rt++) {
            if(arr[rt] % 2 == 1) cnt++; // 홀수 카운트
            while(cnt > k){
                if(arr[lt] % 2 == 1) cnt--;
                lt++;
            }
            answer = Math.max(answer, (rt - lt + 1) - cnt);
        }

        System.out.println(answer);
    }
}
