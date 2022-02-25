package 코테대비.DFS_BFS활용;

import java.util.Scanner;

public class 조합수_메모제이션 { // nCr 구하기
    static int[][] dy = new int[35][35];

    public static int dfs(int n, int r){
        if(dy[n][r] > 0) return dy[n][r]; //미리 구한값은 기록되어있는 값을 바로 return할 수 있도록 메모제이션
        if(n == r || r == 0) return 1;
        else return dy[n][r] = dfs(n-1, r-1) + dfs(n-1, r);
    }

    public static void main(String[] args) {
        조합수_메모제이션 T = new 조합수_메모제이션();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int r = sc.nextInt();
        System.out.println(T.dfs(n, r));
    }
}
