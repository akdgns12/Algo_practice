package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 드래곤커브 {
    static boolean[][] map = new boolean[101][101];
    // x 증가, y 감소, x 감소, y 증가
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            dragonCurve(x,y,d,g);
        }

        int result = 0;
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1])
                    result++;
            }
        }
        System.out.println(result);
    }

    static void dragonCurve(int x, int y, int dir, int generation) {
        ArrayList<Integer> direction = new ArrayList<>(); // 방향을 저장할 연결리스트
        direction.add(dir); // 현재 방향 저장

        for (int i = 0; i < generation; i++) {
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
