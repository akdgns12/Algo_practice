package 코테대비.배열;

import java.util.Scanner;

public class 봉우리 {
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static int solution(int n,int[][] arr){
        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean flag = true;
                for(int k=0; k<4; k++){
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx >= 0 && ny >= 0 && nx < n && ny < n && arr[i][j] <= arr[nx][ny]) {
                        flag = false;
                        break;
                    }
                }
                if(flag) answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        System.out.println(solution(n,arr));
    }


}
