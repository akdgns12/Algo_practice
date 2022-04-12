package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class BOJ2108 {
    // 통계학 / 실버3 / 구현
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        arr = new int[n];
        int temp1 = 0;
        int temp2 = 0;
        int temp3 = 0;
        int temp4 = 0;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            arr[i] = num;
        }

        // 1. 산술평균
        for (int i = 0; i < n; i++) {
            temp1 += arr[i];
        }
        System.out.println(temp1 / n);

        // 2. 중앙값
        Arrays.sort(arr);
        int lt = 0;
        int rt = n-1;
        int mid = (lt + rt) / 2;
        System.out.println(arr[mid]);

        // 3. 최빈값
        int cnt = 0;
        int max = -1;
        int mod = arr[0];
        boolean check = false;
        for (int i = 0; i < n - 1; i++) {
            if(arr[i] == arr[i+1]){
                cnt++;
            }else{
                cnt = 0;
            }

            // 첫번째 작은 값
            if(max < cnt){
                max = cnt;
                mod = arr[i];
                check = true;
            }
            // 두번째 작은 값
            else if(max == cnt && check == true){
                mod = arr[i];
                check = false;
            }
        }

        System.out.println(mod);

        // 4. 범위
        System.out.println(arr[n-1] - arr[0]);

    }
}
