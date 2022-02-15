package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 괄호추가하기 {
    static int N, ans;
    static ArrayList<Character> operator;
    static ArrayList<Integer> nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String input = br.readLine();

        operator = new ArrayList<>();
        nums = new ArrayList<>();

        for(int i=0; i<N; i++){
            char c = input.charAt(i);
            if(c == '+' || c == '-' || c == '*'){
                operator.add(c);
                continue;
            }
            nums.add(Character.getNumericValue(c));
        }

        ans = Integer.MIN_VALUE;
        dfs(nums.get(0), 0);

        System.out.println(ans);
    }

    // 연산
    public static int calc(char op, int n1, int n2){
        switch (op){
            case'+':
                return n1 + n2;
            case'-':
                return n1 - n2;
            case'*':
                return n1 * n2;
        }
        return -1;
    }

    // dfs, 백트래킹 활
    public static void dfs(int result, int opIdx){
        // 주어진 연산자의 개수를 초과하였을 경우
        if(opIdx >= operator.size()){
            ans = Math.max(ans, result);
            return;
        }
        // 괄호가 없는 경우
        int res1 = calc(operator.get(opIdx), result, nums.get(opIdx+1));
        dfs(res1, opIdx+1);

        // 괄호가 있는 경우
        if(opIdx + 1 < operator.size()){
            // result의 오른쪽에 있는 있는 값을 연산함
            int res2 = calc(operator.get(opIdx+1), nums.get(opIdx+1), nums.get(opIdx+2));
            // 현재 result와 방금 구한 괄호 값을 연산한 결과와
            dfs(calc(operator.get(opIdx), result, res2), opIdx+2);
        }


    }
}
