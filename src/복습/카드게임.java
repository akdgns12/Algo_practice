package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 카드게임 {
    static int[] cards;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            cards = new int[n+1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                cards[i] = Integer.parseInt(st.nextToken());
            }

            dp = new int[n+1][n+1];
            recursion(0, n - 1, true);
        }
    }

    // True : 근우 차례, False : 명우 차례
    static int recursion(int left, int right, boolean flag) {
        // 게임이 종료됨을 뜻함 [ 0(left) 1 2 ... n-2 n-1(right)]
        if(left > right) return 0;
        // 값이 0이 아니라면 이미 설정한 값이니 가지치기
        if(dp[left][right] != 0) return dp[left][right];

        if(flag) { // 근우가 고를때는, 왼쪽 카드와 오른쪽 카드 중 최대값을 고를 수 있도록
            return dp[left][right] = Math.max(cards[left] + recursion(left + 1, right, false),
                    cards[right] + recursion(left, right - 1, false));
        }else{ // 명우가 고를땐, 왼쪽 카드와 오른쪽 카드 중 최소값을 고를 수 있도록
            return dp[left][right] = Math.min(recursion(left + 1, right, true), recursion(left, right-1, true));
        }
    }
}
