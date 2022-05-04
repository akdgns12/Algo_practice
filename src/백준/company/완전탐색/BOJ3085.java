package 백준.company.완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ3085 {
    // 사탕게임 / 실버3 / 완탐
    // 조건에 맞게 상근이가 먹을 수 있는 사탕의 최대 개수 리턴
    // 모든 경우의 수를 탐색해도 시간은 충분.
    /*
        위에서부터 오른쪽으로 swap 처음위치를 바꾸기 때문에 nx, ny는 항상 아래쪽과 오른쪽만 보면 된다.
     */
    static int n;
    static char[][] map;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new char[n][n];

        // N의 범위가 50까지, 완탐 가능
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                // 양옆 바꾸고
                swapBothSide(i, j);
                // 비교
                compare();
                // 원상복귀
                swapBothSide(i, j);

                // 위아래 바꾸고
                swapUpAndDown(j, i);
                compare();
                swapUpAndDown(j, i);
            }
        }

        System.out.println(result);
    }

    static void swapBothSide(int i, int j) {
        char temp;
        temp = map[i][j];
        map[i][j] = map[i][j+1];
        map[i][j+1] = temp;
    }

    static void swapUpAndDown(int j, int i) {
        char temp;
        temp = map[j][i];
        map[j][i] = map[j+1][i];
        map[j+1][i] = temp;
    }

    static void compare() {
        // 양 옆 비교
        for (int i = 0; i < n; i++) {
            // 한 줄, 한 행마다 초기화 그리고 사탕은 무조건 1개 이상
            int sum = 1;
            for (int j = 0; j < n - 1; j++) {
                // 양옆이 같다면
                if (map[i][j] == map[i][j + 1]) {
                    sum++;
                } else {
                    result = Math.max(sum, result);
                    sum = 1;
                }
            }
            result = Math.max(sum, result);
        }

        for (int i = 0; i < n; i++) {
            int sum = 1;
            for (int j = 0; j < n - 1; j++) {
                if (map[j][i] == map[j + 1][i]) {
                    sum++;
                }else{
                    result = Math.max(sum, result);
                    sum = 1;
                }
            }
            result = Math.max(sum, result);
        }
    }
}
