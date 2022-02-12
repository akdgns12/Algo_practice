package 코테대비.투포인터_슬라이딩윈도우;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class 두배열합치기_단순히정렬한풀이_이렇게하면안됨 {
    public static ArrayList<Integer> solution(int n, int m, int[] arr, int [] arr2){
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            answer.add(arr[i]);
        }

        for (int j = 0; j < m; j++) {
            answer.add(arr2[j]);
        }

        Collections.sort(answer);
        return answer;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        int[] arr2 = new int[m];
        for (int j = 0; j < m; j++) {
            arr2[j] = sc.nextInt();
        }

        for (int x : solution(n, m, arr, arr2)) {
            System.out.print(x + " ");
        }
    }
}
