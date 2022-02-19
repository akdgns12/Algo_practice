package 코테대비.정렬_이분검색과_결정알고리즘;

import java.util.ArrayList;
import java.util.Scanner;

public class 선택정렬 {
    // 선택정렬 - 최솟값을 찾아 맨앞자리의 값과 위치를 바꿔주는 것
    public static int[] solution(int n, int[] arr){
        for (int i = 0; i < n - 1; i++) {
            int idx = i;
            for (int j = i + 1; j < n; j++) {
                if(arr[j] < arr[idx])
                    idx = j;
            }
            int tmp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = tmp;
        }

        return arr;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        for (int x : solution(n, arr)) System.out.print(x + " ");
    }
}
