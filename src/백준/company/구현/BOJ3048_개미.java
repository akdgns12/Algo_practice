package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ3048_개미 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n1 = Integer.parseInt(st.nextToken());
        int n2 = Integer.parseInt(st.nextToken());

        // 두 그룹에서 중복되는 알파벳 없음
        String str1 = br.readLine();
        String str2 = br.readLine();

        ArrayList<Node> list = new ArrayList<>();
        for(int i=n1-1; i>=0; i--){
            list.add(new Node(str1.charAt(i), 1));
        }

        for(int i=0; i<n2; i++){
            list.add(new Node(str2.charAt(i), -1));
        }

        int T = Integer.parseInt(br.readLine());
        while(T --> 0){
            for(int i=0; i<list.size() - 1; i++){
                Node cur = list.get(i);
                Node next = list.get(i+1);

                if(cur.dir != -1 && cur.dir != next.dir){ // 첫번째 그룹이고 현재문자가 다음 문자와 다른방향일 경우 switch
                    list.set(i, next);
                    list.set(i+1, cur);
                    i++; // next의 다음으로
                }
            }
        }

        String ans = "";
        for(int i=0; i<list.size(); i++) ans += list.get(i).word;

        System.out.println(ans);
    }

    static class Node{
        char word;
        int dir;
        public Node(char word, int dir){
            this.word = word;
            this.dir = dir;
        }
    }
}
