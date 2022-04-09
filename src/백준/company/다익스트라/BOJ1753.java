package 백준.company.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1753 {
    // 최단경로 / 골드5 / 다익스트라
    static int v, e;
    static int[] distance;
    static ArrayList<Node>[] list;
    static final int INF = (int)1e9;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        distance = new int[v+1];
        visited = new boolean[v+1];
        list = new ArrayList[v+1];
        for (int i = 1; i <= v; i++) {
            list[i] = new ArrayList<>();
        }

        Arrays.fill(distance, INF);

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, dist));
        }

        dijkstra(start);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= v; i++) {
            if(distance[i] == INF) sb.append("INF").append("\n");
            else sb.append(distance[i]).append("\n");
        }

        System.out.println(sb.toString());
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int now = node.end;

            if(visited[now]) continue;
            else visited[now] = true;

            for (Node next : list[now]){
                if (distance[next.end] > distance[now] + next.dist) {
                    distance[next.end] = distance[now] + next.dist;
                    pq.offer(new Node(next.end, distance[next.end]));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int end, dist;

        public Node(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
}
