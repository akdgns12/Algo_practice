package Practice_2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1325 {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < M; i ++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // a가 b를 신뢰
            int b = Integer.parseInt(st.nextToken());

            
        }
    }
}
