package 백준.company.완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ3040 {
    // 백설공주와 일곱난장이 / 브론즈 2 / 완탐
    // 의자 7개, 접시 7개, 나이프 7개
    // 일곱난장이의 모자에 적혀진 수의 합이 100이 되도록 적어놓음
    // 아홉 난장이의 모자에 쓰여있는 수가 주어질 때, 일곱난쟁이를 찾는 프로그램
    // 9명 중 7명을 뽑는 조합(무작위로 뽑기 때문에 순서가 상관없음)
    static boolean[] visited;
    static int[] nums;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new int[9];
        nums = new int[7];

        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dfs(0, 0);
    }

    static void dfs(int cnt, int start) {
        if(cnt == 7){ // 7명을 뽑았다면, 뽑은 난쟁이의 수를 더하고 100이라면 출력
            int total = 0;
            for (int i = 0; i < nums.length; i++) {
                total += nums[i];
            }
            if(total == 100){
                for (int i = 0; i < nums.length; i++) {
                    System.out.println(nums[i]);
                }
            }
            return;
        }

        for (int i = start; i < arr.length; i++) {
            nums[cnt] = arr[i];
            dfs(cnt + 1, i + 1);
        }
    }
}
