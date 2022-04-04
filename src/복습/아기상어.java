package 복습;

import com.sun.security.jgss.GSSUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아기상어 {
    static int n;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static ArrayList<Info> fishes;
    static boolean[][] visited;
    static int time = 0;
    static int count;
    static int sharkX, sharkY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        int size = 2; // 초기 상어 크기

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                }
            }
        }

        while(true){
            fishes = new ArrayList<>();
            Queue<Info> q = new LinkedList<>();
            q.offer(new Info(sharkX, sharkY, 0));
            visited = new boolean[n][n];
            visited[sharkX][sharkY] = true;

            while(!q.isEmpty()){
                Info shark = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = shark.x + dx[i];
                    int ny = shark.y + dy[i];

                    if(nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) continue;

                    if (map[nx][ny] >= 1 && map[nx][ny] < size) { // 먹을 수 있는 물고기라면
                        q.offer(new Info(nx, ny, shark.dist + 1));
                        fishes.add(new Info(nx,ny,shark.dist+1));
                        visited[nx][ny] = true;
                    }else if(map[nx][ny] == 0 || map[nx][ny] == size){ // 지나갈 수 있는 곳
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
                if(eatingFish.dist > fishes.get(i).dist){
                    eatingFish = fishes.get(i);
                }

                if (eatingFish.dist == fishes.get(i).dist) { // 거리가 같다면 x가 더 작은 순
                    if (eatingFish.x > fishes.get(i).x) {
                        eatingFish = fishes.get(i);
                    }
                }
            }

            time += eatingFish.dist;
            count++;
            map[eatingFish.x][eatingFish.y] = 0;

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
        public Info(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
