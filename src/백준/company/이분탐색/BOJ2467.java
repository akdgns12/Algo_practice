package 백준.company.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2467 {
    // 용액 / 골드5 / 이진탐색
    /*
        산성, 알칼리 용액. 양의 정수, 음의 정수
        두 용액을 혼합해 특성값이 0에 가장 가까운 용액을 만들려 함
     */
    // 투포인터, 이진탐색으로 모두 가능한데
    // 비슷하지만 탐색 방법이 다르다
    /*
        1. 투포인터로 해결할 때는
        0과 n-1을 각 투포인터로 지정한다
        투포인터를 사용하여 합이 0에 가까운 구간을 탐색한다

        2. 이진탐색으로 해결할 때는
        투 포인터와 아이디어는 같지만 탐색방식이 다름. 하나의 원소를 픽한 다음, 나머지 원소 중
        현재 원소* -1와 가장 가까운 원소를 이진탐색을 통해 찾는다.
        그래야 0에 가장 가까운 값을 찾을 수 있기 때문
            1. arr[i] 기준점을 뽑는다.
            2. [i+1 ~ n-1]구간에 있는 원소 중 arr[i]* -1와 가장 가까운 값을 이진탐색을 통해 탐색
                1. min > Math.abs(arr[i] + arr[mid]) 0에 가장 가까운 값이 갱신되면 저장
                2. arr[mid] >= -arr[i] 기준점* -1보다 크다면 위치를 rt = mid - 1로 옮겨줌
                3. arr[mid] < -arr[i] 기준점* -1보다 작다면 위치를 lt = mid + 1로 옮겨줌

     */
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int lt = 0;
        int rt = arr.length - 1;

        while (lt <= rt) {
            int mid = (lt + rt) / 2;

            if(arr[mid] == 0){

            }
        }
    }
}
