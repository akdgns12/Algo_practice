package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2638 {
    // 치즈 / 골드4 / 구현
    static int n, m;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        // 1 : 치즈, 0 : 치즈가 없는 부분
        int sum = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) sum += 1;
            }
        }

        // 치즈가 모두 녹아 없어지는데 걸리는 시간, 한 턴에 2변 이상이 공기와 접촉한 치즈는 녹음.
        int time = 0;
        while (true) {
            if(sum == 0) break; // 치즈개수가 0이 되면 종료
            int[][] check = new int[n][m]; // 접촉한 공기의 수를 셀 배열
            boolean[][] visited = new boolean[n][m];
            Queue<Node> q = new LinkedList<>();
            q.offer(new Node(0, 0));
            visited[0][0] = true;

            // 큐에 치즈가 아닌 곳을 넣어주며 공기 주변을 탐색
            while(!q.isEmpty()){
                Node node = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = node.x + dx[d];
                    int ny = node.y + dy[d];

                    // 범위 벗어나거나 방문했던 곳이라면 pass
                    if(nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]) continue;
                    if(map[nx][ny] == 1){ // 치즈가 있는 곳이라면
                        check[nx][ny]++; // 접촉한 공기 수 +1 하고 pass
                        continue;
                    }

                    visited[nx][ny] = true;
                    q.offer(new Node(nx, ny));
                }
            }

            // 체크된 수가 2이상이라면 해당 좌표 치즈 녹아야함.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(check[i][j] > 1){
                        map[i][j] = 0;
                        sum--;
                    }
                }
            }

            time++;
        }

        System.out.println(time);
    }

    static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
