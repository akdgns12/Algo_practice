package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15685_드래곤커브 {
    static int N, x, y, d, g;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0}; // 우 상 좌 하
    static int[] dy = {0, -1, 0, 1};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine()); // 드래곤 커브의 개수

        map = new int[101][101];
        /*
            드래곤 커브를 모두 그리고
            1x1 정사각형의 네 꼭짓점이 드래곤 커브의 일부인 것의 개수 출력
         */
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken()); // 방향
            g = Integer.parseInt(st.nextToken()); // 세대

            curve(x, y, d, g);
        }

        int ans = check();

        sb.append(ans);
        System.out.println(sb.toString());
    }

    static int check(){
        int cnt = 0;
        for(int i=0; i<100; i++){
            for(int j=0; j<100; j++){
                if(map[i][j] == 1 && map[i][j+1] == 1 && map[i+1][j] == 1
                && map[i+1][j+1] == 1){
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static void curve(int x, int y, int d, int g){
        List<Integer> direction = new ArrayList<>();
        direction.add(d);

        for(int i=1; i<=g; i++){ // 1세대부터 시작
            for(int j=direction.size() - 1; j>=0; j--){
                direction.add((direction.get(j) + 1) % 4);
            }
        }

        map[y][x] = 1;

        for(int dir : direction){
            x += dx[dir];
            y += dy[dir];

            map[y][x] = 1;
        }
    }
}
