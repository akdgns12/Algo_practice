package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 무한부스터 {
    /*
        0,0에서 출발하여 n-1,m-1에 도착하는 즉시 게임 종료
        우,하 둘 중 하나 x칸
        아이템을 획득하는 부스터의 최소 갯수
     */
    static int n, m;
    static int[][] map, cnt;
    static int[] dx = {0,1};
    static int[] dy = {1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        cnt = new int[n][m]; // 이동횟수 저장

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                cnt[i][j] = Integer.MAX_VALUE;
            }
        }

        bfs();
        System.out.println(cnt[n-1][m-1]);
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0));
        cnt[0][0] = 0;

        while (!q.isEmpty()){
            Node now = q.poll();

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < map[now.x][now.y]; j++) { // 부스터만큼 반복하면서 값을 더함
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                    if (cnt[nx][ny] > cnt[now.x][now.y] + 1) { // 범위는 안넘어가고, 가려는 값보다 현재 값 + 1이 더 작으면 갱신
                        cnt[nx][ny] = cnt[now.x][now.y] + 1;
                        q.offer(new Node(nx, ny));
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






