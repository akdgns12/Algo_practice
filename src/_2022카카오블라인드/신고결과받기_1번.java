package _2022카카오블라인드;


import java.util.HashMap;
import java.util.HashSet;

public class 신고결과받기_1번 {
    public static int[] solution(String[] id_list, String[] report, int k){
        int[] answer = new int[id_list.length];
        HashMap<String, Integer> s2i = new HashMap<>(); // 아이디를 0,1,2,... 의 인덱스 변환
        int n = id_list.length;
        HashSet<Integer>[] s = new HashSet[n];
        for (int i=0; i<n; i++)
            s[i] = new HashSet<>();

        for (int i=0; i<n; i++)
            s2i.put(id_list[i], i);

        for (String rep : report) {
            String[] idlist = rep.split(" ");
            int id1 = s2i.get(idlist[0]);
            int id2 = s2i.get(idlist[1]);
            s[id2].add(id1);
        }
        for (int i = 0; i < n; i++) {
            if(s[i].size() < k) continue;
            for (int x : s[i]) answer[x]++;
        }

        return answer;
    }
}


