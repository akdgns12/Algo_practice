package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 계단오르기 {
    static int n;
    static int[] dp, step;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        step = new int[n+1];
        // i번째 계단까지 올라섰을 때 밟지 않을 계단의 합의 최솟값, 단 i번째 계단은 반드시 밟지않을 계단으로 선택해야 함
        dp = new int[3000001];
        for (int i = 1; i <= n; i++) {
            step[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = step[1];
        dp[2] = Math.max(dp[1] + step[2], step[2]);

        /*
            n번째 계단을 밟는 경우의 수는 잘생각해보면
            1. n-2번째에서 n으로 오는 경우
            2. n-1번째에서 n으로 오는 경우가 있다.
              2-1) 여기서 n-1, n 이렇게 계단을 밟았다면 문제의 조건인 연속 세 계단을 밟으면 안되기 때문에
                   n-1계단을 밝기전 n-3에서 오는 케이스만 문제의 조건에 부합하기 때문에 같이 고려해줘야 한다
                   따라서
               1. dp[i] = dp[i-2] + step[i]
               2. dp[i] = dp[i-3] + step[i-1] + step[i] 이렇게 2가지 케이스로 점화식을 세워야 한다
         */
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 3] + step[i - 1] + step[i], dp[i - 2] + step[i]);
        }

        System.out.println(dp[n]);
    }
}
