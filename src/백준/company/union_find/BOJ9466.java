package 백준.company.union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ9466 {
    // 텀프로젝트 / 골드3 / 유니온 파인드
    // 싸이클이 존재하는 경우만 팀, 팀에 속하지 못한 사람의 수를 출력
    static int t;
    static int n;
    static int[] arr;
    static boolean[] visited, finished;
    static int teamCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n+1];

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            finished = new boolean[n+1];
            visited = new boolean[n+1];

            for (int node = 1; node <= n; node++) {
                dfs(node);
            }

            System.out.println();
        }
    }

    static void dfs(int node) {
        if(visited[node]){
            finished[node] = true;
            teamCnt++;
        }else{
            visited[node] = true;
        }

        int next = arr[node];
        if (!finished[next]) {
            dfs(next);
            visited[node] = false;
            visited[node] = true;
        }
    }
}
