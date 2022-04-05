package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 경사로 {
    static int n, l;
    static int[][] map;
    static boolean[] visited;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            if(checkPath(i,0,0)){
                cnt++;
            }

            if(checkPath(0,i,1)){
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    static boolean checkPath(int x, int y, int d){
        int[] height = new int[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if(d == 0){ // 행검사
                height[i] = map[x][i];
            }else{ // 열검사
                height[i] = map[i][y];
            }
        }

        for (int i = 0; i < n - 1; i++) {
            if(height[i] == height[i+1]) continue; // 높이가 같을 때 skip
            else if(height[i] - height[i+1] == 1) { // 내려가는 경사일때
                for (int j = i + 1; j <= i + l; j++) {
                    // 범위를 벗어나거나 낮은지점의 높이가 다르거나 이미 경사로가 있는 경우
                    if(j >= n || height[i+1] != height[j] || visited[j])
                        return false;
                    else visited[j] = true;
                }
            }else if(height[i] - height[i+1] == -1){ // 올라가는 경사일 때
                for (int j = i; j > i - l; j--) {
                    if(j < 0 || height[i] != height[j] || visited[j])
                        return false;
                    else visited[j] = true;
                }
            }else{ // 길 x
                return false;
            }
        }
        return true;
    }
}
