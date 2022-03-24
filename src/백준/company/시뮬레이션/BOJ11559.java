package 백준.company.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11559 {
    // Puyo Puyo / 골드 5 / 시뮬레이션
    /*
        1. 입력받은 필드를 탐색하며 뿌요가 있는 필드에 도달하면 그 근처에 같은 색의 뿌요가 몇개있는지
        BFS를 통해 탐색
        2. 같은 색의 뿌요가 4개 이상이라면 해당 뿌요들 연쇄
        3. 연쇄가 일어났다면 뿌요들을 아래로 떨어뜨리고 연속 연쇄 수를 하나 늘려준다. 연쇄가 일어나지 않았다면
        탐색 종료
     */
    static class Color{
        int x, y;
        public Color(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static char[][] map;
    static boolean[][] visited;
    static int n = 12, m = 6;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static ArrayList<Color> list;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[n][m];

        for(int i=0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<m; j++){
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
                        bfs(i,j,map[i][j]);

                        if(list.size() >= 4){
                            isFinished = false; // 연쇄가 일어났으므로 더 탐색해야 한다
                            for(int k=0; k<list.size(); k++){
                                map[list.get(k).x][list.get(k).y] = '.'; // 터트려서 없앰
                            }
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
        for(int i=0; i<6; i++){
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

    static void bfs(int x, int y, char color){
        Queue<Color> q = new LinkedList<>();
        list.add(new Color(x,y));
        q.offer(new Color(x,y));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Color cur = q.poll();

            for(int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || ny <0 || nx >= n || ny >= m) continue;
                if(!visited[nx][ny] && map[nx][ny] == color){
                    visited[nx][ny] = true;
                    q.offer(new Color(nx,ny));
                    list.add(new Color(nx,ny));
                }
            }
        }
    }
}
