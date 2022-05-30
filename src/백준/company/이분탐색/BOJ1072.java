package 백준.company.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1072 {
    // 게임 / 실버3 / 이분탐색
    /*
       게임횟수 : x
       이긴 게임 : y
       z는 형택의 승률, 소수점 버림.

        최소 몇 번의 게임을 더해 승률인 z가 변하는지 구하라

        절대 변하지 않는다면 -1 출력
     */
    static int x, y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        int z = getPercent(x, y);

        int ans = -1;
        int lt = 0;
        int rt = Integer.MAX_VALUE;

        while (lt <= rt) {
            int mid = (lt + rt) / 2;

            if(getPercent(x + mid, y + mid) != z){
                ans = mid;
                rt = mid - 1;
            }else{
                lt = mid + 1;
            }
        }

        System.out.println(ans);
    }

    static int getPercent(int x, int y) {
       return (int) ((long) y * 100 / x);
    }
}
