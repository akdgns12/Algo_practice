package 백준.company.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1182 {
    // 부분수열의 합 / 실버 2 / 백트래킹
    static int n, s;
    static int[] arr;
    static boolean[] visited;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0,0);
        if(s == 0) answer--; // 공집합 제외, s가 0일경우만 신경쓰면 됨
        System.out.println(answer);
    }

    static void dfs(int depth, int sum) {
        if(depth == n) {
            if (sum == s) answer++;
            return;
        }

        dfs(depth+1, sum);
        dfs(depth+1, sum + arr[depth]);
    }
}
