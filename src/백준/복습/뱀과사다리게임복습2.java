package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 뱀과사다리게임복습2 {
    static int N, M;
    static int[] count = new int[101];
    static int[] ladderAndSnake = new int[101];
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
            ladderAndSnake[x] = y;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            ladderAndSnake[u] = v;
        }

        bfs(1); // 시작위치
    }

    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;
        count[1] = 0; // 해당 인덱스에 오기위해 주사위 굴린 횟수 저장 배열

        while(!q.isEmpty()){
            int cur = q.poll();

            if(cur == 100){
                System.out.println(count[cur]);
                return;
            }

            for(int i=0; i<7; i++){
                int next = cur + i;

                // 범위 벗어나면 skip
                if(next > 100) continue;

                if(visited[next]) continue;
                visited[next] = true;

                // 뱀과 사다리 위치일 때
                if(ladderAndSnake[next] != 0){
                    if(!visited[ladderAndSnake[next]]){
                        visited[ladderAndSnake[next]] = true;
                        q.offer(ladderAndSnake[next]);
                        count[ladderAndSnake[next]] = count[cur] + 1;
                    }
                }else{
                    q.offer(next);
                    count[next] = cur + 1;
                }
            }
        }
    }
}
