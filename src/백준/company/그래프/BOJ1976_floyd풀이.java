package 백준.company.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1976_floyd풀이 {
    public static void main(String[] args) throws IOException {
        // 여행 가자 / 골드 4 / 플로이드-와샬
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] able = new int[N][N];
        int[] cities = new int[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                able[i][j] = Integer.parseInt(st.nextToken());
                if(i == j) able[i][j] = 1;
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            cities[i] = Integer.parseInt(st.nextToken())-1;
        }

        for(int k=0; k<N; k++){
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(able[i][k] == 1 && able[k][j] == 1)
                        able[i][j] = 1;
                }
            }
        }

        boolean flag = true;
        for(int i=0; i<M-1; i++){
            if(able[cities[i]][cities[i+1]] == 0) {
                flag = false;
                break;
            }
        }

        System.out.println(flag == true ? "YES" : "NO");
    }
}
