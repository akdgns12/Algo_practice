package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2665 {
    // 미로만들기 / 골드4 / BFS
    // 일부분은 검은 방, 나머지는 흰 방. 시작방에서 끝방으로 가야하는데, 경로가 없을 경우 검은 방을 흰방으로 바꿔야함
    // 바꿔야할 검은방 최소로 리턴
    // 평소처럼 boolean으로 visited 배열을 만들어 체크하는 것이 아닌, int형으로 각 위치마다 방을 바꾼 횟수를 저장시키며 나아가는 방법
    // 시작할때 visited 배열을 모두 무한대로 초기화 해두고, BFS를 진행하면서 해당 위치에 최소값을 저장해 나가는 것
    // 벽을 만났을 때, 해당 벽을 기준으로 visited배열의 해당 위치값을 +1로 증가시키며 탐색을 진행한다.
    // BFS는 큐가 완전히 빈 상태가 될 때까지 상하좌우로 뻗어나가기 때문에, 같은 위치가 여러번 들어올 것이다. 이때 들어오는 값들 중 가장 최소 값을 저장하면 된다.
    // (즉, 이미 값이 존재할 때 새로 들어온 값보다 작으면 continue)
    /*
        생각 - 그냥 시작점에서 끝점으로 가는 최단거리를 BFS로 구하면서 지나온 검은 칸의 갯수 중 최소 값을 저장하면 될듯?
     */
    static int n;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new int[n][n];

        // 0 : 검은방, 1 : 흰방
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j) - '0';
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        bfs(0,0);
        System.out.println(visited[n-1][n-1]);
    }

    static void bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        visited[x][y] = 0; // 시작값은 0,0

        while(!q.isEmpty()){
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

                if(visited[nx][ny] <= visited[now.x][now.y]) continue; // 만약 옮기기 전의 값보다 옮긴 값에 이미 더 작은 값이 들어있으면 pass

                if(map[nx][ny] == '1'){ // 이동할 수 있는 곳이면 visited은 이전값 넣어줌
                    q.offer(new Node(nx, ny));
                    visited[nx][ny] = visited[now.x][now.y];
                }
                else{ // 이동할 수 없는 벽이면, 값을 + 1
                    q.offer(new Node(nx, ny));
                    visited[nx][ny] = visited[now.x][now.y] + 1;
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
