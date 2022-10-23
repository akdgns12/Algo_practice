package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_5643_키순서 {
    static int N, M;
    static int ans;
    static int[][] map;
    static int INF = (int)1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++){
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            map = new int[N+1][N+1];

            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    map[i][j] = INF;
                }
            }

            // i번 사람보다 j가 크다
            for(int i=1; i<=M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                map[a][b] = 1;
            }

            ans = 0;

            floyd();

            int[] isKnow = new int[N+1];
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    if(map[i][j] != INF){
                        isKnow[i]++;
                        isKnow[j]++;
                    }
                }
            }

            for(int i=1; i<=N; i++){
                if(isKnow[i] == N-1) ans++;
            }


            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static void floyd(){
        for(int k=1; k<=N; k++){
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    if(i == j) continue;
                    if(map[i][j] > map[i][k] + map[k][j]){
                        map[i][j] = map[i][k] + map[k][j];
                    }

                }
            }
        }
    }
}
