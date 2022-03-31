package 백준.company.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15684 {
    // 사다리 조작 / 골드 4 / 시뮬레이션, 구현
    /*
        사다리 이어진 곳 : 1

     */
    static int n, m, h;
    static int[][] map;
    static int ans = Integer.MAX_VALUE;
    static boolean isFinish = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 세로선의 개수
        m = Integer.parseInt(st.nextToken()); // 가로선의 개수
        h = Integer.parseInt(st.nextToken()); // 세로선마다 가로선을 놓을 수 있는 위치의 개수

        map = new int[h+1][n+1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            // x높이에서 y번과 y+1번을 잇는 선이 있다
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map[x][y] = 1; // 1 : 우측으로 이동
            map[y][y+1] = 2; //  2 : 좌측으로 이동
        }

        // 추가할 가로선의 갯수를 미리 정해놔야 탐색 종료 조건으로 걸 수 있다.
        // 아래 반복문에서 i는 추가할 가로선의 수
        for (int i = 0; i <= 3; i++) {
            ans = i;
            dfs(1, 1, 0);
            if(isFinish) break;
        }
        System.out.println(isFinish ? ans : -1);
    }

    static void dfs(int x, int y, int count){
        if(isFinish) return;
        if(ans == count){
            if(check()) isFinish = true;
            return;
        }

        for (int i=x; i<=h; i++){
            for(int j=y; j<n; j++){
                if (map[i][j] == 0 && map[i][j + 1] == 0) {
                    map[i][j] = 1;
                    map[i][j+1] = 2;

                    dfs(1, 1, count+1);

                    // 추가했던 가로선을 다시 제거한다
                    map[i][j] = 0;
                    map[i][j+1] = 0;
                }
            }
        }
    }

    // i번에서 출발해 i번으로 도착하는지 검사
    static boolean check(){
        // 세로선 검사
        for (int i = 1; i <= n; i++) {
            int ny = i;
            int nx = 1;
            // 가로선 끝까지
            while(nx <= h){
                if(map[nx][ny] == 1) ny++; // 우측으로 이동
                else if(map[nx][ny] == 2) ny--; // 좌측으로 이동
                nx++; // 아래로 1칸 이동
            }

            if(ny != i) return false; // i번으로 출발해서 i번으로 도착하지 않는게 하나라도 있다면 false
        }
        return true;
    }
}
