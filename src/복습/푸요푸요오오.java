package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 푸요푸요오오 {
    static int n = 12, m = 6;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
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

        int cnt = 0;
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
                            for (int k = 0; k < list.size(); k++) {
                                map[list.get(k).x][list.get(k).y] = '.';
                            }
                        }
                    }
                }
            }

            if(isFinished) break;
            gravity();
            cnt++;
        }

        System.out.println(cnt);
    }

    static void gravity(){
        for (int i = 0; i < 6; i++) {
            for (int j = n - 1; j > 0; j--) {
                if(map[j][i] == '.'){
                    for (int k = j - 1; k >= 0; k--) {
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

    static void bfs(int x, int y, char color){
        Queue<Color> q = new LinkedList<>();
        visited[x][y] = true;
        q.offer(new Color(x, y));
        list.add(new Color(x, y));

        while(!q.isEmpty()){
            Color cur = q.poll();

            for (int i = 0; i < 4; i++) {
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

    static class Color{
        int x, y;

        public Color(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
