package 백준.company.완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1476 {
    // 날짜 계산 / 실버5 / 완탐
    static int E, S, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 지구, 태양, 달 의 수
        E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 조건에 맞게 우리가 알고있는 년도로 변환
        // 1년이 지날때마다 세 수는 모두 1씩 증가, 범위를 초과하면 다시 1로
        int e = 1, s = 1, m = 1;
        int answer = 1;
        while (true) {
            if(e > 15){
                e = 1;
            }

            if(s > 28) {
                s = 1;
            }

            if(m > 19) {
                m = 1;
            }

            if(e == E & s == S & m == M){
                break;
            }

            e++;
            s++;
            m++;
            answer++;
        }

        System.out.println(answer);
    }
}
