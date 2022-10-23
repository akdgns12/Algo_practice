package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 상호의 배틀필드
public class SWEA_1873 {
    static int h, w;
    static char[][] map;
    static int[] dx = {-1,1,0,0}; // 상하우좌
    static int[] dy = {0,0,-1,1};
    static Car car;
    static Queue<Character> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for(int t=1; t<=tc; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new char[h][w];
            for(int i=0; i<h; i++){
                String str = br.readLine();
                for(int j=0; j<w; j++){
                    map[i][j] = str.charAt(j);
                    switch (map[i][j]){
                        case '^':
                            car = new Car(i, j, 0);
                            break;
                        case 'v':
                            car = new Car(i, j, 1);
                        case '<':
                            car = new Car(i, j, 2);
                            break;
                        case '>':
                            car = new Car(i, j, 3);
                            break;
                    }
                }
            }

            int n = Integer.parseInt(br.readLine());
            char[] command = br.readLine().toCharArray();

            // 사용자 명령을 큐에 넣고 차례대로 꺼내면서 command에 맞게 수행
            for(int i=0; i<n; i++){
                q.offer(command[i]);
            }

            solve();
        }
    }

    static void solve(){
        while(!q.isEmpty()){
            switch (q.poll()){
                case 'U':
                    move(0);
                    break;
                case 'D':
                case 'L':
                case 'R':
                case 'S':

            }
        }
    }

    static void move(int dir){ // 방향전환은 무조건 함
        car.dir = dir;
        int nx = car.x + dx[dir];
        int ny = car.y + dy[dir];
        // 이동가능하다면
        if (!isOut(nx, ny) && map[nx][ny] == '.') {
            map[nx][ny] = '.';

        }

    }

    static boolean isOut(int x, int y){
        if(x < 0 || y < 0 || x >= h || y >= w) return true;

        return false;
    }

    static void move(char type, int x, int y){
        switch (type){
            case 'U':
                if(canGo(x,y,0)){
                    map[x+dx[0]][y+dy[0]] = '^';
                    map[x][y] = '.';
                }else{
                    map[x][y] = '^';
                }
            case 'D':
                if(canGo(x,y,1)){
                    map[x+dx[1]][y+dy[1]] = 'v';
                    map[x][y] = '.';
                }else{
                    map[x][y] = 'v';
                }
            case 'R':
                if(canGo(x,y,2)){
                    map[x+dx[2]][y+dy[2]] = '>';
                    map[x][y] = '.';
                }else{
                    map[x][y] = '>';
                }
            case 'L':
                if(canGo(x,y,3)){
                    map[x+dx[3]][y+dy[3]] = '<';
                    map[x][y] = '.';
                }else{
                    map[x][y] = '<';
                }
        }
    }

    static boolean canGo(int x, int y, int dir){
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || ny < 0 || nx >= h || ny >= w) return false;
            if(map[nx][ny] == '.') return true;
            else return false;
    }

    static void shoot(int x, int y, int dir){
        while(true){
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || ny < 0 || nx >= h || ny >= w) break;
            if(map[nx][ny] == '#') break;
            if(map[nx][ny] == '*'){
                map[nx][ny] = '.';
                x = nx;
                y = ny;
            }

            x = nx;
            y = ny;
        }
    }

    static class Car{
        int x, y;
        int dir;
        public Car(int x, int y, int dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
