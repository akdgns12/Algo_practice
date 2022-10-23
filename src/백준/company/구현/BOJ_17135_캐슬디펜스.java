package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17135_캐슬디펜스 {
    static int N, M, D;
    static int[][] map, copy;
    static int[] archer;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        copy = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        archer = new int[M];
        for(int i=0; i<M; i++){
            archer[i] = i;
        }
        int[] temp = new int[3];
        ans = Integer.MIN_VALUE;
        dfs(0, 0, temp);

        System.out.println(ans);
    }

    static void copyMap(){
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                copy[i][j] = map[i][j];
            }
        }
    }

    static void dfs(int cnt, int start, int[] temp){
        if(cnt == 3){
            copyMap();

            int killCnt = 0;
            int turn = 0;
            Queue<Node> q = new LinkedList<>();

            while(turn < N){
                // 궁수 위치
                for(int k=0; k<3; k++){
                    int x = N - turn;
                    int y = temp[k];

                    int min = Integer.MAX_VALUE;
                    int minX = -1;
                    int minY = -1;

                    for(int i=N-1-turn; i>=0; i--){
                        for(int j=0; j<M; j++){
                            if(copy[i][j] == 1){
                                int dist = Math.abs(i - x) + Math.abs(j - y);

                                if(dist <= D){
                                    if(dist < min){
                                        min = dist;
                                        minX = i;
                                        minY = j;
                                    }
                                    else if(dist == min){
                                        if(minY > j){
                                            minX = i;
                                            minY = j;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if(minX != -1 && minY != -1){
                        q.offer(new Node(minX, minY));
                    }
                }

                while(!q.isEmpty()){
                    Node now = q.poll();

                    if(copy[now.x][now.y] == 1){
                        copy[now.x][now.y] = 0;
                        killCnt++;
                    }
                }

                turn++;
            }

            ans = Math.max(ans, killCnt);
            return;
        }

        for(int i=start; i<M; i++){
            temp[cnt] = archer[i];
            dfs(cnt + 1, i + 1, temp);
        }
    }

    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
