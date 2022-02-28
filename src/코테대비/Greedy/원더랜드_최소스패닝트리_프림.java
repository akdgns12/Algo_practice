package 코테대비.Greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 원더랜드_최소스패닝트리_프림 { //프림 알고리즘 활용(우선순위 큐)
    static class Edge implements Comparable<Edge> {
        int node;
        int cost;
        public Edge(int node, int cost){
            this.node = node;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o){
            return this.cost - o.cost;
        }
    }
    static int v,e;
    static int a,b,c;
    static int[] parent;
    static ArrayList<ArrayList<Edge>> graph;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        v = sc.nextInt();
        e = sc.nextInt();
        parent = new int[v+1];
        ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>();
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<Edge>());
        }
        int[] check = new int[v+1];
        for (int i = 0; i < e; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
            graph.get(a).add(new Edge(b,c));
            graph.get(b).add(new Edge(a,c));
        }
        int answer = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1,0)); //1번 정점부터
        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            int endNode = cur.node;
            if(check[endNode] == 0){ // 체크되지 않은 것들만
                check[endNode] = 1;
                answer += cur.cost;
                for (Edge ob : graph.get(endNode)){
                    if(check[ob.node] == 0) pq.offer(new Edge(ob.node, ob.cost));
                }
            }
        }
        System.out.println(answer);
    }
}
