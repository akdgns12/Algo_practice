package 코테대비.배열;

import java.util.Scanner;

public class 멘토링 {
    public static int solution(int n, int m, int[][] arr){
        int answer = 0;
        for (int i = 1; i <= n; i++) { // i 학생
            for (int j = 1; j <= n; j++) { // j 학생
                int cnt = 0;
                for (int k = 0; k < m; k++) { // test
                    int pi = 0;
                    int pj = 0;
                    for (int s = 0; s < n; s++) { // 등수
                        if (arr[k][s] == i)
                            pi = s;
                        if(arr[k][s] == j)
                            pj = s;
                    }
                    if(pi < pj)
                        cnt++;
                }
                if(cnt == m)
                    answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        System.out.println(solution(n, m, arr));
    }


}
