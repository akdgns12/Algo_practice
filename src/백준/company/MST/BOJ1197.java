package 백준.company.MST;

import 코테대비.Greedy.친구인가_Union_Find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ1197 {
    // 최소 스패닝 트리 / 골드 5 / MST
    static int v, e;
    static int[] parent;
    static ArrayList<Edge> edges = new ArrayList<>();
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        parent = new int[v+1];
        // 부모테이블 상에서 자기자신으로 초기화
        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(cost, a, b));
        }

        // 간선을 비용순으로 정렬
        Collections.sort(edges);

        // 간선을 하나씩 확인하며
        for (int i = 0; i < edges.size(); i++) {
            int cost = edges.get(i).dist;
            int a = edges.get(i).nodeA;
            int b = edges.get(i).nodeB;
            // 사이클이 발생하지 않는 경우에만 집합에 포함
            if(findParent(a) != findParent(b)){
                unionParent(a,b);
                result += cost;
            }
        }
    }

    // 특정 원소가 속한 집함을 찾기
    static int findParent(int x){
        // 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
        if(x == parent[x]) return x;
        else return parent[x] = findParent(parent[x]);
    }

    static void unionParent(int x, int y){
        x = findParent(x);
        y = findParent(y);

        if(x < y) parent[y] = x;
        else parent[x] = y;
    }

    static class Edge implements Comparable<Edge>{
        int dist;
        int nodeA;
        int nodeB;
        public Edge(int dist, int nodeA, int nodeB){
            this.dist = dist;
            this.nodeA = nodeA;
            this.nodeB = nodeB;
        }
        @Override
        public int compareTo(Edge o){
            return this.dist - o.dist;
        }
    }
}
