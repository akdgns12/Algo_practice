package 백준.company.시뮬레이션;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20057 {
    static int n;
    static int[][] map;
    // 토네이도가 움직이는 순서인 좌-하-우-상 순으로 모래가 퍼지는 방향
    static int[][] dsx= {{-1,1,-2,-1,1,2,-1,1,0}, // 기준인 y의좌표의 '모래'에서 퍼진다
            {-1,-1,0,0,0,0,1,1,2},
            {1,-1,2,1,-1,-2,1,-1,0},
            {1,1,0,0,0,0,-1,-1,-2}};
    static int[][] dsy= {{1,1,0,0,0,0,-1,-1,-2},
            {-1,1,-2,-1,1,2,-1,1,0},
            {-1,-1,0,0,0,0,1,1,2},
            {1,-1,2,1,-1,-2,1,-1,0}};
    static int[] sandpercent = {1,1,2,7,7,2,10,10,5};
    static int[] dx = {0,1,0,-1}; // 토네이도의 이동방향 순서 좌-하-우-상
    static int[] dy = {-1,0,1,0};
    // 토네이도가 좌-하-우-상 방향으로 이동하는 횟수, 한 턴이 끝날때마다 +2씩 증가
    static int[] dc = {1,1,2,2};

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

        int result = tornado(n/2, n/2);
        System.out.println(result);
    }

    /*
        격자의 가운데부터 토이네도의 이동 시작
        n/2, n/2 좌표가 초기 시작 좌표
        1.
     */
    static int tornado(int x, int y){
        int totaloutsand = 0;

        int curx = x;
        int cury = y;

        while(true){
            // 좌-하-우-상 순으로 토네이도 이동
            for(int i=0; i<4; i++){
                // 각 방향으로 이동하는 횟수만큼 이동
                for(int move=0; move<dc[i]; move++){
                    // 이동한 위치(예시에서의 y를 의미)
                    int nx = curx + dx[i];
                    int ny = cury + dy[i];
                    /*
                        토네이도가 범위를 벗어나는 경우는 소멸한 경우
                        이때는 격자 밖으로 나간 모래의 양 return
                     */
                    if(nx < 0 || ny < 0 || nx >= n || ny >= n )
                        return totaloutsand;

                    // 예시에서의 y
                    int sand = map[nx][ny]; // y에 있는 모래의 양
                    map[nx][ny] = 0; // y에 있는 모든 모래 이동하기 때문에 0으로 초기화
                    int spreadtotal = 0;

                    // 해당 방향에서 비율이 적혀있는 칸으로 해당 비율만큼 모래 이동
                    for(int j=0; j<9; j++){
                        /*
                            i는 좌-하-우-상 중 해당하는 방향 의미
                            j는 y를 기준으로 설정한 각 비율이 있는 위치를 의미
                         */
                        int sandx = nx + dsx[i][j];
                        int sandy = ny + dsy[i][j];
                        // y에 있었던 모래에서 각 칸에 적혀있는 비율만큼 이동
                        int spread = (sand * sandpercent[j]) / 100;

                        // 범위를 넘어가면 격자 밖으로 나간 모래이기 때문에 누적
                        if(sandx < 0 || sandy < 0 || sandx >= n || sandy >= n)
                            totaloutsand += spread;
                        else
                            map[sandx][sandy] += spread;

                        // 이동한 모래중 이동한 모래의 양 구하기 -> 나머지 a칸으로 이동
                        spreadtotal += spread;
                    }

                    // 예시에서의 a칸
                    // a은 y에서의 좌-하-우-상 중 해당하는 방향으로 한 칸 이동한 곳
                    int alphax = nx + dx[i];
                    int alphay = ny + dy[i];
                    // a로 이동하는 모래의 양은 비율이 적혀있는 칸으로 이동하지 않은 남아있는 모래의 양
                    int alpha = sand - spreadtotal;

                    // 범위를 넘어가면 격자 밖으로 나간 모래이기 때문에 누적
                    if(alphax < 0 || alphay < 0 || alphax >= n || alphay >= n)
                        totaloutsand += alpha;
                    // 범위를 넘어가지 않았다면 a칸에 모래 누적
                    else
                        map[alphax][alphay] += alpha;

                    // 현재위치 갱신
                    curx = nx;
                    cury = ny;
                }
            }

            // 토네이도가 이동하는 횟수는 한 턴이 끝날때마다 +2씩 증가
            for(int k=0; k<4; k++)
                dc[k] += 2;
        }
    }
}
