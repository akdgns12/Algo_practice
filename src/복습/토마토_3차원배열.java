package 복습;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 토마토_3차원배열 {
    static int m,n,h;
    static int[][][] map;
    static int[] dx = {0,0,0,0,1,-1}; // 위 아래 왼 오 앞 뒤
    static int[] dy = {0,0,-1,1,0,0};
    static int[] dz = {1,-1,0,0,0,0};
    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        m = sc.nextInt(); // 가로
        n = sc.nextInt(); // 세로
        h = sc.nextInt();

        map = new int[h][n][m];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for(int k=0; k<m; k++){
                    map[i][j][k] = sc.nextInt();
                    if(map[i][j][k] == 1) q.offer(new Node(i, j, k));
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs(){
        while(!q.isEmpty()){
            Node node = q.poll();

            for (int i = 0; i < 6; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                int nz = node.z + dz[i];

                if(nx >= 0 && ny >= 0 && nz >= 0 && nx < n && ny < m && nz < h){
                    if(map[nz][nx][ny] == 0){
                        q.offer(new Node(nz, nx, ny));
                        map[nz][nx][ny] = map[node.z][node.x][node.y] + 1;
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if(map[i][j][k] == 0) // 모두 다 익지 않았다면
                        return -1;
                    result = Math.max(result, map[i][j][k]);
                }
            }
        }

        if(result == 0) // 최대값 구했는데도 0이면 모두다 익어있는 상태
            return 0;
        else
            return result - 1; // 일수를 구하는 것이므로 -1 해준다 ( 토마토가 1이기때문에 +1된 상황)
    }

    static class Node{
        int x, y, z;
        public Node(int z, int x, int y){
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }
}
