package 백준.company.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BOJ2417 {
    // 정수제곱근 / 실버 4 / 이진탐색
    /*
        java에서 자료형
        Long - 64비트(-9223372036854775808 ~ 9223372036854775807)
        BigInteger - 무한한 값
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        long lt = 0;
        long rt = n;
        long answer = n;

        while (lt <= rt) {
            long mid = (lt + rt) / 2;
            BigInteger bigMid = BigInteger.valueOf(mid);

            if(bigMid.multiply(bigMid).compareTo(BigInteger.valueOf(n)) >= 0){
                answer = Math.min(answer, mid);
                rt = mid - 1;
            }else{
                lt = mid + 1;
            }
        }

        System.out.println(answer);
    }
}
