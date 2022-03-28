package 백준.company.정렬1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ2751 {
    // 수 정렬하기 2 / 실버 5 / 병합정렬, Collection.sort()로 해결 가능
    static int n;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    /**
     * 병합정렬로 해결해보자 -> 안되네..
     */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        //list 계열 중 하나를 쓰면 된다.
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(list);

        for(int val : list){
            sb.append(val).append('\n');
        }
        System.out.println(sb);
    }
}
