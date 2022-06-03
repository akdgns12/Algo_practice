package 복습;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ숨바꼭질 {
    // 1. 다익스트라 알고리즘으로 1번 점으로부터 모든 정점까지의 최소거리 구한다
    // 2. 거리 중에 최대 거리, 최대 거리 위치, 최대값이 몇 번 나오는지 체크
    static int n, m;
    static ArrayList<Integer>[] list;
    static int[] dist;
    static boolean[] visited;
    static final int INF = (int)1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        dist = new int[n+1];
        Arrays.fill(dist, INF);

        visited = new boolean[n+1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        dijkstra(1);
    }

    static void dijkstra(int start) {
        dist[start] = 0;
        visited[start] = true;
        Queue<Integer> q = new LinkedList<>();

        while(!q.isEmpty()){
            int cur = q.poll();

            for (int i = 0; i < list[cur].size(); i++) {
                int next = list[cur].get(i);

                if(!visited[next] && dist[next] > dist[cur] + 1){
                    dist[next] = dist[cur] + 1;
                    q.offer(next);
                    visited[next] = true;
                }
            }
        }

        int max = -1;
        int maxPos = -1;
        int maxNum = 1;

        for (int i = 1; i <= n; i++) {
            if (max < dist[i]) {
                max = dist[i];
                maxPos = i;
                maxNum = 1;
            } else if (max == dist[i]) {
                maxNum++;
            }
        }

        System.out.println(maxPos + " " + max + " " + maxNum);
    }
}
