package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아기상어복습2 {
    static int n;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited;
    static ArrayList<Info> fishes;
    static int sharkX, sharkY;
    static int size;
    static int count;
    static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    map[i][j] = 0;
                    sharkX = i;
                    sharkY = j;
                }
            }
        }

        while(true){
            fishes = new ArrayList<>();
            visited = new boolean[n][n];
            Queue<Info> q = new LinkedList<>();
            q.offer(new Info(sharkX, sharkY, 0));
            visited[sharkX][sharkY] = true;

            while(!q.isEmpty()){
                Info shark = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = shark.x + dx[i];
                    int ny = shark.y + dy[i];

                    if(nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) continue;
                    if(map[nx][ny] >=1 && map[nx][ny] < size){ // 먹을 수 있는 물고기
                        q.offer(new Info(nx, ny, shark.dist + 1));
                        fishes.add(new Info(nx,ny, shark.dist +1));
                        visited[nx][ny] = true;
                    }else if(map[nx][ny] == 0 || map[nx][ny] == size){ // 지나갈 수 있는 곳
                        q.offer(new Info(nx, ny, shark.dist + 1));
                        visited[nx][ny] = true;
                    }
                }
            }

            if(fishes.size() == 0){ // 먹을 수 있는 물고기가 없다면
                System.out.println(time);
                return;
            }

            // 먹을 수 있는 물고기가 있다면
            Info eatingFish = fishes.get(0);
            for (int i = 1; i < fishes.size(); i++) {
                if(eatingFish.dist > fishes.get(i).dist){ // 그 중 더 가까운 거리의 물고기가 먹을 수 있는 고기로
                    eatingFish = fishes.get(i);
                }


                if(eatingFish.dist == fishes.get(i).dist){ // 거리가 같다면 더 작은 x
                    if(eatingFish.x > fishes.get(i).x){
                        eatingFish = fishes.get(i);
                    }
                }
            }

            time += eatingFish.dist;
            count++;
            map[eatingFish.x][eatingFish.y] = 0;

            if(size == count){
                size++;
                count = 0;
            }

            sharkX = eatingFish.x;
            sharkY = eatingFish.y;
        }

    }

    static class Info{
        int x, y;
        int dist;
        public Info(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
