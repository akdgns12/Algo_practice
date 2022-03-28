package 백준.company.정렬1;

import java.io.BufferedReader;

public class Merge_Sort {
    static int n = 10;
    static int[] arr = new int[10000001];
    static int[] tmp = new int[10000001]; // merge 함수에서 리스트 2개를 합친 결과를 임시로 저장하고 있을 변수

    public static void main(String[] args) {
        merge_sort(0, n);
        for(int i=0; i<n; i++) System.out.print(arr[i] + " ");
    }

    // mid = (st + en) / 2라고 할 때, a[st:mid], arr2[mid:en]은 이미 정렬이 되어있는 상태
    static void merge(int st, int en){
        int mid = (st + en) / 2;
        int lIdx = st; // a[st:mid]에서 값을 보기 위해 사용하는 index
        int rIdx = en; // a[mid:en]에서 값을 보기 위해 사용하는 index
        for(int i=st; i<en; i++){
            if(rIdx == en) tmp[i] = arr[lIdx++];
            else if(lIdx == st) tmp[i] = arr[rIdx++];
            else if(arr[lIdx] <= arr[rIdx]) tmp[i] = arr[lIdx++];
            else tmp[i] = arr[rIdx++];
        }
        for(int i=st; i<en; i++) arr[i] = tmp[i]; // tmp에 임시저장해둔 값을 a로 옮길
    }

    // a[st:en]을 정렬하고 싶다.
    static void merge_sort(int st, int en){
        if(en - st == 1) return; // 길이 1인 경우
        int mid = (st + en) / 2;
        merge_sort(st, mid); // st to mid-1을 정렬한다.
        merge_sort(mid, en); // mid to en-1을 정렬한다.
        merge(st, en);
    }
}
