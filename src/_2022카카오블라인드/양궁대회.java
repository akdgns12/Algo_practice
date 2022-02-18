package _2022카카오블라인드;

import java.util.ArrayList;

public class 양궁대회 {
    static int max;
    static int[] answer;

    public static int[] solution(int n, int[] info){
        answer = new int[11];
        int[] result = new int[n];
        dfs(0, n, 0, result, info);
        // max == 0 이면 한번도 라이언이 이긴적이 없음
        return max == 0 ? new int[]{-1} : answer;
    }

    static void dfs(int start, int r, int idx, int[] result, int[] info){
        if(idx == r){
            int[] info2 = new int[11];
            // 과녁 점수를 어피치와 같이 과녁점수 맞춘 갯수로 표현
            for (int score : result) {
                info2[10 - score]++;    // 10점부터 인덱스는 0으로 시작하니까
            }

            int ryan = 0;
            int apeach = 0;

            for (int i = 0; i < 11; i++) {
                //info[0] = 10점, info[1]=9점 맞추기 위해 score계산
                int score = 10 - i;
                //라이언이 이긴 경우
                if (info2[i] > info[i]) {
                    ryan += score;
                }
                //어피치가 이긴 경우
                else if(info2[i] < info[i]){
                    apeach += score;
                }
                //과녁에 맞추고 어피치와 라이언이 같은 갯수를 쏜 경우
                else if (info[i] != 0 && info[i] == info2[i]) {
                    apeach += score;
                }
            }

            if (ryan - apeach > max){
                max = ryan - apeach;
                answer = info2;
            }
            return;
        }

        for (int i = start; i < 11; i++) {
            // 라이언이 쏜 과녁 점수 저장
            result[idx] = i;
            dfs(i, r, idx + 1, result, info);
        }
    }

    /*
        n발의 화살을 0에서 10점 사이의 값으로 정해야 함, 값을 중복될 수 있고 순서는
        고려하지 않아도 됨 -> 중복 조합 -> 백트래킹으로 해야함!
        n이 1부터 10까지고 점수는 0~10점까지 중복조합이므로 경우의 수는 11H10 = 184756개
        탐색가능한 범위 -> 완전탐색
        과녁을 맞힌 점수를 중복조합으로 구한다. 구한 점수를 info에 맞게 변경한 후에
        라이언과 어피치의 점수를 조건에 따라 구한다. 최대점수가 0인 경우 라이언은
        이긴적이 없는 경우고 0이 아니면 라이언이 이길 수 있는 방법이 있기 때문에
        최대 값을 찾아 배열을 출력
     */

    public static void main(String[] args) {
        int n = 5;// 화살의 개수
        // 어피치가 맞힌 과녁 점수ㄴ의 개수 10점부터 0점까지 순서대로 담은 배열
        int[] info = {2,1,1,1,0,0,0,0,0,0,0};

        for (int x : solution(n, info)) System.out.print(x + " ");
    }
}
