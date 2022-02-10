package 코테대비.배열;

import java.util.Scanner;

public class 보이는학생 {
    public static int solution(int n, int[] arr){
        int answer = 1;
        int max = arr[0];

        for (int i = 1; i < n; i++) {
            if(max < arr[i]){
                max = arr[i];
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
