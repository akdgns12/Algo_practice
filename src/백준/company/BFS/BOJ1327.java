package 백준.company.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1327 {
    // 소트게임
    // 어떤 수를 뒤집으면 그 수부터 오른쪽으로 k개의 수를 뒤집어야 함.
    // 입력으로 들어온 순열을 오름차순으로 만들려할때, 게임을 최대한 빨리 끝내고 싶을 때, 수를 최소 몇개 선택해야 하는지 구하라
    static int n, k;
    static ArrayList<String> al = new ArrayList<>(); // 오름차순으로 정렬하기 위해서

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // n자리의 순열
        k = Integer.parseInt(st.nextToken());
        String str = ""; // 원래 순열

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            String unit = st.nextToken();
            al.add(unit);
            str += unit;
        }
        // 인덱스 주어진 순열의 길이 - k 부터 시작
        // 수 뒤집고 전체 순열이 오름차순인지 검사
        // 아니라면 인덱스 감소해서 k개의 수 뒤집고 다시 반복

        Collections.sort(al, (o1, o2) -> o1.compareTo(o2)); // 정답 오름차순 문자열

        String answer = "";

        for (String str2 : al) {
            answer += str2;
        }

        // bfs 수행해서 answer 문자열(오름차순 문자열)이 나오면 됨
        System.out.println(bfs(str, answer));
    }

    static int bfs(String str, String answer) { // 빈 문자열, 정답 문자열
        Queue<Node> q = new LinkedList<>();
        HashSet<String> visited = new HashSet<>(); // 중복 피하기 위해
        q.offer(new Node(str, 0));

        while(!q.isEmpty()){
            Node now = q.poll();
            String result = now.str;
            int count = now.cnt;

            if (result.equals(answer)) {
                return count;
            }

            if(!visited.contains(result)){
                visited.add(result);
                for (int i = 0; i <= n - k; i++) {
                    String mid = usingSb(result.substring(i, k+i));
                    String head_tail = result.substring(0, i) + mid + result.substring(i+k, result.length());
                    q.offer(new Node(head_tail, count + 1));
                }
            }
        }

        return -1;
    }

    // reverse메소드 사용하기 위해 StringBuilder 사용
    static String usingSb(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }

    static class Node{
        String str;
        int cnt;

        public Node(String str, int cnt) {
            this.str = str;
            this.cnt = cnt;
        }
    }
}
