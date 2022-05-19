package 백준.company.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13458 {
    // 시험감독 / 브론즈 2 / 구현
    static int n;
    static int[] arr;
    static int b,c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine()); // 시험장 개수
        arr = new int[n];

        // 각각의 시험장 마다 응시생 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        b = Integer.parseInt(st.nextToken()); //총감독관 감시할 수 있는 응시자 수
        c = Integer.parseInt(st.nextToken()); //부감독관 감시할 수 있는 응시자 수

        long answer = 0;
        for (int i = 0; i < n; i++) {
            int students = arr[i];
            students -= b; // 총감독관은 무조건 한 명이므로
            answer++; //감독관 수 1만 올려줌

            if(students > 0){ //총감독관이 감시하는 학생 수 제외한 나머지
                if(students % c == 0){
                    answer += students / c;
                }
                else{ //부감독관이 감시하는 학생 수로 나누고 나머지가 남았으면 1명 추가
                    answer += students / c  + 1;
                }
            }
        }

        System.out.println(answer);
    }
}
