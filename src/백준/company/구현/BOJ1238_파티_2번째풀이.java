package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1238_파티_2번째풀이 {
    static int N, M, X;
    static List<ArrayList<Node>> list;
    static int[] dist, dist2;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for(int i=0; i<=N; i++){
            list.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list.get(a).add(new Node(b, cost));
        }

//        for(int i=1; i<=N; i++){
//            dijkstra(i);
//        }
        dist = new int[N+1];
        visited = new boolean[N+1];
        int[] answer = new int[N+1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dijkstra(X, dist);

        for(int i=1; i<=N; i++){
            dist2 = new int[N+1];
            Arrays.fill(dist2, Integer.MAX_VALUE);
            visited = new boolean[N+1];
            dijkstra(i, dist2);
            answer[i] += dist2[X];
        }

        for(int i=1; i<=N; i++){
            answer[i] += dist[i];
        }

        Arrays.sort(answer);
        System.out.println(answer[N]);
    }

    static void dijkstra(int start, int[] dist){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
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
    }

    static class Node implements Comparable<Node>{
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
