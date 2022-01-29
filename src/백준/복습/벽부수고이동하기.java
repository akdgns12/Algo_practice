package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고이동하기 {
    /**
     * 상하좌우를 확인해서 BFS 로 진행한다. 벽을 최대 한 번 까지 부술 수 있기 때문에, 다음 갈 위치가 벽일 경우
     *
     * 한번도 벽을 부순적이 없다면 -> 벽을 부수고 이동한다.
     * 한번이라도 벽을 부순적이 있으면 -> 갈 수 없다.
     * 위 조건만 생각해서 처리하면 된다고 생각했다. 하지만 벽을 부수고 간 경우가 특정 위치에서
     * 먼저 도달하지만 최종적으로 부수지 않고 간 경우가 더 빠를 수 있다는 반례가 존재하기 때문에
     * 일반적인 이중배열 visit 처리로는 풀이가 불가했다. 아래 반례를 실행해보면 이해할 수 있다.
     *
     * 8 8
     * 01000100
     * 01010100
     * 01010100
     * 01010100
     * 01010100
     * 01010100
     * 01010100
     * 00010100
     *
     * 그래서 visited를 3중 배열로 만들어서, 벽을 부수고 탐색하는 경우와 부수지 않고 탐색하는 경우를 따로 처리해주었다.
     * visited[n][m][0]은 벽을 한번도 안부순 애들이 탐색한 경우, visited[n][m][1]은 벽을 한번 부수고 탐색한 경우이다.
     *
     * BFS 탐색 조건은 다음과 같다.
     *
     * 벽이 아니면
     * 부신 벽이 여태까지 없었으면 -> visited[?][?][0] 방문 처리 + queue에 넣음
     * 벽을 한번 부신 적이 있으면 -> visited[?][?][1] 방문 처리 + queue에 넣음
     * 벽이면
     * 한번도 벽을 부신 적이 없으면 부수고 -> visited[?][?][1] 방문 처리 + queue에 넣음
     */
    static int N,M;
    static int[][] map;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][2];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0,0,1, 0));
        visited[0][0][0] = true;

        while(!q.isEmpty()){
            Node node = q.poll();

            if(node.x == N-1 && node.y == M-1){
                System.out.println(node.cnt);
                return;
            }

            for(int i=0; i<4; i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

//                벽이 아니면
//           * 부신 벽이 여태까지 없었으면 -> visited[?][?][0] 방문 처리 + queue에 넣음
//                        * 벽을 한번 부신 적이 있으면 -> visited[?][?][1] 방문 처리 + queue에 넣음

//                        * 벽이면
//                        * 한번도 벽을 부신 적이 없으면 부수고 -> visited[?][?][1] 방문 처리 + queue에 넣음
                if(map[nx][ny] == 1){ // 벽을 만남
                    // 벽 부신적 없다면
                    if(node.brokeWallcnt == 0 && !visited[nx][ny][1]){
                        visited[nx][ny][1] = true; // 벽 부심
                        q.offer(new Node(nx, ny, node.cnt+1, 1));
                    }
                }else{ // 빈 칸
                    if(!visited[nx][ny][node.brokeWallcnt]){
                        q.offer(new Node(nx, ny, node.cnt+1, node.brokeWallcnt));
                        visited[nx][ny][node.brokeWallcnt] = true;
                    }
                }

            }
        }
        System.out.println(-1);
    }

    static class Node{
        int x,y;
        int cnt;
        int brokeWallcnt;
        public Node(int x, int y, int cnt, int brokeWallcnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.brokeWallcnt = brokeWallcnt;
        }
    }
}
