package 코테대비.정렬_이분검색과_결정알고리즘;

import java.util.Arrays;
import java.util.Scanner;

public class 마구간정하기_결정알고리즘_이분탐색 {
    static int count(int[] arr, int dist){
        int cnt = 1; // 말의 마리수
        int ep = arr[0];
        for (int i=1; i<arr.length; i++){
            if(arr[i] - ep >= dist){ // arr[i] - ep : 다음 말을 배치하고난 다음 ep에 배치한 말과의 좌표가 받아온 거리보다 크거나 같다면
                cnt++; // arr[i]에 말 배치하고 말 마리수 증가
                ep = arr[i]; // 다음 검사를 위해 ep 좌표 변경
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
            int mid = (lt + rt) / 2; // mid - 가장 가까운 두 말의 거리
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
        System.out.println(solution(n, c, arr));
    }
}
