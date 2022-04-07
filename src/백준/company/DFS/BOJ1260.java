package 백준.company.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1260 {
    // DFS와 BFS / 실버 2 / DFS탐색 결과와 BFS탐색 결과 출력
    static int n, m, v;
    static ArrayList<Integer>[] list;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 정점의 개수
        m = Integer.parseInt(st.nextToken()); // 간선의 수
        v = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점의 번호

        list = new ArrayList[n+1];

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        // 방문가능한 정점이 여러개라면 작은 순으로 방문해야 하기 때문에 정렬
        for (int i = 1; i <= n; i++) {
            Collections.sort(list[i]);
        }

        visited = new boolean[n+1];
        dfs(v);
        System.out.println();

        // bfs에서 사용하기 위해 방문배열 초기화
        visited = new boolean[n+1];
        bfs(v);
        System.out.println();
    }

    static void dfs(int num){
        if(visited[num])
            return;

        visited[num] = true;
        System.out.print(num + " ");
        for (int i : list[num]){
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    static void bfs(int num){
        Queue<Integer> q = new LinkedList<>();
        q.offer(num);
        visited[num] = true;

        while(!q.isEmpty()){
            int cur = q.poll();
            System.out.print(cur + " ");

            for (int x : list[cur]){
                if(!visited[x]){
                    visited[x] = true;
                    q.offer(x);
                }
            }
        }
    }
}
