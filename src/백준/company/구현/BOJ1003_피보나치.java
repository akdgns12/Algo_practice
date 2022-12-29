package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1003_피보나치 {
    static int zero, one;
    static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++){ // 각 TC마다 0과 1이 출력되는 횟수
            int N = Integer.parseInt(br.readLine());

            fibo(N);
            sb.append(zero + " ").append(one + "\n");
        }

        System.out.println(sb);
    }

    static void fibo(int N){
        zero = 1;
        one = 0;
        sum  = 1;

        for(int i=0; i<N; i++){
            zero = one;
            one = sum;
            sum = zero + one;
        }
    }
}
