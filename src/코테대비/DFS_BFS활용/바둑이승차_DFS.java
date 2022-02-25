package 코테대비.DFS_BFS활용;

import java.util.Scanner;

public class 바둑이승차_DFS {
    static int c, n;
    static int max = Integer.MIN_VALUE;
    
    public static void dfs(int depth, int sum, int[] arr) {
        if(sum > c) return;
        if(depth == n){
            max = Math.max(max, sum);
            return;
        }
        else{
            dfs(depth+1, sum+arr[depth], arr); //포함한다
            dfs(depth+1, sum, arr); //포함하지 않는다
        }
    }

    public static void main(String[] args){
        바둑이승차_DFS T = new 바둑이승차_DFS();
        Scanner sc = new Scanner(System.in);
        c = sc.nextInt();
        n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        T.dfs(0, 0, arr);
        System.out.println(max);
    }
}
