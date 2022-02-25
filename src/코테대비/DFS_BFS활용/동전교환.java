package 코테대비.DFS_BFS활용;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class 동전교환 {
    static int n, m;
    static int result = Integer.MAX_VALUE;

    public static void dfs(int count, int sum, Integer[] arr){
        if(sum > m) return;
        if(count >= result) return; //이미 구한 result보다 더 깊은 탐색 가지치기
        if(sum == m){
            result = Math.min(result, count);
            return;
        }
        else{
            for (int i = 0; i < n; i++) { //동전의 종류만큼 가지뻗기
                dfs(count+1, sum + arr[i], arr);
            }
        }
    }

    public static void main(String[] args) {
        동전교환 T = new 동전교환();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); //동전종류의 개수
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        //입력받은 값을 큰 값부터 탐색해서 효율적이게 탐색할 수 있도록 내림차순
        Arrays.sort(arr, Collections.reverseOrder());
        m = sc.nextInt(); //거슬러줄 금액
        T.dfs(0, 0, arr);
        System.out.println(result);
    }
}
