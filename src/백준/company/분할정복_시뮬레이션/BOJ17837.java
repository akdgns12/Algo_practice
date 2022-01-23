package 백준.company.분할정복_시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ17837 {
    // 새로운게임2 / 골드 2 / 시뮬레이션
    static class Marker{
        int id, x, y, dir;
        public Marker(int id, int x, int y, int dir){
            this.id = id;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static int N,K;
    static int[][] color;
    static ArrayList<Integer>[][] map;
    static Marker[] markers;
    static int[] dx = {0,0,-1,1}; // 우좌상하
    static int[] dy = {1,-1,0,0};

    static int WHITE = 0;
    static int RED = 1;
    static int BLUE = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 체스판의 크기
        K = Integer.parseInt(st.nextToken()); // 말의 개수

        color = new int[N][N]; // 체스판의 색상정보
        map = new ArrayList[N][N]; // 말의 인덱스 정보?
        markers = new Marker[K]; // 말의 위치와 방향 정보 저장할 클래스 배열

        // 컬러맵과 마커맵 초기화
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                color[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = new ArrayList<>();
            }
        }

        // 마커배열 초기화
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;
            markers[i] = new Marker(i, x, y, dir);
            map[x][y].add(i);
        }

        int time = 0;
        while(time <= 1000){
            time++;
            for(int i=0; i<K; i++){
                if(move(i))
                    System.out.println(time);
            }
        }
    }

    // 한 사이클에 순서대로 말들이 모두 움직이기
    static boolean move(int i){

        return true;
    }

    static void changeDirection(){

    }


}