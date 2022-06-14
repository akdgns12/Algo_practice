package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ22352 {
    // 항체인식 / 골드 5 / BFS
    // 백신 맞기 전 -> 후를 비교해야 하는데, 이 때 백신이 떨어진 부분으로 추정되는 곳을 타겟으로 BFS를 돌려야 한다.
    /*
        1. 백신을 놓기 전과 후 다른 부분을 찾는다.
        2. 백신을 놓은 후(변화된 부분)을 저장하고 -> 놓기 전 부분을 바꿔준다 (동일한 결과가 나오는지)
        3. 이때 놓기 전 부분은 따로 저장하여 BFS 탐색의 조건으로 사용
     */
    static int n, m;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] beforeMap, afterMap;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        beforeMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                beforeMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        afterMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                afterMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean flag = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (beforeMap[i][j] != afterMap[i][j]) {
                    bfs(i, j, afterMap[i][j]);
                    // 백신 놓은 한 곳을 중심으로 bfs 진행한뒤 전과 후 비교해야 하는데
                    // 여기서 반복문 벗어날 flag로 처리해주지 않으면, 한 곳이 아니라 더 bfs
                    // 탐색을 진행하게 되므로 한 곳만 진행할 수 있도록 처리해줌
                    flag = true;
                    break;
                }
            }
            if(flag) break;
        }

        if (check()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static boolean check() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (beforeMap[i][j] != afterMap[i][j]) { // 다르면 NO
                    return false;
                }
            }
        }
        return true;
    }

    static void bfs(int x, int y, int after) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        visited = new boolean[n][m];
        visited[x][y] = true;

        int before = beforeMap[x][y];

        beforeMap[x][y] = after;

        while (!q.isEmpty()){
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]) continue;
                if(beforeMap[nx][ny] == before){
                    q.offer(new Node(nx, ny));
                    visited[nx][ny] = true;
                    beforeMap[nx][ny] = after;
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
