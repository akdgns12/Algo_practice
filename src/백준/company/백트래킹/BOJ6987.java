package 백준.company.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ6987 {
    // 월드컵 / 골드 5 / 완탐, 백트래킹
    /*
        총 6개국으로 구성된 각 나라마다 승,무,패 이런식으로 3가지
        입력으로 주어진 한줄에 각국의 승무패 순서대로 주어지면 그 결과들이 가능한 결과인지 아닌지 판별
        생각
        1. 가능한 매치들을 전부 살펴봐야 함 : A-B,C,D,E,F / B-C,D,E,F / C-D,E,F / D-E,F / E-F
        2. 매치들을 돌며 승-패/무-무/패-승의 경우를 살펴봄
        3. 재귀가 15회(총 경기 횟수)돈 경우 : 유효한 경기결과!
     */
    static int[] home = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
    static int[] away = {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};
    static int[][] score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        score = new int[6][3];

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            int total = 0;
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    score[j][k] = Integer.parseInt(st.nextToken());
                    total += score[j][k];
                }
            }

            if(total > 30){
                System.out.print(0 + " ");
                continue;
            }

            if(dfs(0)) System.out.print(1 + " ");
            else System.out.print(0 + " ");
        }
    }

    // 백트래킹
    static boolean dfs(int game) {
        if(game == 15){
            return true;
        }

        // 홈 팀이 이기는 경우
        if(score[home[game]][0] > 0 && score[away[game]][2] > 0){
            score[home[game]][0]--;
            score[away[game]][2]--;
            if(dfs(game + 1)) return true;
            score[home[game]][0]++;
            score[home[game]][2]++;
        }

        // 어웨이 팀이 이기는 경우
        if(score[home[game]][2] > 0 && score[away[game]][0] > 0){
            score[home[game]][2]--;
            score[away[game]][0]--;
            if(dfs(game + 1)) return true;
            score[home[game]][2]++;
            score[away[game]][0]++;
        }

        // 비기는 경우
        if(score[home[game]][1] > 0 && score[away[game]][1] > 0){
            score[home[game]][1]--;
            score[away[game]][1]--;
            if(dfs(game + 1)) return true;
            score[home[game]][1]++;
            score[away[game]][1]++;
        }

        return false;
    }
}
