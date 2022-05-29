package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ5800 {
    // 수학점수 / 실버 5 / 최대, 최소, 점수 차이
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        k = Integer.parseInt(br.readLine());
        int idx = 1;

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int[] arr = new int[n];

            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);
            int min = arr[0];
            int max = arr[n-1];
            int dif = max - min;

            System.out.println("Class " + idx);
            System.out.print("max " + max + ", " + "Min " + min + ", Largest gap " + dif);
            idx++;
        }
    }
}
