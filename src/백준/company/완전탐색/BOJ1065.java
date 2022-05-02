package 백준.company.완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1065 {
    // 한수 / 실버4 / 완탐
    // 어떤 양의 정수 X의 각 자리가 등차수열을 이룬다면, 그 수를 한수라고 한다. 등차수열은 연속된 두 개의 수의 차이가 일정한 수열을 말한다.
    // N이 주어졌을 때, 1보타 크거나 같고, N보다 작거나 같은 한수의 개수를 리턴
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 1~99까지는 무조건 한수
        if(N < 100){
            System.out.println(N);
        }
        else{
            int count = 99;
            for (int i = 100; i <= N; i++) {
                if(han(i))
                    count++;
                }
            System.out.println(count);
            }
        }

    static boolean han(int number) {
        int num1 = number / 100 - (number % 100 / 10); // 셋째 자릿수와 둘째 자릿수의 차
        int num2 = (number % 100) - number%10; // 둘째 자릿수와 첫째 자릿수의 차
        if(num1 == num2)
            return true;
        else
            return false;
    }
}
