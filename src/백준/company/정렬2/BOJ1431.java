package 백준.company.정렬2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ1431 {
    // 시리얼 번호 / 실버 3 / 정렬
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        String[] serial = new String[n];
        for (int i = 0; i < n; i++) {
            serial[i] = br.readLine();
        }

        Arrays.sort(serial, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() < o2.length()){
                    return -1;
                }else if(o1.length() > o2.length()){
                    return 1;
                }else { // 둘의 길이가 같다면 자리수 합이 작은게 먼저
                    int sum = 0;
                    for (int i = 0; i < o1.length(); i++) {
                        if(o1.charAt(i) >= '0' && o1.charAt(i) <= '9'){
                            sum += o1.charAt(i) - '0';
                        }
                    }

                    int sum2 = 0;
                    for (int i = 0; i < o2.length(); i++) {
                        if(o2.charAt(i) >= '0' && o2.charAt(i) <= '9'){
                            sum2 += o2.charAt(i) - '0';
                        }
                    }

                    if(sum < sum2){
                        return -1;
                    }else if(sum > sum2){
                        return 1;
                    }else{ // 자리수 합도 같다면 사전 순 비교
                        return o1.compareTo(o2);
                    }
                }
            }
        });

        for (String i : serial) System.out.println(i);
    }
}
