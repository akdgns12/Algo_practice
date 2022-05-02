package 백준.company.완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9613 {
    // GCD합 / 실버3 / 완탐
    // GCD - 최대공약수
    static int t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        // 각 케이스마다 가능한 모든 쌍의 최대공약수의 합을 출력
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            long sum = 0;
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    sum += gcd(arr[i], arr[j]);
                }
            }

            System.out.println(sum);
        }
    }

    // 두 수의 최대공약수 구하는 함수
    static int gcd(int a, int b) {
        if(b == 0){
            return a;
        }else{
            return gcd(b, a % b);
        }
    }
}
