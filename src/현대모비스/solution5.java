package 현대모비스;

public class solution5 {
    static int solution(int[][] arr, int n, int k) {
        int answer = 0;
        int len = arr.length;

        int[][] dp = new int[n][k]; // [분대원 구성 수][물약 사용 개수] : 로 얻을 수 있는 전투력 최대값

        while (n --> 0) {
            for (int i = 0; i < k; i++) {
                int basic = arr[i][0];
                int poten = arr[i][1] * i;

                dp[n][i] = Math.max(dp[n][i], dp[n-1][i-1] + basic + poten);
            }
        }

        answer = dp[n][k];
        return answer;
    }

    public static void main(String[] args) {
        // 2차원배열(기본능력, 잠재능력), 분대원 구성 수, 물약 사용 개수
        int[][] arr = {
                {2,3},
                {0,5},
                {1,7},
                {5,1},
                {8,2},
        };
        int n = 3;
        int k = 5;

        solution5 T = new solution5();
        System.out.println(T.solution(arr, n , k));
    }
}
