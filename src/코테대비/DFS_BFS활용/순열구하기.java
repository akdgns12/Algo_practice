package 코테대비.DFS_BFS활용;

import java.util.Scanner;

public class 순열구하기 {
    static int n, m;
    static int[] pm, arr;
    static boolean[] visited;

    public static void dfs(int depth){
        if(depth == m){ //순열이 하나 완성됨
            for(int x : pm) System.out.print(x + " ");
            System.out.println();
        }else{
            for (int i = 0; i < n; i++) {
                if(!visited[i]){ //i번쨰 인덱스에있는 숫자가 쓰이지 않았을 떄
                    visited[i] = true;
                    pm[depth] = arr[i];
                    dfs(depth+1);
                    visited[i] = false;
                }
            }
        }
    }

    public static void main(String[] args){
        순열구하기 T = new 순열구하기();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n]; //입력된 숫자
        for (int i=0; i<n; i++) arr[i] = sc.nextInt();
        visited = new boolean[n]; //체크매열
        pm = new int[m];
        T.dfs(0);
    }
}
