package 코테대비.정렬_이분검색과_결정알고리즘;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 결정알고리즘 - 이분검색을 이용해 답을 도출하는 방식
 * 결정알고리즘은 lt ~ rt 사이에 문제에서 요구하는 답이 무조건 있다 했을때만 사용가능
 */
public class 뮤직비디오_결정알고리즘_이분탐색 {
    public static int count(int[] arr, int capacity){
        int cnt = 1, sum = 0; // DVD 장수, DVD에 담아내는 곡들의 합
        for (int x : arr) {
            if (sum + x > capacity) {
                cnt++;
                sum = x;
            }
            else sum += x;
        }
        return cnt;
    }

    public static int solution(int n, int m, int[] arr){
        int answer = 0;
        int lt = Arrays.stream(arr).max().getAsInt(); // arr배열의 최대값을 return
        int rt = Arrays.stream(arr).sum(); // arr배열의 원소를 모두 합한 값 return
        while (lt <= rt) {
            int mid = (lt + rt) / 2; // DVD 한장의 용량
            if(count(arr, mid) <= m) {
                answer = mid;
                rt = mid - 1;
            }
            else
                lt = mid + 1;
        }

        return answer;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(solution(n, m, arr));
    }
}
