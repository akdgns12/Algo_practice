package 코테대비.DFS_BFS활용;

import java.util.Scanner;

public class 동전교환 {
    static int n, m;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) {
        동전교환 T = new 동전교환();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); //동전종류의 개수
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        m = sc.nextInt(); //거슬러줄 금액

        System.out.println(result);
    }
}
