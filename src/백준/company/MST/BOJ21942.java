package 백준.company.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 문제 잘읽자!! 자료형 범위 int 넘어간다.. long으로 바꿔주고 바로 해결
 */
/*
    최소 스패닝 트리를 만드는 문제
    최소 스패닝 트리는 크루스칼, 프림 알고리즘을 이용해 만들 수 있으며, 크루스칼 알고리즘은 유니온파인드
    알고리즘을 이용해 만들 수 있다.
    우선 주어진 간선을 비용의 오름차순으로 정렬한다.
    비용이 낮은 간선부터 차례로 연결한다. 이때 연결은 두 노드의 부모노드가 같지 않을때만 Union으로
    두 노드의 부모 노드를 합쳐주는 것으로 표현한다.
    비용이 낮은 간선부터 연결했기 때문에, 모든 도시가 연결되면(최소 스패닝 트리) 남은 간선은 연결하지 않아도 되고,
    절약한 비용을 계산하여 출력하면 된다. 만약 모든 노드의 부모노드가 같지 않은 경우는 사이클이 발생하여
    모든 노드를 연결할 수 없기 때문에 이때는 -1 출력하면 된다.
 */
public class BOJ21942 {
    // 도시건설 / 골드 4 / 최소스패닝트리
    static class Node implements Comparable<Node> {
        int x, y;
        int cost;
        public Node(int x, int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
    static int n, m;
    static int[] parent;
    static ArrayList<Node> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        parent = new int[n+1];
        for (int i=1; i<=n; i++) parent[i] = i;

        long totalCost = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            totalCost += cost;
            list.add(new Node(a,b,cost));
        }

        long answer = 0;
        Collections.sort(list);
        for (Node ob : list){
            if(find(ob.x) != find(ob.y)){
                answer += ob.cost;
                union(ob.x, ob.y);
            }
        }

        for (int i = 2; i <= n; i++) {
            if(!findParent(1,i)){
                System.out.println(-1);
                return;
            }
        }

        System.out.println(totalCost - answer);
    }

    static boolean findParent(int x, int y){
        x = find(x);
        y = find(y);
        if(x == y) return true;
        else return false;
    }

    static int find(int x){
        if(x == parent[x]) return x;
        else return parent[x] = find(parent[x]);
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
