package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 텀프로젝트_dfs심화 {
    static int T,n;
    static int[] arr;
    static boolean[] visited; // 단순히 방문체크 배열
    static boolean[] isFinished; // 해당 노드를 시작으로 싸이클이 형성했는지 판단하는 배열
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc<T; tc++){
            n = Integer.parseInt(br.readLine());
            visited = new boolean[n+1];
            isFinished = new boolean[n+1];
            arr = new int[n+1];
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=n; i++){
                int from = i;
                int to = Integer.parseInt(st.nextToken());
                arr[from] = to;
            }

            count = 0;
            for(int i=1; i<=n; i++){
                dfs(i);
            }

            System.out.println(n - count);
        }
    }

    static void dfs(int now){
        visited[now] = true;
        int next = arr[now];

        if(!visited[next]){
            dfs(next);
        }else{
            if(!isFinished[next]){
                count++;
                while(next != now){
                    count++;
                    next = arr[next];
                }
            }
        }
        isFinished[now] = true;
    }
}
