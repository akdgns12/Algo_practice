package 백준.company.완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1057 {
    // 토너먼트 / 실버2 / 완탐
    // N명이 참가하는 토너먼트, 인접한 번호끼리 경기. 이긴 사람은 다음 라운드에 진출하고, 진 사람은 그 라운드에서 떨어진다. 만약 그 라운드의 참가자가 홀수라면
    // 마지막 번호를 가진 참가자는 다음 라운드로 부전승, 다음 라운드에선 번호를 1번부터 다시 매긴다.
    // 1라운드에서 김지민의 번호와 임한수의 번호가 주어질 때, 김지민과 임한수가 몇 라운드에서 대결하는지 리턴

    // 간단한 수학 문제, N번호의 참가자는 경기에서 이겼을 때 다음 라운드 번호는 (N+1)/2
    // 김지민과 임한수의 다음 라운드 번호가 동일하다면 둘이 같이 경기를 했다는 의미이기 때문에 그 때 count값 리턴
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int cnt = 0;

        while (cnt < (N + 1) / 2) {
            cnt++;
            K = (K+1)/2;
            L = (L+1)/2;

            if(K == L){
                System.out.println(cnt);
                return;
            }
        }

        if(cnt == 0) System.out.println(-1);
    }
}
