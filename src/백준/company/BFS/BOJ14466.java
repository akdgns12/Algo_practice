package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ14466 {
    // 소가 길을 건너간 이유 6 / 골드 4 / BFS
    /*
        어떤 두 소는 길을 건너지 않으면 만나지 못한다. 이런 소가 몇 쌍인지 세어보자
     */
    // 소의 위치를 시작점으로 bfs를 통해 목초지 탐색, 다리가 존재하는 경우는 탐색 x
    // 탐색이 끝나고 다른 소 위치에 대한 방문 여부를 체크하는데 이때 방문이 되지 않는 경우는 다리를 지나지 않으면 만날 수 없다는 것을 의미하기 때문에
    // 해당 경우를 모두 찾으면 된다.
    // 각각의 소에서 다리를 이용하지 않고 다른 소들을 방문할 수 있는지 물어보는 문제

    static int n, k, r;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<Node> bridge; // 길 정보 저장
    static ArrayList<Node> cow; // 소의 위치 정보 저장
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken()); // k마리의 소
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i=0; i<n; i++) Arrays.fill(map[i], -1);


        visited = new boolean[n][n];
        bridge = new ArrayList<>();

        for (int i = 0; i < r; i++) { // 길 정보
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;

            bridge.add(new Node(x1, y1, x2, y2));
        }

        cow = new ArrayList<>();
        for (int i = 0; i < k; i++) { // 소의 위치
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            cow.add(new Node(x, y));
        }

        // 소가 있는 위치를 기준으로 탐색 시작
        for (int i = 0; i < k; i++) {
            if(map[cow.get(i).x][cow.get(i).y] == -1){ // 이미 탐색이 된 곳이라면 pass
                bfs(cow.get(i), i);
            }
        }

        int cnt = 0;
        for (int i = 0; i < k - 1; i++) {
            for (int j = i + 1; j < k; j++) {
                // 영역이 다른 곳에 있으면 길을 건너야 하므로 cnt++
                if(map[cow.get(i).x][cow.get(i).y] != map[cow.get(j).x][cow.get(j).y]) cnt++;
            }
        }

    }

    // bfs를 이용해 길을 건너는 경우를 제외하고 만날 수 있는 소들을 탐색
    static void bfs(Node cow, int area) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(cow.x, cow.y));
        visited = new boolean[n][n];
        visited[cow.x][cow.y] = true;
        map[cow.x][cow.y] = area;

        while (!q.isEmpty()){
            Node now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x1 + dx[i];
                int ny = now.y1 + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) continue;

                for (Node next : bridge) { // 둘 사이에 다리가 있는지 확인
                    if(next.x1 == now.x && next.y1 == now.y && next.x2 == nx && next.y2 == ny) continue;
                    if(next.x2 == now.x && next.y2 == now.y && next.x1 == nx && next.y1 == ny) continue;
                }
                q.offer(new Node(nx, ny));
                visited[nx][ny] = true;
                map[nx][ny] = area; // 탐색한 자리의 영역을 표시
            }
        }
    }

    static class Node{
        int x1, y1, x2, y2;
        int x, y;

        public Node(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
