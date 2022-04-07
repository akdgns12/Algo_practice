package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 바이러스 {
    static int n, m;
    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static int totalVirus = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        list = new ArrayList[n+1];
        visited = new boolean[n+1];
        int start = 1;

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }


        System.out.println(dfs(start));
    }

    static int dfs(int start){
        visited[start] = true;
        for (int nxt : list[start]){
            if(!visited[nxt]){
                totalVirus++;
                dfs(nxt);
            }
        }
        return totalVirus;
    }
}
