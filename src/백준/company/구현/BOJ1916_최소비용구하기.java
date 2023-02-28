package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1916_최소비용구하기 {
    static int N, M;
    static List<ArrayList<Node>> list;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 도시 개수
        M = Integer.parseInt(br.readLine()); // 버스 개수

        list = new ArrayList<>();
        for(int i=0; i<=N; i++){
            list.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list.get(start).add(new Node(end, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        visited = new boolean[N+1];

        int ans = Integer.MAX_VALUE;
        System.out.println(dijkstra(start, end));
    }

    static int dijkstra(int start, int end){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
//        visited[start] = true; 바로 방문체크하면 시작은 어떻게 할건데 영훈아
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(visited[now.end]) continue;
            visited[now.end] = true;

            for(Node next : list.get(now.end)){
                if(dist[next.end] > dist[now.end] + next.cost){
                    dist[next.end] = dist[now.end] + next.cost;
                    pq.offer(new Node(next.end, dist[next.end]));
                }
            }

        }

        return dist[end];
    }

    static class Node implements Comparable<Node> {
        int end, cost;
        public Node(int end, int cost){
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
}
