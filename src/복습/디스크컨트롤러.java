package 복습;

import java.util.Arrays;
import java.util.PriorityQueue;

public class 디스크컨트롤러 {
    public static void main(String[] args) {
        디스크컨트롤러 p = new 디스크컨트롤러();
        int[][] jobs = {{0,3}, {1,9}, {2,6}};
        System.out.println(p.solution(jobs));
    }

    public int solution(int[][] jobs){
        int answer = 0;
        // 요청 시간순 오름차순으로 정렬
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        // 소요시간순 오름차순으로 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> o1[1] - o2[1]);

        int count = 0;
        int end = 0;
        int i = 0;
        while(count < jobs.length){
            while(i < jobs.length && jobs[i][0] <= end){
                pq.add(jobs[i++]);
            }

            if(pq.isEmpty()){
                end = jobs[i][0];
            }else{
                int[] tmp = pq.poll();
                answer += tmp[1] + end - tmp[0];
                end += tmp[1];
                count++;
            }
        }

        return answer / jobs.length;
    }


}
