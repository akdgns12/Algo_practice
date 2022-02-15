package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 여행가자 {
    static int N,M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 정점의 수
        M = Integer.parseInt(br.readLine()); // 여행계획에 속한 도시들의 수

        map = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(i == j) map[i][j] = 1; // 자기자신은 이미 연결되어있는 것과 마찬가지이기 때문에
            }
        }

        int[] cities = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            cities[i] = Integer.parseInt(st.nextToken());
        }

        // 플로이드와샬
        for(int k=0; k<N; k++){
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(map[i][k] == 1 && map[k][j] == 1)
                        map[i][j] = 1;
                }
            }
        }

        boolean flag = false;
        for(int i=0; i<M-1; i++){ // 동혁이의 여행계획에 속한 도시들이 가능한지 여부 판별
            if(map[cities[i]][cities[i+1]] == 0){
                flag = false;
                break;
            }
        }

        System.out.println(flag == true ? "YES" : "NO");


    }
}
