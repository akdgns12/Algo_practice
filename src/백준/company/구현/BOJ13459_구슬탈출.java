package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13459_구슬탈출 {
    static int N, M;
    static char[][] map;
    static Queue<Marble> q = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'R'){
                    q.offer(new Marble(i, j, 0));
                }else if(map[i][j] == 'B'){
                    q.offer(new Marble(i, j, 0));
                }
            }
        }

        solve();
    }

    static void solve(){
        boolean[][][][] visited = new boolean[N][M][N][M];

    }


    static class Marble{
        int x, y;
        int dist;
        public Marble(int x, int y, int dist){
            this.x = x;
            this.y  = y;
            this.dist = dist;
        }
    }
}
