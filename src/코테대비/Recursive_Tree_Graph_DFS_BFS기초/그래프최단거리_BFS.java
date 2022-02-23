package 코테대비.Recursive_Tree_Graph_DFS_BFS기초;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 그래프최단거리_BFS {
    static int n, m;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    static int[] distance;

    static void bfs(int node){
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        distance[node] = 0;
        visited[node] = true;
        while(!q.isEmpty()){
            int curNode = q.poll();
            for (int nextNode : graph.get(curNode)){
                if(!visited[nextNode]){
                    visited[nextNode] = true;
                    q.offer(nextNode);
                    distance[nextNode] = distance[curNode] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        그래프최단거리_BFS T = new 그래프최단거리_BFS();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        visited = new boolean[n+1];
        distance = new int[n+1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
        }

        T.bfs(1);
        for (int i = 2; i <= n; i++) {
            System.out.println(i + ":" + distance[i]);
        }
    }
}
