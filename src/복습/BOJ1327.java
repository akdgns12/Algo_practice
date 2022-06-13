package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1327 {
    static int n, k;
    static ArrayList<String> al = new ArrayList<>();

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        String origin = "";

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            String str = st.nextToken();
            al.add(str);
            origin += str;
        }

        Collections.sort(al, (o1, o2) -> o1.compareTo(o2));

        String answer = "";

        for (String str : al) {
            answer += str;
        }

        System.out.println(bfs(origin, answer));
    }

    static int bfs(String origin, String answer) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(origin, 0));
        HashSet<String> visited = new HashSet<>();

        while(!q.isEmpty()){
            Node now = q.poll();

            if (now.str.equals(answer)) {
                return now.cnt;
            }

            if (!visited.contains(now.str)) {
                visited.add(now.str);
                for (int i = 0; i <= n - k; i++) {
                    String mid = usingSb(now.str.substring(i, i+k));
                    String head_tail = now.str.substring(0, i) + mid + now.str.substring(k+i, now.str.length());
                    q.offer(new Node(head_tail, now.cnt + 1));
                }
            }
        }
        return -1;
    }

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

