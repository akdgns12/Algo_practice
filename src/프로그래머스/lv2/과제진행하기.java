package 프로그래머스.lv2;

import java.util.Arrays;
import java.util.Stack;

public class 과제진행하기 {
    public static void main(String[] args) {
        과제진행하기 T = new 과제진행하기();
        String plans[][] = {
                {"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}
        };
        System.out.println(T.solution(plans));
    }

    public String[] solution(String[][] plans) {
        String[] answer = new String[plans[0].length];
        Arrays.sort(plans, (o1, o2) -> ToSec(o1[1]) - ToSec(o2[1]));
        Stack<Node> st = new Stack<Node>();

        int size = plans.length;
        int idx = 0;
        int z = 0;
        int currentTime = 0;
        int startTime = 0;
        int playTime = 0;
        int nextTime = 0;
        while(idx != size){
            startTime = ToSec(plans[idx][1]);
            playTime = Integer.parseInt(plans[idx][2]);
            currentTime = startTime + playTime;

            // 진행하던 과제 멈추는지 체크
            if(size - 1 != idx){
                nextTime = ToSec(plans[idx+1][1]);
                if(currentTime > nextTime){
                    st.push(new Node(plans[idx][0], startTime, currentTime - nextTime));
                    currentTime = nextTime;
                    idx++;
                    continue;
                }
            }

            answer[z++] = plans[idx][0];

            // 과제 끝냈는데, 진행중이던 과제 있는지
            while (!st.isEmpty()){
                int remain = nextTime - currentTime;

                Node now = st.pop();
                if(remain >= now.playTime){
                    answer[z++] = now.str;
                    currentTime += remain;
                }else{
                    st.push(new Node(now.str, now.startTime, now.playTime - remain));
                    break;
                }
            }

            idx++;
        }

        while(!st.isEmpty()){
            answer[z++] = st.pop().str;
        }


        return answer;
    }

    static class Node{
        String str;
        int startTime;
        int playTime;
        public Node(String str, int startTime, int playTime){
            this.startTime = startTime;
            this.playTime = playTime;
            this.str = str;
        }
    }

    static int ToSec(String time){
        String[] ch = time.split(":");
        return Integer.parseInt(ch[0]) * 60 + Integer.parseInt(ch[1]);
    }
}
