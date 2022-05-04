package Test;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Solution3 {
    static boolean[] isOn; // 전구 켜짐 여부

    public static void main(String[] args) throws IOException {
        Solution3 T = new Solution3();
        String S = "photo.jpg, Warsaw, 2013-09-05 14:08:15\n" +
                "john.png, London, 2015-06-20 15:13:22\n" +
                "myFriends.png, Warsaw, 2013-09-05 14:07:13\n" +
                "Eiffel.jpg, Paris, 2015-07-23 08:03:02\n" +
                "pisatower.jpg, Paris, 2015-07-22 23:59:59\n" +
                "BOB.jpg, London, 2015-08-05 00:02:03\n" +
                "notredame.png, Paris, 2015-09-01 12:00:00\n" +
                "me.jpg, Warsaw, 2013-09-06 15:40:22\n" +
                "a.png, Warsaw, 2016-02-13 13:33:50\n" +
                "b.jpg, Warsaw, 2016-01-02 15:12:22\n" +
                "c.jpg, Warsaw, 2016-01-02 14:34:30\n" +
                "d.jpg, Warsaw, 2016-01-02 15:15:01\n" +
                "e.png, Warsaw, 2016-01-02 09:49:09\n" +
                "f.png, Warsaw, 2016-01-02 10:55:32\n" +
                "g.jpg, Warsaw, 2016-02-29 22:13:11";


        System.out.println(T.solution(S));
    }

    static ArrayList<Node>[] list;

    static String solution(String S) {
        String answer = "";

        String[] str = S.split("\n");

        HashMap<String, Integer> map = new HashMap<>(); // 도시, 도시번호

        list = new ArrayList[str.length]; // 도시 번호가 같은 것에 대한 년월일, 시간
        for (int i = 0; i < str.length; i++) {
            list[i] = new ArrayList<>();
        }

        int idx = 0;
        for (int i = 0; i <str.length; i++) {
            String[] arr = str[i].split(" ");

            for (int j = 0; j < arr.length; j++) {
                if(j % 4 == 0){ // i번째 사진이름

                }else if(j % 4 == 1){ // 도시이름
                    if(!map.containsKey(str[j])){
                        map.put(str[j], idx++);
                    }else{ // 이미 나왔던 도시라면
                        int year = changeYearToNum(arr[j+1]);
                        int time = changeTime(arr[j + 2]);
                        list[idx].add(new Node(year, time));
                    }
                }else if(j % 4 == 2){ // 년월일

                }else if(j % 4 == 3){ // 시 분 초

                }
            }
        }




        return answer;
    }

    static int changeYearToNum(String str){
        String[] arr = str.split("-");
        int year = 0;

        for (int i = 0; i < arr.length; i++) {

        }

        return year;
    }

    static int changeTime(String str){
        int time = 0;

        return time;
    }

    static class Node implements Comparable<Node>{
        int year;
        int time;

        public Node(int year, int time){
            this.year = year;
            this.time = time;
        }
        @Override
        public int compareTo(Node o){
            if(this.year == o.year) return this.time - o.time;
            else return this.year - o.year;
        }
    }
}

