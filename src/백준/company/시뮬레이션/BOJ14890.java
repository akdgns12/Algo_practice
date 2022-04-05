package 백준.company.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14890 {
    // 경사로 / 골드 3 / 시뮬레이션
    // 경사로 길이 L, 경사로는 항상 높이가 1, 낮은칸에 놓으며 낮은칸과 높은칸의 높이차는 1
    // 경사로를 놓을 칸의 높이는 모두 같아야 하고, L개의 칸이 연속되어 있어야 한다.
    // 지나갈 수 있는 길의 개수 return
    static int n, l;
    static int[][] map;
    static boolean[] visited;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken()); // 경사로 길이

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 행과 열 따로 계산
        for (int i = 0; i < n; i++) {
            if (checkPath(i, 0, 0)) { // 행검사
                cnt++;
            }

            if (checkPath(0, i, 1)) { // 열검사
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    // d : 행검사인지 열검사인지 판별할 변수
    static boolean checkPath(int x, int y, int d) {
        int[] height = new int[n]; // 높이 체크하기 위한 배열
        visited = new boolean[n]; // 경사로 설치되어있는지 체크

        for (int i = 0; i < n; i++) {
            if (d == 0) {
                height[i] = map[x][i]; // 행 검사
            }else{
                height[i] = map[i][y]; // 열 검사
            }
        }

        for (int i = 0; i < n-1; i++) {
            if(height[i] == height[i+1]) continue; // 높이가 같을 때 skip
                // 내려가는 경사
            else if (height[i] - height[i + 1] == 1) {
                for (int j = i + 1; j <= i + l; j++) {
                    // 범위 넘어가거나 낮은지점의 칸의 높이가 다르거나 이미 경사로가 있는 경우
                    if(j >= n || height[i+1] != height[j] || visited[j])
                        return false;
                    else visited[j] = true;
                }
            }
            // 올라가는 경사
            else if(height[i] - height[i+1] == -1){
                for (int j = i; j > i-l; j--) {
                    // 범위 넘어가거나 낮은지점의 칸의 높이가 다르거나 이미 경사로가 있는 경우
                    if (j < 0 || height[i] != height[j] || visited[j])
                        return false;
                    else visited[j] = true;
                }
            }
            // 높이가 2칸 이상 차이 날 때(길 X)
            else
                return false;
        }
        return true;
    }
}
