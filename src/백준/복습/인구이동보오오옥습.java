package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 인구이동보오오옥습 {
    static int n,l,r;
    static int[][] map;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static boolean[][] visited;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j <n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        while(true){
            boolean isMove = false;
            visited = new boolean[n][n];

            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(visited[i][j]) continue;

                    if(union(i,j)){
                        isMove = true;
                    }
                }
            }
            if(isMove){
                answer++;
            }else{
                System.out.println(answer);
                break;
            }
        }
    }

    static boolean union(int x, int y){
        Queue<Node> q = new LinkedList<>();
        ArrayList<Node> list = new ArrayList<>();

        visited[x][y] = true;
        q.offer(new Node(x,y));
        list.add(new Node(x,y));

        int sum = map[x][y];
        while(!q.isEmpty()){
            Node node = q.poll();

            for(int i=0; i<4; i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

                if(visited[nx][ny]) continue;

                int dif = Math.abs(map[node.x][node.y] - map[nx][ny]);
                if(dif >= l && dif <= r) {
                    sum += map[nx][ny];
                    q.offer(new Node(nx,ny));
                    visited[nx][ny] = true;
                    list.add(new Node(nx,ny));
                }
            }
        }

        if(list.size() == 1){
            return false;
        }else{
            int people = sum / list.size();

            for(Node node : list){
                map[node.x][node.y] = people;
            }
        }
        return true;
    }

    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
