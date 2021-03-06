package 백준.company.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16236 {
    // 아기상어 / 골드 3 / 시뮬레이션
    static int n;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int sharkX, sharkY;
    static ArrayList<Info> fishes;
    static boolean[][] visited;
    static int count;
    static int time = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        int size = 2; // 상어 초기 크기

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) { // 아기상어
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                }
            }
        }


        while(true){
            fishes = new ArrayList<Info>(); // 먹을 수 있는 물고기 정보
            Queue<Info> q = new LinkedList<>();
            visited = new boolean[n][n];
            q.offer(new Info(sharkX, sharkY, 0));
            visited[sharkX][sharkY] = true;

            while(!q.isEmpty()){
                Info shark = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = shark.x + dx[i];
                    int ny = shark.y + dy[i];

                    if(nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) continue;

                    // 먹을 수 있는 물고기를 찾은 경우
                    if(map[nx][ny] >= 1 && map[nx][ny] < size){
                        q.offer(new Info(nx, ny, shark.dist + 1));
                        fishes.add(new Info(nx, ny, shark.dist+1));
                        visited[nx][ny] = true;
                    }else if(map[nx][ny] == 0 || map[nx][ny] == size){ // 먹을 수는 없지만 지나갈 수 있는 경우
                        q.offer(new Info(nx, ny, shark.dist + 1));
                        visited[nx][ny] = true;
                    }
                }
            }

            // 사이즈가 0인 경우 먹을 수 없는 물고기이므로 출력
            if(fishes.size() == 0){
                System.out.println(time);
                return;
            }

            //먹을 수 있는 물고기인 경우
            Info eatingFish = fishes.get(0);
            for (int i = 1; i < fishes.size(); i++) {
                if(eatingFish.dist > fishes.get(i).dist){ // 거리가 최소인 물고기로 갱신
                    eatingFish = fishes.get(i);
                }

                if(eatingFish.dist == fishes.get(i).dist) { // 거리가 같은 경우 x가 더 작은 물고기가 우선순위
                    if(eatingFish.x > fishes.get(i).x){
                        eatingFish = fishes.get(i);
                    }
                }
            }

            time += eatingFish.dist; // 먹은 물고기의 거리를 time에 추가
            count++; //먹은 물고기 카운트 증가
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