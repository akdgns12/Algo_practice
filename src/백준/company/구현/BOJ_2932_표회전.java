package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2932_표회전 {
    static int N, K, R, C;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        int idx = 1;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                map[i][j] = idx++;
            }
        }

        print();
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
        }


    }

    static void print(){
        System.out.println();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(map[i][j] + " " );
            }
            System.out.println();
        }
    }
}
