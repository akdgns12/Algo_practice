package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DFS_BFS {
    static int n, m, v;
    static ArrayList<Integer>[] list;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        for (int i = 1; i <= n; i++) {
            Collections.sort(list[i]);
        }

        visited = new boolean[n+1];
        dfs(v);

        System.out.println();
        visited = new boolean[n+1];
        bfs(v);
    }

    static void dfs(int num){
        if(visited[num])
            return;

        visited[num] = true;
        System.out.print(num + " ");
        for (int i : list[num]) {
            if(!visited[i]){
                dfs(i);
            }
        }
    }

    static void bfs(int num) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(num);
        visited[num] = true;

        while(!q.isEmpty()){
            int cur = q.poll();

            System.out.print(cur + " ");
            for (int i : list[cur]){
                if(!visited[i]){
                    visited[i] = true;
                    q.offer(i);
                }
            }
        }
    }
}
