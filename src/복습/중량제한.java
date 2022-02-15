package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 중량제한 {
    // 최소신장트리 mst 크루스칼 알고리즘 반대로 역이용 최대 신장트리로
    static class Node implements Comparable<Node>{
        int x,y;
        int cost;
        public Node(int x, int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o){
            return o.cost - this.cost;
        }
    }
    static int N,M;
    static int[] parent;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        for(int i=1; i<=N; i++){ // 부모 테이블 자기자신 가리키도록 초기화
            parent[i] = i;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            pq.offer(new Node(a,b,cost));
        }


        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        while(!pq.isEmpty()){
            Node node = pq.poll();

            int cost = node.cost;
            int x = node.x;
            int y = node.y;

            union(x,y);
            if(find(s) == find(e)){
                System.out.println(cost);
                break;
            }
        }

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
        if(x != y){
            parent[y] = x;
        }else{
            parent[x] = y;
        }
    }
}
