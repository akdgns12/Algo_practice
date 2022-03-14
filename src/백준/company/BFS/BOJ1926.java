package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * static int max = Integer.MIN_VALUE
 * 이것때문에 계속 틀렸다... max = 0 으로 수정해주고 통과
 * 이유가 뭐지..?
 * 아! 문제 좀 끝까지 읽자 영훈아..
 * 단, 그림이 하나도 없는 경우에는 가장 넓은 그림의 넓이는 0이다. -> 문제에 써진 조건
 * 그림이 하나도 없는 경우 Integer.MIN_VALUE가 max값이 된다... 이럼 안되잖아!
 */
public class BOJ1926 {
    // 그림 / 실버 1 / BFS
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1,};
    static int picture = 0;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(!visited[i][j] && map[i][j] == 1){
                    visited[i][j] = true;
                    bfs(i,j);
                    picture++; // 그림의 개수 증가
                }
            }
        }

        System.out.println(picture);
        System.out.println(max);
    }

    static void bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x,y));
        int area = 1; // 그림의 넓이 변수

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(visited[nx][ny] || map[nx][ny] == 0) continue;
                visited[nx][ny] = true;
                q.offer(new Node(nx, ny));
                area++;
            }
        }

        max = Math.max(max, area);
    }

    static class Node{
        int x,y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
