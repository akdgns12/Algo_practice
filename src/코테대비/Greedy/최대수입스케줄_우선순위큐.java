package 코테대비.Greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 최대수입스케줄_우선순위큐 {
    static class Lecture implements Comparable<Lecture> {
        int money, date;
        public Lecture(int money, int date){
            this.money = money;
            this.date = date;
        }
        @Override
        public int compareTo(Lecture o ){ //시간 내림차순으로 정렬
            return o.date - this.date;
        }
    }
    static int n, m, d;
    static int max = Integer.MIN_VALUE;

    public static int solution(ArrayList<Lecture> arr){
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        Collections.sort(arr);
        int j = 0;
        for (int i=max; i>=1; i--){ //max날짜부터 1일까지
            for (; j < n; j++) {
                if(arr.get(j).date < i) break;
                else pq.offer(arr.get(j).money);
            }
            if(!pq.isEmpty()) answer += pq.poll();
        }


        return answer;
    }

    public static void main(String[] args) {
        최대수입스케줄_우선순위큐 T = new 최대수입스케줄_우선순위큐();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        ArrayList<Lecture> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            m = sc.nextInt(); //강의료
            d = sc.nextInt(); //강의 일자
            arr.add(new Lecture(m,d));
            if(d > max) max = d;
        }
        System.out.println(T.solution(arr));
    }
}
