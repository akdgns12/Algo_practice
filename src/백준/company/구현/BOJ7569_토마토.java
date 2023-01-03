package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ7569_토마토 {
    static int M, N, H;
    static int[][][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로
        H = Integer.parseInt(st.nextToken()); // 높

        map = new int[H][N][M];

        for(int h=0; h<H; h++){
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++){
                    map[h][i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

//        for(int h=0; h<H; h++){
//            for(int i=0; i<N; i++){
//                for(int j=0; j<M; j++){
//                    System.out.print(map[h][i][j]);
//                }
//                System.out.println();
//            }
//        }


    }
}
