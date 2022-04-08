package 백준.company.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11725 {
    // 트리의 부모 찾기 / 실버2 / DFS
    // 루트 노드가 1일때 각 노드의 부모 노드 출력하는 문제
    static int n;
    static ArrayList<Integer>[] list;
    static int[] parents;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        list = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }
        // parents[i] = j (i의 부모 j)
        parents = new int[n+1];

        dfs(1, 0);

        for (int i=2; i<=n; i++) System.out.println(parents[i]);
    }

    static void  dfs(int start, int parent) {
        parents[start] = parent;
        for (int num : list[start]){
            if(num != parent){
                dfs(num, start);
            }
        }
    }
}
