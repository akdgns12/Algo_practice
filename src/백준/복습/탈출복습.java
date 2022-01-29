package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 탈출복습 {
    /*
        물을 먼저 이동시키고 비버 이동(물이 찰 예정인 칸으로 고슴도치는 이동할 수 없기 때문)
        필요한 자료구조
        비버의 좌표관리할 class
        물이 퍼질 좌표 관리할 class

     */
    static class Node {
        int x, y;
        int time;

        public Node(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static int r, c;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Node> water = new LinkedList<>();
    static Queue<Node> biber = new LinkedList<>();
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'S') {
                    biber.add(new Node(i, j, 0));
                } else if (map[i][j] == '*') {
                    water.add(new Node(i, j));
                }
            }
        }

        bfs();
        System.out.println(min == Integer.MAX_VALUE ? "KAKTUS" : min);
    }

    // 물이 먼저 이동하고
    // 그다음 비버가 이동한다
    /*
        물의 사이즈만큼
     */

    /**
     * water, biber 사이즈 만큼 돌려주는 이유는
     * -> 기존에 큐에 들어있던 물만 퍼져야 하고,
     * -> 기존에 큐에 들어있던 비버의 위치에서 퍼져야 하기 때문에
     */
    static void bfs() {
        while(!biber.isEmpty()) {
            int len = water.size();
            // 물퍼트리기
            for(int i=0; i<len; i++) {
                Node p = water.poll();
                int x = p.x;
                int y = p.y;

                for(int j=0; j<4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    // 범위를 벗어나면 skip
                    if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                    // 인접한 좌표가 도착지이거나 벽이거나 물이 차있는지역이라면 skip
                    if(map[nx][ny] == 'D' || map[nx][ny] == 'X' || map[nx][ny] == '*') continue;
                    map[nx][ny] = '*';
                    water.add(new Node(nx, ny));
                }
            }

            len = biber.size();
            for(int i=0; i<len; i++) {
                // 고슴도치 이동
                Node p = biber.poll();
                int x = p.x;
                int y = p.y;
                int time = p.time;

                for(int j=0; j<4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                    if(map[nx][ny] == 'D') {
                        min = Math.min(min, time+1);
                        return;
                    }
                    else if(map[nx][ny] == '.') {
                        map[nx][ny] = 'S';
                        biber.add(new Node(nx, ny, time+1));
                    }
                }
            }

        }
}
