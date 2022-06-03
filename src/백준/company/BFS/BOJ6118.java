package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ6118 {
    // 숨바꼭질 / 실버 1 / BFS
    /*
        숨어야 하는 헛간번호(답이 여러개라면 가장작은 헛간번호), 헛간까지의 거리, 그 헛간과 같은 거리를 같는 헛간의 개수
        그래프 문제, 1번 노드에서 가장 먼 거리의 노드 찾기(여러개라면 번호가 작은 노드)
     */
    static int n, m;
    static final int INF = (int)1e9;
    static int[] dist;
    static boolean[] visited;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 헛간의 개수 n
        m = Integer.parseInt(st.nextToken()); // 모든 헛간은 m개의 양방향 길로 이어짐

        list = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        dist = new int[n + 1];
        Arrays.fill(dist, INF);

        visited = new boolean[n+1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        dijkstra(1); // 1에서 시작
    }

    static void dijkstra(int start) {
        dist[start] = 0;
        visited[start] = true;

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

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

        // 헛간까지의 거리
        int max = -1;
        // 숨어야 하는 헛간번호
        int maxPos = -1;
        // 그 헛간과 같은 거리인 헛간 개수
        int maxNum = 1;

        // 최댓값 찾기
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
