package 코테대비.DFS_BFS활용;

import java.util.Scanner;

public class 섬나라아일랜드_DFS {
    static int n;
    static int[][] map;
    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,1,1,1,0,-1,-1,-1};
    static int answer = 0;

    public static void dfs(int x, int y, int[][] map){
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx >= 0 && nx < n && ny >= 0 && ny < n
            && map[nx][ny] == 1){
                map[nx][ny] = 0;
                dfs(nx,ny,map);
            }
        }
    }

    public static void solution(int[][] map){
        for (int i = 0; i < n; i++) {
            for(int j=0; j<n; j++)
                if(map[i][j] == 1){
                    answer++;
                    map[i][j] = 0;
                    dfs(i,j, map);
                }
        }
    }
    public static void main(String[] args) {
        섬나라아일랜드_DFS T = new 섬나라아일랜드_DFS();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        T.solution(map);
        System.out.println(answer);
    }
}
