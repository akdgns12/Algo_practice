package 백준.company.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11062 {
    // 카드 게임 / 골드 3 / DP
    /*
        n개의 카드, 각 카드에는 점수가 적혀있다.한 턴에는 가장 왼쪽이나 가장 오른쪽에 있는 카드를 가져갈 수 있음. 카드가 더이상 남아있지 않을때까지
        턴 반복, 게임의 점수는 자신이 가져간 카드에 적힌 수의 합, 근우가 얻는 점수를 리턴 (각자 자신의 점수를 높이기 위해 최선의 전략으로 임한다 = 맨앞과 뒤 중 가장 큰 수를 가져간다 각자가)
        생각 생각
        - 근우는 왼쪽 카드와 오른쪽 카드 중 더 점수가 높은 카드를 뽑아야 하고, 명우는 근우가 왼쪽카드와 오른쪽카드 중 더 낮은 점수를 갖도록 만드는 카드를 뽑아야 한다.
        dp[서로의 차례][남은 카드 중 맨 왼쪽][남은 카드 중 맨 오른쪽]
        으로 배열 정의한 후, 차례가 0일 때는 근우, 차례가 1일때는 명우로 설정하여 위에서 언급한 최선의 전략으로 재귀 함수로 짜면 됨
        여기서 기저사례는 카드의 맨 왼쪽 위치가 맨 오른쪽 위치보다 크거나 같을 때인데, 근우 차례일 경우 해당 카드의 점수를 더하고, 명우 차례일 경우 더하지 않아야 함.
     */
    static int[] cards;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            cards = new int[n+1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                cards[i] = Integer.parseInt(st.nextToken());
            }

            dp = new int[2][n+1][n+1]; // 근우와 명우의 차례, 카드의 맨 왼쪽, 카드의 맨 오른쪽 -> dp 값은 근우가 얻는 점수.
            for (int i = 0; i < 2; i++) {
                for (int j = 1; j <= n; j++) {
                    for (int k = 1; k <= n; k++) {
                        dp[i][j][k] = -1;
                    }
                }
            }

            sb.append(recursion(0, 1, n) + "\n");
        }

        System.out.println(sb.toString());
    }

    static int recursion(int turn, int start, int end) {
        if (dp[turn][start][end] != -1) { // 이미 알고있으면 가지치기
            return dp[turn][start][end];
        }

        // 기저사례
        if (start >= end) {
            if(turn == 0) { // 근우차례면
                return cards[start];
            } else { // 명우 차례면
                return 0;
            }
        }

        dp[turn][start][end] = 0;

        if (turn == 0) { // 근우 차례에 경우는 왼쪽 카드를 뽑았을 떄와 오른쪽 카드를 뽑았을 때 중, 숫자의 합이 크도록 설정.
            dp[turn][start][end] = Math.max(recursion(turn + 1, start + 1, end) + cards[start],
                    recursion(turn + 1, start, end - 1) + cards[end]);
        } else { // 명우 차례에 경우는 근우가 더 작은 카드를 뽑게 해야하므로, 왼쪽 카드를 뽑았을 때와 오른쪽 카드를 뽑았을 때 중, 숫자의 합이 작도록 설정.
            dp[turn][start][end] = Math.min(recursion(turn - 1, start + 1, end), recursion(turn - 1, start, end - 1));
        }

        return dp[turn][start][end];
    }
}
