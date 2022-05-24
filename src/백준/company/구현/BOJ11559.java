package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ11559 {
    // 푸요푸요 / 골드 4 / 구현
    /*
        같은 색이 상하좌우 4개 이상 모이게 되면 터짐,
        연쇄가 몇 번 연속으로 일어날지 계산하라
     */
    // 1. 필드를 탐색하며 뿌요가 있는 필드에 도달하면 그 근처에 같은 색의 뿌요가 몇개있는지 BFS를 통해 탐색
    // 2. 같은 색의 뿌요가 4개 이상이라면 해당 뿌요들 인쇄
    // 3. 연쇄가 일어났다면 뿌요들을 아래로 떨어뜨리고, 연쇄 수 증가, 연쇄가 일어나지 않았다면 탐색 종료
    static char[][] map;
    static boolean[][] visited;
    static int n = 12, m = 6;
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
            boolean isFinished = true; // 연쇄 종료 여부
            visited = new boolean[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(map[i][j] != '.'){ // 빈칸이 아니라 뿌요가 있다면
                        list = new ArrayList<>();
                        bfs(i, j, map[i][j]);

                        if(list.size() >= 4){
                            isFinished = false; // 연쇄가 일어났으므로 더 탐색해야 한다.
                            for (int k = 0; k < list.size(); k++) {
                                map[list.get(k).x][list.get(k).y] = '.'; // 터트려서 없앰
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

    static void bfs(int x, int y, char color){
        Queue<Color> q = new LinkedList<>();
        q.offer(new Color(x,y));
        visited[x][y] = true;
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

    static void gravity(){
        for (int i = 0; i < 6; i++) {
            for (int j = n-1; j > 0; j--) {
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

    static class Color{
        int x, y;

        public Color(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
