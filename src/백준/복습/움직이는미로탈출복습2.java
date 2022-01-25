package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 움직이는미로탈출복습2 {
    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static char[][] map;
    static int[] dx = {0,-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,0,1,1,1,0,-1,-1,-1};
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<8; i++){
            map[i] = br.readLine().toCharArray();
        }

        bfs();
        System.out.println(answer);
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(7,0));

        while(!q.isEmpty()){
            int size = q.size();

            for(int i=0; i<size; i++){
                Node node = q.poll();

                if(map[node.x][node.y] == '#') continue;

                if(node.x == 0 && node.y == 7){
                    answer = 1;
                    return;
                }
                for(int d=0; d<9; d++){
                    int nx = node.x + dx[d];
                    int ny = node.y + dx[d];

                    if(nx >= 0 && ny >= 0 && nx < 8 && ny < 8
                    && map[nx][ny] == '.'){
                        q.offer(new Node(nx, ny));
                    }
                }
            }

            moveWall();
        }
    }

    static void moveWall(){
        for(int i=7; i>=0; i--){
            for(int j=0; j<8; j++){
                if(i == 0) map[i][j] = '.';
                else map[i][j] = map[i-1][j];
            }
        }
    }
}
