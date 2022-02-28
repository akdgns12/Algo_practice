package 코테대비.Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 다익스트라알고리즘 {
    static class Edge implements Comparable<Edge> {
        int node;
        int cost;
        public Edge(int node, int cost){
            this.node = node;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o){ //작은 비용부터 나올 수 있도록 오름차순 정렬
            return this.cost - o.cost;
        }
    }
    static int n, m, c;
    static ArrayList<ArrayList<Edge>> graph; //Edge라는 객체를 저장할 수 있는 ArrayList(인접리스트)
    static int[] dist;
    static int answer = Integer.MAX_VALUE;
    static int INF = (int)1e9;

    public static void solution(int node){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(node, 0));
        dist[node] = 0;
        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            int now = cur.node;
            int nowCost = cur.cost;
            if(nowCost > dist[now]) continue;
            for (Edge ob : graph.get(now)){
                if(dist[ob.node] > nowCost + ob.cost){
                    dist[ob.node] = nowCost + ob.cost;
                    pq.offer(new Edge(ob.node, dist[ob.node]));
                }
            }
        }
    }

    public static void main(String[] args) {
        다익스트라알고리즘 T = new 다익스트라알고리즘();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); //정점의 개수
        m = sc.nextInt(); //가중치 간선의 개수
        graph = new ArrayList<ArrayList<Edge>>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Edge>());
        }
        dist = new int[n+1];
        Arrays.fill(dist, INF);
        for (int i = 0; i < n; i++) {
            n = sc.nextInt();
            m = sc.nextInt();
            c = sc.nextInt();
            graph.get(n).add(new Edge(m,c));
        }
        T.solution(1);
        for (int i = 2; i <= n; i++) {
            if(dist[i] != INF) System.out.println(i + " " + dist[i]);
            else System.out.println(i + "Impossible");
        }
    }
}
