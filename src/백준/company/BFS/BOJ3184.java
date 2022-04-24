package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3184 {
    // 양 / 실버2 / BFS
    /*
        . - 빈공간, # - 울타리, o - 양, v - 늑대
        조건에 맞게 탐색한 후 살아남은 양과 늑대의 수 리턴
     */
    static int r, c;
    static int sheep, wolf;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // map의 각 영역마다 bfs 탐색해야하기 때문에
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(map[i][j] == '#' || visited[i][j]) continue;
                bfs(i, j);
            }
        }

        System.out.println(sheep + " " + wolf);
    }

    static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        visited[x][y] = true;
        // 해당 영역의 양과 늑대의 수
        int sheepCnt = 0, wolfCnt = 0;
        // 시작점이 늑대라면
        if(map[x][y] == 'o') sheepCnt++;
        // 시작점이 양이라면
        else if(map[x][y] == 'v') wolfCnt++;

        while(!q.isEmpty()){
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                // 범위 벗어나거나 이미 방문한 곳인 경우 pass
                if(nx < 0 || ny < 0 || nx >= r || ny >= c || visited[nx][ny]) continue;
                // 울타리인 경우 pass
                if(map[nx][ny] == '#') continue;
                // 양이면
                if(map[nx][ny] == 'o') sheepCnt++;
                // 늑대면
                if(map[nx][ny] == 'v') wolfCnt++;
                visited[nx][ny] = true;
                q.offer(new Node(nx, ny));
            }
        }
        // 양의 수가 더 많으면 양이 살아남고,
        // 늑대의 수가 더 많으면 늑대가 살아남음
        if(sheepCnt > wolfCnt) sheep += sheepCnt;
        else wolf += wolfCnt;
    }

    static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
