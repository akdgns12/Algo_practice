package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ10519_저울 {
    static int N, M;
    static int[] isConnect;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 물건 수 : 정점 수
        M = Integer.parseInt(br.readLine()); // 물건 쌍의 개수 : 간선 수?

        list = new ArrayList[N+1];
        for(int i=0; i<=N; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){ // a가 b보다 무거움
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
        }

        isConnect = new int[N+1];
        // (1~N) i와 비교결과를 알 수 없는 물건의 개수 출력
        for(int i=1; i<=N; i++){
            bfs(i);
        }

        for(int i=1; i<=N; i++) System.out.println(N - isConnect[i]);
    }

    // 그냥 bfs진행하면 서로 관계는 파악할 수 있지만 i번보다 앞선 번호를 셀 수 없다.
    // 연결됐고 i번보다 작다면 무조건 i보다 가볍다.
    // 애초에 i번에서 연결된 정점을 방문할때 정점을 방문하면 정점이 i번과 연결됐다고 카운팅해주기
    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        q.offer(start);
        visited[start] = true;
        isConnect[start]++;
//        if(start == 6) System.out.println("6 : " + isConnect[6]);

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : list[now]) {
                if (visited[next]) continue;
                visited[next] = true;
                q.offer(next);
                isConnect[start]++;
//                System.out.println("start : " + start  + " " + isConnect[start]);
                isConnect[next]++;
//                System.out.println("next : " + next + " " + isConnect[next]);
            }
        }
    }
}
