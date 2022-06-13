package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ24444 {
    // 알고리즘 수업 - 너비우선탐색1 / 실버 2 / BFS
    // 주어진 정점에서 시작하여 bfs로 노드를 방문할 경우
    // 노드의 방문 순서를 출력

    static int n, m, r;
    static boolean[] visited;
    static int[] order;
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
        visited = new boolean[n+1];

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

        bfs(r);
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        int cnt = 0;
        order = new int[n+1];
        while (!q.isEmpty()){
            int cur = q.poll();
            cnt++;
            order[cur] = cnt;

            for (int next : list[cur]) {
                if(!visited[next]){
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
