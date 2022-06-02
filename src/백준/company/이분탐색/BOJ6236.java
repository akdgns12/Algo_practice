package 백준.company.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ6236 {
    // 용돈관리 / 실버2 / 이진탐색
    /*
       n일 동안 자신이 사용할 금액을 계산, M번만 통장에서 돈을 빼서 쓰기로 함
        통장에서 K원을 인출하여, 하루를 보낼 수 있으면 그대로 사용,
        모자라게 되면 남은 금액은 통장에 집어넣고 다시 K원을 인출
     */
    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // N일 동안
        m = Integer.parseInt(st.nextToken()); // M번만 통장에서 돈을 빼서 사용

        // 인출 금액 K를 최소화해야함
        arr = new int[n];
        int lt = 1;
        int rt = 1;
        for (int i = 0; i < n; i++) { // i번째 날에 이용할 금액
            int money = Integer.parseInt(br.readLine());
            arr[i] = money;
            lt = Math.max(lt, arr[i]); // 최소한 이용할 금액 중 가장 큰 금액은 되어야 함
            rt += arr[i];
        }

        int answer = 0;
        while (lt <= rt) {
            int mid = (lt + rt) / 2; // 인출해야할 최소 금액

            // 인출 횟수가 m을 초과하는 경우, 더 적은 인출 횟수를 만들기 위해 더 큰 금액으로 인출하게 끔 조정
            if (getCnt(mid) > m) {
                lt = mid + 1;
            } else { // 인출 횟수가 m 이하인 경우, mid값 저장해두고, 인출 횟수를 늘리기 위해, 더 적은 인출금액으로 조정
                rt = mid - 1;
                answer = mid;
            }
        }


        System.out.println(answer);
    }

    // 인출 횟수
    static int getCnt(int mid) {
        int remain = mid;
        int cnt = 1;

        for (int i = 0; i < n; i++) {
            if (remain - arr[i] < 0) {
                remain = mid;
                cnt++;
            }

            remain -= arr[i];
        }

        return cnt;
    }
}
