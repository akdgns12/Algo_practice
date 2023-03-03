package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1238_파티 {
    static int N, M, X;
    static List<ArrayList<Node>> list;
    static PriorityQueue<Node> pq;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 학생 수
        M = Integer.parseInt(st.nextToken()); // 간선 수
        X = Integer.parseInt(st.nextToken()); // 도착 점

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

        int[] totalDist = new int[N+1];
        // N명의 학생들의 왕복 최단거리 중 최댓값
        for(int i=1; i<=N; i++) {
            dist = new int[N+1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            visited = new boolean[N+1];
            totalDist[i] += start(i);

            dist = new int[N+1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            visited = new boolean[N+1];
            totalDist[i] += back(X, i);
        }

        Arrays.sort(totalDist);
        System.out.println(totalDist[N]);
    }

    static int start(int start){
        pq = new PriorityQueue<>();
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

        return dist[X];
    }

    static int back(int start, int destination){
        pq = new PriorityQueue<>();
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

        return dist[destination];
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
