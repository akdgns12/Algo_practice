package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ텀프로젝트 {
    static int T;
    static int n;
    static int[] arr;
    static boolean[] visited;
    static boolean[] finished;
    static int teamCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            visited = new boolean[n+1];
            finished = new boolean[n+1];

            teamCnt = 0;
            for (int k = 1; k <= n; k++) {
                dfs(k);
            }
            System.out.println();
        }

    }

    static void dfs(int node){
        if(visited[node]){
            finished[node] = true;
            teamCnt++;
        }else{
            visited[node] = true;
        }

        int next = arr[node];
        if(!finished[next]){
            dfs(next);
            visited[node] = false;
            finished[node] = true;
        }
    }
}
