package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 외판원순회_DFS {
    static int n;
    static int[][] map;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            visited[i] = true;
            dfs(i, 0, i, 0);
            visited[i] = false;
        }

        System.out.println(answer);
    }

    static void dfs(int now, int cnt, int start, int totalCost) {
        if(totalCost > answer) return;
        if (cnt == n - 1 && map[now][start] != 0) {
            answer = Math.min(answer, totalCost + map[now][start]);
            return;
        }

        for (int i = 0; i < n; i++) {
            if(cnt < n - 1 && i == start) continue; // 모돈 도시를 방문한게 아니고 방문하려는 도시가 시작점이라면 pass
            if(now == i) continue; // 자기자신에게 가는 거라면 pass
            else if(!visited[i]){
                visited[i] = true;
                dfs(i, cnt + 1, start, totalCost + map[now][i]);
                visited[i] = false;
            }
        }
    }
}
