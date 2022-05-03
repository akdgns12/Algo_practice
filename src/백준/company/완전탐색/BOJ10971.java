package 백준.company.완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10971 {
    // 외판원 순회 2 / 실버 2 / 완탐, DFS완전탐색 + 백트래킹문제
    // 1~N번의 도시들이 있는데, 한 도시에서 출발해 N개의 도시를 모두 거쳐 다시 원래의 도시로 가장 적은 비용을 들여 돌아오는 비용 리턴
    static int n;
    static int[][] w;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        w = new int[n][n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
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
        if(totalCost > answer) return; // 이미 구해놓은 비용보다 더 크다면 넘어간다(가지치기)
        if (cnt == n - 1 && w[now][start] != 0) { // cnt가 도시의 수와 같고(모든 도시를 다 방문했다) w[now][start] != 0 (시작도시로 돌아갈 수 있다면)
            // 모든 경우 순회
            answer = Math.min(answer, totalCost + w[now][start]); // 더 작은 비용이 answer가 될 수 있게
            return;
        }

        for (int i = 0; i < n; i++) {
            if(cnt < n-1 && i == start) continue;
            if(now == i) continue;
            else if(!visited[i] && w[now][i] != 0){ // 방문한 적 없고, 갈 수 없는 경우(0)이 아니라면
                visited[i] = true; // 방문처리
                dfs(i, cnt + 1, start, totalCost + w[now][i]); // now -> i로, cnt증가, start, totalCost + 해당 도시 방문할 때 든 비용
                visited[i] = false;
            }
        }
    }
}
