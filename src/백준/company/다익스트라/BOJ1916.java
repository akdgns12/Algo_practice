package 백준.company.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1916 {
    // 최소비용 구하기 / 골드 5 / 다익스트라
    static int n, m;
    static int[] distance;
    static boolean[] visited;
    static final int INF = (int)1e9;
    static ArrayList<Node>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 정점 개수
        m = Integer.parseInt(br.readLine()); // 간선 개수
        distance = new int[n+1];
        visited = new boolean[n+1];
        list = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, dist));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        Arrays.fill(distance, INF);

        System.out.println(dijkstra(start, dest));
    }

    static int dijkstra(int start, int dest) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.end;

            if(visited[now]) continue;
            else visited[now] = true;

            for (Node next : list[now]) {
                // 해당 정점의 최단테이블 거리 > 현재정점까지의 거리 + 해당정점까지의 최단테이블거리
                if (distance[next.end] > distance[now] + next.dist) {
                    distance[next.end] = distance[now] + next.dist;
                    pq.offer(new Node(next.end, distance[next.end]));
                }
            }
        }

        return distance[dest];
    }

    static class Node implements Comparable<Node> {
        int end, dist;

        public Node(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o){
            return this.dist - o.dist;
        }
    }
}
