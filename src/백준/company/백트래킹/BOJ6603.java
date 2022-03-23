package 백준.company.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ6603 {
    // 로또 / 실버 2 / 백트래킹
    // k개의 수에서 6개를 골라 사전 순으로 출력
    static int k;
    static int[] s;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if(k == 0){
                break;
            }
            visited = new boolean[k];
            s = new int[k];
            for (int i = 0; i < k; i++) {
                s[i] = Integer.parseInt(st.nextToken());
            }
            dfs(0,0);
            System.out.println();
        }
    }

    static void dfs(int start, int depth){
        if(depth == 6){
            for(int i=0; i<k; i++){
                if(visited[i]){
                    System.out.print(s[i] + " ");
                }
            }
            System.out.println();
            return;
        }

        for(int i=start; i<k; i++){
            visited[i] = true;
            dfs(i+1, depth+1);
            visited[i] = false;
        }
    }
}
