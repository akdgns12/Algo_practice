package 백준.company.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1759 {
    // 암호만들기 / 골드 5 / 백트래킹
    static int l, c;
    static char[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new char[c];
        visited = new boolean[c];
        for (int i = 0; i < c; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);

        dfs(0,0);
    }

    static void dfs(int start, int cnt){
        if(cnt == l){
            int moCnt = 0;
            int jaCnt = 0;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < c; i++) {
                if(visited[i]){
                    sb.append(arr[i]);

                    if(arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
                        moCnt++;
                    } else{
                        jaCnt++;
                    }
                }
            }

            if(moCnt >= 1 && jaCnt >= 2) System.out.println(sb);
        }

        for (int i=start; i<c; i++){
            visited[i] = true;
            dfs(i + 1, cnt + 1);
            visited[i] = false;
        }
    }
}
