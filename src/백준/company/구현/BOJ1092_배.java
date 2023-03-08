package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ1092_배 {
    static int N, M;
    static Integer[] crain, box;
    static boolean[] visited;
    static int ans = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 크레인 수
        st = new StringTokenizer(br.readLine());
        crain = new Integer[N];
        for(int i=0; i<N; i++){
            crain[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine()); // 박스 수
        box = new Integer[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            box[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(crain, Collections.reverseOrder());
        Arrays.sort(box, Collections.reverseOrder());

        visited = new boolean[M];
        System.out.println(solve());
    }

    static int solve(){
        int cnt = 0, time = 0;
        while (cnt < M){
            int i = 0;

            if(crain[0] < box[0]){
                return -1;
            }

                for(int j=0; j<M; j++){
                    if(i == N) break;
                    if(visited[j])continue;

                    if(crain[i] < box[j]){ // 박스 무게가 더 크다면 skip
                        continue;
                    }

                    if(crain[i] >= box[j]){
                        cnt++;
                        i++;
                        visited[j] = true;
                    }
                }

            time++;
        }

        return time;
    }
}
