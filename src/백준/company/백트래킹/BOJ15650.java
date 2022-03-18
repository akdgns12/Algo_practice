package 백준.company.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15650 {
    // N과M(2) / 백트래킹
    static int n, m;
    static int[] arr;
    static boolean[] isUsed;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

         arr = new int[m];
         isUsed = new boolean[n];

        dfs(0,0);
    }

    static void dfs(int depth, int start){
        if(depth == m){
            for (int i = 0; i < m; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i=start; i<n; i++){
            if(!isUsed[i]){
                arr[depth] = i+1;
                isUsed[i] = true;
                dfs(depth + 1, i + 1);
                isUsed[i] = false;
            }
        }
    }
}
