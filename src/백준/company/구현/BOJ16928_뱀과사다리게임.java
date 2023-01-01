package 백준.company.구현;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16928_뱀과사다리게임 { // 1번에서 시작해 100번에 도착해야함
    static int N, M;
    static int[] ladderSnake;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ladderSnake = new int[101];

        for(int i=0; i<N+M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            ladderSnake[x] = y;
        }

        bfs();
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[101];
        q.offer(new Node(1, 0));
        visited[1] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            if(now.position == 100){
                System.out.println(now.cnt);
                return;
            }

            // 1~6 주사위만큼 이동
            for(int i=1; i<=6; i++){
                int next = now.position + i;

                if(next > 100 || visited[next]) continue;

                if(ladderSnake[next] != 0){ // 해당 칸에 뱀이나 사다리면 => 처음엔 방문처리를 같이 검사했지만 그렇게 하게되면, 뱀이나 사다리이지만 방문했던 칸은 그냥 넘어가야 하는데 그러질 못함
                    if(!visited[ladderSnake[next]]){ // 해당 칸을 방문한 적 없다면
                        visited[ladderSnake[next]] = true;
                        q.offer(new Node(ladderSnake[next], now.cnt + 1));
                    }
                }else{ // 그냥 일반 칸이라면
                    visited[next] = true;
                    q.offer(new Node(next, now.cnt +1));
                }
            }
        }
    }

    static class Node{
        int position;
        int cnt;
        public Node(int position, int cnt){
            this.position = position;
            this.cnt = cnt;
        }
    }
}
