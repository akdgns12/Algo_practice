package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 카카오_신고결과받기 {
    public static int[] solution(String[] id_list, String[] report, int k){
        int n = id_list.length;
        int[] answer = new int[n];
        HashSet<String> rep = new HashSet<>();
        HashMap<String, Integer> map = new HashMap<>();
        int idx = 0;
        for (String id : id_list) {
            map.put(id, idx++);
        }

        int[] cnt = new int[n];
        for (String r : report) {
            if(rep.contains(r)) continue; // 중복 제외
            String[] idList = r.split(" ");
            cnt[map.get(idList[1])]++;
            rep.add(r);
        }

        for (String r : rep){
            String[] idList = r.split(" ");
            if(cnt[map.get(idList[1])] >= k){
                answer[map.get(idList[0])]++;
            }
        }

        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int k = 2;
        for (int x : solution(id_list, report, k)) System.out.println(x);
    }
}
