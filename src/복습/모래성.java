package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 모래성 {
    static int h, w;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,-1,0,1,1,1,0,-1}; // 상, 우상, 우, 우하, 하, 좌하, 좌, 좌상
    static int[] dy = {0,1,1,1,0,-1,-1,-1};
    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        map = new char[h][w];
        visited = new boolean[h][w];

        for (int i = 0; i < h; i++) {
            String str = br.readLine();
            for (int j = 0; j < w; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        // 모래가 모래성 높이와 같거나 더 큰 경우 큐에 넣기
        int num = 0;
        int cnt = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if(map[i][j] == '.') continue;

                num = map[i][j] - '0';
                if(num == 9) continue;

                cnt = checkCnt(i,j);

                if(cnt >= num){
                    q.offer(new Node(i,j));
                    visited[i][j] = true;
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs(){
        int time = 0;
        while(!q.isEmpty()){
            int size = q.size();


            for (int i = 0; i < size; i++) {
                Node node = q.poll();

                map[node.x][node.y] = '.'; // 파도쳐서 모래로 변한 처리해준다

                for (int d = 0; d < 8; d++) {
                    int nx = node.x + dx[d];
                    int ny = node.y + dy[d];

                    if(nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
                    if(map[nx][ny] == '.') continue;

                    int num = map[nx][ny] - '0';
                    if(!visited[nx][ny] && checkCnt(nx, ny) >= num){
                        q.offer(new Node(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
            time++;
        }

        return time;
    }

    static int checkCnt(int x, int y){
        int cnt = 0;

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(map[nx][ny] == '.'){
                cnt++;
            }
        }
        return cnt;
    }

    static class Node{
        int x, y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
