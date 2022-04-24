package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {
    // 벽 부수고 이동하기 / 골드 4 / BFS
    // 0 : 이동가능, 1 : 이동불가능한 벽
    // 1. 맵을 탐색하면서 좌표와 벽을 부순 횟수, 지금까지의 거리를 같이 들고 다녀야 한다.
    // 벽을 부수지 않고 방문한 체크 배열과 벽을 부수고 방문한 체크 배열이 필요하다.
    // 2. 다음은 흔한 상하좌우 탐색을 하는데,
    // 다음 좌표가 벽이 아닐경우와 벽일 경우로 나눠서 생각해야 한다.
    // 각 단계에서 이미 방문했을 경우 pass 해주고, 이동할 수 있는 상태라면 큐에 넣어주자.
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][][] visited; // 2차원 방문 배열에 + 벽을 부순 여부체크까지 하기 위해 3차원으로 선언
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m][2];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs();
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 1, 0)); // 시작칸과 끝나는 칸도 거리에 포함

        while(!q.isEmpty()){
            Node now = q.poll();

            if (now.x == n - 1 && now.y == m - 1) {
                System.out.println(now.cnt);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                // 범위 벗어나면 pass
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                // 이동할 수 있는 곳으로 이동할 경우
                if(map[nx][ny] == 0){
                    // 이미 왔던 길일 경우 pass
                    if(visited[nx][ny][now.breakWallCnt]) continue;
                    q.offer(new Node(nx, ny, now.cnt + 1, now.breakWallCnt));
                    visited[nx][ny][now.breakWallCnt] = true;
                }
                // 벽을 부수고 이동해야 할 경우
                else {
                    // 벽을 부술 수 없거나, 이미 왔던 길일 경우 pass
                    if(now.breakWallCnt != 0 || visited[nx][ny][now.breakWallCnt+1]) continue;
                    q.offer(new Node(nx, ny, now.cnt + 1, now.breakWallCnt + 1));
                    visited[nx][ny][now.breakWallCnt + 1] = true;
                }
            }
        }
        System.out.println(-1);
    }

    static class Node{
        int x, y;
        int cnt;
        int breakWallCnt;

        public Node(int x, int y, int cnt, int breakWallCnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.breakWallCnt = breakWallCnt;
        }
    }
}
