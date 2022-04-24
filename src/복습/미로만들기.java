package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미로만들기 {
    /*
        첫 노드에서 오른쪽 아래 노드로 가는 최단거리 중에서 검은색 벽을 가장 적게 바꾸는 방법의 횟수를 출력하는 문제 벽 부수고 이동하기 문제와 유사
        검은색 벽을 흰색 벽으로 바꾸는 최소 횟수를 누적하여 계산하며 탐색하기 위해 다익스트라 방법을 사용. dist배열을 사용하여 현재 위치의 가장 최소 횟수를
        저장할 수 있도록 했다.
        현재 거리보다 더 적은 횟수로 이동할 수 있는 방법이 잇다면 그 방향으로 이동시켜 주었다. 이때 map이 1이면 횟수를 더해주지 않았고, 0이면 흰색으로 변경해
        주어야 하므로 횟수를 누적하여 더해주었다.
     */
    static int n;
    static int[][] map;
    static int[][] dist;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        bfs();
        System.out.println(dist[n-1][n-1]);
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0));
        dist[0][0] = 0;

        while(!q.isEmpty()){
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

                // MAX_VALUE로 초기화 해놓았기 때문에

                if(map[nx][ny] == '1'){ // 다음 위치가 흰방이면
                    if (dist[nx][ny] > dist[now.x][now.y]) {
                        q.offer(new Node(nx, ny));
                        dist[nx][ny] = dist[now.x][now.y];
                    }
                }
                else{
                    if(dist[nx][ny] > dist[now.x][now.y]){ // 다음 위치가 검은방이면
                        q.offer(new Node(nx, ny));
                        dist[nx][ny] = dist[now.x][now.y] + 1; // 검은방 누적해서 더해줌
                    }
                }

            }
        }
    }

    static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
