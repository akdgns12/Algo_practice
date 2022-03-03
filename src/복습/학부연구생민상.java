package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 학부연구생민상 {
    static int n, m;
    static int[][] map;
    static boolean[][][] visited;
    static int[][] answer;
    static int[] dx = {-1,0,1,0}; // 상 우 하 좌
    static int[] dy = {0,1,0,-1};
    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map =  new int[n][m];
        visited = new boolean[n][m][4];
        answer = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    answer[i][j] = 1;
                    for (int dir = 0; dir < 4; dir++) {
                        visited[i][j][dir] = true;
                        q.offer(new Node(i,j,dir));
                    }
                }
            }
        }

        bfs();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(answer[i][j] == 1) cnt++;
            }
        }

        System.out.println(cnt);
    }

    static void bfs(){
        while(!q.isEmpty()){
            Node cur = q.poll();
            int dir = cur.dir;
            int nx = cur.x + dx[dir];
            int ny = cur.y + dy[dir];

            if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            if(visited[nx][ny][dir]) continue;
            visited[nx][ny][dir] = true;
            answer[nx][ny] = 1;
            if(map[nx][ny] == 1 && dir % 2 == 1) continue;
            else if(map[nx][ny] == 2 && dir % 2 == 0) continue;
            else if(map[nx][ny] == 3){
                if(dir >= 2){
                    dir = 2 + (dir+1) % 2;
                }
                else{
                    dir = (dir + 1) % 2;
                }
            }
            else if(map[nx][ny] == 4){
                if(dir == 0){
                    dir = 3;
                }else if(dir == 1){
                    dir = 2;
                }else if(dir == 2){
                    dir = 1;
                }else{
                    dir = 0;
                }
            }
            else if(map[nx][ny] == 9) continue;
            q.offer(new Node(nx, ny, dir));
        }
    }

    static class Node{
        int x, y;
        int dir;
        public Node(int x, int y, int dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
