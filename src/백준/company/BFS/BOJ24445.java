package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ24445 {
    // 너비우선탐색2 / 실버2 / BFS
    static int n, m, r;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());


        list = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        // 인접정점 내림차순으로 방문
        for (int i = 1; i <= n; i++) {
            Collections.sort(list[i], Collections.reverseOrder());
        }

        bfs(r);
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        boolean[] visited = new boolean[n+1];
        visited[start] = true;

        int cnt = 0;
        int[] order = new int[n+1];
        while (!q.isEmpty()){
            int cur = q.poll();
            cnt++;
            order[cur] = cnt;

            for (int next : list[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(order[i]);
        }
    }
}
