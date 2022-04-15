package _2018카카오;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 셔틀버스 {
    public static void main(String[] args) {

    }

    static class Solution{
        /*
            1. 로직 구현할때 온 순서대로 버스에 태울 생각이니까 timetable을 정렬하자
            2. timetable을 정렬하고 미리 분 단위로 바꿔놓자 hour * 60 + min으로!! 그래야 int끼리 비교할 수 있어서 좋다
            3. 어차피 주인공 콘은 제일 마지막에 탈 생각이니까 n을 줄여가면서 탑승할 시간 근처로 버스를 흘려보내자(while문)
            4. 자리가 있으면 그냥 버스가 출발할 때 와서 타면 된다.
            5. 근데 자리가 없어서 누군가를 밀쳐내야 하는 상황이라면 제일 마지막에 타는 사람보다 1분 일찍 와서 타면된다.
         */
        public String solution(int n, int t, int m, String[] timetable){
            String answer = "";
            // 크루가 도착하는 시간을 오름차순으로 정렬
            PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
            for(String table : timetable){
                int time = Integer.parseInt(table.substring(0,2)) * 60 + Integer.parseInt(table.substring(3));

                pq.offer(time);
            }

            int busTime = 9*60;
            int last = 0;

            while(n --> 0){ //셔틀 운행 횟수 줄여가면서
                int accept = m;
                int time = 0; // 마지막으로 탄 크루의 시간을 저장하는 변수

                while(!pq.isEmpty()){ // 기다리는 크루가 있다면
                    if(pq.peek() <= busTime && accept > 0){ // 현재 버스의 도착시간보다 일찍왔으며, 수용인원도 있을 때
                        accept--;
                        time = pq.poll();
                    }else break;
                }

                // 마지막 버스가 아님
                if(n > 0) {
                    if (pq.isEmpty()) { // 기다리는 크루가 없다면
                        last = busTime + ((n + 1) * t); // 마지막 버스의 도착시간
                        break;
                    }

                    busTime += t; // 버스의 다음 시간 세팅
                }else{ // 마지막 버스
                    if(accept > 0) last = busTime; // 수용인원이 있다면, 버스의 도착시간으로
                    else last = time - 1; // 없다면, 마지막으로 탄 크루의 시간보다 1분 일찍
                    break;
                }
            }

            answer = String.format("%02d", last/60) + ":" + String.format("%02d", last%60);

            return answer;
        }
    }
}
