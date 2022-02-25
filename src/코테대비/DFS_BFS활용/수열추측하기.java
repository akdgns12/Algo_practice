package 코테대비.DFS_BFS활용;

import java.util.Scanner;

public class 수열추측하기 {
    static int n, f;
    static int[] b, p; //combi값 저장배열, 순열 저장 배열
    static boolean[] visited;
    static boolean flag = false;
    static int[][] dy = new int[35][35];

    public int combi(int n, int r){
        if(dy[n][r] > 0) return dy[n][r];
        if(n == r || r == 0) return 1;
        else return dy[n][r] = combi(n-1, r-1) + combi(n-1, r);
    }

    public void dfs(int depth, int sum) {
        if(flag) return;
        if (depth == n) {
            if(sum == f){
                for (int x : p) System.out.print(x + " ");
                flag = true;
            }
        } else {
            for (int i = 1; i <= n; i++) { // i 자체가 순열을 만드는 숫자
                if (!visited[i]) {
                    visited[i] = true;
                    p[depth] = i;
                    dfs(depth+1, sum+(p[depth]*b[depth]));
                    visited[i] = false;
                }
            }
        }
    }


    public static void main(String[] args) {
        수열추측하기 T = new 수열추측하기();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        f = sc.nextInt();
        b = new int[n];
        p = new int[n];
        visited = new boolean[n+1];
        for (int i = 0; i < n; i++) {
            b[i] = T.combi(n-1, i);
        }
        T.dfs(0,0);
    }
}
