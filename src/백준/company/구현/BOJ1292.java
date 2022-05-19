package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1292 {
    // 쉽게푸는문제 / 실버5 / 구현
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        ArrayList<Integer> list = new ArrayList<>();
        int sum = 0;
        // 1, 2 2, 3 3 3, 4 4 4 4, 5 5 5 5
        // 1부터 B까지
        for (int i = 1; i <= b; i++) {
            // i를 i번 반복해서 list에 추가
            for (int j=1; j<=i; j++)
                list.add(i);
        }

        // 인덱스는 0부터 시작하기 때문에 1 뺴워야함
        int idx = a - 1;
        for (int k = 0; k <= list.size(); k++) {
            sum += list.get(idx++);
            // 끝에 도달하면 break;
            if(idx == b)
                break;
        }

        System.out.println(sum);
    }
}
