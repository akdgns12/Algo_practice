package 코테대비.HashMap_TreeSet;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeSet;

public class K번째큰수 {
    public static int solution(int n, int k, int[] arr){
        int answer = -1;
        TreeSet<Integer> tset = new TreeSet<>(Collections.reverseOrder());
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int l = j + 1; l < n; l++) {
                    tset.add(arr[i] + arr[j] + arr[l]);
                }
            }
        }
        int cnt = 0;
//        tset.remove(143);
//        System.out.println(tset.size());
//        System.out.println(tset.first()); // 제일 앞의 자료
//        System.out.println(tset.last()); // 제일 뒤의 자료

        for (int x : tset) {
            cnt++;
            if (cnt == k) return x;
        }

        return answer;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(solution(n,k,arr));
    }


}
