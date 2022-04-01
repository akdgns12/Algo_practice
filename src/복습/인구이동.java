package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 인구이동 {
    static int n,l,r;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int answer= 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            boolean isMove = false; // 연합이 일어났는지 안일어났는지 체크하는 변수

            visited = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // 이미 연합에 속한 나라는 무시
                    if(visited[i][j]) continue;

                    if(union(i,j))
                        isMove = true;
                }
            }

            // 연합이 일어난다면 횟수 증가
            if(isMove){
                answer++;
                // 연합이 일어나지 않는다면 종료
            }else{
                System.out.println(answer);
                break;
            }
        }
    }

    // (x,y)의 나라를 기준으로 조건에 부합하는 나라들을 연합하는 함수
    static boolean union(int x, int y){
        Queue<Node> q = new LinkedList<>();
        ArrayList<Node> group = new ArrayList<>(); // (연합의 인구수) / (연합을 이루고 있는 칸의 개수)의 조건에서 (연합을 이루고 있는 칸의 개수)를 알기 위해 사용

        q.offer(new Node(x,y));
        group.add(new Node(x,y));
        visited[x][y] = true;

        int sum = map[x][y]; // (연합의 인구수)를 파악하기 위해 사용하는 변수
        while(!q.isEmpty()){
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) continue;

                int dif = Math.abs(map[cur.x][cur.y] - map[nx][ny]); // 이웃한 나라와의 인구수 차이
                if(dif < l || dif > r) continue; // 연합할 수 없는 조건 무시

                sum += map[nx][ny]; // 인구수 누적
                q.offer(new Node(nx,ny));
                group.add(new Node(nx,ny));
                visited[nx][ny] = true;
            }
        }

        // 연합할 수 있는 나라가 없는 경우
        if(group.size() == 1){
            return false;
        }else{
            // 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)
            int people = sum / group.size();

            for (Node node : group) {
                map[node.x][node.y] = people;
            }
            return true;
        }

    }

    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
