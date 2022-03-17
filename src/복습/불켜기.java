package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 불켜기 {
    static int n, m;
    static int[][] map;
    static ArrayList<Node>[][] graph;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        visited = new boolean[n][n];
        graph = new ArrayList[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n;j ++){
                graph[i][j] = new ArrayList<Node>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            graph[x][y].add(new Node(a,b));
        }

        System.out.println(bfs());
    }

    static boolean isCanVisit(int x, int y){
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            if(visited[nx][ny]) return true;
        }
        return false;
    }

    static int bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0,0));
        map[0][0] = 1;
        visited[0][0] = true;

        while(!q.isEmpty()){
            Node node = q.poll();

            // 그래프 순회하며 불 켜기 -> 인접한 곳이고 불을 킬 수 있어야 큐에 넣는다
            for (Node next : graph[node.x][node.y]){
                if(visited[next.x][next.y]) continue;
                if(isCanVisit(next.x, next.y)){
                    visited[next.x][next.y] = true;
                    q.offer(new Node(next.x, next.y));
                }
                map[next.x][next.y] = 1;
            }
            // 인접한 곳 이동
            for(int i=0; i<4; i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(!visited[nx][ny] && map[nx][ny] == 1){
                    q.offer(new Node(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer += map[i][j];
            }
        }

        return answer;
    }

    static class Node{
        int x,y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
