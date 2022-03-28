package 백준.company.정렬1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ18870 {
    // 좌표압축 / 실버2 / 정렬
    /**
     * 결국, 문제를 잘 읽어보면
     * 주어진 입력 값들을 정렬했을 때 몇번째 순서인지 출력하는 문제
     * -> 입력 배열은 후에 다시 사용되어 새로운 배열에 복사한 다음 정렬.
     * Map을 사용하여 0부터 좌표의 순서를 저장. 중복되는 좌표값들이 있기 때문에
     * Map을 사용.
     * 입력값들을 Map에서 찾으면 그 순서를 알 수 있다.
     */
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        HashMap<Integer, Integer> map = new HashMap<>();
        int idx = 0;
        for (int n : sorted) {
            if(!map.containsKey(n)){
                map.put(n, idx++);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int n : arr) {
            sb.append(map.get(n)).append(" ");
        }

        System.out.println(sb.toString());
    }
}
