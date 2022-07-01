package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2477 {
    // 참외밭 / 실버2 / 구현
    /*
        1제곱미터에 자라는 참외의 개수와
        육각형의 임의의 한 꼭짓점에서 출발해서 반시계방향으로 둘레를 돌면서
        지나는 변의 방향과 길이가 순서대로 주어짐.
        이 참외밭에서 자라는 참외의 수를 구하라
     */
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 1제곱미터에 자라는 참외의 개수
        k = Integer.parseInt(br.readLine());
        int[] arr = new int[6];
        int maxX = 0, maxY = 0; // 최대 세로길이 최대 가로길이
        int idxX =0, idxY = 0;

        // 육각형의 전체넓이를 구한뒤 k 곱하면 됨
        // 1 동, 2 서, 3 남, 4 북
        for (int i = 0; i < 6; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());

            arr[i] = len;
        }

        /*
            어차피 가장 넓은 부분의 사각형의 가로 세로 길이는
            연속적으로 입력받아지기 때문에
            가장 큰 넓이 값이 전체 사각형의 넓이
         */
        int big = 0;
        int small = 0;
        int idx = -1;
        for (int i = 0; i < 6; i++) {
            int tmp = arr[i] * arr[(i + 1) % 6];

            if (big < tmp) {
                big = tmp;
                idx = i;
            }
        }

        // 반시계방향으로 둘레를 쭉 적어 넣어왔으므로 3번째 4번째 인덱스 길이가 제외부분
        small = arr[(idx + 3) % 6] * arr[(idx + 4) % 6];
        System.out.println(k * (big - small));
    }
}
