package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 효율적인해킹 {
    static int n,m;
    static int a,b;
    static int[] answer;
    static boolean[] visited;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        answer = new int[n+1];
        list = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            list[a].add(b);
        }

        for (int i = 1; i <= n; i++) {
            visited = new boolean[n+1];
            bfs(i);
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, answer[i]);
        }

        for (int i = 1; i <= n; i++) {
            if(answer[i] == max){
                System.out.print(i + " ");
            }
        }
    }

    static void bfs(int node){
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        visited[node] = true;

        while(!q.isEmpty()){
            int now = q.poll();

            for (int next : list[now]) {
                if(!visited[next]){
                    visited[next] = true;
                    answer[next]++;
                    q.offer(next);
                }
            }
        }
    }
}
