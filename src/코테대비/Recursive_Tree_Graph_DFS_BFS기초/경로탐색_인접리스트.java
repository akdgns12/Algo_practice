package 코테대비.Recursive_Tree_Graph_DFS_BFS기초;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 정점이 많은 경우에는 인접행렬은 비효율적!
 * 인접리스트로 하는게 좋다
 */
public class 경로탐색_인접리스트 {
    static int n, m, answer = 0;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;

    static void dfs(int node) {
        if(node == n) answer++;
        else{
            for (int nextNode : graph.get(node)){ //현재 노드인 node번 ArrayList 객체에 연결된 nextNode들의 ArrayList객체들에 접근
                if(!visited[nextNode]){
                    visited[nextNode] = true;
                    dfs(nextNode);
                    visited[nextNode] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        경로탐색_인접리스트 T = new 경로탐색_인접리스트();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList<ArrayList<Integer>>();

        /*
            객체 생성, 0~n번 객체 why? -> 우리는 밑에서 입력으로 받는 a번 객체 ex) 1~5번까지의 객체에 접근해야 한다고 했을 때(get(a))이런식으로 접근을 해야하는데
            그렇게 접근하기 위해서 0~n번까지의 ArrayList<Integer> 객체를 생성해놓고 1~n번까지 사용하면 된다
         */
        for (int i = 0; i <=n; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new boolean[n+1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
        }

        visited[1] = true;
        T.dfs(1);
        System.out.println(answer);
    }
}
