package 백준.company.정렬2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ10825 {
    // 국영수 / 실버 4 / 정렬
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[][] arr = new String[n][4];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().split(" ");
        }

        Arrays.sort(arr, new Comparator<String[]>(){
            @Override
            public int compare(String[] o1, String[] o2) {
                if(Integer.parseInt(o1[1]) == Integer.parseInt(o2[1])){
                    if(Integer.parseInt(o1[2]) == Integer.parseInt(o2[2])){
                        if(Integer.parseInt(o1[3]) == Integer.parseInt(o2[3])){
                            // 모든 점수가 같으면 이름 사전순
                            return o1[0].compareTo(o2[0]);
                        }
                        // 국어 점수와 영어 점수가 같으면 수학 점수 내림차순
                        return Integer.compare(Integer.parseInt(o2[3]), Integer.parseInt(o1[3]));
                    }
                    // 국어 점수가 같으면 영어점수가 오름차순
                    return Integer.compare(Integer.parseInt(o1[2]), Integer.parseInt(o2[2]));
                }
                // 국어 점수 내림차순
                return Integer.compare(Integer.parseInt(o2[1]), Integer.parseInt(o1[1]));
            }
        });

        for (int i = 0; i < n; i++) {
            System.out.println(arr[i][0]);
        }
    }
}
