package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11725_트리의부모찾기 {
    static List<ArrayList<Integer>> list;
    static int N;
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 노드 수
        parent = new int[N+1];
        list = new ArrayList<>();
        visited = new boolean[N+1];
        for(int i=0; i<=N; i++){
            list.add(new ArrayList<>());
        }

        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
        }

        dfs(0,1,0);
        for(int i=2; i<=N; i++) System.out.println(parent[i]);
    }

    static void dfs(int depth, int now, int pre){
        if(depth == N / 2) return;

        parent[now] = pre;
        for(int next : list.get(now)){
            if(visited[next]) continue;
            visited[next] = true;
            dfs(depth + 1, next, now);
        }
    }
}
