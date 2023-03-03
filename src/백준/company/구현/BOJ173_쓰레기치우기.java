package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ173_쓰레기치우기 {
    static int N, M;
    static int[][] map;
    static int totalTrash = 0;
    static Queue<Node> q = new LinkedList<>();
    static int[] dx = {0, -1}; // 좌 상
    static int[] dy = {-1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] > 0) {
                    totalTrash++;
                    q.offer(new Node(i, j));
                }
            }
        }

        int ans = 0;
        while(totalTrash > 0){
            Node now = q.poll();

            int x = now.x;
            int y = now.y;

            ans++;
            int nx = x, ny = y;
            
        }

        System.out.println(ans);
    }

    static boolean RangeCheck(int x, int y){
        if(x < 0 || y < 0 || x >= N || y >= M) return false;
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
