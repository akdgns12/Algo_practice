package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 게으른백곰_슬라이딩윈도우 {
    static int n,k;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[1000001];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            arr[x] = g;
        }

        int sum = 0;
        int max = 0;
        int window = 1 + (2 * k);
        for (int i = 0; i <= 1000000; i++) {
            if(i >= window){
                sum -= arr[i - window];
            }
            sum += arr[i];
            if (sum > max) {
                max = sum;
            }
        }
        System.out.println(max);
    }
}
