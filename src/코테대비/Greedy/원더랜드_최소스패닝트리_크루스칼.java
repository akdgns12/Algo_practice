package 코테대비.Greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/*
    크루스칼 알고리즘
    - 그래프에서 최소 스패닝 트리를 뽑아내는 과정
    - 그래서 싸이클이 존재해선 안된다. -> 싸이클이 존재한다면 트리가 아니라 그래프가 됨
    - 그래서 싸이클이 존재하는 여부를 합집합찾기(유니온파인드) 알고리즘으로 검사할 수 있다
    - 두 집합 번호가 같다면 이미 트리에 존재하는 정점들이므로 싸이클이 생기지 않게 선택하지 않아야 한다
 */
public class 원더랜드_최소스패닝트리_크루스칼 { //크루스칼, 유니온파인드 활용
    static class Edge implements Comparable<Edge>{
        int x, y;
        int cost;
        public Edge(int x, int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o){// cost기준 오름차순 정렬
            return this.cost - o.cost;
        }
    }
    static int v,e;
    static int a,b,c;
    static ArrayList<Edge> arr;
    static int[] parent;

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        v = sc.nextInt(); //도시의 개수(정점의 개수)
        e = sc.nextInt(); //도로의 개수(간선의 개수)
        parent = new int[v+1];
        arr = new ArrayList<>();
        for (int i=1; i<=v; i++) parent[i] = i;
        for (int i=0; i<e; i++) {
            a = sc.nextInt(); // a도시와 b도시 연결된 간선비용 c
            b = sc.nextInt();
            c = sc.nextInt();
            arr.add(new Edge(a, b, c));
        }
        int answer = 0;
        Collections.sort(arr);
        /*
            참고 : 이미 트리를 완성했는데 계속해서 정점들을 탐색한다면 낭비..
            트리의 정점이 n이라면 간선의 개수는 무조건 n-1
            이 점을 이용하여
            정점을 선택할때마다 카운팅해주고 선택된 정점의 개수가 n-1개가 되면 break; 해준다면
            낭비적인 그래프 탐색을 막을 수 있다.
         */
        for (Edge ob : arr){
            // 두 정점이 같은 집합인지 find함수로 확인 -> 같은집합이 아닐 경우에만 비용추가
            if(find(ob.x) != find(ob.y)) {
                answer += ob.cost;
                //같은 집합이 아니라서 두 정점을 선택했으므로 같은 집합으로 합쳐준다
                union(ob.x, ob.y);
            }
        }

        System.out.println(answer);
    }
}
