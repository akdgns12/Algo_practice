package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 복습_최소스패닝트리 {
    static int v, e;
    static int[] parent;
    static ArrayList<Edge> edges = new ArrayList<>();
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken()); // 정점 개수
        e = Integer.parseInt(st.nextToken()); // 간선 개수

        parent = new int[v+1];
        // 부모테이블 상에서 자기자신으로 초기화
        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        // 간선 정보
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            edges.add(new Edge(a, b, dist));
        }

        // 간선 가중치 오름차순으로 정렬
        Collections.sort(edges);

        // 간선들을 하나씩 확인하며
        for (int i = 0; i < edges.size(); i++) {
            int cost = edges.get(i).dist;
            int a = edges.get(i).nodeA;
            int b = edges.get(i).nodeB;

            // 사이클이 발생하지 않는 경우에만 집합에 포함(최소스패닝트리 조건)
            if(find(a) != find(b)){
                union(a,b);
                result += cost;
            }
        }
        System.out.println(result);
    }

    static int find(int x){
        if(x == parent[x]) return x;
        else return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x < y){
            parent[y] = x;
        }else{
            parent[x] = y;
        }
    }

    static class Edge implements Comparable<Edge>{
        int nodeA;
        int nodeB;
        int dist;

        public Edge(int nodeA, int nodeB, int dist) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }
}
