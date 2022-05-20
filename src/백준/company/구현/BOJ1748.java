package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1748 {
    // 수 이어쓰기 1 / 실버 1 / 구현
    static int n;
    static int size;
    static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        size = input.length();
        n = Integer.parseInt(input);

        // 1~n까지 이어서 쓴 수의 총자릿수
        // 형변환 말고 최적화 시켜서 풀어야 함, 안그럼 시초남
        for (int i = 1; i < size; i++) {
            answer += i * 9 * Math.pow(10, i - 1);
        }

        answer += size * (n - Math.pow(10, size - 1) + 1);
        System.out.println(answer);
    }
}
