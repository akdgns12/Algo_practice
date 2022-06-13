package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ24447 {
    // 너비 우선 탐색 4 / 실버 2 / BFS
    // 노드의깊이 x 노드의 방문순서
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
        for (int i = 1; i <= n; i++) {
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

        int dep = 0;
        int cnt = 0;
        long[] depth = new long[n+1]; // 노드깊이
        Arrays.fill(depth, -1);
        long[] order = new long[n+1]; // 노드 방문순서

        while (!q.isEmpty()){
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int now = q.poll();
                cnt++;
                order[now] = cnt;
                depth[now] = dep;

                for (int next : list[now]) {
                    if(!visited[next]){
                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }

            dep++;
        }

        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += depth[i] * order[i];
        }
        System.out.println(sum);
    }
}
