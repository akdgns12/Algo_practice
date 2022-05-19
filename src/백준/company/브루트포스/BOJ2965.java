package 백준.company.브루트포스;

import java.util.Scanner;

public class BOJ2965 {
    // 캥거루 세마리 / 수학 / 브론즈3
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        System.out.println(b - a > c - b ? b - a - 1 : c - b - 1);
    }
}
