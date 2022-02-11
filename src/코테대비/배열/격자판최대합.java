package 코테대비.배열;

import java.util.Scanner;

public class 격자판최대합 {
    public static int solution(int n, int[][] map){
        int answer = Integer.MIN_VALUE;
        int sum1, sum2;

        for (int i = 0; i < n; i++) {
            sum1 = sum2 = 0;
            for (int j = 0; j < n; j++) {
                sum1 += map[i][j]; // 각 행의 합
                sum2 += map[j][i]; // 각 열의 합
            }
            answer = Math.max(answer, sum1);
            answer = Math.max(answer, sum2);
        }
        sum1=sum2=0;
        for (int i = 0; i < n; i++) {
            sum1 += map[i][i];
            sum2 += map[i][n-i-1];
        }
        answer = Math.max(answer, sum1);
        answer = Math.max(answer, sum2);


        return answer;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        System.out.println(solution(n, map));
    }
}
