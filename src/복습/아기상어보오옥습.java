package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아기상어보오옥습 {
    static int n, m;
    static int[][] map;
    static int time = 0;
    static int count;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited;
    static int sharkX, sharkY;
    static ArrayList<Info> fishes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        int size = 2;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
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

                    if(map[nx][ny] >= 1 && map[nx][ny] < size){ // 먹을 수 있는 물고기를 만나면
                        fishes.add(new Info(nx, ny, shark.dist + 1));
                        q.offer(new Info(nx, ny, shark.dist + 1));
                        visited[nx][ny] = true;
                    }else if(map[nx][ny] == 0 || map[nx][ny] == size) { // 먹을 수 없지만 지나갈 수 있는 경우
                        q.offer(new Info(nx, ny, shark.dist + 1));
                        visited[nx][ny] = true;
                    }
                }
            }

            if(fishes.size() == 0){
                System.out.println(time);
                return;
            }

            Info eatingFish = fishes.get(0);
            for (int i = 1; i < fishes.size(); i++) {
                if (eatingFish.dist < fishes.get(i).dist) {
                    eatingFish = fishes.get(i);
                }
                if (eatingFish.dist == fishes.get(i).dist) {
                    if (eatingFish.x > fishes.get(i).x) {
                        eatingFish = fishes.get(i);
                    }
                }
            }

            time += eatingFish.dist;
            count++;
            if(count == size){
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

        public Info(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
