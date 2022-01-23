package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 뱀과사다리게임 {
    static int N,M;
    static int[] ladderAndSnake = new int[101];
    static boolean[] visited = new boolean[101];
    static int[] count = new int[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 사다리 수
        M = Integer.parseInt(st.nextToken()); // 뱀 수

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x =  Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            ladderAndSnake[x] = y;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            ladderAndSnake[u] = v;
        }

        bfs(1);
    }

    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(1); // 시작위치 넣어주고 시작
        visited[1] = true;
        count[1] = 0; // 해당 인덱스까지 오는데 굴린 주사위 횟수

        while(!q.isEmpty()){
            int cur = q.poll();

            // 종료조건
            if(cur == 100){
                System.out.println(count[cur]);
                return;
            }

            for(int i=1; i<7; i++){
                int next = cur + i;
                if(next > 100) continue;
                if(visited[next]) continue;
                visited[next] = true;

                if(ladderAndSnake[next] != 0){ // 사다리 또는 뱀의 위치일 때
                    if(!visited[ladderAndSnake[next]]){
                        // 방문하지 않았다면
                        visited[ladderAndSnake[next]] = true;
                        q.offer(ladderAndSnake[next]);
                        count[ladderAndSnake[next]] = count[cur] + 1;
                    }
                }else{ // 사다리 또는 뱀의 위치가 아닐 때
                    q.offer(next);
                    count[next] = count[cur] + 1;
                }
            }
        }
    }
}
