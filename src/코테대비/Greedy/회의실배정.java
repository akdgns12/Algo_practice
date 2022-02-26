package 코테대비.Greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class 회의실배정 {
    static class Time implements Comparable<Time> {
        int start, end;
        public Time(int start, int end){
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Time o){
            //종료시간 기준으로 오름차순, 종료시간 같으면 시작시간 기준으로 오름차순
            if(this.end == o.end) return this.start - o.end;
            else return this.end - o.end;
        }
    }
    static int n;

    public static int solution(ArrayList<Time> arr, int n){
        Collections.sort(arr);
        int cnt = 0;
        int endTime = 0;
        for (Time ob : arr){
            if(ob.start >= endTime){
                cnt++;
                endTime = ob.end;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        회의실배정 T = new 회의실배정();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        ArrayList<Time> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            arr.add(new Time(start, end));
        }
        System.out.println(T.solution(arr, n));
    }
}
