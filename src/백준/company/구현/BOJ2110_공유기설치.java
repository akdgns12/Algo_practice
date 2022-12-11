package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2110_공유기설치 {
    static int N, C;
    static int[] house;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 집 개수
        C = Integer.parseInt(st.nextToken()); // 설치할 공유기 수

        house = new int[N];
        for(int i=0; i<N; i++){
            house[i] = Integer.parseInt(br.readLine());
        }

        // C개의 공유기를 설치해서 가장 인접한 두 공유기 사이의 거리를 최대로 하기
        Arrays.sort(house);

        int lt = 1; // 거리 최솟값
        int rt = house[N-1] - house[0]; // 거리 최댓값

        while(lt <= rt){
            int mid = (lt + rt) / 2;

            // mid 거리로 설치 가능한 공유기 수가 C개가 안되면 => 거리 좁혀서 더 설치해야함
            if(setCCTV(mid) < C){
                rt = mid - 1;
            }else{
                lt = mid + 1;
            }
        }

        System.out.println(lt - 1);
    }

    static int setCCTV(int dist){
        int pre = house[0];
        int cnt = 1;

        for(int i=1; i<N; i++){
            int now = house[i]; // 지금 공유기 설치할 곳

            if(now - pre >= dist){ // 설치할 수 있는 곳이라면
                cnt++;
                pre = now;
            }
        }

        return cnt;
    }
}
