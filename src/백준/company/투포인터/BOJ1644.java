package 백준.company.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1644 {
    // 소수의 연속합 / 골드 3 / 투 포인터
    // 주어진 수르 연속된 소수의 합으로 나타낼 수 있는 경우의 수
    static int n;
    static boolean[] isPrime;
    static ArrayList<Integer> prime = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        isPrime = new boolean[n+1];
        // 소수 구하기 -> 에라토스테네스의 체
        isPrime[0] = isPrime[1] = true;
        for (int i = 2; i*i <= n; i++) {
            if (!isPrime[i]) { // 소수라면
                for (int j = i * i; j <= n; j += i) { // 소수의 배수들을 제외시켜주기위해 true로 변경(소수의 배수들은 해당 소수를 가지기 때문에 소수가 아님)
                    isPrime[j] = true; // 소수가 아니면 true로 변경
                }
            }
        }

        // 1~n 사이의 소수를 연결리스트에 삽입
        for (int i = 1; i <= n; i++) {
            if(!isPrime[i]) prime.add(i);
        }

        // 슬라이딩 윈도우
        int left = 0, right = 0, sum = 0, count = 0;
        while (true) {
            if(sum >= n) // 구간 합이 n보다 크다면 구간 합에서 왼쪽의 값 하나 빼기
                sum -= prime.get(left++);
            else if(right == prime.size()) // right가 1~n 사이의 마지막 소수에 도달했다면 종료
                break;
            else // 구간 합이 n보다 작다면 오른쪽 값 추가
                sum += prime.get(right++);

            if(sum == n) // 구간 합이 n인 경우를 찾은 경우
                count++;
        }

        System.out.println(count);
    }
}
