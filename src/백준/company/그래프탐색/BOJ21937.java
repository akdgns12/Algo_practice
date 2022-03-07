package 백준.company.그래프탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ21937 {
    // 작업 / 실버 1 / dfs?
    static int n, m;
    static int x;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 정점의 개수
        m = Integer.parseInt(st.nextToken()); // 작업 순서 정보 개수
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        visited = new boolean[n+1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(b).add(a);
        }

        x = Integer.parseInt(br.readLine()); // 작업 X

        // 작업 X를 하기 위해 먼저 해야하는 일의 개수 출력
        // x 노드부터 탐색
        dfs(x);
        System.out.println(answer);
    }

    static void dfs(int node){
        visited[node] = true;

        for (int nextNode : graph.get(node)){
            if(!visited[nextNode]){
                answer += 1;
                dfs(nextNode);
            }
        }
    }
}
