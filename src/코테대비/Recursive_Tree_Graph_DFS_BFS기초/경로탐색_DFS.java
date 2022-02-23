package 코테대비.Recursive_Tree_Graph_DFS_BFS기초;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 경로탐색_DFS { //방향그래프
    static int answer = 0;
    static int n,m;
    static int[][] graph;
    static boolean[] visited;

    static void dfs(int node){
        if(node == n) answer++;
        else{ //뻗어나가기
            for (int i = 1; i <= n; i++) {
                if(graph[node][i] == 1 && !visited[i]){ //해당노드에서 연결되어있고, 방문을 하지 않았다면 이동가능
                    visited[i] = true;
                    dfs(i);
                    visited[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        경로탐색_DFS T = new 경로탐색_DFS();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

         n = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());
        graph = new int[n+1][m+1];
        visited = new boolean[n+1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1;
        }
        visited[1] = true;
        T.dfs(1);
        System.out.println(answer);
    }
}
