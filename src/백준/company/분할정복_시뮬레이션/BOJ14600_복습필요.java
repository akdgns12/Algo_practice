package 백준.company.분할정복_시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14600_복습필요 {
    // 샤워실 바닥깔기 / 골드 5 / 분할정복
    /*
        대표적인 트로미노 알고리즘 문제
     */
    static int k, n, num = 0, arr[][] = new int[130][130];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 배수구의 위치 좌표(x,y)
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;
        // 문제에서 가장 왼쪽 아래가(1,1), 가장 오른쪽 위가(2^k, 2^k)
        arr[y][x] = -1;
        n = (int)Math.pow(2, k);

        solution(0, 0, n);

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    // x,y,한 변의 길이
    static void solution(int x, int y, int size){
        int sz = size / 2; // 4분할 했을 시 한 변의 길이
        num += 1;
        // 4 구간으로 분할 후, 각 구간에 아무것도 채워진게 없는 경우 중간에 L트로미노의 일부를 칠함.
        if(divide(x, y, sz)) arr[x + sz - 1][y + sz - 1] = num;
        if(divide(x, y + sz, sz)) arr[x + sz - 1][y + sz] = num;
        if(divide(x + sz, y, sz)) arr[x + sz][y + sz - 1] = num;
        if(divide(x + sz, y + sz, sz)) arr[x + sz][y + sz] = num;

        if(size == 2) return;

        solution(x, y, sz);
        solution(x, y + sz, sz);
        solution(x + sz, y, sz);
        solution(x + sz, y + sz, sz);
    }
    // 각 구간에 채워진 것이 있는지 없는지 판별하는 메서드
    static boolean divide(int x, int y, int size){
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if(arr[i][j] != 0) return false;
            }
        }
        return true;
    }
}
