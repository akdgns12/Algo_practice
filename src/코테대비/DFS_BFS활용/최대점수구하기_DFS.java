package 코테대비.DFS_BFS활용;

import java.util.Scanner;

//부분집합에 가져온다(푼다), 안가져온다(안푼다)

public class 최대점수구하기_DFS {
    static int n, m;
    static int max = Integer.MIN_VALUE;

    public static void dfs(int depth, int sum, int time, int[] ps, int[] pt){
        if(time > m) return;
        if(depth == n){
            max = Math.max(max, sum);
            return;
        }
        else{
            dfs(depth + 1, sum + ps[depth], time + pt[depth], ps, pt); //문제 푼다
            dfs(depth + 1, sum, time, ps, pt); //문제 안푼다
        }
    }

    public static void main(String[] args) {
        최대점수구하기_DFS T = new 최대점수구하기_DFS();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt(); //문제 점수
            b[i] = sc.nextInt(); //문제 푸는데 걸린 시간
        }
        T.dfs(0,0,0,a,b);
        System.out.println(max);
    }
}
