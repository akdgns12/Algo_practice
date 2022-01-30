package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Group Study 수빈님 풀이
 * 이렇게도 풀 수 있구나 많이 배웠다!
 * -> 이렇게 푸는 풀이가 좀 더 직관적인 것 같다
 */
public class 탈출복습3 {
    static class Node{
        int x, y;
        int time;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    static int r,c;
    static char[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Queue<Node> biber = new LinkedList<>();
    static Queue<Node> water = new LinkedList<>();
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];

        for(int i=0; i<r; i++){
            String str = br.readLine();
            for(int j=0; j<c; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S'){
                    biber.add(new Node(i,j,0));
                }else if(map[i][j] == '*'){
                    water.add(new Node(i,j));
                }
            }
        }

        int time = 0;
        while(true){
            time++;
            moveWater(); // 고슴도치는 물이 찰 에정인 곳으로 이동할 수 없기 때문에 물 먼저 이동

            // 비버의 굴에 도착한 경우
            if(moveBiber()){
                System.out.println(time);
                break;
            }
            // 큐에 아무것도 없으면 더이상 비버가 이동할 곳이 없는 경우
            if(biber.size() == 0){
                System.out.println("KAKTUS");
                break;
            }
        }
    }


    // 물 먼저 움직이고 나서, 비버 움직여야 한다.
    // 주의할 점, 원래 있던 비버와 물의 양만큼만 움직여야 하니
    // 각 큐의 사이즈 만큼 반복할 수 있도록 한다.
    // 물 이동 함수
    static void moveWater(){
        int loop = water.size(); // 기존에 큐에 있던 물만 퍼져야 함
        while(loop --> 0){
            Node node = water.poll();

            for(int i=0; i<4; i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;

                if(map[nx][ny] == '.'){
                    map[nx][ny] = '*';
                    water.offer(new Node(nx, ny));
                }
            }
        }
    }

    static boolean moveBiber(){
        int loop = biber.size(); // 기존의 큐에 있던 비버의 위치에서 탐색
        while(loop --> 0){
            Node node = biber.poll();

            for(int i=0; i<4; i++){
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= r-1 || ny >= c-1) continue;
                // 이동한 곳이 비버의 굴이면 return true
                if(map[nx][ny] == 'D'){
                    return true;
                }
                // 아무것도 없는 곳이면
                if(map[nx][ny] == '.'){
                    map[nx][ny] = 'S'; // 위치 이동
                    biber.offer(new Node(nx, ny));
                }

            }
        }
        return false;
    }
}
