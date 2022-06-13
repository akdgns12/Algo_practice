package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ24446 {
    // 너비우선탐색3 / 실버 2 / BFS
    // 너비우선 탐색 트리에 있는 모든 노드의 깊이를 출력
    static int n, m, r;
    static ArrayList<Integer>[] list;
    static boolean[] visited;

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

        bfs(r);
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        int dep = 0;
        int[] depth = new int[n+1];
        Arrays.fill(depth, -1);
        while (!q.isEmpty()){
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int cur = q.poll();

                depth[cur] = dep;
                for (int next : list[cur]) {
                    if (!visited[next]) {
                        visited[next]  = true;
                        q.offer(next);
                    }
                }
            }

            dep++;
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(depth[i]);
        }
    }
}
