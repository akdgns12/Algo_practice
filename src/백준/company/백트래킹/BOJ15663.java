package 백준.company.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ15663 {
    // N과M 9
    static int n, m;
    static int[] arr;
    static boolean[] visited;
    static int[] result;
    static HashSet<String> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        visited = new boolean[n];
        result = new int[m];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        dfs(0);
        System.out.println(sb.toString());
    }
    static void dfs(int depth){
        if(depth == m){
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < m; i++) {
                sb2.append(result[i] + " ");
            }
            if(!set.contains(sb2.toString())){ // 중복제거
                sb.append(sb2.toString()).append('\n');
                set.add(sb2.toString());
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if(!visited[i]){
                result[depth] = arr[i];
                visited[i] =true;
                dfs(depth+1);
                visited[i] = false;
            }
        }
    }
}
