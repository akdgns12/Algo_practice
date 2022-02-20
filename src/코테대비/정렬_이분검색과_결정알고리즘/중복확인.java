package 코테대비.정렬_이분검색과_결정알고리즘;

import java.util.Arrays;
import java.util.Scanner;

public class 중복확인 { // HashMap으로 O(n)으로 해결할 수도 있다. 연습삼아 정렬으로도 풀 수 있어야 한다.
    public static String solution(int n, int[] arr){
        String answer = "U";
        Arrays.sort(arr);
        for (int i = 0; i < n-1; i++) {
            if(arr[i] == arr[i+1]) return "D";
        }

        return answer;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(solution(n, arr));
    }


}
