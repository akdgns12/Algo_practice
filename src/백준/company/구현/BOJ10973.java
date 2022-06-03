package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10973 {
    // 이전 순열 / 실버 3 / 구현
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 주어진 수들의 사전순 순열로 가장 처음에 오는 순열 출력
        // 만약 주어진 수가 가장 먼저 오는 순열일 경우 -1
        if (permutation(arr)) {
            for (int i = 0; i < n; i++) {
                System.out.print(arr[i] + " ");
            }
        } else {
            System.out.println("-1");
        }
    }

    static boolean permutation(int[] arr) {
        // 1. arr[i-1] > arr[i]를 만족하는 첫 번째 수 찾기
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] <= arr[i]) {
            i -= 1;
        }

        // 첫번째 순열인 경우
        if (i <= 0) {
            return false;
        }
        // 2. arr[i-1] > arr[j]를 만족하는 첫번째 수 찾기
        int j = arr.length - 1;
        while (arr[j] >= arr[i - 1]) {
            j -= 1;
        }

        // 3.arr[i-1]과 arr[j] swap
        swap(arr, i - 1, j);

        // 4. i부터 arr.length - 1까지 순열 뒤집기
        j = arr.length - 1;
        while (i < j) {
            swap(arr, i, j);
            i += 1;
            j -= 1;
        }
        return true;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
