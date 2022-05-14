package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15684 {
    // 사다리 조작 / 골드 4 / 완탐
    // 두 가로선이 연속하거나 접하면 안됨
    // i번의 세로선의 결과가 i번이 나와야 한다. -> 추가해야 하는 가로선의 개수의 최솟값 구하라
    // 정답이 3보다 큰 값이면 -1, 불가능한 경우에도 -1 -> 모든 경우 탐색해봐야함 재귀로 dfs
    static int n, m, h;
    static int[][] map;
    static boolean isFinish = false;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 세로선의 수
        m = Integer.parseInt(st.nextToken()); // 가로선의 수
        h = Integer.parseInt(st.nextToken()); // 가로선을 놓을 수 있는 위치의 개수

        map = new int[h+1][m+1];
        // 가로선 정보 : b번 세로선, b+1번 세로선을 a번 위치에서 연결
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 1; // 오른쪽으로 이동
            map[a][b+1] = 2; // 왼쪽으로 이동
        }

        for (int i = 0; i <= 3; i++) { // 설치해야 하는 가로선 제한이 3개까지니까, 가로선 개수 0~3까지 하나씩 검사
            answer = i;
            dfs(1, 1, 0); // (1,1)에서 시작
            if(isFinish) break;
        }

        System.out.println(isFinish ? answer : -1);
    }

    static void dfs(int x, int y, int count) {
        if(isFinish) return;
        if (answer == count) {
            if(check()) isFinish = true;
            return;
        }

        for (int i = x; i <= h; i++) {
            for (int j = y; j < n; j++) {
                if (map[i][j] == 0 && map[i][j + 1] == 0) { // 가로선이 없는 곳이라면, 가로선을 설치해주고
                    map[i][j] = 1;
                    map[i][j+1] = 2;
                    dfs(1, 1, count+1);
                    map[i][j] = 0;
                    map[i][j+1] = 0;
                }
            }
        }
    }

    static boolean check() {
        for (int i = 1; i <= n; i++) { // i번 열의 좌표를 검사
            int nx = 1; // x는 1에서 시작
            int ny = i;

            while (nx <= h) { // h까지 x를 탐색하는 동안
                if(map[nx][ny] == 1) ny++; // 해당 좌표가 1이라면 오른쪽으로 이동
                else if(map[nx][ny] == 2) ny--; // 해당 좌표가 2라면 왼쪽으로 이동
                nx++; // 행 증가
            }
            if(ny != i) return false; // 행 끝까지 검사했는데 i번열이 i번이 아니라면 false
        }
        return true;
    }
}
