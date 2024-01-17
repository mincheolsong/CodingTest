import java.io.*;
import java.util.*;


public class Main {

    static final int INF = (int)1e9;
    static int N,S;
    static int[] arr;
    static int ans = INF;

    static void solve(){

        int sum = 0;
        int end = 0;
        for(int start=0;start<N;start++){

            while(sum < S && end < N){
                sum += arr[end];
                end+=1;
            }

            if(sum >= S){
                ans = end-start < ans ? end-start : ans;
            }
            sum -= arr[start];

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(ans == INF ? 0 : ans);



    }

}

