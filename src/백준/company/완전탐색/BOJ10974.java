package 백준.company.완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10974 {
    // 모든 순열 / 실버3 / 완탐
    // 1~N까지의 수로 이뤄진 순열을 사전순으로 출력
    static int n;
    static boolean[] visited;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        visited = new boolean[n];

        dfs(0, "");
    }

    static void dfs(int depth, String str){
        if(depth == n){
            System.out.println(str);
            return;
        }

        for (int i = 0; i < n; i++) {
            if(!visited[i]){
                visited[i] = true;
                dfs(depth + 1, str + arr[i] + " ");
                visited[i] = false;
            }
        }
    }

}
