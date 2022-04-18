package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9205 {
    // 맥주 마시면서 걸어가기 / 실버1 / BFS
    /*
        출발은 상근이네 집, 맥주 한박스에는 맥주 20개, 50미터에 한병씩
        편의점에 들릴 수 있는데 빈병은 버리고 새 맥주를 삼. 하지만 맥주는 20병을 넘을 수 없음
        편의점을 나선 직후에도 50미터를 가기전 맥주 한병을 마셔야함.
        편의점, 상근이네 집, 목적지 좌표가 주어짐.

        생각
        50m에 맥주 한캔 먹음 그런 맥주 캔을 20캔 가지고 있다
        20 * 50 = 1000,즉 1000미터 이하의 거리에 편의점이 있어야 함 -> 목적지에 도착할 때까지

        문제 자체는 쉬운데 문제 자체의 의도를 파악하느라 좀 힘들었음
     */
    static int t;
    static int n, x, y;
    static int endX, endY;
    static ArrayList<Node> store; // 편의점 좌표 저장할 구조체

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine()); // 편의점 개수

            // 출발점
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            store = new ArrayList<>();
            for (int i = 0; i < n; i++) { // 편의점 좌표
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());

                store.add(new Node(x,y));
            }
            // 목적지
            st = new StringTokenizer(br.readLine());
            endX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());

            System.out.println(bfs(startX, startY) ? "happy\n" : "sad\n");
        }
    }

    static boolean bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[n]; // 편의점 방문체크
        q.offer(new Node(x, y));

        while (!q.isEmpty()) {
            Node node = q.poll();
            int nowX = node.x;
            int nowY = node.y;

            if (Math.abs(nowX - endX) + Math.abs(nowY - endY) <= 1000) { // 현재좌표에서 도착점까지 1000미터 이내면 갈 수 있으니 true 리턴
                return true;
            }

            for (int i = 0; i < n; i++) {
                if(!visited[i]){
                    int nx = store.get(i).x;
                    int ny = store.get(i).y;
                    int dist = Math.abs(nowX - nx) + Math.abs(nowY - ny);
                    if (dist <= 1000) {
                        visited[i] = true;
                        q.offer(new Node(nx, ny));
                    }
                }
            }
        }
        return false;
    }

    static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
