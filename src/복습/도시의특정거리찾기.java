package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 도시의특정거리찾기 {
    static int n, m, k, x;
    static ArrayList<Integer>[] list;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        dist = new int[n+1];
        Arrays.fill(dist, -1);
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
        }

        bfs(x);
        boolean check = false;
        for (int i = 1; i <= n; i++) {
            if(dist[i] == k){
                System.out.println(i);
                check = true;
            }
        }
        if(!check)System.out.println(-1);
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : list[now]) {
                if (dist[next] == -1) {
                    dist[next] = dist[now] + 1;
                    q.offer(next);
                }
            }
        }
    }
}
