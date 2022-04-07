package 백준.company.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2606 {
    // 바이러스 / 실버 3 / DFS
    static int n, m;
    static ArrayList<Integer>[] list; // 인접리스트
    static boolean[] visited; // 정점 탐색여부 체크
    static int totalVirus = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 컴퓨터 수 (정점)
        m = Integer.parseInt(br.readLine()); // 연결된 컴퓨터 쌍의 수(간선)
        int start = 1; // 탐색 시작할 정점의 번호

        list = new ArrayList[n+1]; // 인덱스 편의상 n+1설정, 0번째 요소는 사용 X
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 간선으로 이어진 정점 1
            int b = Integer.parseInt(st.nextToken()); // 정점1과 이어진 정점2

            list[a].add(b);
            list[b].add(a);
        }

        System.out.println(dfs(start));
    }

    static int dfs(int i) {
        visited[i] = true;
        for (int nxt : list[i]){
            if(!visited[nxt]){
                totalVirus++;
                dfs(nxt);
            }
        }
        return totalVirus;
    }
}
