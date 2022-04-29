package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2234 {
    // 성곽 / 골드4 / BFS + 비트마스킹(데이터 정제하면서 사용해야 편함)
    // 생각먼저 생각!
    /*
        1. 이 성에 있는 방의 개수
        2. 가장 넓은 방의 넓이
        3. 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
     */
    static int n, m;
    static int[][] map;
    static int[] dx = {0,-1,0,1}; // 서 북 동 남
    static int[] dy = {-1,0,1,0};
    // 3번 조건인 하나의 벽 제거하여 얻을 수 있는 가장 넓은 방의 크기 구하기 위해
    static int[][] check;
    static int room;
    static int[] roomSize = new int[2501];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        check = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // bfs탐색으로 초기 상태의 방 갯수와 가장 넓은방 찾기
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(check[i][j] == 0){
                    room++; // 방 갯수 증가
                    check[i][j] = room; // 방 번호 부여
                    max = Math.max(max, bfs(i, j)); // 가장 넓은 방 찾기
                }
            }
        }

        // 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                    if(check[i][j] != check[nx][ny]){ // 둘이 다른방인데 벽을 사이에 두고 붙어있다면
                        ans = Math.max(ans, roomSize[check[i][j]] + roomSize[check[nx][ny]]);
                    }
                }
            }
        }

        System.out.println(room);
        System.out.println(max);
        System.out.println(ans);
    }

    // 이 성에 있는 방의 개수, 가장 넓은 방의 개수
    static int bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x,y));

        int count = 1; // 방의 넓이 1로 시작
        while(!q.isEmpty()){
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                if((map[now.x][now.y] & 1 << i) == 0){ // 해당 방향에 벽이 없는지 확인
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if(nx < 0 || ny < 0 || nx >= m || ny >= n) continue;

                    if(check[nx][ny] == 0){
                        q.offer(new Node(nx, ny));
                        count++;
                        check[nx][ny] = room;
                    }
                }
            }
        }

        roomSize[room] = count; // 각방의 넓이 저장

        return count;
    }

    static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
