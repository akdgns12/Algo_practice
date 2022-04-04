package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 괄호추가 {
    static int n;
    static int ans;
    static ArrayList<Character> ops;
    static ArrayList<Integer> nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String str = br.readLine();

        ops = new ArrayList<>();
        nums = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            char c = str.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                ops.add(c);
                continue;
            }
            nums.add(Character.getNumericValue(c));
        }

        ans = Integer.MIN_VALUE;
        dfs(nums.get(0), 0);
        System.out.println(ans);
    }

    static void dfs(int sum, int opIdx){
        if(opIdx >= ops.size()){
            ans = Math.max(sum, ans);
            return;
        }

        // 1. 괄호 없을 때
        int res1 = calc(ops.get(opIdx), sum, nums.get(opIdx+1));
        dfs(res1, opIdx + 1);

        // 2. 괄호 있을 때
        if(opIdx + 1 < ops.size()){ // 인덱스 범위 오류 방지
            // sum의 오른쪽에 있는 값을 연산한다.
            int res2 = calc(ops.get(opIdx + 1), nums.get(opIdx+1), nums.get(opIdx+2));

            // 현재 sum과 방금 구한 괄호 값을 연산한 결과와 괄호 오른쪽에 존재하는 연산자의 인덱스를 넘긴다.
            dfs(calc(ops.get(opIdx), sum, res2), opIdx+2);
        }
    }

    static int calc(char op, int n1, int n2) {
        switch (op) {
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
