package 백준.company.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2156 {
    // 포도주 시식 / 실버 1 / DP
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] wine = new int[n+1];
        int[] dp = new int[n+1];

        for(int i=1; i<=n; i++)
            wine[i] = Integer.parseInt(br.readLine());

        dp[0] = 0; // 1부터 입력받았으니 0번째 인덱스는 0으로 초기화
        dp[1] = wine[1]; // 첫잔일 경우

        if(n >= 2)
            dp[2] = wine[1] + wine[2];

        /*
            와인잔을 선택하는 경우는 크게 i번째 와인잔을 선택할 것이냐 아니냐로 나눌 수 있다
            i번째 와인잔을 선택하는 경우 두 칸 이전의 와인잔을 선택하는 경우와
            세칸 이전의 와인잔, 한칸 이전의 와인잔을 선택하는 경우로 나눌 수 있다.
            총 3가지의 경우 중 합이 가장 큰 경우를 찾아 dp에 저장한다.
         */
        /*
            dp[i] : i번째 마셨을 떄, 최대로 마실 수 있는 포도잔의 양
            wine[i] : 포도주 i번째 잔에 들어있는 포도주의 양
            포도주는 세번 연속해서 마실 수 없으므로, i번 째(마지막)포도주는 3가지 경우로 나눌 수 있다.
            마시지 않는 경우, 1잔째 마신 경우, 2잔 째 마신 경우
            우리가 구하려는 dp[n]을 구해보자.
            1) i번째 포도주 잔을 마시지 않는 경우
            - dp[i] = dp[i-1]
            2) i번 째(마지막)포도주가 1잔 째인 경우
            - i번째 잔은 마시고 i-1번째 잔은 안마신 것이므로
            - dp[i] = dp[i-1] + wine[i]
            3) 마지막 i번째 포도주 잔이 2잔째인 경우
            - i,i-1번째 잔은 마시고, i-2번째 잔은 안마신 것이므로
            - dp[i] = dp[i-3] + wine[i-1] + wine[i]
         */
        for(int i=3; i<=n; i++)
            dp[i] = Math.max(Math.max(dp[i-1], dp[i-2] + wine[i]), dp[i-3] + wine[i-1] + wine[i]);

        System.out.println(dp[n]);
    }
}
