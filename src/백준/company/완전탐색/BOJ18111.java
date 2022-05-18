package 백준.company.완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18111 {
    // 마인크래프트 / 실버 2 / 완탐
    /*
        마인크래프트는 1,1,1 크기의 블록들로 이루어진 3차원
        세로 n, 가로 m 크기의 집터
        땅을 고르는데 걸리는 시간과 땅의 높이 출력, 여러개 있다면 그중에서 땅의 높이가 가장 높은 것 출력
     */
    // 블록을 전체적으로 평평하게 만드는 문제인데, 인벤에서 블록을 가져와 채울것인지, 현재 블록을 깎고 인벤에
    // 넣은건지 판단해야 함. 입력으로 주어지는 맵의 최댓값 - 최솟값이 맞춰야할 높이
    static int n, m, b;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    static int height = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken()); // 작업을 시작할 때 인벤토리에 들어있는 블록의 수

        map = new int[n][m];
        int max = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) { // 땅의 높이정보
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
                min = Math.min(min, map[i][j]);
            }
        }

        // map의 최댓갑에서 최솟값까지 반복하면서
        for (int i = max; i >= min; i--) {
            int time = 0;
            int inven = b;

            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m; y++) {
                    if (map[x][y] == i) {
                        continue;
                    } else {
                        int diff = Math.abs(i - map[x][y]);
                        if(i > map[x][y]){ // 2번작업, 인벤에서 하나 꺼내 블록에 채워넣음
                            inven -= diff;
                            time += diff;
                        }else { // 1번작업, 블록을 제거하여 인벤에 넣음(2초)
                            inven += diff;
                            time += (2 * diff);
                        }
                    }
                }
            }
            if(inven < 0){
                continue;
            }

            if(time < answer){
                answer = time;
                height = i;
            }
        }

        System.out.println(answer + " " + height);


    }
}
