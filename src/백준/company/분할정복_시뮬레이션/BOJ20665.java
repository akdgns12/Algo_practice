package 백준.company.분할정복_시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20665 {
    // 독서실 거리두기 / 골드 5 / 분할정복 && 시뮬
    static int N,T,P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 좌석 개수
        T = Integer.parseInt(st.nextToken()); // 독서실 예약자 수
        P = Integer.parseInt(st.nextToken()); // 민규가 좋아하는 좌석번호

        for(int i=0; i<T; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<2; j++){
                String str = st.nextToken();
            }
        }
    }
}
