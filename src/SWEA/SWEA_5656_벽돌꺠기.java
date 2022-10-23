package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌꺠기 {
    static int N, W, H;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 구슬 떨어트리는 횟수
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            map = new int[H][W];
            for(int i=0; i<H; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<W; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans = Integer.MAX_VALUE;
            dfs(0, map);

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void dfs(int cnt, int[][] map){
        if(cnt == N){
            int result = calc(map);
            ans = Math.min(ans, result);
            return;
        }

        int[][] copy = new int[H][W];
        for(int i=0; i<W; i++){
            int x = 0;
            while (x < H && map[x][i] == 0) x++;
            if(x == H){
                dfs(cnt + 1, map);
            }else{
                copyMap(map, copy);
                crush(x, i, copy);
                downMap(copy);
                dfs(cnt + 1, copy);
            }
        }
    }

    static void crush(int x, int y, int[][] copy){
        Queue<Node> q = new LinkedList<>();
        if(copy[x][y] > 1){
            q.offer(new Node(x, y, copy[x][y]));
        }
        copy[x][y] = 0;

        while (!q.isEmpty()){
            Node now = q.poll();

            for(int i=0; i<4; i++){
                int nx = now.x;
                int ny = now.y;

                for(int k=1; k<now.size; k++){
                    nx += dx[i];
                    ny += dy[i];

                    if(nx >= 0 && ny >= 0 && nx < H && ny < W && copy[nx][ny] != 0){
                        if(copy[nx][ny] > 1){
                            q.offer(new Node(nx, ny, copy[nx][ny]));
                        }
                        copy[nx][ny] = 0;
                    }
                }
            }
        }
    }

    static class Node{
        int x, y;
        int size;

        public Node(int x, int y, int size){
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }

    static void downMap(int[][] copy){
        Queue<Integer> q;

        for(int i=0; i<W; i++){
            q = new LinkedList<>();

            for(int j=H-1; j>=0; j--){
                if(copy[j][i] != 0) {
                    q.offer(copy[j][i]);
                    copy[j][i] = 0;
                }
            }

            int x = H-1;
            while (!q.isEmpty()){
               copy[x--][i] = q.poll();
            }
        }
    }

    static void copyMap(int[][] map, int[][] copy){
        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++){
                copy[i][j] = map[i][j];
            }
        }
    }

    static int calc(int[][] map){
        int sum = 0;
        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++){
                if(map[i][j] > 0) sum++;
            }
        }
        return sum;
    }
}
