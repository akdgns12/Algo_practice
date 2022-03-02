package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ21922 {
    /*
        1,2,3,4 : 물건
        9 : 에어컨
        0 : 빈공간
     */
    static int n, m;
    static int[][] map;
    static int[][] answer;
    static boolean[][][] visited;
    static int[] dx = {-1,0,1,0}; // 상 우 하 좌
    static int[] dy = {0,1,0,-1};
    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m][4];
        answer = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){ // 에어컨이라면
                    answer[i][j] = 1; // 바람이 통한 곳 카운팅 하기 위한 배열
                    for (int k=0; k<4; k++){ //에어컨의 4방향에 push
                        visited[i][j][k] = true;
                        q.offer(new Node(i,j,k));
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
            int nx = cur.x + dx[cur.dir];
            int ny = cur.y + dy[cur.dir];

            if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
            if(visited[nx][ny][cur.dir]) continue;
            visited[nx][ny][cur.dir] = true;
            answer[nx][ny] = 1;
            if(map[nx][ny] == 1 && cur.dir % 2 == 1) continue;
            else if(map[nx][ny] == 2 && cur.dir % 2 == 0) continue;
            else if(map[nx][ny] == 3){
                if(cur.dir >= 2){
                    cur.dir = 2 + (cur.dir+1) % 2;
                }else{
                    cur.dir = (cur.dir+1) % 2;
                }
            }
            else if(map[nx][ny] == 4){
                if(cur.dir == 0){
                    cur.dir = 3;
                }else if(cur.dir == 1){
                    cur.dir = 2;
                }else if(cur.dir == 2){
                    cur.dir = 1;
                }else{
                    cur.dir = 0;
                }
            }
            else if(map[nx][ny] == 9) continue;
            q.offer(new Node(nx, ny, cur.dir));
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
