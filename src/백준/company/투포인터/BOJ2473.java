package 백준.company.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2473 {
    // 세 용액 / 골드 4 / 이분탐색
    // 세 포인터를 움직이는 방식은 너무 복잡하고 그렇다고 for문 세개를 돌려서 완탐하기에도 범위가 너무 크다.
    // 따라서 기준점 하나의 값을 지정하고 두 용액을 투 포인터로 사용하는 방식이 가장 간단하다.
    /*
        기준점으로 잡을 하나의 값을 for문으로 lt,rt로 설정할 두 수를 제외한 범위까지 돌려준다
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] arr = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long min = Integer.MAX_VALUE;
        long[] res = new long[3]; // 결과를 담을 배열

        long diff = Long.MAX_VALUE;

        // 가장 왼쪽부터 시작해서 가장 오른쪽 끝까지
        for (int i = 0; i < n; i++) {
            int lt = i+1; // 기준점으로 i를 잡았으니 그 다음부터
            int rt = n-1;

            while(lt < rt){
                // 세 용액의 합
                long sum = arr[i] + arr[lt] + arr[rt];

                // 차이 계산
                long cur_diff = Math.abs(sum);

                // 차이가 더 작다면 원소 저장
                if(cur_diff < diff){
                    diff = cur_diff;
                    res[0] = arr[i];
                    res[1] = arr[lt];
                    res[2] = arr[rt];
                }

                if (sum > 0) { // 차이가 0보다 크다. 오른쪽 원소를 한 칸 앞으로
                    rt--;
                } else { // 차이가 0보다 작다. 왼쪽 원소를 한 칸 앞으로
                    lt++;
                }
            }
        }

        System.out.println(res[0] + " " + res[1] + " " + res[2]);
    }
}
