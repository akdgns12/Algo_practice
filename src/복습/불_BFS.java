package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 불_BFS {
    static int w,h;
    static char[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Queue<Node> person;
    static Queue<Node> fires;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            fires = new LinkedList<>();
            person = new LinkedList<>();

            for (int i = 0; i < h; i++) {
                String str = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = str.charAt(j);
                    if(map[i][j] == '*'){
                        fires.offer(new Node(i, j));
                    } else if (map[i][j] == '@') {
                        person.offer(new Node(i, j, 0));
                    }
                }
            }

            result = -1;
            bfs();
            if(result == -1) System.out.println("IMPOSSIBLE");
            else System.out.println(result);
        }
    }

    static void bfs(){
        while(!person.isEmpty()){
            int size = fires.size();
            for (int i = 0; i < size; i++) {
                Node node = fires.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = node.x + dx[d];
                    int ny = node.y + dy[d];

                    if(nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
                    if(map[nx][ny] == '.' || map[nx][ny] == '@'){
                        map[nx][ny] = '*';
                        fires.offer(new Node(nx, ny));
                    }
                }
            }

            size = person.size();
            for (int i = 0; i < size; i++) {
                Node node = person.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = node.x + dx[d];
                    int ny = node.y + dy[d];

                    if(nx < 0 || ny < 0 || nx >= h || ny >= w) {
                        result = node.time + 1;
                        return;
                    }

                    if(map[nx][ny] == '.'){
                        person.offer(new Node(nx, ny, node.time + 1));
                        map[nx][ny] = '@';
                    }
                }
            }
        }
    }

    static class Node{
        int x, y;
        int time;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
