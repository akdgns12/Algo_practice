package SWEA.모의SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5644_무선충전 {
    static int M, A;
    static int[] personA, personB;
    static int[][] bcList;
    static int ax, ay, bx, by;
    static int[] dy = {0, -1, 0, 1, 0}; //0 상 우 하 좌
    static int[] dx = {0, 0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); // 총 이동시간
            A = Integer.parseInt(st.nextToken()); // BC의 개수

            personA = new int[M+1];
            personB = new int[M+1];

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=M; i++){
                personA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=M; i++){
                personB[i] = Integer.parseInt(st.nextToken());
            }

            bcList = new int[A][4];
            for(int i=0; i<A; i++){
                st = new StringTokenizer(br.readLine());
                bcList[i][0] = Integer.parseInt(st.nextToken());
                bcList[i][1] = Integer.parseInt(st.nextToken());
                bcList[i][2] = Integer.parseInt(st.nextToken());
                bcList[i][3] = Integer.parseInt(st.nextToken());
            }

            ax = ay = 1;
            bx = by = 10;

            sb.append("#").append(tc).append(" ").append(move()).append("\n");
        }
        System.out.println(sb.toString());
    }

    static int move(){
        int totalSum = 0;

        for(int i=0; i<=M; i++){
            ax += dx[personA[i]];
            ay += dy[personA[i]];
            bx += dx[personB[i]];
            by += dy[personB[i]];

            totalSum += calcCharge();
        }

        return totalSum;
    }

    static int calcCharge(){
        int max = 0;

        for(int i=0; i<A; i++){
            for(int j=0; j<A; j++){
                int sum = 0;

                int chargeA = check(i, ax, ay);
                int chargeB = check(j, bx, by);

                if(i != j){
                    sum = chargeA + chargeB;
                }else{
                    sum = Math.max(chargeA, chargeB);
                }

                if(max < sum)
                    max = sum;
            }
        }

        return max;
    }

    static int check(int type, int x, int y){
        return (Math.abs(bcList[type][0] - x) + Math.abs(bcList[type][1] - y)) <= bcList[type][2]
                ? bcList[type][3] : 0;
    }
}
