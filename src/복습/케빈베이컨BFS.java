package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 케빈베이컨BFS {
    static int n, m;
    static int[] dist;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
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

        int minCnt = Integer.MAX_VALUE;
        int minIdx = 0;

        for (int i = 1; i <= n; i++) {
            int cnt = bfs(i);
            if(minCnt > cnt){
                minCnt = cnt;
                minIdx = i;
            }
        }

        System.out.println(minIdx);
    }

    static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        dist = new int[n+1];
        dist[start] = 0;
        int cnt = 0;

        while(!q.isEmpty()){
            int now = q.poll();

            for (int next : list[now]) {
                if(dist[next] != 0) continue;
                dist[next] = dist[now] + 1;
                cnt += dist[next];
                q.offer(next);
            }
        }
        return cnt;
    }
}
