package 백준.company.정렬2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ1181 {
    // 단어순 정렬 / 실버 4 / 정렬
    static int n;
    static String[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new String[n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            arr[i] = str;
        }

        Arrays.sort(arr, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2){
                if(o1.length() == o2.length()){
                    return o1.compareTo(o2);
                }else if(o1.length() < o2.length()){
                    return -1;
                }else{
                    return 1;
                }
            }
        });
        System.out.println(arr[0]);
        for (int i = 1; i < n; i++) {
            //중복되지 않는 단어만 출력
            if (!arr[i].equals(arr[i - 1])) {
                System.out.println(arr[i]);
            }
        }
    }
}
