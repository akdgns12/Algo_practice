package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1783 {
    /*
        1. 2칸 위로, 1칸 오른쪽
        2. 1칸 위로, 2칸 오른쪽
        3. 1칸 아래로, 2칸 오른쪽
        4. 2칸 아래로, 1칸 오른쪽
     */
    static int n,m;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        if (n == 1) {
            // n이 1이면 이동불가(시작지점만 포함)
            result = 1;
        } else if (n == 2) {
            // n이 2일땐, 2,3번 방향으로 움직일 수 있음
            // 절대 4방향 다 움직일 수 없으므로 최댓값은 4
            result = Math.min((m + 1) / 2, 4);
        } else if (n >= 3) {
            // m=7부터 4방향 모두 이동 가능
            // 4방향 다 이동한 후에는 y값이 1씩 증가하는 1번, 4번 이동을 반복
            // 즉, m-2개의 칸을 갈 수 있음
            if (m < 7) {
                result = Math.min(m, 4);
            } else {
                result = m - 2;
            }
        }

        System.out.println(result);
    }
}
