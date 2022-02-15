package 복습;

import java.util.ArrayList;
import java.util.Scanner;

public class 뒤집은소수 {
    public static boolean isPrime(int num){
        if(num == 1) return false;
        for (int i = 2; i < num; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }

    public static ArrayList<Integer> solution(int n, int[] arr){
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int tmp = arr[i];
            int res = 0;
            while (tmp > 0) {
                int t  = tmp%10; // 해당 수의 일의자리
                res = res*10 + t; // 먼저 붙인 자리수 10을 곱해 올려가며 구한 일의자리 끝에 붙이기
                tmp = tmp/10;
            }
            if(isPrime(res)) answer.add(res);
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

        for (int x : solution(n, arr)) {
            System.out.print(x + " ");
        }
    }


}
