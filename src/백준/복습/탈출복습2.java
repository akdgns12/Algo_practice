package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 탈출복습2 {
    static class Node{
        int x, y;
        int time;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    static int r,c;
    static char[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int min = Integer.MAX_VALUE;
    static Queue<Node> biber = new LinkedList<>();
    static Queue<Node> water = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];

        for(int i=0; i<r; i++){
            String str = br.readLine();
            for(int j=0; j<c; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S'){
                    biber.add(new Node(i,j,0));
                }else if(map[i][j] == '*'){
                    water.add(new Node(i,j));
                }
            }
        }

        bfs();
        System.out.println(min == Integer.MAX_VALUE ? "KAKTUS" : min);
    }

    static void bfs(){
        while(!water.isEmpty()){
            int size = water.size();

            for(int i=0; i<size; i++){
                Node node = water.poll();

                for(int j=0; j<4; j++){
                    int nx = node.x + dx[i];
                    int ny = node.y + dy[i];

                    if(nx < 0 || ny < 0 || nx >= r-1 || ny >= c-1) continue;

                    if(map[nx][ny] == 'D' || map[nx][ny] == 'X' ||
                    map[nx][ny] == '*') continue;
                    map[nx][ny] = '*';
                    water.offer(new Node(nx,ny));
                }
            }

            size = biber.size();
            for(int i=0; i<size; i++){
                Node node = biber.poll();

                for(int j=0; j<4; j++){
                    int nx = node.x + dx[j];
                    int ny = node.y + dy[j];

                    if(nx < 0 || ny < 0 || nx >= r-1 || ny >= c-1) continue;

                    if(map[nx][ny] == 'D'){
                        min = Math.min(min, node.time+1);
                        return;
                    }
                    else if(map[nx][ny] == '.'){
                        map[nx][ny] = 'S';
                        biber.add(new Node(nx, ny, node.time+1));
                    }
                }
            }
        }
    }
}
