package 백준.company.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ166637 {
    // 괄호 추가하기 / 골드3 / dfs, 백트래킹
    /**
     * 1. 이전의 연산 결과를 순서대로 계산함
     * 2. 이전의 연산 결과 오른쪽에 있는 2개의 값을 괄호로 처리하여 계산하고 이전의 연산 결과와 합침.
     */
    static int n;
    static int answer;
    static ArrayList<Character> operator;
    static ArrayList<Integer> nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String str = br.readLine();

        operator = new ArrayList<>();
        nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char c = str.charAt(i);
            if(c == '+' || c == '-' || c == '*'){
                operator.add(c);
                continue;
            }
            nums.add(Character.getNumericValue(c));
        }

        answer = Integer.MIN_VALUE;
        dfs(nums.get(0), 0);

        System.out.println(answer);
    }

    static void dfs(int result, int opIdx){
        // 주어진 연산자의 개수를 초과하였을 경우
        if(opIdx >= operator.size()){
            answer = Math.max(answer, result);
            return;
        }

        // 괄호가 없는 경우
        int res1 = calc(operator.get(opIdx), result, nums.get(opIdx + 1));
        dfs(res1, opIdx + 1);
        
        // 괄호가 있는 경우
        if(opIdx + 1 < operator.size()){ // 인덱스 범위 오류 방지
            // result의 오른쪽에 있는 값을 연산함.
            int res2 = calc(operator.get(opIdx + 1), nums.get(opIdx + 1), nums.get(opIdx + 2));

            // 현재 result와 방금 구한 괄호 값을 연산한 결과와 괄호 오른쪽에 존재하는 연산자의 인덱스를 넘김.
            dfs(calc(operator.get(opIdx), result, res2), opIdx + 2);
        }
    }
    
    static int calc(char op, int n1, int n2){
        switch(op){
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '*':
                return n1 * n2;
        }
        return -1;
    }
}
