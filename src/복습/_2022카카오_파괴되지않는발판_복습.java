package 복습;

public class _2022카카오_파괴되지않는발판_복습 {
    public static int solution(int[][] board, int[][] skill){
        int answer = 0;
        int n = board.length;
        int m = board[0].length;
        // 변화량 테이블
        int[][] d = new int[n+1][m+1];

        for (int i = 0; i < skill.length; i++) {
            int type = skill[i][0], x1 = skill[i][1], y1 = skill[i][2], x2 = skill[i][3], y2 = skill[i][4], degree = skill[i][5];
            if (type == 1) degree = -degree;
            d[x1][y1] += degree;
            d[x2+1][y1] -= degree;
            d[x1][y2+1] -= degree;
            d[x2+1][y2+1] += degree;
        }

        //누적합 구하기
        //오른쪽 합하기
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                d[i][j] += d[i-1][j];
            }
        }

        //아래로 합하기
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                d[i][j] += d[i][j-1];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(d[i][j] + board[i][j] > 0 ) answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] board = {
                {5, 5, 5, 5, 5},
                {5, 5, 5, 5, 5},
                {5, 5, 5, 5, 5},
                {5, 5, 5, 5, 5}
        };
        int[][] skill = {
                {1, 0, 0, 3, 4, 4},
                {1, 2, 0, 2, 3, 2},
                {2, 1, 0, 3, 1, 2},
                {1, 0, 1, 3, 3, 1}
        };

        System.out.println(solution(board, skill));
    }
}
