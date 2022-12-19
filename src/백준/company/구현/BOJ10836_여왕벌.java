package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10836_여왕벌 {
    static int M, N;
    static int[][] map;
    static int[] dx = {0,-1,-1}; // 좌,좌상,상
    static int[] dy = {-1,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][M];
        for(int i=0; i<M; i++){
            for(int j=0; j<M; j++){
                map[i][j] = 1;
            }
        }

        for(int k=0;k<N; k++){ // i날 성장크기 정보(0,1,2의 개수) , N일동안
            st = new StringTokenizer(br.readLine());
            int zero = Integer.parseInt(st.nextToken());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());

            // 맨 왼쪽 열
            for(int i=M-1; i>0; i--){
                if(zero != 0){
                    zero--;
                }else if(one != 0){
                    one--;
                    map[i][0] += 1;
                }else if(two != 0){
                    two--;
                    map[i][0] += 2;
                }
            }

            // 맨 위쪽 행
            for(int i=0; i<M; i++){
                if(zero != 0){
                    zero--;
                }else if(one != 0){
                    one--;
                    map[0][i] += 1;
                }else if(two != 0){
                    two--;
                    map[0][i] += 2;
                }
            }
        }

        // 나머지
        for(int i=1; i<M; i++){
            for(int j=1; j<M; j++){
                map[i][j] = Math.max(map[i-1][j], Math.max(map[i-1][j-1], map[i][j-1]));
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++){
            for(int j=0; j<M; j++){
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void print(){
        for(int i=0; i<M; i++){
            for(int j=0; j<M; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
