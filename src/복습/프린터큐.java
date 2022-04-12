package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 프린터큐 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            Queue<Node> q = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int prior = Integer.parseInt(st.nextToken());
                q.offer(new Node(j, prior));
            }

            int rank = 1;
            while(!q.isEmpty()){
                Node cur = q.poll();

                Iterator<Node> it = q.iterator();
                boolean isCheck = true;
                while(it.hasNext()){
                    Node document = (Node) it.next();
                    if(cur.prior < document.prior){
                        isCheck = false;
                        break;
                    }
                }

                if(isCheck == false) q.offer(cur);
                else{
                    if(cur.idx == m) System.out.println(rank);
                    else rank++;
                }
            }
        }
    }

    static class Node{
        int idx;
        int prior;

        public Node(int idx, int prior){
            this.idx = idx;
            this.prior = prior;
        }
    }
}
