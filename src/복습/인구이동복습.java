package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 인구이동복습 {
    static int n,l,r;
    static int[][] map;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static boolean[][] visited;
    static ArrayList<Node> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(move());
    }

    // 더이상 인구이동이 없을때까지 반복
    static int move(){
        int result = 0;
        while(true){
            boolean isUnion = false;
            visited = new boolean[n][n];
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(!visited[i][j]){
                        int sum = bfs(i,j); // bfs탐색으로 열릴 수 있는 국경 확인하며 인구이동할 총 인구수 반환

                        if(list.size() > 1){
                            changePopulation(sum); // 열린 국경선 내의 노드들 인구 변경

                            isUnion = true;
                        }
                    }
                }
            }
            if(!isUnion) return result;
            else result++;
        }
    }

    static int bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        list = new ArrayList<>();
        visited[x][y] = true;
        q.offer(new Node(x,y));
        list.add(new Node(x,y));

        int sum = map[x][y];
        while(!q.isEmpty()){
            Node node = q.poll();

            for(int i=0; i<4; i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n
                || visited[nx][ny]) continue;

                int dif = Math.abs(map[node.x][node.y] - map[nx][ny]);
                if(dif >= l && dif <= r){
                    q.offer(new Node(nx,ny));
                    list.add(new Node(nx,ny));
                    visited[nx][ny] = true;
                    sum += map[nx][ny];
                }
            }
        }
        return sum;
    }

    static void changePopulation(int sum){
        int avg = sum / list.size();
        for(Node node : list){
            map[node.x][node.y] = avg;
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
