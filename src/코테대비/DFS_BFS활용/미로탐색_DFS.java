package 코테대비.DFS_BFS활용;

import java.util.Scanner;

public class 미로탐색_DFS {
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int answer = 0;

    public static void dfs(int x, int y){
        if (x == 7 && y == 7) {
            answer++;
        }
        else{
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx >= 1 && nx <= 7 && ny >= 1 && ny <= 7 &&
                map[nx][ny] == 0){
                    map[nx][ny]= 1; //빈칸이면 1로 체크
                    dfs(nx, ny);
                    map[nx][ny] = 0; // 체크 풀어준다
                }
            }
        }

    }

    public static void main(String[] args){
        미로탐색_DFS T = new 미로탐색_DFS();
        Scanner sc = new Scanner(System.in);
        map = new int[8][8];
        for (int i = 1; i <= 7; i++) {
            for (int j = 1; j <= 7; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        map[1][1] = 1; // 지나온 곳 1로 체크
        T.dfs(1,1);
        System.out.println(answer);
    }
}
