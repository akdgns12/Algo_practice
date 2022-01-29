package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고이동하기복습3 {
    static int N, M;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][2];

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

            if(node.x == N-1 && node.y == M -1){
                System.out.println(node.cnt);
                return;
            }

            for(int i=0; i<4; i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                if(map[nx][ny] == 1){
                    if(node.brokeWallcnt == 0 && !visited[nx][ny][1]){
                        visited[nx][ny][1] = true;
                        q.offer(new Node(nx, ny, node.cnt+1, 1));
                    }
                }else{
                    if(!visited[nx][ny][node.brokeWallcnt]){
                        visited[nx][ny][node.brokeWallcnt] = true;
                        q.offer(new Node(nx, ny, node.cnt +1, node.brokeWallcnt));
                    }
                }
            }

        }
        System.out.println(-1);
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
