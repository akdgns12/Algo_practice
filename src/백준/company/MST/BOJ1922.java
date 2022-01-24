package 백준.company.MST;

import java.beans.ConstructorProperties;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 크루스칼 알고리즘은 다음과 같은 절차로 진행된다.
 * 1. STEP(1) : 모든 간선들을 거리(비용,가중치)를 기준으로 오름차순으로 정렬한다.
 * 2. STEP(2) :  정렬된 간선을 순서대로 선택한다.
 * 3. STEP(3) :  선택한 간선의 두 정점이 연결되어 있지 않으면, 해당 두 정점을 연결시킨다.
 */
public class BOJ1922 {
    // 네트워크연결 / 골드 4 / MST 크루스칼 알고리즘
    static class Edge implements Comparable<Edge>{
        int x, y, cost;

        public Edge(int x, int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o){
            return this.cost - o.cost;
        }
    }

    static int N,M;
    static int[] parent;
    static ArrayList<Edge> list; // 모든 간선을 담을 리스트와, 최종 비용(거리)를 담을 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 노드 개수
        M = Integer.parseInt(br.readLine()); // 간선 개수

        parent = new int[N+1];
        for(int i=1; i<=N; i++){ // 부모테이블 상에서 부모를 자기자신으로 초기화, 초기세팅
            parent[i] = i;
        }

        list = new ArrayList<>();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list.add(new Edge(a,b,cost));
        }

        // 간선을 비용(거리)순으로 정렬
        Collections.sort(list);

        int result = 0;
        // 간선을 하나씩 확인하며
        for(int i=0; i<list.size(); i++){
            Edge edge = list.get(i);

            // 사이클이 발생하지 않는 경우에만 집합에 포함
            if(find(edge.x) != find(edge.y)){
                union(edge.x,edge.y);
                result += edge.cost;
            }
        }

        System.out.println(result);
    }

    static int find(int x){
        if(x == parent[x]){
            return x;
        }else{
            return parent[x] = find(parent[x]);
        }
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x < y){
            parent[y] = x;
        }else{
            parent[x] = y;
        }
    }
}
