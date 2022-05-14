package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백설공주와일곱난장이 {
    static int[] arr, ans;
    static boolean[] visited;
    static int sum;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[9];
        visited = new boolean[9];
        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        ans = new int[7];
        dfs(0, 0);
        System.out.println(sb);
    }

    static void dfs(int index, int sum) {
        if(index == 7){
            if(sum == 100){
                sb = new StringBuilder();
                for (int val : ans) {
                    sb.append(val).append('\n');
                }
            }
            return;
        }

        for (int i = index; i < 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                ans[index] = arr[i];
                dfs(index + 1, sum + arr[i]);
                visited[i] = false;
            }
        }
    }


}
