package 백준.company.정렬1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class BOJ11931 {
    // 수 정렬하기 4 / 실버 5 / 정렬

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }

        list.sort(Comparator.reverseOrder());

        StringBuilder sb = new StringBuilder();
        for(int val : list){
            sb.append(val).append('\n');
        }

        System.out.println(sb);
    }
}
