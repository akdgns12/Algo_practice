package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4179 {
    // 불! / 골드 4 / BFS
    static int r, c;
    static char[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Queue<Node> person = new LinkedList<>();
    static Queue<Node> fire = new LinkedList<>();
    static int result = -1;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'J') {
                    person.add(new Node(i,j,0));
                }else if(map[i][j] == 'F'){
                    fire.add(new Node(i,j,0));
                }
            }
        }

       bfs();
        if(result == -1) System.out.println("IMPOSSIBLE");
        else System.out.println(result);
    }

    static int bfs(){
        while(!person.isEmpty()){
            // 불이 먼저 퍼진다
            int size = fire.size();
            for(int i=0; i<size; i++){ // 큐의 사이즈만큼 for문을 해주지 않으면 bfs가 계속 진행되기 때문에,
                 // -> 문제의 조건에 맞는 순서는 불이 한 번 퍼지고 지훈이가 한 번 퍼지고 이런식으로 동작해야함
                Node node = fire.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = node.x + dx[d];
                    int ny = node.y + dy[d];

                    if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                    if(map[nx][ny] == '#' || map[nx][ny] == 'F') continue;
                    map[nx][ny] = 'F';
                    fire.add(new Node(nx,ny,node.dist + 1));
                }
            }

                // 지훈이가 불을 피해 이동
                size = person.size();
                for (int i = 0; i < size; i++) {
                    Node node = person.poll();

                    for (int d = 0; d < 4; d++) {
                        int nx = node.x + dx[d];
                        int ny = node.y + dy[d];

                        if(nx < 0 || ny < 0 || nx >= r || ny >= c){
                           result = node.dist + 1;
                           return result;
                        }

                        if(map[nx][ny] == '#' || map[nx][ny] == 'F' || map[nx][ny] == 'J') continue;
                        map[nx][ny] = 'J';
                        person.add(new Node(nx, ny, node.dist + 1));
                    }
                }
            }

        return -1;
    }

    static class Node{
        int x, y;
        int dist;
        public Node(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
