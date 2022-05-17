package 백준.company.완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17135 {
    // 캐슬디펜스 / 골드4 / 완탐
    /*
        3명의 궁수를 각 한칸에 배치할 수 있음. 동시에 공격, 거리가 D 이하인 적 중에서 가장 가까운 적,
        그러한 적이 여럿일 경우 가장 왼쪽에 있는 적, 같은 적이 여러 궁수에게 공격당할 수도 있음
        공격이 끝나면 적이 이동, 적은 아래로 한 칸 이동, 성이 있는 칸으로 이동한 경우 게임에서 제외
        모든 적이 격자판에서 제외되면 게임 끝
     */
    // 궁수 3명을 배치하는 조합 중 어떤 배치가 가장 많은 적을 제거할 수 있는지 알아내야 함
    /*
       1. 3명의 궁수를 배치할 위치 선택
        selArchers(cnt, idx, combiOfArcher)
        cnt : 선택된 궁수의 수
        idx : 선택된 열 값의 다음 값
        combiOfArcher : 3명의 선택된 궁수의 열 값 배열
        for문을 돌면서 combiOfArcher에 0~N-1 중 3개의 조합을 선택
        세 위치가 모두 선택되면 공격~!!

        2. 공격 && kill 수
        attack(combiOfArcher)
        원래는 한 턴이 끝나면 적이 앞으로 이동하지만, 궁수의 행을 옮기는 것이 더 간단하여 그것으로 구현
        turn의 값이 N보다 작을 동안 반복
        궁수의 행 위치 : x = N - turn
        궁수의 열 위치 : y = combiOfArcher[k]
        공격가능한 적을 탐색
        👉 D이하 거리 중, 가장 가깝고 왼쪽인 적의 위치를 q에 new int[]{minX, minY} 저장
        모두 탐색하면, 공격 killOfTurn += 1;
     */
    static class Node{
        int x, y;
        public Node(int x, int y) {
            this.x =x ;
            this.y = y;
        }
    }
    static int[][] map,copy;
    static int[] archer;
    static int N,M,D;
    static int max=Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        archer=new int[M]; // 각 궁수의 행을 저장
        for(int i=0;i<M;i++)
            archer[i]=i;

        map=new int[N][M];
        copy=new int[N][M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j]= Integer.parseInt(st.nextToken());
            }
        }

        int[] temp=new int[3];
        combi(0,0,temp);

        System.out.println(max);
    }

    static void copymap() {
        for(int i=0;i<N;i++)
            copy[i]=Arrays.copyOf(map[i],M);
    }

    static void combi(int depth, int start, int[] temp) {
        // 3명의 궁수를 배치했다면
        if(depth==3) {
            copymap();

            int count=0; // 제거 가능한 적의 수
            int turn=0;
            Queue<Node> q=new LinkedList<>(); // 제거할 적의 위치 저장

            /*
             * 적들을 한 행씩 옮기는 것보다 궁수들을 한 행 위로 올리는 것이 더 간단함!
             * turn이 증가하면서 궁수들의 행 한 칸씩 전진
             */
            while(turn<N) {
                // 3명의 궁수 탐색
                for(int k=0;k<3;k++) {
                    int x=N-turn; // 궁수의 행
                    int y=temp[k]; // 궁수의 열

                    int min=Integer.MAX_VALUE; // 적까지의 최소거리
                    int minX=-1; // 적의 위치 저장
                    int minY=-1;

                    // 모든 적 탐색
                    for(int i=N-1-turn;i>=0;i--) {
                        for(int j=0;j<M;j++) {
                            // 적이 있다면 거리 계산
                            if(copy[i][j]==1) {
                                int distance=Math.abs(i-x)+Math.abs(j-y);

                                // 적과의 거리가 d 이하라면 공격 가능
                                if(distance<=D) {
                                    // 적과의 거리가 가장 최소라면
                                    if(distance<min) {
                                        // 적의 정보 갱신
                                        min=distance;
                                        minX=i;
                                        minY=j;
                                    }
                                    // 최소거리를 가진 적이 둘 이상이라면
                                    else if(distance==min) {
                                        // 가장 왼쪽에 있는 적 정보 저장(minY가 작은 것이 더 왼쪽에 있는 적)
                                        if(minY>j) {
                                            minX=i;
                                            minY=j;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    // 모든 적을 탐색했다면 제거할 적의 위치 큐에 삽입
                    if(minX!=-1&&minY!=-1)
                        q.offer(new Node(minX,minY));
                }

                // 제거할 적의 맵 값을 0으로 변경, 제거한 적의 수 count
                while(!q.isEmpty()) {
                    Node cur =q.poll();

                    /*
                     * 이때 여러 궁수가 하나의 적을 제거할 수 있기 때문에
                     * 맵의 값이 궁수가 있는 곳인지(값이 1인지) 확인하고 죽인 궁수들의 수를 count해주어야 한다.
                     * 이미 앞선 궁수에 의해 제거된 적일수 있기 때문!
                     */
                    if(copy[cur.x][cur.y]==1) {
                        copy[cur.x][cur.y]=0;
                        count++;
                    }
                }
                turn++; // 한 턴 증가 -> 궁수들 한 칸 위로 전진
            }

            max=Math.max(max,count); // 최댓값 갱신
            return;
        }

        // 궁수가 위치할 수 있는 m칸 중 3개의 칸에 궁수 배치 -> 백트래킹, 조합
        for(int i=start;i<archer.length;i++) {
            temp[depth]=archer[i];
            combi(depth+1,i+1,temp);
        }
    }
}
