package 백준.company.BFS;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ14502_연구소 {
    static int[][] arr;
    static int N, M;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static List<Node> virusList;
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로

        arr = new int[N][M];
        virusList = new ArrayList<>();

        for(int i=0; i<N; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 2) virusList.add(new Node(i,j));
            }
        }

        solve(0, 0, arr);
        System.out.println(ans);
    }

    
    static void solve(int cnt, int start, int[][] arr){
        if(cnt == 3){ // 벽 3개 다세우면
            fill(arr); // 퍼트리고, 카운팅
            rollBack(arr);
            return;
        }

        for(int i=start; i<N*M; i++){ // 1차원으로 펴서
            int x = i / M;
            int y = i % M;

            if(arr[x][y] == 0){
                arr[x][y] = 1;
                solve(cnt + 1, i+1, arr);
                arr[x][y] = 0;
            }
        }
    }

    static void rollBack(int[][] arr){
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(arr[i][j] == 3){
                    arr[i][j] = 0;
                }
            }
        }
    }

    // 바이러스 퍼트리기
    static void fill(int[][] arr){
        int[][] virusMap = new int[N][M];

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                virusMap[i][j] = arr[i][j];
            }
        }

        Queue<Node> q = new LinkedList<>();
        for(int i=0; i<virusList.size(); i++){
            q.offer(virusList.get(i));
        }

        while(!q.isEmpty()){
             Node now = q.poll();

             for(int i=0; i<4; i++){
                 int nx = now.x + dx[i];
                 int ny = now.y + dy[i];

                 if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                 if(virusMap[nx][ny] == 0){
                     virusMap[nx][ny] = 3;
                     q.offer(new Node(nx, ny));
                 }
             }
        }

        counting(virusMap);
    }
    
    // 최종 카운팅
    static void counting(int[][] arr){
        int cnt = 0;

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++) {
                if(arr[i][j] == 0) cnt++;
            }
        }

        ans = Math.max(ans, cnt);
    }

    static class Node{
        int x, y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
