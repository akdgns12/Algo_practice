package 백준.company.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16236 {
    // 아기상어 / 골드3 / 구현
    // 생각생각
    // 초기 아기상어 크기 2
    // 맵에 먹을 수 있는 물고기가 있는지 탐색, 물고기가 있다면
    // 그 고기 먹으러 감, 먹을 수 있는 고기가 많다면 가장 가까운 곳으로 최우선순위(좌상단)
    static int n, m;
    static int[][] map;
    static ArrayList<Info> fishes;
    static boolean[][] visited;
    static int sharkX, sharkY;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int count;
    static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        int size = 2; // 초기상어크기

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            fishes = new ArrayList<>(); // 먹을 수 있는 물고기 정보
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

                    if(map[nx][ny] >= 1 && map[nx][ny] < size){ // 먹을 수 있는 물고기를 찾은 경우
                        q.offer(new Info(nx, ny, shark.dist + 1));
                        fishes.add(new Info(nx, ny, shark.dist + 1));
                        visited[nx][ny] = true;
                    }else if(map[nx][ny] == 0 || map[nx][ny] == size){ // 먹을 수는 없지만 지나갈 수 있는 경우
                        q.offer(new Info(nx, ny, shark.dist + 1));
                        visited[nx][ny] = true;
                    }
                }
            }

            // 먹을 수 있는 물고기 list의 사이즈가 0인 경우 먹을 수 있는 물고기가 없다.
            if(fishes.size() == 0){
                System.out.println(time);
                return;
            }

            // 여기까지 온다면 먹을 수 있는 물고기 있는 것
            Info eatingFish = fishes.get(0);
            for (int i = 1; i < fishes.size(); i++) {
                if(eatingFish.dist > fishes.get(i).dist){ // 먹을 수 있는 물고기 중 거리가 최소인 것으로
                    eatingFish = fishes.get(i);
                }

                if(eatingFish.dist == fishes.get(i).dist){ // 거리가 같다면 x가 작은 물고기가 우선순위
                    if (eatingFish.x > fishes.get(i).x) {
                        eatingFish = fishes.get(i);
                    }
                }
            }

            time += eatingFish.dist;
            count++;
            map[eatingFish.x][eatingFish.y] = 0;

            if (count == size) {
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
