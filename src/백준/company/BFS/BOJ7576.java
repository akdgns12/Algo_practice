package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7576 {
    // 토마토 / 골드 5 / BFS
    static int n, m;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    q.offer(new Node(i,j));
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs(){
        while(!q.isEmpty()){
            Node node = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                // 범위 벗어나면 스킵
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                // 익지 않은 토마토가 아니라면 스킵
                if(map[nx][ny] != 0) continue;
                map[nx][ny] = map[node.x][node.y] + 1;
                q.offer(new Node(nx, ny ));
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 토마토가 안익은게 있으면
                if(map[i][j] == 0) return -1;
                // 최대일수 구하기
                max = Math.max(max, map[i][j]);
            }
        }

        // 토마토가 모두 익은 상태일 경우
        if(max == 1)
            return 0;
        // 걸린 일수는 최대 날짜에서 1 빼줘야 함
        else
            return max - 1;
    }

    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
