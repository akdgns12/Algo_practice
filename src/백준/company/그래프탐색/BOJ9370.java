package 백준.company.그래프탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ9370 {
    static class Node implements Comparable<Node> {
        int node;
        int cost;
        public Node(int node, int cost){
            this.node = node;
            this.cost= cost;
        }
        @Override
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
    static int T;
    static int n,m,t;
    static int start,g,h;
    static ArrayList<ArrayList<Node>> list;
    static boolean[] visited;
    static int[] dist;
    static int INF = (int)1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); //도시
            m = Integer.parseInt(st.nextToken()); //도로
            t = Integer.parseInt(st.nextToken()); //목적지 후보

            list = new ArrayList<>();
            for (int j = 0; j <=n; j++) {
                list.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken()); //시작점
            g = Integer.parseInt(st.nextToken()); // 교차로
            h = Integer.parseInt(st.nextToken()); // 교차로

            //양방향 그래프
            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                list.get(a).add(new Node(b, cost));
                list.get(b).add(new Node(a, cost));
            }

            int[] candidate = new int[t]; // 목적지 후보 담을 배열
            for (int j = 0; j < t; j++) {
                candidate[j] = Integer.parseInt(br.readLine());
            }

            PriorityQueue<Integer> result = new PriorityQueue<>();

            // start - end 최단경로와 같은 start - g - h - end 혹은 s - h - g - end 최단경로 찾기
            for (int end : candidate) {
                long res1 = dijkstra(start, g) + dijkstra(g, h) + dijkstra(h, end);
                long res2 = dijkstra(start, h) + dijkstra(h, g) + dijkstra(g, end);
                long res3 = dijkstra(start, end);

                if(Math.min(res1, res2) == res3){
                    result.offer(end);
                }
            }

            while(!result.isEmpty()){
                sb.append(result.poll() + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static int dijkstra(int start, int end){
        dist = new int[n+1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node node = pq.poll();

            int now = node.node;
            int d = node.cost;

            if(dist[now] > d) continue; //이미 처리된 경우

            for (Node next : list.get(now)){
                int cost = dist[now] + next.cost;

                if(cost < dist[next.node]){
                    dist[next.node] = cost;
                    pq.offer(new Node(next.node, cost));
                }
            }
        }

        return dist[end];
    }
}
