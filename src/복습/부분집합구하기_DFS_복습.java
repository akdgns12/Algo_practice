package 복습;

import java.util.Scanner;

public class 부분집합구하기_DFS_복습 {
    static int number;
    static boolean[] visited;

    static void dfs(int depth){
        if(depth == number+1){
            for (int i = 1; i <= number; i++) {
                if(visited[i]) System.out.print(i+ " ");
            }
            System.out.println();
            return;
        }else{
            visited[depth] = true;
            dfs(depth+1);
            visited[depth] = false;
            dfs(depth+1);
        }
    }

    public static void main(String[] args) {
        부분집합구하기_DFS_복습 T = new 부분집합구하기_DFS_복습();
        Scanner sc = new Scanner(System.in);
        number = sc.nextInt();
        visited = new boolean[number+1];
        T.dfs(1);
    }
}
