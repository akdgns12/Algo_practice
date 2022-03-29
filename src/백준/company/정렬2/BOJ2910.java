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

        /**
         * HashMap과 LinkedHashMap차이
         * - HashMap은 데이터를 넣을 때 key의 순서를 보장하지 않는다. 반면 LinkedHashMap은 key의 순서를 지킨다
         */
        // 자주 등장하는 빈도순대로 정렬(LinkedHashMap으로 데이터를 넣을 때 key의 순서 지키도록 한다)
        HashMap<Integer, Integer> map = new LinkedHashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        //key들을 모두 배열에 저장한다.
        ArrayList<Integer> list = new ArrayList<>(map.keySet());

        /**
         * 자바에서 Arrays.sort는 int,char같은 primitive type(원시타입) 배열에서 정렬을 지원한다.(String은 예외로 가능) 이 함수의 알고리즘은
         * dual pivot QuickSort
         * 내림차순 정렬을 위해서는 primitive type이 아니라 Wrapper 클래스로 선언해야 한다. 그 이유는 Collections에 도움을 받아야 하기 때문이다.
         * Collections.reverseOrder()을 추가로 인자로 넣으면 된다.
         * ex ) Arrays.sort(numbers, Collections.reverseOrder());
         *
         * Collections.sort는 ArrayList, LinkedList와 같은 Collection 타입의 정렬을 지원
         * 이 함수의 정렬 알고리즘은 merge sort. 그 이유는 퀵 정렬과 달리 병합정렬은 stable한 정렬이기 때문이다. stable한 정렬은 같은 key값을 가진
         * node가 정렬 전과 정렬 후의 위치가 달라지지 않는다는 의미. int 같은 경우, 같은 값이 여러 개 있을 때 해당 숫자는 서로 위치가 달라져도 문제될 것이
         * 없기에 퀵정렬을 사용한다.
         */
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
