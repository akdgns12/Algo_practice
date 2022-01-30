package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 뱀사다리게임 {
    static int N, M;
    static int[] ladderAndSanke = new int[101];
    static int[] count = new int[101];
    static boolean[] visited = new boolean[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            ladderAndSanke[x] = y;
        }

        for(int j=0; j<M; j++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            ladderAndSanke[u] = v;
        }

        bfs(1);
    }

    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        visited[1] = true;
        count[1] = 0;

        while(!q.isEmpty()){
            int cur = q.poll();

            if(cur == 100){
                System.out.println(count[cur]);
                return;
            }

            for(int i=1; i<7; i++){
                int next = cur + i;

                if(next > 100) continue;
                if(visited[next]) continue;
                visited[next] = true;

                if(ladderAndSanke[next] != 0 ){ // 뱀과 사다리의 위치일 때
                    if(!visited[ladderAndSanke[next]]){
                        visited[ladderAndSanke[next]] = true;
                        count[ladderAndSanke[next]] = count[cur] + 1;
                        q.offer(ladderAndSanke[next]);
                    }
                }else{ // 빈 칸 일때
                    q.offer(next);
                    count[next] = count[cur] + 1;
                }
            }
        }

    }
}
