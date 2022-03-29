package 백준.company.정렬2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ11656 {
    // 접미사배열 / 실버 4 / 정렬
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int n = str.length();
        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            arr[i] = str.substring(i);
        }

        Arrays.sort(arr);
        for (String i : arr) {
            System.out.println(i);
        }
    }
}
