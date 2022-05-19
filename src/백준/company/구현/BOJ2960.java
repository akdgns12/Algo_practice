package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2960 {
    // 에라토스테네스의 체 / 실버 4 / 구현
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // n 보다 작거나 같은 모든 소수를 찾는 과정
        // k번째 수를 지우는 수를 구하라
        boolean[] visited = new boolean[n+1];

        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                if(!visited[j]){
                    cnt++;
                    visited[j] = true;
                }

                if(cnt == k) {
                    System.out.println(j);
                    return;
                }
            }
        }

    }
}
