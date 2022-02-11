package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 회전초밥 {
    static int n,d,k,c;
    static int[] food;
    static int[] visited;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        food = new int[n]; // 벨트에 놓인 초밥의 번호
        visited = new int[d+1]; // 각 번호의 초밥을 먹었는지 확인하는 방문 체크 배열

        for (int i = 0; i < n; i++) {
            food[i] = Integer.parseInt(br.readLine());
        }

        sliding_window();
        System.out.println(result);
    }

    static void sliding_window() {
        int count = 0;

    }
}
