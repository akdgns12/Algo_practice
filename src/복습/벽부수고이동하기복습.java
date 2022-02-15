package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고이동하기복습 {
    static int N, M;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][2]; // 좌표 방문처리와 벽 부순 여부 함께 관리

        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs(0,0);
    }

    static void bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, 1, 0));
        visited[x][y][0] = true;

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

                // 벽을 부수지 않고 이동하는 목적지로 이동하는 경우와
                // 1. 벽을 부수지 않았을 때
                // 고려해야할 것 1-1 방문하지 않았는지 체크, 벽을 부수지 않았는지
                // 벽을 부수고 목적지로 이동하는 경우
                // 고려해야 할 것 1-1 방문하지 않았는지 체크, 벽을 부수지 않았는지?
                if(map[nx][ny] == 1){ // 벽을 만나면
                    if(node.brokeWallcnt == 0 && !visited[nx][ny][1]){
                        visited[nx][ny][1] = true;
                        q.offer(new Node(nx, ny, node.cnt + 1, node.brokeWallcnt));
                    }
                }else{
                    if(!visited[nx][ny][node.brokeWallcnt]){
                        visited[nx][ny][node.brokeWallcnt] = true;
                        q.offer(new Node(nx, ny, node.cnt + 1, node.brokeWallcnt));
                    }
                }
            }
        }
    }

    static class Node{
        int x, y;
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
