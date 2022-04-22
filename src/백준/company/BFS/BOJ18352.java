package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ18352 {
    // 특정 거리의 도시 찾기 / 실버 2 / BFS
    /*
       어떤 나라에는 1번부터 N번까지의 도시와 M개의 단방향 도로가 존재. 모든 도로의 거리는 1

     */
    static int n, m, k, x;
    static ArrayList<Integer>[] list;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 정점 개수
        m = Integer.parseInt(st.nextToken()); // 간선 개수
        k = Integer.parseInt(st.nextToken()); // 최단거리 K
        x = Integer.parseInt(st.nextToken()); // 출발 정점 번호

        dist = new int[n+1];
        Arrays.fill(dist, -1);

        list = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) { // 간선 정보
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
        }

        bfs(x);
        boolean check = false;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == k) {
                System.out.println(i);
                check = true;
            }
        }
        if(!check) System.out.println(-1);
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        dist[start] = 0;

        while(!q.isEmpty()){
            int now = q.poll();

            for (int next : list[now]) {
                if(dist[next] == -1){ // 방문한적이 없으면
                    dist[next] = dist[now] + 1;
                    q.offer(next);
                }
            }
        }
    }
}

