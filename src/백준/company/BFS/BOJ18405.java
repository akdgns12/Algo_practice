package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ18405 {
    // 경쟁적 전염 / 골드 5 / BFS
    /*
        매 초마다 번호가 낮은 종류의 바이러스부터 증식. 증식 과정 중 이미 바이러스가 존재한다면 그곳에는
        다른 바이러스가 들어갈 수 없음.
        S초가 지난후에 (x,y)에 존재하는 바이러스의 종류를 출력하는 프로그램
        만약 해당 위치에 바이러스가 존재하지 않는다면 0 출력

     */

    static int n, k;
    static int S, X, Y;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static ArrayList<Node> virusList = new ArrayList<>();
    static Queue<Node> virus = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0) {
                    virusList.add(new Node(map[i][j], 0, i, j));
                }
            }
        }

        Collections.sort(virusList);
        for (int i = 0; i < virusList.size(); i++) {
            virus.offer(virusList.get(i));
        }

        // 바이러스가 있는지 확인할 위치
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken()); // S초뒤에 (X,Y) 위치에 존재하는 바이러스 종류 출력
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        // 낮은 번호의 바이러스부터 퍼짐
        bfs();

        System.out.println(map[X][Y]);
    }

    // 낮은 번호의 바이러스부터 퍼트리기
    static void bfs(){
        while(!virus.isEmpty()){
            Node cur = virus.poll();

            if(cur.time == S) break;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 1 || ny < 1 || nx > n || ny > n || map[nx][ny] != 0) continue;

                if(map[nx][ny] == 0){
                    map[nx][ny] = cur.num;
                    virus.add(new Node(cur.num, cur.time + 1, nx, ny));
                }
            }
        }
    }

    static class Node implements Comparable<Node>{
        int x, y, time, num;

        public Node(int num, int time, int x, int y) {
            this.num = num;
            this.time = time;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            return this.num - o.num;
        }
    }
}
