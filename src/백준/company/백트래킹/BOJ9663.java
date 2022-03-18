package 백준.company.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9663 {
    // n-queen / 골드 5 / 백트래킹
    /*
        퀸이 위협하는 자리는 같은 행, 같은 열, 대각선 위치
        같은 행에는 놓을 수 없기 때문에 1차원 배열(col[row])를 사용. 행(row)에 놓이는 퀸의 열(col) 값을 담는다.
     */
   static int n;
   static int[] col;
   static int answer = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        col = new int[n+1]; // 퀸이 놓이는 열 (size : 행의 수)
        dfs(0);
        System.out.println(answer);
    }

    static void dfs(int row){
        if(!isValid(row)){ // 유효하지 않은 경우, return (백트래킹, 가지치기)
            return;
        }

        if(row == n){ // 모든 행에 퀸을 둔 경우
            answer++;
            return;
        }

        for (int i = 1; i <= n; i++) { // 1열 n열까지
            col[row+1] = i; // row가 0일 때, 1행의 1열에 퀸을 위치하는 경우의 수부터 n열에 위치하는 경우의 수까지,
            dfs(row+1);
        }
    }

    static boolean isValid(int row){
        for (int i = 1; i < row; i++) { // 1행부터 row - 1행까지 (row 이전 행에 이미 퀸을 위치시킨 상태)
            // 같은열에 퀸이 존재하거나 || 대각선(열의 차이의 절댓값 == 행의 차이)에 위치한 경우 false return
            if(col[row] == col[i] || Math.abs(col[row] - col[i]) == row - i) return false;
        }
        return true;
    }
}
