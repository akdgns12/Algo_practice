package 쩜튜브;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 드래곤커브 {
    static int n;
    // x좌표 증가, y좌표 감소, x좌표 감소, y좌표 증가 순서
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] map = new boolean[101][101]; // 격자판의 크기가 100 x 100

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()); // 시작 방향
            int g = Integer.parseInt(st.nextToken()); // 드래곤 세대

            dragoncurve(x,y,d,g);
        }

        int result = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                // 정사각형의 네꼭짓점이 모두 드래곤 커브인 경우 count
                if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1])
                    result++;
            }
        }
        System.out.println(result);
    }

    /**
     *  0세대 - 0
     *  1세대 - 0 1
     *  2세대 - 0 1 2 1
     *  3세대 - 0 1 2 1 2 3 2 1
     *
     *  i세대 대 이동방향은 i-1세대 이동 방향을 역으로 보면서 +1을 해주는 형식
     */
    static void dragoncurve(int x, int y, int dir, int generation) {
        ArrayList<Integer> direction = new ArrayList<>(); // 방향을 저장할 연결리스트
        direction.add(dir); // 현재 방향 저장

        /*
            연결리스트에 저장된 방향을 꺼내 다음 방향을 계산하여 연결리스트에 저장한다.
            이러한 과정을 입력받은 세대만큼 반복한다.
         */
        for (int i = 0; i < generation; i++) { // 해당 드래곤 커브의 세대만큼 반복
            for (int j = direction.size() - 1; j >= 0; j--) { //
                direction.add((direction.get(j) + 1) % 4);
            }
        }

        /*
            해당 문제는 x,y좌표의 형태로 주어지기 때문에
            배열로 생각하기 위해서는 y와 x의 위치를 바꿔줘야 한다.
         */
        map[y][x] = true;

        /*
            연결리스트에 저장된 방향들을 꺼내 시작 좌표로부터
            해당 방향으로 이동한 좌표의 배열값을 바꿔준다.
         */
        for (Integer d : direction) {
            x += dx[d];
            y += dy[d];

            map[y][x] = true; // 드래곤 커버 표시
        }
    }
}
