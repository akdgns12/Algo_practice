package _2022카카오블라인드;

public class 파괴되지않은발판 {
    /*
        board -> 초기 맵의 내구도 상태배열
        skill -> type 1 : 적의 공격으로 내구도 낮추는 행위
                type 2 :회복으로 맵의 내구도 높이는 정보배열
        최종적으로 내구도가 1이상으로 파괴되지 않은 건물의 수를 return
        시간초과가 나지 않기 위해서는 누적합?
        https://blog.encrypted.gg/1031 -> 바킹독 누적합 설명 및 파괴되지 않은 발판 풀이
        간단하게 누적합이 뭔지 보자면
        int[] arr = {0,1,2,3,4,5,6,7,8,9};
//        이렇게 있을 때 구간 a ~ b까지의 합을 구하는 쿼리를 2천만개 요구한다 하면 for문을 통해 매번 구간합을 구해 return하게 된다면 매우 비효율적
//        그래서 구간합을 구해 return 할 수 있도록 하자 -> 구간합을 저장하는 배열 pSum
//        int[] pSum = new int[arr.length];
//        for(int i=1; i<11; i++)
//            psum[i] = psum[i-1] + arr[i]; // i번째까지의 구간 합은 (i-1번째까지의 구간합 + i번째 값(arr[i]))
        이것이 누적합의 기본 개념
        여기서는 발판의 변화량을 각 좌표마다 다 적용시키는게 아닌, 변화의 시작점과 끝점에 변화량 값을 기록해 놓아
        -> 이렇게 기록해놓은 테이블의 오른쪽과 아래쪽으로의 누적합만 계산할 수 있도록 하는 방법을 사용
     */
    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length;
        int[][] d = new int[n+1][m+1];
        // 변화량 테이블에 스킬 기록
        for (int i = 0; i < skill.length; i++) {
            int type = skill[i][0];
            int x1 = skill[i][1];
            int y1 = skill[i][2];
            int x2 = skill[i][3];
            int y2 = skill[i][4];
            int degree = skill[i][5];
            if(type == 1) degree = -degree; // 적의 공격이면 변화량 -로 변환
            d[x1][y1] += degree;
            d[x1][y2+1] -= degree;
            d[x2+1][y1] -= degree;
            d[x2+1][y2+1] += degree;
        }

        //누적합 처리
        // 오른쪽으로 합산
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                d[i][j] += d[i-1][j];
            }
        }
        // 아래로 합산
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                d[i][j] += d[i][j-1];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(d[i][j] + board[i][j] > 0) answer++;
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

        /**
         *  일반적인 브루트포스를 통한 코드 답은 나오지만 시간초과 코드(효율성을 통과하지 못한다)
         */
//    public static int solution(int[][] board, int[][] skill) {
//        int answer = 0;
//        int n = board.length;
//        int m = board[0].length;
//
//        for (int[] tmp : skill) {
//            int sign = tmp[0] == 1 ? -1 : 1;
//            for (int i = tmp[1]; i <= tmp[3]; i++) {
//                for (int j = tmp[2]; j <= tmp[4]; j++) {
//                    board[i][j] += sign * tmp[5];
//                }
//            }
//        }
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                if(board[i][j] > 0) answer++;
//            }
//        }
//
//        return answer;
//    }
//    public static void main(String[] args) {
//        int[][] board = {
//                {5,5,5,5,5},
//                {5,5,5,5,5},
//                {5,5,5,5,5},
//                {5,5,5,5,5}
//        };
//        int[][] skill = {
//                {1,0,0,3,4,4},
//                {1,2,0,2,3,2},
//                {2,1,0,3,1,2},
//                {1,0,1,3,3,1}
//        };
//        System.out.println(solution(board, skill));
//
//    }
//}
