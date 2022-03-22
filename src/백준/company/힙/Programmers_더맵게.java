package 백준.company.힙;

import java.util.PriorityQueue;

public class Programmers_더맵게 {
    public static void main(String[] args) {
        int[] scoville = {1,2,3,9,10,12};
        int k = 7;
        Programmers_더맵게 p = new Programmers_더맵게();
        System.out.println(p.solution(scoville, k));
    }

    public int solution(int[] scoville, int k){
        int answer = 0;

        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int sc : scoville){
            q.offer(sc);
        }

        while(q.peek() <= k){
            if(q.size() == 1){
                return -1;
            }

            int a = q.poll();
            int b = q.poll();
            int c = a + (b*2);
            q.offer(c);
            answer++;
        }

        return answer;
    }
}
