package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9466_텀프로젝트 {
    static int n;
    static int[] arr;
    static boolean[] visited, isCycle;
    static int ans;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            n = Integer.parseInt(br.readLine());

            arr = new int[n+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1; i<=n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            ans = 0;
            visited = new boolean[n+1]; // dfs탐색시 방문했는지
            isCycle = new boolean[n+1];
            for(int i=1; i<=n; i++) {
                dfs(i);
            }

            sb.append(n-ans).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int now){
        if(visited[now]){ // 방문했던 지점이면 리턴
            System.out.println("visitedNode : " + now);
            return;
        }

        visited[now] = true;
        int next = arr[now]; // 현재노드가 가리키는 다음 노드

        if(!visited[next]){ // 다음노드를 방문한 적이 없으면
            dfs(next);
        }else{ // 방문한적이 있는 노드야
            if(!isCycle[next]){ // 근데 이 next라는 다음노드가 방문했는데
                ans++;
                for(int i=next; i != now; i=arr[i]){
                    ans++;
                }
            }
        }

        isCycle[now] = true;
    }
}
