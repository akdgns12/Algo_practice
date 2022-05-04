package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 사탕게임_완탐 {
    static int n;
    static char[][] map;
    static int[] dx = {1,0}; // 아래쪽, 오른쪽
    static int[] dy = {0,1};
    static int result = 0;
    /*
        위에서부터 오른쪽으로 swap 처음위치를 바꾸기 때문에 nx,ny는 항상 아래와 오른쪽만 보면 된다.
        N 범위에 들어오고 바꾸려는 요소가 이전과 같지 않다면 교환하고, 이 상태에서 가장 많이 사탕을 먹을 수 있는 개수가 몇개인지 센다.
        swap한 요소를 제자리로 돌려놓는다.
        사탕개수를 체크할 때, 같은 열, 같은 행만 체크하면 되기 때문에 한 번에 썼다. [i][j] 위치만 바꿔주면 된다.
        비교하다가 다른 요소가 나오면 cnt를 1로 하고 다시 비교를 시작한다.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.max(result, swap(i, j));
            }
        }

        System.out.println(result);
    }

    static int swap(int i, int j) {
        int max = 0;

        for (int d = 0; d < 2; d++) {
            int nx = i + dx[d];
            int ny = j + dy[d];
            if (nx >= 0 && ny >= 0 && nx < n && ny < n && map[i][j] != map[nx][ny]) { // 범위 안이고 인접한 문자가 다른 문자라면
                // 서로의 값 바꿔주고
                char temp = map[i][j];
                map[i][j] = map[nx][ny];
                map[nx][ny] = temp;

                max = Math.max(max, countCandy());

                // 원상복귀
                map[nx][ny] = map[i][j];
                map[i][j] = temp;
            }
        }

        return max;
    }

    // 사탕개수 체크(모두 같은색으로 이뤄져 있는 가장 긴 연속 부분(행 또는 열))
    static int countCandy() {
        int tmpMax = 1;
        for (int i = 0; i < n; i++) {
            char nowH = map[i][0]; // 행
            char nowV = map[0][i]; // 열
            int cntH = 1;
            int cntV = 1;

            for (int j = 1; j < n; j++) {
                if (nowH != map[i][j]) { // 행 검사, 비교하다가 다른요소가 나오면 cnt를 1로 하고 다시 비교할 수 있도록
                    tmpMax = Math.max(tmpMax, cntH);
                    nowH = map[i][j];
                    cntH = 1;
                }
                else cntH++; // 같다면 카운팅 증가

                if (nowV != map[j][i]) { // 열 검사
                    tmpMax = Math.max(tmpMax, cntV);
                    nowV = map[j][i];
                    cntV = 1;
                } else cntV++;
            }
            tmpMax = Math.max(tmpMax, cntH > cntV ? cntH : cntV);
        }

        return tmpMax;
    }
}
