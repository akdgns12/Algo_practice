package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ점프게임 {
    static int n, k;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[2][n];
        visited = new boolean[2][n];

        for (int i = 0; i < 2; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    static int bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0));
        visited[0][0] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            // 한 칸 앞 이동
            if(now.x + 1 >= n){
                return 1;
            }else if(!visited[now.line][now.x+1] && map[now.line][now.x+1] == 1){
                q.offer(new Node(now.line, now.x + 1, now.time + 1));
                visited[now.line][now.x + 1] = true;
            }

            // 한 칸 뒤 이동
            if(now.x - 1 > 0){
                if(!visited[now.line][now.x-1] && map[now.line][now.x-1] == 1 && now.x - 1 > now.time){
                    q.offer(new Node(now.line, now.x - 1, now.time + 1));
                    visited[now.line][now.x - 1] = true;
                }
            }

            if(now.x + k >= n){
                return 1;
            } else if (!visited[1 - now.line][now.x + k] && map[1 - now.line][now.x + k] == 1) {
                q.offer(new Node(1 - now.line, now.x + k , now.time + 1));
                visited[1-now.line][now.x+k] = true;
            }
        }

        return 0;
    }

    static class Node{
        int line;
        int x;
        int time;

        public Node(int line, int x, int time) {
            this.line = line;
            this.x = x;
            this.time = time;
        }
    }
}
