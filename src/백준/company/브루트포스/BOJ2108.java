package 백준.company.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2108 {
    // 통계학 / 실버3 / 구현
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        arr = new int[n];
        double sum = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }

        Arrays.sort(arr);

        int cnt = 0;
        int max = -1;
        int mod = arr[0];
        boolean check = true; // 빈도수가 같을 때 두번째 값을 찾기위한 boolea 변수
        for (int i = 0; i < n - 1; i++) {
            if(arr[i] == arr[i+1]){
                cnt++;
            }else{
                cnt = 0;
            }

            // 최빈값
            if(max < cnt){
                max = cnt;
                mod = arr[i];
                check = true;
            }
            // 빈도수 같을 때 두번째 최빈값
            else if(max == cnt && check == true){
                mod = arr[i];
                check = false;
            }
        }

        System.out.println(Math.round(sum / n));
        System.out.println(arr[(n-1)/2]);
        System.out.println(mod);
        System.out.println(arr[n-1] - arr[0]);
    }
}
