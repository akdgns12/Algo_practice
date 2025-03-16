package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1504_최단거리 {
    static int N, E;
    static List<List<Node>> list;
    static int[] dist;
    static boolean[] visited;
    static final int INF = 200000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for(int i=0; i<=N; i++){
            list.add(new ArrayList<>());
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.get(a).add(new Node(b, c));
            list.get(b).add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int answer = 0;
        answer += dijkstra(1, x);
        answer += dijkstra(x, y);
        answer += dijkstra(y, N);

        int answer2 = 0;
        answer2 += dijkstra(1, y);
        answer2 += dijkstra(y, x);
        answer2 += dijkstra(x, N);

        if(answer == INF && answer2 == INF) System.out.println(-1);
        else System.out.println(Math.min(answer, answer2));
    }

    static int dijkstra(int start, int end){
        PriorityQueue<Node> pq = new PriorityQueue();
        pq.offer(new Node(start, 0));
        visited = new boolean[N+1];
        dist = new int[N+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(visited[now.end]) continue;
            visited[now.end] = true;

            for(Node next : list.get(now.end)){
                if(dist[next.end] > dist[now.end] + next.dist){
                    dist[next.end] = dist[now.end] + next.dist;
                    pq.offer(new Node(next.end, dist[next.end]));
                }
            }
        }

        return dist[end];
    }

    static class Node implements Comparable<Node>{
        int end;
        int dist;
        public Node(int end, int dist){
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o){
            return this.dist - o.dist;
        }
    }
}
