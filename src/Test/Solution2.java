package Test;


import java.io.IOException;

public class Solution2 {
    static boolean[] isOn; // 전구 켜짐 여부

    public static void main(String[] args) throws IOException {
        Solution2 T = new Solution2();
        int[] A = {2, 1, 3, 5, 4};
        System.out.println(T.solution(A));
    }

    static int solution(int[] A) {
        int answer = 0;

        int len = A.length;
        isOn = new boolean[len];

        for (int i = 0; i < len; i++) { // 순서대로 A[i]번째 전구를 킨다
            int swit = A[i];

            // swit번째 전구가 켜지려면, 첫번째부터 ~ swit-1번째까지 스위치 모두 켜져 있어야 함
            if(check(swit)) answer++;
        }

        return answer;
    }

    static boolean check(int num) {
        for (int i = 0; i < num; i++) {
            if(isOn[i]) continue;
            else return false;
        }

        return true;
    }
}

