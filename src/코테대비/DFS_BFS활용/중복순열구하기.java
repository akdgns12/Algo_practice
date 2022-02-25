package 코테대비.DFS_BFS활용;

import java.util.Scanner;

/*
    1~N까지 번호가 적힌 구슬이 있다. 이 중 중복을 허락하여 M번을 뽑아 일렬로 나열하는 방법 모두 출력
 */
public class 중복순열구하기 {
    static int[] pm;
    static int n, m;

    public static void dfs(int depth){
        if(depth == m){
            for (int i = 0; i < m; i++) {
                System.out.print(pm[i] + " ");
            }
            System.out.println();
        }
        else{
            for (int i = 1; i <= n; i++) {
                pm[depth] = i;
                dfs(depth+1);
            }
        }
    }

    public static void main(String[] args) {
        중복순열구하기 T = new 중복순열구하기();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        pm = new int[m];
        T.dfs(0);
    }
}
