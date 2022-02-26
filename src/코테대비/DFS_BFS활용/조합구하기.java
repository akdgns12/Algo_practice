package 코테대비.DFS_BFS활용;

import java.util.Scanner;

public class 조합구하기 { // 1~N까지 적힌 번호의 구슬이 있다. 이 중 M개를 뽑는 방법의 수를 구하라
    static int n, m;
    static int[] combi;

    public static void dfs(int depth, int start){
        if(depth == m){
            for (int x : combi) System.out.print(x + " ");
            System.out.println();
        }
        else{
            for (int i = start; i <= n; i++) {
                combi[depth] = i;
                dfs(depth+1, i+1);
            }
        }
    }

    public static void main(String[] args){
        조합구하기 T  = new 조합구하기();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        combi = new int[m];
        T.dfs(0,1);
    }
}
