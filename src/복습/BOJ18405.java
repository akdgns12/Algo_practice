package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ18405 {
    static int n, k;
    static int[][] map;
    static int S,X,Y;
    static ArrayList<Node> virusList = new ArrayList<>();
    static Queue<Node> q = new LinkedList<>();
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] !=  0){
                    virusList.add(new Node(i, j, map[i][j], 0));
                }
            }
        }

        Collections.sort(virusList);
        for (int i = 0; i < virusList.size(); i++) {
            q.offer(virusList.get(i));
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        bfs();
        System.out.println(map[X][Y]);
    }

    static void bfs(){
        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.time == S) break;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || ny < 1 || nx > n || ny > n || map[nx][ny] != 0) continue;

                if(map[nx][ny] == 0){
                    map[nx][ny] = cur.num;
                    q.offer(new Node(nx, ny, cur.num, cur.time + 1));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int x, y, num, time;

        public Node(int x, int y, int num, int time) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Node o){
            return this.num - o.num;
        }
    }
}
