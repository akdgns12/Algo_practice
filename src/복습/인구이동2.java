package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 인구이동2 {
    static int n, l , r;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited;
    static int ans = 0;

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
            boolean isMove = false;

            visited = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(visited[i][j]) continue;

                    if(isUnion(i,j))
                        isMove = true;
                }
            }

            if(isMove) {
                ans++;
            }else{
                System.out.println(ans);
                break;
            }
        }
    }

    static boolean isUnion(int x, int y){
        Queue<Node> q = new LinkedList<>();
        ArrayList<Node> group = new ArrayList<>();

        q.offer(new Node(x,y));
        group.add(new Node(x,y));
        visited[x][y] = true;

        int sum = map[x][y];
        while(!q.isEmpty()){
            Node node = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) continue;

                int dif = Math.abs(map[node.x][node.y] - map[nx][ny]);
                if(dif < l || dif > r) continue;

                q.offer(new Node(nx,ny));
                group.add(new Node(nx,ny));
                visited[nx][ny] = true;
                sum += map[nx][ny];
            }
        }

        if(group.size() == 1){
            return false;
        }else{
            int people = sum / group.size();

            for (Node node : group){
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
