import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static long[] a;
    static long[] sorted;
    static long cnt;

    static void merge(int start,int mid,int end){
        int i = start;
        int j = mid+1;
        int k = start;

        while(i <= mid && j<=end){
            if(a[i] <= a[j]){
                sorted[k] = a[i++];
            }else{
                cnt += mid-i+1;
                sorted[k] = a[j++];
            }
            k+=1;
        }

        while(i<=mid){
            sorted[k++] = a[i++];
        }
        while(j<=end){
            sorted[k++] = a[j++];
        }

        for(int t=start;t<=end;t++){
            a[t] = sorted[t];
        }

    }

    static void mergeSort(int start,int end){
        // 크기가 1보다 큰 경우
        if(start < end){
            int mid = (start+end)/2;
            mergeSort(start,mid);
            mergeSort(mid+1,end);
            merge(start,mid,end);
        }

    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        a = new long[N];
        sorted = new long[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        cnt = 0;

        mergeSort(0,N-1);
        System.out.println(cnt);


    }

}





