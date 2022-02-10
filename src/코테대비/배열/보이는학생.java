package 코테대비.배열;

import java.util.Scanner;

public class 보이는학생 {
    public static int solution(int n, int[] arr){
        int answer = 0;

        for (int i = 0; i < n-1; i++) {
            if (arr[i] < arr[i + 1]) {
                answer += 1;
            }
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

        System.out.println(solution(n,arr));
    }
}
