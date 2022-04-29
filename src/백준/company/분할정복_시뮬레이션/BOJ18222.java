package 백준.company.분할정복_시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ18222 {
    // 투에-모스 문자열 / 실버 2 / 재귀를 사용한 분할정복
    // 0과1로 이뤄진 무한한 문자열 x, 다음과 같은 과정으로 만들어짐
    /*
        1. X는 맨 처음에 "0"으로 시작한다.
        2. X에서 0을 1로, 1을 0으로 뒤바꾼 문자열 X'을 만든다.
        3. X의 뒤에 X'를 붙인 문자열을 X로 다시 정의한다.
        4. 2~3의 과정을 무한히 반복한다.
     */
    /*
        문자열은 2의 배수로 증가.
        문자열을 도표로 그려보면, k의 값은 k보다 작은 2의 거듭제곱을 뺀 숫자에 해당하는 값을 반전한 값임을 알 수 있다.
        0이면 1, 1이면 0으로 반전되기 때문에 1 - X 꼴로 return한다.
     */
    // 자연수 k가 주어질 때 문자열 x의 k번째에는 무슨 문자가 오는지 리턴
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long k = Integer.parseInt(br.readLine());

        arr = new long[64];
        arr[0] = 1;
        for (int i = 1; i < 64; i++) {
            arr[i] = arr[i-1] * 2;
        }

        System.out.println(solve(k));
    }

    static int solve(long k) {
        if(k == 1) return 0;

        for (int i = 0; i < 64; i++) {
            if(arr[i] >= k) return 1 - solve(k - arr[i - 1]);
        }

        return 0;
    }
}
