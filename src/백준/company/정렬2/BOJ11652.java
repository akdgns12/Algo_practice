package 백준.company.정렬2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ11652 {
    // 카드 / 실버 4 / 정렬
    static int n;
    static long[] arr; // 문제의 정수 범위에 따른 자료형 주의!! long으로 선언해줘야 함

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(arr);

        int cnt = 1, maxCnt = 1, maxIdx = 0;
        for (int i = 1; i < n; i++) {
            if(arr[i] == arr[i-1]) cnt++;
            else cnt = 1;

            if(cnt > maxCnt){
                maxCnt = cnt;
                maxIdx = i;
            }
        }
        System.out.println(arr[maxIdx]);
    }
}
