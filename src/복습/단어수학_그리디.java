package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class 단어수학_그리디 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = c.length - 1; j >= 0; j--) {
                int pow = (int) Math.pow(c[j], c.length - 1 - j);
                map.put(c[j] - 'A', map.getOrDefault(c[j] - 'A', 0) + pow);
            }
        }

        ArrayList<Integer> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList, (o1, o2) -> (map.get(o1) - map.get(o2)));

        int total = 0;
        int num = 10 - map.size();
        for (int key : map.keySet()) {
            total += map.get(key) * num;
        }

        System.out.println(total);
    }
}
