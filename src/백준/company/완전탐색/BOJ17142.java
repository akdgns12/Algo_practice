package 백준.company.완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17142 {
    // 연구소3 / 골드4 / 완탐
    // 초기에 바이러스는 비활성 상태, 그 중 M개를 선택해 활성상태로 하려함
    // 연구소에 모든 빈 칸에 바이러스를 퍼뜨리는 최소 시간
    static int n, m;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited;
    static ArrayList<Node> virus = new ArrayList<>();
    static Node[] active;
    static int answer = Integer.MAX_VALUE;
    static int emptySpace = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        active = new Node[m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2){
                    virus.add(new Node(i, j, 0));
                }else if(map[i][j] == 0) emptySpace++;
            }
        }

        dfs(0, 0);

        if(emptySpace == 0){
            System.out.println(0);
        }else{
            dfs(0, 0);
            System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
        }
    }

    // m개의 바이러스 선택
    static void dfs(int start, int cnt) {
        if(cnt == m){
            bfs(emptySpace);
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            active[cnt] = virus.get(i);
            dfs(i+1, cnt+1);
        }
    }

    // 바이러스 퍼트리기
    static void bfs(int emptySpace){
        Queue<Node> q = new LinkedList<>();
        visited = new boolean[n][n];

        for (int i = 0; i < m; i++) {
            Node node = active[i];
            visited[node.x][node.y] = true;
            // 큐에 활성화된 바이러스 넣기
            q.offer(node);
        }

        while(!q.isEmpty()){
            Node node = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                // 범위 벗어나거나, 벽이거나, 방문했던 곳이라면 pass
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(visited[nx][ny]|| map[nx][ny] == 1) continue;

                if(map[nx][ny] == 0){ // 빈공간이라면
                    emptySpace--;
                }

                if(emptySpace == 0){
                    answer = Math.min(answer, node.time + 1);
                    return;
                }

                visited[nx][ny] = true;
                q.offer(new Node(nx, ny, node.time + 1));
            }
        }
    }

    static class Node{
        int x, y;
        int time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
