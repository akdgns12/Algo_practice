package 백준.company.그래프탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9466 {
    // 텀 프로젝트 / 골드 3 / 그래프 탐색(dfs)
    /*
        어느 프로젝트 팀에도 속하지 않는 학생들의 수 계산해서 return
        -> 즉, 싸이클에 속하지 않는 노드번호를 찾아 count해서 return
        -> 결국, 전체 인원인 n에서 싸이클을 형성하는 노드들의 수를 count해서
        n - count하면 원하는 answer
     */
    static int T;
    static int n;
    static int[] arr;
    static boolean[] visited; // 단순히 방문체크하는 배열
    static boolean[] finished; // 해당 노드를 시작으로 하는 싸이클을 검출했는가 체크하는 배열
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        while(T-->0){
            n = Integer.parseInt(br.readLine());
            visited = new boolean[n+1]; // 단순히 방문 체크하는 배열
            arr = new int[n+1];
            finished = new boolean[n+1]; // 해당 노드를 시작으로 싸이클을 형성했는지 체크하는 배열

            for(int i=1; i<=n; i++){
                st = new StringTokenizer(br.readLine());
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=1; i<=n; i++){
                dfs(i);
            }

            System.out.println(n - count);
        }
    }

    // 싸이클을 형성하지 않는 노드 번호들이 return 값
    static void dfs(int now){
        visited[now] = true;
        int next = arr[now];
        /**
         * finished 배열로 해당 방문노드 탐색 종료 여부를 체크해줌..
         *  -> 만약 finished = false 탐색이 종료되지 않았는데 방문이 된다면
         *  그 노드는 싸이클이 있는 노드, 간주하고 카운트를 해주면 된다.
         *  -> 그리고 해당 노드의 꼬리를 쫓아가서 싸이클에 해당되는 모든 노들를 카운트하면 된다.
         */
        if(!visited[next]){
            dfs(next);
        }else{
            if(!finished[next]){
                count++;
                while(next != now){
                    count++;
                    next = arr[next];
                }
            }
        }
        finished[now] = true;

    }
}
