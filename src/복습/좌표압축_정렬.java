package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 좌표압축_정렬 {
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

        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        HashMap<Integer, Integer> map = new HashMap<>();

        int idx = 0;
        for (int n : sorted) {
            if (!map.containsKey(n)) {
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
