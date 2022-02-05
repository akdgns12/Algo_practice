package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 마법사상어와토네이도_복습 {
    static int n;
    static int[][] map;
    // 토네이도의 이동방향 좌-하-우-상
    static int[] dx = {0,-1,0,1};
    static int[] dy = {-1,0,1,0};
    // 토네이도 이동에 따른 모래가 퍼지는 방향
    static int[][] dsx= {{-1,1,-2,-1,1,2,-1,1,0}, // 기준인 y의좌표의 '모래'에서 퍼진다
            {-1,-1,0,0,0,0,1,1,2},
            {1,-1,2,1,-1,-2,1,-1,0},
            {1,1,0,0,0,0,-1,-1,-2}};
    static int[][] dsy= {{1,1,0,0,0,0,-1,-1,-2},
            {-1,1,-2,-1,1,2,-1,1,0},
            {-1,-1,0,0,0,0,1,1,2},
            {1,-1,2,1,-1,-2,1,-1,0}};
    // 토네이도가 이동할 때 칸 수, 한 턴이 끝날 때 마다 +2씩 증가
    static int[] dc = {1,1,2,2};
    static int[] sandpercent = {1,1,2,7,7,2,10,10,5};

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

        int result = tornado(n/2,n/2); // 초기 시작위치 격자판 중앙좌표
        System.out.println(result);
    }

    static int tornado(int x, int y){
        int totaloutsand = 0;

        int curx = x;
        int cury = y;

        while(true){
            // 토네이도 이동방향 좌-하-우-상 순서로
            for(int i=0; i<4; i++){
                for(int move=0; move<dc[i]; move++){
                    // 이동한 위치(예시에서의 y)
                    int nx = curx + dx[i];
                    int ny = cury + dy[i];

                    // 토네이도가 범위 밖으로 나가면 종료
                    if(nx < 0 || ny < 0 || nx >= n || ny >= n)
                        return totaloutsand;

                    // y에 있는 모래의 양
                    int sand = map[nx][ny];
                    map[nx][ny] = 0; // y에 있는 모든 모래 이동하기 때문에 0으로 초기화
                    int spreadtotal = 0;

                    for(int j=0; j<9; j++){
                        int sandx = nx + dsx[i][j];
                        int sandy = ny + dsy[i][j];

                        int spread = (sand * sandpercent[j]) / 100;

                        // 퍼진 모래가 범위밖으로 벗어나면 결과값에 추가
                        if(sandx < 0 || sandy < 0 || sandx >= n || sandy >= n)
                            totaloutsand += spread;
                        else
                            map[sandx][sandy] += spread;

                        // 이동한 모래 중 이동한 모래의 양 구하기 -> 나머지 a칸으로 이동
                        spreadtotal += spread;
                    }

                    // 예시에서의 a칸
                    // y의 모래 - 이동한 모래
                    int alpha = sand - spreadtotal;
                    // a는 y에서 토네이도 이동방향으로 한 칸 이동한 곳
                    int alphax = nx + dx[i];
                    int alphay = ny + dy[i];

                    // 범위 벗어나면 결과값에 추가
                    if(alphax < 0 || alphay < 0 || alphax >= n || alphay >= n)
                        totaloutsand += alpha;
                    else
                        map[alphax][alphay] += alpha;

                    // 현재위치 갱신
                    curx = nx;
                    cury = ny;
                }
            }

            // 토네이도가 4방향으로 이동한 후(한 턴이 끝나고 난 후)
            // 다음 턴엔 +2씩 증가된 값으로 이동
            for(int k=0; k<4; k++)
                dc[k] += 2;
        }
    }
}
