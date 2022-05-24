package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2503 {
    // 숫자 야구 / 실버 4 /구현
    // 민혁이의 물음들과 각각의 물음에 대한 영수의 답이 입력으로 주어질 때
    // 영수가 생각하고 있을 가능성이 있는 답의 총 개수
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        String[] mh = new String[n]; // 민혁이가 질문한 세 자리 수
        int[][] mh_info = new int[n][2]; // 민혁이가 질문한 세 자리수에 대한 정보 [0] : 스트라이크 [1] : 볼

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            mh[i] = st.nextToken();
            mh_info[i][0] = Integer.parseInt(st.nextToken());
            mh_info[i][1] = Integer.parseInt(st.nextToken());
        }

        // 2. 연산 브루트포스(서로 다른 세자리 수)
        int answer = 0;
        for (int i = 1; i <= 9; i++) { // 첫째자리
            for (int j = 1; j <= 9; j++) { // 둘째자리
                if(i != j) {
                    for (int k = 1; k <= 9; k++) { // 셋째자리
                        if(k != i && k != j) {
                            boolean flag = true;
                            for (int s = 0; s < n; s++) { // 민혁이가 질문한 수
                                int strike = 0;
                                int ball = 0;
                                String str = mh[s];
                                if(str.charAt(0) - '0' == i) strike++;
                                if(str.charAt(1) - '0' == j) strike++;
                                if(str.charAt(2) - '0' == k) strike++;

                                if(str.charAt(0) - '0' == j || str.charAt(0) - '0' == k) ball++;
                                if(str.charAt(1) - '0' == i || str.charAt(1) - '0' == k) ball++;
                                if(str.charAt(2) - '0' == i || str.charAt(2) - '0' == j) ball++;

                                if(mh_info[s][0] != strike || mh_info[s][1] != ball){
                                    flag = false;
                                    break;
                                }
                            }
                            if(flag) answer++;
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
