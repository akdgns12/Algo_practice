package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1599_민식어리팩 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];
        for(int i=0; i<N; i++) arr[i] = br.readLine().replaceAll("k", "c").replaceAll("ng", "nz");
        Arrays.sort(arr);
        for(int i=0; i<N; i++) System.out.println(arr[i].replaceAll("c", "k").replaceAll("nz", "ng"));
    }
}
