package 백준.company.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14889 {
    // 스타트와 링크 / 실버2 / 시뮬레이션
    static int n;
    static int[][] map;
    static boolean[] visited;
    static int ans = Integer.MAX_VALUE;

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

        // 가능한 조합 모두 찾아서 최솟값인 경우 return
        dfs(0,0);
        System.out.println(ans);
    }

    static void dfs(int idx, int count){
        if(count == n/2){
            calc(); // 하나의 조합, 경우의 수 찾으면 능력치 차이 계산
            return;
        }

        for (int i = idx; i < n; i++) {
            if(!visited[i]){
                visited[i] = true;
                dfs(i+1, count+1);
                visited[i] = false;
            }
        }
    }

    static void calc(){
        int start = 0;
        int link = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if(visited[i] && visited[j]){
                    start += map[i][j];
                    start += map[j][i];
                }else if(!visited[i] && !visited[j]){
                    link += map[i][j];
                    link += map[j][i];
                }
            }
        }

        // 능력치 차이
        int diff = Math.abs(start - link);

        if(diff == 0){
            System.out.println(diff);
            System.exit(0);
        }

        ans = Math.min(ans, diff);
    }
}
