package 백준.company.슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10025 {
    // 게으른 백곰 / 실버 4 / 슬라이딩 윈도우
    static int n,k;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 얼음 양동이 수
        k = Integer.parseInt(st.nextToken()); // 좌우로 닿을 수 있는 최대거리

        arr = new int[1000001];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            arr[x] = g;
        }

        int sum = 0;
        int max = 0;
        /*
            window변수에 부분 배열의 고정길이를 저장하고 i를 포인터 변수로 지정하여
            i가 window크기를 넘어가면 직접 부분 배열과 현재 부분 배열 중 겹치지 않는 값
            (arr[i-window])을 빼주고 추가된 값을 더해준다.
         */
        int window = 1 + (2*k); // 곰의 가동 범위(부분 배열의 크기)

        for (int i = 0; i <= 1000000; i++) {
            // 초반 k-1범위 전까지는 sum을 max랑 비교하면 안됨.
            if (i >= window) { // i가 윈도우의 길이와 같거나 크다면(고정 부분배열 완성된 시점)
                sum -= arr[i - window]; // 윈도우 맨 앞의 수 빼준다
            }
            sum += arr[i]; // 윈도우 끝은 더해준다
            if (sum > max) {
                max = sum;
            }
        }
        System.out.println(max);
    }
}
