package 백준.company.세그먼트트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2243 {
    // 사탕상자 / 플레 5 / 세그먼트 트리
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 수정이가 사탕상자에 손을 댄 횟수

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            if(a == 1){ // 사탕상자에서 사탕을 꺼내는 경우
                int b = Integer.parseInt(st.nextToken()); // 꺼낼 사탕의 순위
            }else if(a == 2){ // 사탕을 넣는 경우
                int b = Integer.parseInt(st.nextToken()); // 넣을 사탕의 맛을 나타내는 정수
                int c = Integer.parseInt(st.nextToken()); // 그러한 사탕의 개수
                if(c > 0){ // 양수일 경우 사탕을 넣는 경우

                }else{ // 음수일 경우 사탕을 빼는 경우

                }
            }
        }

        // A가 1인 모든 입력에 대해서, 꺼낼 사탕의 맛의 번호를 출력한다
        System.out.println();
    }
}
