package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ로또 {
    static int k;
    static int[] s;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            if(k == 0){
                break;
            }
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            for (int i=0; i<k; i++){
                s[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);
            System.out.println();
        }
    }

    static void dfs(int start, int depth){
        if(depth == 6){
            for(int i=0; i<k; i++){
                if (visited[i]) {
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
