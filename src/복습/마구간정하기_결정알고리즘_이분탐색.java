package 복습;

import java.util.Arrays;
import java.util.Scanner;

public class 마구간정하기_결정알고리즘_이분탐색 {
    // 주어진 최대 거리 dist로 배치 가능한 말 마리 수 return하는 함수
    static int count(int[] arr, int dist) {
        int cnt = 1;
        int ep = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] - ep >= dist){ // 다음 좌표의 마구간과 이전 좌표사이의 거리가 받아온 거리보다 작거나 같다면
                cnt++; // 말 배치하고
                ep = arr[i]; // 이전좌표 현재 좌표로 갱신
            }
        }
        return cnt;
    }

    public static int solution(int n, int c, int[] arr){
        int answer = 0;
        Arrays.sort(arr);
        int lt = 1;
        int rt = arr[n-1];

        while (lt <= rt){
            int mid = (lt + rt) / 2;
            if(count(arr, mid) >= c){
                answer = mid;
                lt = mid + 1;
            }
            else rt = mid - 1;
        }
        return answer;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(solution(n,c,arr));
    }
}
