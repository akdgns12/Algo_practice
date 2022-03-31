package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 드래곤커브_복 {
    static int n;
    static boolean[][] map = new boolean[101][101];
    static int[] dx = {1, 0, -1, 0}; // x증가, y감소, x감소, y증가
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            dragounCurve(x,y,d,g);
        }

        // 네 꼭짓점이 드래곤 커브인 정사각형 카운팅
        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]){
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    static void dragounCurve(int x, int y, int dir, int generation) {
        ArrayList<Integer> direction = new ArrayList<>();
        direction.add(dir);

        for (int i = 1; i <= generation; i++) {
            for (int j = direction.size() - 1; j >= 0; j--) {
                direction.add((direction.get(j) + 1) % 4);
            }
        }

        map[y][x] = true;

        for (Integer d : direction) {
            x += dx[d];
            y += dy[d];

            map[y][x] = true;
        }
    }
}
