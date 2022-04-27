package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 복습2_MST {
    static int v, e;
    static ArrayList<Edge> edges = new ArrayList<>();
    static int[] parent;
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
            int cost = Integer.parseInt(st.nextToken());

            edges.add(new Edge(a, b, cost));
        }

        Collections.sort(edges);

        for (int i = 0; i < edges.size(); i++) {
            int a = edges.get(i).nodeA;
            int b = edges.get(i).nodeB;
            int cost = edges.get(i).dist;

            if(find(a) != find(b)){
                union(a, b);
                result += cost;
            }
        }
        System.out.println(result);
    }

    static int find(int x) {
        if(x == parent[x]) return x;
        else return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x < y) parent[y] = x;
        else parent[x] = y;
    }

    static class Edge implements Comparable<Edge> {
        int nodeA;
        int nodeB;
        int dist;

        public Edge(int nodeA, int nodeB, int dist) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o){
            return this.dist - o.dist;
        }
    }
}
