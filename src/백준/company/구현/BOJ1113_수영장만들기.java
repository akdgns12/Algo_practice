package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1113_수영장만들기 {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int N, M, maxHeight;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = str.charAt(j) - '0';
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        // ans : 수영장에 물이 얼마만큼 있을 수 있을지
        /*
            음..바로 떠오르진 않네
            1 <= h <= 9, 높이 범위 크지않으니 1~maxHeight까지
            현재 h로 물을 채울 수 있다면 채우기
         */
        solve();
        System.out.println(ans);
    }

    static void solve(){
        for(int h=2; h<=maxHeight; h++){
            visited = new boolean[N][M]; // 높이마다 다시 초기화
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(!visited[i][j] && map[i][j] < h){ // 방문한적 없고, 물 채울 수 있는 곳이면
                        ans += bfs(i, j, h);
                    }
                }
            }
        }
    }

    static int bfs(int x, int y, int h){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        visited[x][y] = true;
        int cnt = 1;

        boolean outOfRange = false;
        while(!q.isEmpty()){
            Node now = q.poll();

            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                // 와 여기서 리턴치면 안되지 영훈아.. 착각한점 : 하나라도 벗어나면 어차피 나머지도 모두 넘치니까 리턴쳐도 된다생각함.
                // 근데 큐에 넣어놓은 나머지 좌표 모두의 주변 4방중 넘치는게 있는지 알아야 하니까 모두 검사해야함
                if(!isRange(nx, ny)){
                    outOfRange = true;
                    continue;
                }

                if(!visited[nx][ny] && map[nx][ny] < h){ // 방문한적 없고 h보다 낮다면
                    q.offer(new Node(nx, ny));
                    visited[nx][ny] = true;
                    cnt++;
                }
            }
        }

        if(outOfRange) return 0;
        else return  cnt;
    }

    static boolean isRange(int x, int y){
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
