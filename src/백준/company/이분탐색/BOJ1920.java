package 백준.company.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1920 {
    // 수 찾기 / 실버 4 / 이분 탐색
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(st.nextToken());
            System.out.println(binarySearch(arr, n, num));
        }
    }

    static int binarySearch(int[] arr, int n, int target){
        int lt = 0;
        int rt = n-1;

        while(lt <= rt){
            int mid = (lt + rt) / 2;

            if(arr[mid] == target){
                return 1;
            }
            if(arr[mid] < target){
                lt = mid + 1;
            }else{
                rt = mid - 1;
            }
        }
        return 0;
    }
}
