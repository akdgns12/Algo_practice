package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2292_벌집 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 규칙 : 벌집 6개씩 늘어남
        int cnt = 1;
        int zip = 1; // 벌집 수

        if(N == 1){
            System.out.println(1);
        }else{
            while(zip <= N){
                zip = zip + (6 * cnt);
                cnt++;
            }
            System.out.println(cnt);
        }
    }
}
