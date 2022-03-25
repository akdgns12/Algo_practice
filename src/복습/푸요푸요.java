package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 푸요푸요 {
    /*
        1. 푸요가 있는 곳을 발견하면 bfs탐색으로 인접한 푸요가 4개 이상인지 확인하고 4개 이상이라면 연쇄반응을 일으킨다.
        2.
     */
    static class Color{
        int x, y;
        public Color(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int n = 12, m = 6;
    static char[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited;
    static ArrayList<Color> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int count = 0;
        while(true){
            boolean isFinished = true;
            visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(map[i][j] != '.'){
                        list = new ArrayList<>();
                        bfs(i, j, map[i][j]);

                        if(list.size() >= 4){
                            isFinished = false;
                            for(Color c : list)
                                map[c.x][c.y] = '.'; // 연쇄반응 터트리기
                        }
                    }
                }
            }
            if(isFinished) break;
            gravity();
            count++;
        }

        System.out.println(count);
    }

    static void gravity(){
        for(int i=0; i<m; i++){
            for(int j=n-1; j>0; j--){
                if(map[j][i] == '.'){
                    for(int k=j-1; k>=0; k--){
                        if(map[k][i] != '.'){
                            map[j][i] = map[k][i];
                            map[k][i] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }

    static void bfs(int x, int y, char color) {
        Queue<Color> q = new LinkedList<>();
        list.add(new Color(x,y));
        q.offer(new Color(x,y));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Color cur = q.poll();

            for(int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(!visited[nx][ny] && map[nx][ny] == color){
                    visited[nx][ny] = true;
                    q.offer(new Color(nx, ny));
                    list.add(new Color(nx, ny));
                }
            }
        }
    }
}
