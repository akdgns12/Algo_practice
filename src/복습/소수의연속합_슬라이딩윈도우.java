package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 소수의연속합_슬라이딩윈도우 {
    static int n;
    static boolean[] isPrime;
    static ArrayList<Integer> prime = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        isPrime = new boolean[n+1];
        // 소수 구하기
        isPrime[0] = isPrime[1] = true;
        for (int i = 2; i*i <= n; i++) {
            if (!isPrime[i]) { // 소수라면
                for (int j = i * i; j <= n; j += i) { // 그 배수들
                    isPrime[j] = true; // 소수가 아닌걸로 처리
                }
            }
        }

        // 소수들 연결리스트에 넣기
        for (int i = 1; i <= n; i++) {
            if (!isPrime[i]) {
                prime.add(i);
            }
        }

        // 슬라이딩 윈도우
        int left = 0, right = 0, sum = 0, count = 0;
        while (true) {
            if (sum >= n) {
                sum -= prime.get(left++);
            } else if (right == prime.size()) { // right가 1~n 사이의 마지막 소수에 도달했다면 종료
                break;
            }else{ // 구간 합이 n보다 작다면
                sum += prime.get(right++);
            }

            if (sum == n) {
                count++;
            }
        }

        System.out.println(count);
    }
}
