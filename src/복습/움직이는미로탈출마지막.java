package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 움직이는미로탈출마지막 {
    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static char[][] map;
    // 현재, 상, 우상, 우, 우하, 하, 좌하, 좌, 좌상
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[8][8];

        for(int i=0; i<8; i++){
            String str = br.readLine();
            for(int j=0; j<8; j++){
                map[i][j] = str.charAt(j);
            }
        }

        System.out.println(bfs() ? 1 : 0);
    }

    // 욱이 먼저 움직이고 나서 벽이 move
    static boolean bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(7,0)); // 욱이의 초기 위치

        while(!q.isEmpty()){
            int size = q.size();

            for(int i=0; i<size; i++){
                Node node = q.poll();

                // 벽이라면 skip
                if(map[node.x][node.y] == '#') continue;

                // 종료조건(목적지에 다다르면 종료)
                if(node.x == 0 && node.y == 7){
                    return true;
                }

                for(int j=0; j<9; j++){
                    int nx = node.x + dx[j];
                    int ny = node.y + dy[j];

                    if(nx >= 0 && ny >= 0 && nx < 8 && ny < 8
                     && map[nx][ny] == '.'){
                        q.offer(new Node(nx, ny ));
                    }
                }
            }
            moveWall();
        }
        return false;
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
