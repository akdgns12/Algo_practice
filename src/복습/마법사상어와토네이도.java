package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 마법사상어와토네이도 {
    static int n;
    static int[][] map;
    // 토네이도 이동 방향 순서 좌-하-우-상
    static int[] dx = {0,-1,0,1};
    static int[] dy = {-1,0,1,0};
    // 토네이동 이동에 따른 모래 퍼지는 방향
    // 토네이도 이동에 따른 모래가 퍼지는 방향
    static int[][] dsx= {{-1,1,-2,-1,1,2,-1,1,0}, // 기준인 y의좌표의 '모래'에서 퍼진다
            {-1,-1,0,0,0,0,1,1,2},
            {1,-1,2,1,-1,-2,1,-1,0},
            {1,1,0,0,0,0,-1,-1,-2}};
    static int[][] dsy= {{1,1,0,0,0,0,-1,-1,-2},
            {-1,1,-2,-1,1,2,-1,1,0},
            {-1,-1,0,0,0,0,1,1,2},
            {1,-1,2,1,-1,-2,1,-1,0}};
    // 토네이도 이동 칸 수, 한 턴 끝날 때마다 +2씩 증가
    static int[] dc = {1,1,2,2};
    // 퍼지는 모래 비율
    static int[] spreadsand = {1,1,2,7,7,2,10,10,5};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = tornado(n/2, n/2); // 초기위치(map의 중앙좌표)
        System.out.println(result);
    }

    static int tornado(int x, int y){
        int totaloutsand = 0;

        int curx = x;
        int cury = y;

        while(true){
            // 1. 토네이도 이동 방향에 따라
            for(int i=0; i<4; i++){
                // 2. 토네이도 이동 칸수에 따라
                for(int move=0; move<dc[i]; move++){
                    // 예시에서의 y위치
                    int nx = curx + dx[i];
                    int ny = cury + dy[i];

                    if(nx < 0 || ny < 0 || nx >= n || ny >= n)
                        return totaloutsand;

                    // y위치의 모래양
                    int sand = map[nx][ny];
                    map[nx][ny] = 0;
                    int spreadtotal = 0;

                    for(int j=0; j<9; j++){
                        int sandx = nx + dsx[i][j];
                        int sandy = ny + dsy[i][j];

                        int spread = (sand * spreadsand[j]) / 100;

                        if(sandx < 0 || sandy < 0 || sandx >=n || sandy >= n)
                            totaloutsand += spread;
                        else
                            map[sandx][sandy] += spread;

                        spreadtotal += spread;
                    }

                    int alpha = sand - spreadtotal;
                    int alphax = nx + dx[i];
                    int alphay = ny + dy[i];

                    if(alphax < 0 || alphay < 0 || alphax >= n || alphay >=n)
                        totaloutsand += alpha;
                    else
                        map[alphax][alphay] += alpha;

                    curx = nx;
                    cury = ny;
                }
            }

            for(int k=0; k<4; k++)
                dc[k] += 2;
        }
    }
}
