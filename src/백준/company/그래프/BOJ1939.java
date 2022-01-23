package 백준.company.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1939 {
    // 중량제한 / 골드 4 / 크루스칼 알고리즘, 이분탐색 + bfs도 가능
    // 한 번의 이동에서 옮길 수 있는 물품들의 중량의 최댓값을 return
    /*
        크루스칼 알고리즘(최소비용 신장트리) 반대로 생각하면 문제에서 주어지는 바를 구할 수 있다
        최소가 아닌 최대값을 찾는 문제이기 때문에 가중치가 큰 순서대로 정렬(내림차순)
        그럼 문제에 주어진 두 도시를 연결하는 최대 가중치가 나올 것이고
        그게 답이 된다.
     */
    static class Edge implements Comparable<Edge>{
        int x,y;
        int cost;

        public Edge(int x, int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o){ // 가중치 기준 내림차순
            return o.cost - this.cost;
        }
    }

    static int N,M;
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정점의 수
        M = Integer.parseInt(st.nextToken()); // 간선의 수

        parent = new int[N+1];
        for(int i=1; i<=N; i++){
            parent[i] = i;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(a,b,cost));
        }

        // 두 공장의 번호
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            int x = cur.x;
            int y = cur.y;

            union(x,y);
            if(find(s) == find(e)){
                System.out.println(cur.cost);
                break;
            }
        }

    }
    // 집합의 원소가 어떤 집합에 포함되어 있는지 판단하는 연산
    /*
        찾고자 하는 원소가 자신의 인덱스 값과 같다면, 즉 자기 자신이 부모인 상황이라면
        그 값이 그 집합을 대표하는 번호..
        그렇지 않다면, 지금 원소가 가리키는 값을 재귀적으로 다시 find함수에 넘겨주어 부모노드까지 탐색한다.
     */
    static int find(int x){
        if(x == parent[x]){
            return x;
        }else{
            return parent[x] = find(parent[x]);
        }
    }
    // union : 서로 다른 두개의 집합을 병합하는 연산
    /*

     */
    static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x != y){
            if(x < y){
                parent[y] = x;
            }else{
                parent[x] = y;
            }
        }
    }
}
