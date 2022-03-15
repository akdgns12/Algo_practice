package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class BOJ11967 {
    static int n, m;
    static ArrayList<Node>[][] graph;
    static boolean[][] visited;
    static int[][] switched;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                graph[i][j] = new ArrayList<Node>();
            }
        }
        visited = new boolean[n][n];
        switched = new int[n][n];

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
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            if(visited[nx][ny]) return true;
        }
        return false;
    }

    // 불켜고 -> 불킨곳이 인접한 곳인지! -> 인접한 곳이라면 이동
    static int bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0,0));
        switched[0][0] = 1;
        visited[0][0] = true;

        while(!q.isEmpty()){
            Node node = q.poll();

            for(Node next : graph[node.x][node.y]){
                if(visited[next.x][next.y]) continue;
                if(isCanVisit(next.x, next.y)){
                    q.offer(new Node(next.x, next.y));
                    visited[next.x][next.y] = true;
                }
                switched[next.x][next.y] = 1;
            }

            for(int i=0; i<4; i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n ) continue;
                if(visited[nx][ny] || switched[nx][ny] == 0) continue;
                visited[nx][ny] = true;
                q.offer(new Node(nx, ny));
            }
        }

        int answer = 0;
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                answer += switched[i][j];

        return answer;
    }

    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}