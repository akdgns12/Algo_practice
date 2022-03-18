package 백준.company.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15655 {
    // N 과 M 6
    static int n, m;
    static int[] arr;
    static boolean[] visited;
    static int[] result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        visited = new boolean[n];
        result = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        dfs(0,0);
        System.out.println(sb);
    }

    static void dfs(int depth, int start){
        if(depth == m){
            for (int i=0; i<m; i++)
                sb.append(result[i] + " ");
            sb.append('\n');
            return;
        }

        for (int i=start; i<n; i++){
            if(!visited[i]){
                result[depth] = arr[i];
                visited[i] = true;
                dfs(depth+1, i+1);
                visited[i] = false;
            }
        }
    }
}
