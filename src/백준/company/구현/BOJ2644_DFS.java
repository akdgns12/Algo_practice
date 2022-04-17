package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2644_DFS {
    // 촌수계산 DFS로 구해보기
    static ArrayList<ArrayList<Integer>> relation = new ArrayList<>();
    static int result = 0;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            relation.add(new ArrayList<>());
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            relation.get(x).add(y);
            relation.get(y).add(x);
        }

        visited = new boolean[n+1];
        dfs(start, end, 0);
        System.out.println(result);
    }

    static void dfs(int start, int end, int cnt) {
        visited[start] = true;

        for(int i=0; i<relation.get(start).size(); i++){
            int next = relation.get(start).get(i);
            if(!visited[next]){
                if(next == end){
                    result = cnt + 1;
                    return;
                }

                dfs(next, end, cnt + 1);
            }
        }
    }
}
