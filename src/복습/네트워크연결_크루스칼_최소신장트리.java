package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 네트워크연결_크루스칼_최소신장트리 {
    static class Node implements Comparable<Node>{
        int x, y, dist;

        public Node(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o){
            return this.dist - o.dist;
        }
    }
    static int N, M;
    static int[] parent;
    static ArrayList<Node> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N+1];
        for(int i=1; i<=N; i++){
            parent[i] = i;
        }

        list = new ArrayList<>();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list.add(new Node(x,y, cost));
        }

        Collections.sort(list);

        int result = 0;
        for(int i=0; i<list.size(); i++){
            Node node = list.get(i);

            if(find(node.x) != find(node.y)){
                union(node.x, node.y);
                result += node.dist;
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
