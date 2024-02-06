import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] arr;
    static int[] ans;
    static int min;

    static void solve(){

        int left = 0, right = N-1;

        while(left<right){
            int cur = arr[left]+arr[right];

            if(Math.abs(cur) < min){
                min = Math.abs(cur);
                ans[0] = arr[left];
                ans[1] = arr[right];
            }

            if(cur == 0){
                return;
            }

            if(cur < 0){
                left+=1;
            }else{
                right-=1;
            }

        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        ans = new int[2];
        min = 2_000_000_000;

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        System.out.println(ans[0] + " " + ans[1]);




    }

}
