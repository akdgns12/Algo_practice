package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2573_빙산 {
    static int[][] map, temp;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Queue<Node> ice = new LinkedList<>();
    static int N, M;
    static boolean isDivide = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0 ;i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(melting());
    }

    /**
     * 빙산들을 큐에 미리 넣어놓고
     * 하나씩 꺼내서 사방 탐색,
     */
    static int melting(){
        int cnt = 0;

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] > 0) ice.offer(new Node(i, j, map[i][j]));
            }
        }

        while(!ice.isEmpty()){
            temp = new int[N][M];
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    temp[i][j] = map[i][j];
                }
            }

            int size = ice.size();
            cnt++;

            for(int sz=0; sz<size; sz++){
                Node now = ice.poll();

                for(int i=0; i<4; i++){
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if(!isOutOfRange(nx,ny)) continue;

                    if(temp[nx][ny] == 0 && map[nx][ny] == 0){ // 주위가 바다면
                            if(temp[now.x][now.y] != 0){
                                temp[now.x][now.y] -= 1;
                            }
                    }
                }
            }

            bfs();
            if(isDivide) {
                return cnt;
            }


            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(temp[i][j] > 0) ice.offer(new Node(i, j, temp[i][j]));
                }
            }


            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    map[i][j] = temp[i][j];
                }
            }
        }

        return 0;
    }

    // 덩어리 나뉘었는지 체크
    static void bfs(){
        boolean[][] check = new boolean[N][M];
        Queue<Node> q = new LinkedList<>();
        boolean out = false;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(temp[i][j] > 0) {
                    q.offer(new Node(i, j, temp[i][j]));
                    check[i][j] = true;
                    out = true;
                    break;
                }
                if(out) break;
            }
        }

        while(!q.isEmpty()){
            Node now = q.poll();

            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(!isOutOfRange(nx, ny) || check[nx][ny]) continue;

                if(temp[nx][ny] > 0){
                    q.offer(new Node(nx, ny, temp[nx][ny]));
                    check[nx][ny] = true;
                }
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(temp[i][j] > 0){
                    if(!check[i][j]) {
                        isDivide = true;
                        return;
                    }
                }
            }
        }
    }

    static boolean isOutOfRange(int x, int y){
        if(x < 0 || y < 0 || x >= N || y >= M) return false;
        else return true;
    }

    static class Node{
        int x, y;
        int height;
        public Node(int x, int y, int height){
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
}
