package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ5073 {
    // 삼각형과 세 변 / 브론즈 3 / 수학
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 0 && b == 0 && c == 0)
                break;

            int max = Math.max(a, Math.max(b,c));

            // 삼각형이 될 수 없는 경우의 수 따지면 x
            if(max >= a+b || max >= a+c || max >= b+c){
                System.out.println("Invalid");
                continue;
            }

            if(a == b && b == c){
                System.out.println("Equilateral");
            }else if(a == b || a == c || b == c){
                System.out.println("Isosceles");
            }else if(max < a+b && max < a+c && max < b+c){
                System.out.println("Scalene");
            }
        }
    }
}
