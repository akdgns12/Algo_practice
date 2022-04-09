package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class MST {
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
        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            edges.add(new Edge(dist, a, b));
        }

        Collections.sort(edges);


        for (int i = 0; i < edges.size(); i++) {
            int cost = edges.get(i).dist;
            int a = edges.get(i).a;
            int b = edges.get(i).b;

            if(findParent(a) != findParent(b)){
                union(a,b);
                result += cost;
            }
        }
    }

    static int findParent(int x){
        if(x == parent[x]) return x;
        else return parent[x] = findParent(parent[x]);
    }

    static void union(int x, int y){
        x = findParent(x);
        y = findParent(y);

        if(x < y) parent[y] = x;
        else parent[x] = y;
    }

    static class Edge implements Comparable<Edge> {
        int dist;
        int a;
        int b;

        public Edge(int dist, int a, int b) {
            this.dist = dist;
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Edge o){
            return this.dist - o.dist;
        }
    }
}
