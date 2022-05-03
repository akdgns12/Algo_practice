package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 단어수학 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 가장 높은 자리수를 가지는 알파벳 순으로 높은 숫자 부여
        // 각 단어 자리수 크기대로 값 정렬하기
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = c.length - 1; j >= 0; j--) {
                int pow = (int) Math.pow(10, c.length - 1 - j); // 해당 단어의 자릿수
                map.put(c[j] - 'A', map.getOrDefault(c[j] - 'A', 0) + pow);
            }
        }

        ArrayList<Integer> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList, (o1, o2) -> (map.get(o1) - map.get(o2))); // 자리수 기준으로 오름차순(자리수 낮은 것들 먼저 오도록 정렬)

        int total = 0;
        int num = 10 - map.size(); // 높은값부터 9를 부여해주기 위해, 10 - 알파벳의 수
        for (int key : keyList) {
            total += map.get(key) * num; // 자릿수 * 낮은수부터
            num++;
        }
        System.out.println(total);
    }
}
