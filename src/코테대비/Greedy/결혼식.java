package 코테대비.Greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class 결혼식 {
    /*
        시간 '(출발 or 도착 문자)' 형태로 시간 기준으로 정렬하고, 시간이 같다면 도착 문자순으로 정렬해야
        한 사람의 떠나는 시간과 다른 사람의 도착 시간이 같을 때 중복으로 세는 경우를 피할 수 있다.
     */
    static class Time implements Comparable<Time>{
        int time;
        char state;
        public Time(int time, char state){
            this.time = time;
            this.state = state;
        }
        @Override
        public int compareTo(Time o){
            if(this.time == o.time) return this.state - o.state;
            else return this.time - o.time;
        }
    }
    static int n;
    static int answer = Integer.MIN_VALUE;

    public static int solution(ArrayList<Time> arr){
        Collections.sort(arr);
        int cnt = 0; //그 시각에 몇 명이 존재하는지
        for (Time ob : arr){
            if(ob.state == 's') cnt++;
            else cnt--;
            answer = Math.max(answer, cnt);
        }


        return answer;
    }

    public static void main(String[] args) {
        결혼식 T = new 결혼식();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        ArrayList<Time> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int arrive = sc.nextInt();
            int leave = sc.nextInt();
            arr.add(new Time(arrive, 's'));
            arr.add(new Time(leave, 'e'));
        }
        System.out.println(T.solution(arr));
    }
}