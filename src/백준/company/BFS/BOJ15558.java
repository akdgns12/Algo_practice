package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ15558 {
    // 점프게임 / 실버1 / bfs
    static int n, k;
    static int[][] map; // 왼쪽줄과 오른쪽 줄의 정보를 담을 map
    static boolean[][] visited; // 방문 여부를 검사할 visited

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken()); // 반대편 줄로 점프할 경우 이동하는 칸수

        map = new int[2][n];
        visited = new boolean[2][n];
        // 0:위험한 칸, 1:안전한 칸
        for (int i = 0; i < 2; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        // 게임 클리어 가능하면 1, 아니면 0
        // 초기 시작은 왼쪽줄의 1번 칸
        System.out.println(bfs());
    }

    static int bfs(){
        Queue<Node> q = new LinkedList<>();
        visited[0][0] = true;
        q.offer(new Node(0, 0, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();

            // 한 칸 앞으로 이동하는 경우
            if(now.x + 1 >= n){
                return 1;
            }else if(!visited[now.line][now.x+1] && map[now.line][now.x + 1] == 1){
                visited[now.line][now.x + 1] = true;
                q.offer(new Node(now.line, now.x + 1, now.time + 1));
            }

            // 옆 줄로 점프해 k칸 앞으로 이동하는 경우
            if (now.x + k >= n) {
                return 1;
            }else if(!visited[1-now.line][now.x + k] && map[1-now.line][now.x + k] == 1){ // 옆 줄이고 k칸 이동한적이 없고, 해당 칸이 1이라면 이동
                visited[1 - now.line][now.x + k] = true;
                q.offer(new Node(1 - now.line, now.x + k, now.time + 1));
            }

            // 한 칸 뒤로 이동하는 경우
            if(now.x - 1 > 0){
                if (!visited[now.line][now.x - 1] && map[now.line][now.x - 1] == 1 && now.x - 1 > now.time) {
                    visited[now.line][now.x - 1] = true;
                    q.offer(new Node(now.line, now.x - 1, now.time + 1));
                }
            }
        }

        // 여기까지 오면 탈출 불가하므로 0리턴
        return 0;
    }


    static class Node{
        int line;
        int x;
        int time;

        public Node(int line, int x, int time) {
            this.line = line;
            this.x = x;
            this.time = time;
        }
    }
}
