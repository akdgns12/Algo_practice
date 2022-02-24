package 코테대비.DFS_BFS활용;

import java.util.Scanner;

public class 합이같은부분집합 {
    static String answer = "NO";
    static int n, total = 0;
    static boolean isFind = false;

    public void dfs(int depth, int sum, int[] arr) {
        if(isFind) return; // falg가 true면 넘어오는 재귀는 다 return처리
        if(sum > total/2) return;
        if (depth == n) {
            if ((total - sum) == sum) {
                answer = "YES";
                isFind = true;
            }
        }
        else{
            dfs(depth+1, sum+arr[depth], arr); // 해당 depth에 있는 원소를 부분집합에 사용하겠다
            dfs(depth+1, sum, arr); //해당 depth에 있는 원소를 부분집합에 사용하지 않겠다
        }
    }

    public static void main(String[] args){
        합이같은부분집합 T = new 합이같은부분집합();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            total += arr[i];
        }
        T.dfs(0,0, arr);
        System.out.println(answer);
    }
}
