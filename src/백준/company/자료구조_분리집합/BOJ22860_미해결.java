package 백준.company.자료구조_분리집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ22860_미해결 {
    // 폴더정리(small) / 골드3 / 그래프, dfs, 자료구조

    static int n, m;
    static ArrayList<ArrayList<String>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 폴더의 총 개수
        m = Integer.parseInt(st.nextToken()); // 파일의 총 개수
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            String p = st.nextToken(); // 상위 폴더의 이름
            String f = st.nextToken(); // 폴더 또는 파일의 이름
            String c = st.nextToken(); // 1 : f 폴더, 0 : f 파일
        }

        int query = Integer.parseInt(br.readLine());
        for (int i = 0; i < query; i++) {
            String str = br.readLine();
            String[] split = str.split("/");
            int len = split.length;
            dfs(split[len - 1]);
        }
    }

    static int dfs(String str){
        int answer = 0;

        return answer;
    }
}
