package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.NotActiveException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5427 {
    // 불 / 골드4 / BFS
    /*
        불은 동서남북으로 퍼져나감, 벽에는 불이 붙지않음. 상근이는 벽을 통과할 수 없음, 불이 옮겨진 칸 또는 이제 붙으려는 칸으로 이동할 수 없음.
        얼마나 빨리 탈출할 수 있는지..
        -생각
        불이 먼저 퍼지고, 사람이 이동.
     */
    static int t;
    static int w, h;
    static char[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Queue<Node> person;
    static Queue<Node> fires;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

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
                    if(map[i][j] == '*'){ // 불들의 위치
                        fires.offer(new Node(i,j));
                    }else if(map[i][j] == '@'){ // 상근이 위치
                        person.offer(new Node(i,j,0));
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
            // 1. 불 먼저 퍼짐
            int size = fires.size();
            for (int i = 0; i < size; i++) { // 불의 개수만큼 한턴 퍼지고, 그다음 사람도 한턴 퍼지고 이런식으로 진행해야 한다
                Node node = fires.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = node.x + dx[d];
                    int ny = node.y + dy[d];

                    // 범위 벗어나면 pass
                    if(nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
                    // 빈칸이거나 사람칸이면 이동
                    if(map[nx][ny] == '.' || map[nx][ny] == '@') {
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
                    // 범위 벗어나면 = 탈출하면
                    if(nx < 0 || ny < 0 || nx >= h || ny >= w){
                        result = node.time + 1;
                        return;
                    }
                    // 다음칸이 빈칸일때만 이동
                    if(map[nx][ny] == '.'){
                        map[nx][ny] = '@';
                        person.offer(new Node(nx, ny, node.time + 1));
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
