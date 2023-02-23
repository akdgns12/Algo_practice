package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ16954_움직이는미로탈출 {
    static char[][] map = new char[8][8];
    static int[] dx = {0,-1,-1,0,1,1,1,0,-1}; // 제자리, 상,우상,우,우하,하,좌하,좌,좌상
    static int[] dy = {0,0,1,1,1,0,-1,-1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<8; i++){
            String str = br.readLine();
            for(int j=0; j<8; j++){
                map[i][j] = str.charAt(j);
            }
        }

        // (7,0) -> (0, 7)
        // 캐릭터 먼저 이동, 그 후 벽이동, 벽이 캐릭터가 있는 칸으로 이동하면 캐릭터 더이상 움직일 수 없음
        System.out.println(bfs(7, 0));
    }

    // 1. 캐릭터 이동 2. 벽 아래로,
    static int bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));

        while(!q.isEmpty()){
            int size = q.size();
            boolean[][] visited = new boolean[8][8];

            // 캐릭터 이동
            for(int sz=0; sz<size; sz++){
                Node now = q.poll();

                // 벽 이동 후
                if(map[now.x][now.y] == '#') continue;

                if(now.x == 0 && now.y == 7){
                    return 1;
                }

                for(int i=0; i<9; i++){
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if(!isOutOfRange(nx, ny) || map[nx][ny] == '#') continue;
                    if(visited[nx][ny]) continue;

                    q.offer(new Node(nx, ny));
                    visited[nx][ny] = true;
                }
            }
            // 벽 아래로
            downWall();
        }

        return 0;
    }

    static void downWall(){
        for(int i = 7 ; i >= 0 ; --i) {
            for(int j = 0 ; j < 8 ; ++j) {
                if(i == 0) map[i][j] = '.';
                else map[i][j] = map[i - 1][j];
            }
        }
    }

    static boolean isOutOfRange(int x, int y){
        if(x < 0 || y < 0 || x >= 8 || y >= 8) return false;
        else return true;
    }

    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
