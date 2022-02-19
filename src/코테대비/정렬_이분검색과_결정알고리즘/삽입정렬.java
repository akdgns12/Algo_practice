package 코테대비.정렬_이분검색과_결정알고리즘;

import java.util.Scanner;

public class 삽입정렬 {
    /*
        1. 현재 타겟이 되는 숫자와 이전 위치에 있는 원소들을 비교(첫번째 타겟은 두번쨰 원소부터 시작)
        2. 타겟이 되는 숫자가 이전 위치에 있던 원소보다 작다면 위치를 서로 교환환다
        3. 그 다음 타겟을 찾아 위와 같은 방법으로 반복
     */
    public static int[] solution(int n, int[] arr){
        for (int i = 1; i < n; i++) {
            int tmp = arr[i], j;
            for (j = i - 1; j >= 0; j--) {
                if(arr[j] > tmp) arr[j+1] = arr[j];
                else break;
            }
            arr[j+1] = tmp;
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
