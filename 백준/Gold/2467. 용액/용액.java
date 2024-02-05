import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] arr;
    static int ans;
    static int[] liquid;

    static void solve(){
        int left = 0, right = N-1;

        while(left<right){
            int cur = arr[left]+arr[right];

            if(Math.abs(cur) < ans){
                ans = Math.abs(cur);
                liquid[0] = arr[left];
                liquid[1] = arr[right];
            }

            if(cur==0){
                return;
            }

            if(cur > 0){
                right-=1;
            }else if(cur < 0){
                left+=1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        liquid = new int[2];

        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ans = 2_000_000_000;
        solve();
        System.out.println(liquid[0] + " " + liquid[1]);



    }

}
