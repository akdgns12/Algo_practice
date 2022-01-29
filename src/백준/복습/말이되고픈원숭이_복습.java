package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 말이되고픈원숭이_복습 {
    static class Node{
        int x,  y;
        int cnt;
        int k;
        public Node(int x, int y, int cnt, int k){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.k = k;
        }
    }
    static int K, W, H;
    static int[][] map;
    /*
        2차원 배열로 방문처리를 하면
        말처럼 이동했을 때와 원숭이로 이동했을 때의 방문지점에 대해 구분할 길이 없다
        구분자를 하나 더 두어 두 방법의 이동에 대해 구분을 지어야 함.
        visited[x][y][k번 이동한 횟수] 이런 식으로...
     */
    static boolean[][][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[] horseX = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] horseY = {1, 2, 2, 1, -1, -2, -2, -1};
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        visited = new boolean[H][W][K+1];

        for(int i=0; i<H; i++){
            st= new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 격자판의 맨 왼쪽위에서 시작, 맨 오른쪽 아래로 가야함.. 최소 동작 횟수 return
        bfs(0,0);

    }

    static void bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        visited[x][y][K] = true;

        while(!q.isEmpty()){
            Node node = q.poll();

            if(node.x == H-1 && node.y == W-1){
                min = Math.min(min, node.cnt);
                return;
            }

            // 아직 k가 0이 아니라면 즉, 말처럼 이동할 기회 남아있다면
            if(node.k > 0){
                for(int i=0; i<8; i++){
                    int nx = node.x + horseX[i];
                    int ny = node.y + horseY[i];

                    /*
                        이때 원숭이가 말처럼 이동했기 때문에,
                        k를 1감소해서 방문여부 검사, 방문처리, 큐의 위치에 삽입
                     */
                    if(nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                    if(!visited[nx][ny][node.k-1] && map[nx][ny] == 0){
                        visited[nx][ny][node.k-1] = true;
                        q.offer(new Node(nx, ny, node.cnt+1, node.k-1));
                    }
                }
            }

            // 원숭이 이동
            for(int i=0; i<4; i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= H || ny >= W) continue;

                if(!visited[nx][ny][node.k] && map[nx][ny] != 1){
                    visited[nx][ny][node.k] = true;
                    q.offer(new Node(nx, ny, node.cnt+1, node.k));
                }
            }
        }
    }
}
