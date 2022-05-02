package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 한수_완탐  {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N < 100) {
            System.out.println(N);
        } else {
            int count = 99; // 100 미만의 수는 무조건 하수이기 때문에

            for (int i = 100; i <= N; i++) {
                if(han(i))
                    count++;
            }
            System.out.println(count);
        }
    }

    static boolean han(int n){
        int num1 = n / 100 - (n % 100 / 10); // 첫째 자리와 둘째 자리의 차
        int num2 = (n%100/10) - n%10; // 둘째 자리와 셋째 자리의 차
        if (num1 == num2) {
            return true;
        }else{
            return false;
        }
    }
}
