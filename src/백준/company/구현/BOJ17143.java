package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17143 {
    // 낚시왕 / 골2 / 구현
    // 낚시왕이 잡은 상어 크기의 합 출력
    static int r, c, m;
    static Shark[][] map;
    static int[] dx = {-1, 0, 1, 0}; // 상 좌 하 우 순
    static int[] dy = {0, -1, 0, 1};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 상어 낚시 격자판 만들고, 각 위치에 상어 클래스로 만든 인스턴스 저장
        map = new Shark[r][c];

        for (int i = 0; i < m; i++) { // 위치, 속력, 방향, 크기
            st = new StringTokenizer(br.readLine());
            int r= Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s= Integer.parseInt(st.nextToken());
            int d= Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            // 방향 쉽게 바꾸기 위해 입력받은 상하우좌(1 2 3 4) -> 상좌하우(0 1 2 3)로 변경
            if(d == 1)
                d = 0;
            else if(d == 4)
                d = 1;

            map[r-1][c-1] = new Shark(r-1, c-1, s, d, z); // 격자판에 상어 저장
        }

        for (int col = 0; col < c; col++) {
            // 1. 낚시왕 이동
            for (int row = 0; row < r; row++) {
                if (map[row][col] != null) {
                    answer += map[row][col].z; // 2. 가장 가까운 상어 크기 정답 변수에 저장
                    map[row][col] = null; // map에서 상어 없애기
                    break;
                }
            }

            // 3. 상어 이동
            Queue<Shark> q = new LinkedList<>();
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if(map[i][j] != null){ // 현재 맵에 있는 상어들 큐에 추가
                        q.add(new Shark(i, j, map[i][j].s, map[i][j].d, map[i][j].z));
                    }
                }
            }

            map = new Shark[r][c]; // 새로운 낚시판 만들기 위해 배열 초기화

            // 모든 상어 한마리씩 꺼내서 이동
            while(!q.isEmpty()){
                Shark sm = q.poll();

                int speed = sm.s;
                if(sm.d == 0 || sm.d == 2)
                    speed %= (r-1) * 2;
                else if(sm.d == 1 || sm.d == 3)
                    speed %= (c-1) * 2;

                for (int s = 0; s < speed; s++) {
                    int nr = sm.r + dx[sm.d];
                    int nc = sm.c + dy[sm.d];

                    if(nr < 0 || nr >= r || nc < 0 || nc >= c){
                        sm.r -= dx[sm.d];
                        sm.c -= dy[sm.d];
                        sm.d = (sm.d + 2) % 4;
                        continue;
                    }

                    // 위치 벗어나지 않을 때는 새로운 위치로 이동
                    sm.r = nr;
                    sm.c = nc;
                }

                // 4. 새로운 위치가 빈공간인지 확인
                if (map[sm.r][sm.c] != null) { // 이미 있다면
                    if (map[sm.r][sm.c].z < sm.z) { // 기존 상어보다 현재 상어가 크다면
                        map[sm.r][sm.c] = new Shark(sm.r, sm.c, sm.s, sm.d, sm.z); // 현재 상어 넣어줌
                    }
                } else {
                    map[sm.r][sm.c] = new Shark(sm.r, sm.c, sm.s, sm.d, sm.z);
                }
            }
        }

        System.out.println(answer);
    }

    static class Shark{
        int r, c, s, d, z;

        Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}
