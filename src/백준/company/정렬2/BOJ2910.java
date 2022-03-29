package 백준.company.정렬2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2910 {
    // 빈도 정렬 / 실버 3 / 정렬 -> 카운팅정렬문제같은데 X 카운팅정렬로 하기에 수의 범위가 너무 큼
    static int n,c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 숫자 N개로 이루어진 수열
        c = Integer.parseInt(st.nextToken()); // 숫자는 모두 C보다 작거나 같다

        // 자주 등장하는 빈도순대로 정렬(LinkedHashMap으로 데이터를 넣을 때 key의 순서 지키도록 한다)
        HashMap<Integer, Integer> map = new LinkedHashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        //key들을 모두 배열에 저장한다.
        ArrayList<Integer> list = new ArrayList<>(map.keySet());

        Collections.sort(list, new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b) {
                return Integer.compare(map.get(b), map.get(a));
            }
        });

        Iterator<Integer> it = list.iterator();
        while(it.hasNext()){
            Integer element = it.next();
            for (int i = 0; i < map.get(element); i++) {
                System.out.print(element + " ");
            }
        }


    }
}
