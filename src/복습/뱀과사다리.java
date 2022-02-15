package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 뱀과사다리 {
    // 뱀과 사다리 게임 / 주사위 조작 최소 몇 번만에 도착점에 도착하는지 return
    static int N, M;
    static int[][] map;
    static int[] count = new int[101];
    static int[] ladderAndSnake = new int[101];
    static boolean[] visited = new boolean[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 사다리의 수
        M = Integer.parseInt(st.nextToken()); // 뱀의 수
        map = new int[100][100];

        // 사다리 정보
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                ladderAndSnake[x] = y;
        }

        // 뱀의 정보
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            ladderAndSnake[u] = v;
        }

        bfs(1); // 시작 위치
    }

    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        visited[1] = true;
        count[1] = 0; // 해당 인덱스까지 오는데 주사위 굴린 횟수

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


                if(ladderAndSnake[next] != 0){ // 사다리 또는 뱀의 위치일 때
                    if(!visited[ladderAndSnake[next]]){
                        visited[ladderAndSnake[next]] = true;
                        q.offer(ladderAndSnake[next]);
                        count[ladderAndSnake[next]] = count[cur] + 1;
                    }
                }else { // 사다리 또는 뱀의 위치가 아닌 그냥 빈 칸일때
                    q.offer(next);
                    count[next] = count[cur] + 1;
                }
            }
        }
    }
}
