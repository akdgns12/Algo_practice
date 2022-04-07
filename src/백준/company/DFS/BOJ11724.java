package 백준.company.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11724 {
    // 연결요소의 개수 / 실버 2 / DFS 방향없는 그래프(그래프가 총 몇개인지 세는 문제)
    static int n, m;
    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

         n = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());

         list = new ArrayList[n+1];
         visited = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            list[u].add(v);
            list[v].add(u);
        }

        for (int i = 1; i <= n; i++) {
            if(!visited[i]){
                dfs(i);
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    static void dfs(int i){
        if(visited[i])
            return;

        visited[i] = true;
        for (int x : list[i]){
            if(!visited[x]){
                dfs(x);
            }
        }
    }
}

