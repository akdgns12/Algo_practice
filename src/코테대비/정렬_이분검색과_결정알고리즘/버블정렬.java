package 코테대비.정렬_이분검색과_결정알고리즘;

import java.util.Scanner;

public class 버블정렬 {
    /*
        1. 앞에서부터 현재원소와 바로 다음의 원소를 비교
        2. 현재원소가 다음 원소보다 크면 원소를 교환
        3. 다음 원소로 이동하여 해당 원소와 그 다음 원소를 비교한다
     */
    public static int[] solution(int n, int[] arr){
        //round는 배열 크기 - 1 만큼 진행됨
        for (int i = 0; i < n-1; i++) {
            //각 라운드별 비교횟수는 배열 크기의 현재 라운드를 뺀 만큼 비교함
            for (int j = 0; j < n - i-1; j++) {
                /*
                     현재 원소가 다음 원소보다 클 경우
                     서로 원소의 위치를 교환
                 */
                if(arr[j] > arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
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
