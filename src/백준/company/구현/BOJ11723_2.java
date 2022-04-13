package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11723_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());
        int[] arr = new int[21];
        int idx = 0;
        String command = "";

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            command = st.nextToken();
            if (st.hasMoreTokens()) {
                idx = Integer.parseInt(st.nextToken());
            }

            switch (command) {
                case "add" :
                    arr[idx] = 1;
                    break;
                case "remove" :
                    arr[idx] = 0;
                    break;
                case "check":
                    sb.append(arr[idx] + "\n");
                    break;
                case "toggle" :
                    arr[idx] = arr[idx] == 1 ? 0 : 1;
                    break;
                case "all" :
                    Arrays.fill(arr, 1);
                    break;
                case "empty":
                    Arrays.fill(arr, 0);
                    break;
            }
        }
        System.out.println(sb.toString());
    }
}
