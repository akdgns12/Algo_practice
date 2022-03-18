package 백준.company.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15652 {
    static int n, m;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];

        dfs(0, 1);
        System.out.println(sb);
    }

    static void dfs(int depth, int start){
        if(depth == m){
            for (int i=0; i<m; i++)
                sb.append(arr[i] + " ");
            sb.append('\n');
            return;
        }

        for (int i = start; i <= n; i++) {
            arr[depth] = 1;
            dfs(depth+1, i);
        }
    }
}
