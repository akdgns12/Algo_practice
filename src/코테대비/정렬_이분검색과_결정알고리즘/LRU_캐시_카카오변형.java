package 코테대비.정렬_이분검색과_결정알고리즘;

import java.util.Scanner;

public class LRU_캐시_카카오변형 { // 삽입정렬 문제!!
    public static int[] solution(int size, int n, int[] arr){
        int[] cache = new int[size];
        for (int x : arr) {
            int pos = -1;
            for (int i=0; i<size; i++) if(x == cache[i]) pos = i; // 캐시 히트일경우, 히트된 곳의 인덱스 pos변수에 저장
            if(pos == -1) { // 캐시 미스인 경우
                for (int i = size - 1; i >= 1; i--) {
                    cache[i] = cache[i - 1];
                }
            }
            else{
                for (int i = pos; i >= 1; i--) {
                    cache[i] = cache[i-1];
                }
            }
            cache[0] = x;
        }

        return cache;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        for (int x : solution(s,n,arr)) System.out.print(x + " ");
    }
}
