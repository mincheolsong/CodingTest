import java.io.*;
import java.util.*;


public class Main {

    static int N,M,K;
    static long[] memo;
    static long[] arr;
    static int a,b;
    static long c;

    static void update_memo(int idx,long gap){
        for(int i=idx;i<N+1;i++){
            memo[i] += gap;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        memo = new long[N+1];
        arr = new long[N+1];

        for(int i=1;i<N+1;i++){
            long n = Long.parseLong(br.readLine());
            arr[i] = n;
            memo[i] = memo[i-1] + arr[i];
        }

        /*for(int r=1;r<N+1;r++){
            System.out.println(Arrays.toString(memo[r]));
        }*/


        for(int i=0;i<M+K;i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Long.parseLong(st.nextToken());

            if(a==1){
                long gap = c - arr[b];
                arr[b]=c;
                update_memo(b,gap);

            }else if(a==2){
                System.out.println(memo[(int)c]-memo[b-1]);
            }
        }

    }

}
