package 백준.company.완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1339 {
    // 단어수학 / 골드4 / 완탐, 그리디 알고리즘
    // 생각
    // 1. 가장 높은 자릿수를 가지는 알파벳 순대로 높은 수를 부여.
    // 따라서, 각 알파벳 자릿수 크기대로 값을 정렬한 후 각 순위에 맞는 수를 부여해주면 최적해를 구할 수 있다.
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] arr = new String[n];

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = c.length - 1; j >= 0; j--) {
                int pow = (int) Math.pow(10, c.length - 1 - j);
                map.put(c[j] - 'A', map.getOrDefault(c[j] - 'A', 0) + pow);
            }
        }

        // map의 key만 담은 arraylist
        ArrayList<Integer> keyList = new ArrayList<>(map.keySet());
        // map의 key(알파벳)값들을 value값 기준 오름차순
        Collections.sort(keyList, (o1, o2) -> (map.get(o1) - map.get(o2)));

        int total = 0;
        int num = 10 - map.size();
        for (int key : keyList) {
            total += map.get(key) * num; // 자릿수 * 높은값
            num++;
        }
        System.out.println(total);
    }
}
