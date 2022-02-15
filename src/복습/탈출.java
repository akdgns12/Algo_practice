package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 탈출 {
    // 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다했으므로
    // 물을 먼저 확장시킨 후 고슴도치를 이동시켜준다
    static int N,M;
    static char[][] map;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static Queue<Node> biber = new LinkedList<>();
    static Queue<Node> water = new LinkedList<>();
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S'){
                    biber.add(new Node(i,j));
                }else if(map[i][j] == 'D'){
                    water.add(new Node(i,j));
                }
            }
        }

        int result = bfs();
        if(result == 0) System.out.println("KAKTUS");
        else System.out.println(result);
    }

    static int bfs(){
        int time = 0;
        int size = 0;

        while(!biber.isEmpty()){
            time++;

            for(int i=0; i<water.size(); i++){
                Node node = water.poll();

                for(int j=0; j<4; j++){
                    int nx = node.x + dx[j];
                    int ny = node.y + dy[j];
                    // 범위 벗어나면 skip
                    if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                    // 돌이거나 비버굴이거나 물차있는곳이면 skip
                    if(map[nx][ny] == 'X' || map[nx][ny] == 'D' || map[nx][ny] == '*') continue;

                    map[nx][ny] = '*';
                    water.offer(new Node(nx, ny));
                }
            }

            // 고슴도치 이동
            for(int i=0; i<biber.size(); i++){
                Node node = biber.poll();

                for(int j=0; j<4; j++){
                    int nx = node.x + dx[j];
                    int ny = node.y + dy[j];

                    if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                    if(map[nx][ny] == 'X' || map[nx][ny] == '*') continue;
                    map[nx][ny] = 'S';
                    if(map[nx][ny] == 'D') return time;
                    biber.offer(new Node(nx, ny));
                }
            }
        }
        return 0;
    }

    static class Node{
        int x,y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
