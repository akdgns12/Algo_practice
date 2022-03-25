package 백준.company.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10816 {
    // 숫자 카드 2 / 실버 4 / 이분 탐색
    // lower_bound, upper_bound로 푸는 문제 (바킹독 강의로 다시 한번 개념 확실하게 이해하자)
    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<m; i++){
           int target = Integer.parseInt(st.nextToken());

           sb.append(upperBound(arr, target) - lowerBound(arr, target)).append(' ');
        }

        System.out.println(sb);
    }

    static int lowerBound(int[] arr, int target){
        int st = 0;
        int en = arr.length;

        while(st < en){
            int mid = (st + en) / 2;

            if(arr[mid] >= target) en = mid;
            else st = mid + 1;
        }
        return st;
    }

    static int upperBound(int[] arr, int target){
        int st = 0;
        int en = arr.length;

        while(st < en){
            int mid = (st + en) / 2;

            if(arr[mid] > target) en = mid;
            else st = mid + 1;
        }
        return st;
    }
}
