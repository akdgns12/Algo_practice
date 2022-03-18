package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 불 {
    static int r,c;
    static char[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Queue<Node> fire = new LinkedList<>();
    static Queue<Node> person = new LinkedList<>();
    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'J'){
                    person.offer(new Node(i,j,0));
                }else if(map[i][j] == 'F'){
                    fire.offer(new Node(i,j,0));
                }
            }
        }

        bfs();
        if(result == -1) System.out.println("IMPOSSIBLE");
        else System.out.println(result);
    }

    static void bfs(){
        while(!person.isEmpty()){

            int size = fire.size();
            for (int i = 0; i < size; i++) {
                Node node = fire.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = node.x + dx[d];
                    int ny = node.y + dy[d];

                    if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                    if(map[nx][ny] == '#' || map[nx][ny] == 'F') continue;
                    map[nx][ny] = 'F';
                    fire.offer(new Node(nx, ny, node.time + 1));
                }
            }

            size = person.size();
            for (int i=0; i<size; i++){
                Node node = person.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = node.x + dx[d];
                    int ny = node.y + dy[d];

                    if(nx < 0 || ny < 0 || nx >= r || ny >= c) {
                        result = node.time + 1;
                        return;
                    }

                    if(map[nx][ny] == '#' || map[nx][ny] == 'F' || map[nx][ny] == 'J') continue;
                    map[nx][ny] = 'J';
                    person.offer(new Node(nx, ny, node.time + 1));
                }
            }
        }
    }

    static class Node{
        int x, y;
        int time;
        public Node(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
