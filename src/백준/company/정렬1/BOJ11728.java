package 백준.company.정렬1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11728 {
    // 배열합치기 / 실버 5 / 병합 정렬
    static int n, m;
    static int[] a, b;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new int[n];
        b = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(a);

        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < m; j++) {
            b[j] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(b);

        // 병합 정렬
        int aIdx = 0, bIdx = 0;
        for (int i = 0; i < n+m; i++) {
            if(aIdx == n){
                sb.append(b[bIdx++] + " ");
                continue;
            }
            if(bIdx == m){
                sb.append(a[aIdx++] + " ");
                continue;
            }
            if(a[aIdx] <= b[bIdx]){
                sb.append(a[aIdx++] + " ");
            }else{
                sb.append(b[bIdx++] + " ");
            }

        }
        System.out.println(sb.toString());
    }
}
