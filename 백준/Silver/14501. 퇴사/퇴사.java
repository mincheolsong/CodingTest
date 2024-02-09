import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[][] arr;
    static int ans;
    static void solve(int idx,int sum,int prev){

        if(idx==N){
            ans = Math.max(ans,sum);
            return;
        }

        if(idx > N){
            ans = Math.max(ans,sum - prev);
            return;
        }


        for(int i=idx;i<N;i++){
            solve(i+arr[0][i],sum+arr[1][i], arr[1][i]);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new int[2][N];
        ans = 0;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int a,b;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            arr[0][i] = a;
            arr[1][i] = b;
        }

        solve(0,0,0);

        System.out.println(ans);

    }

}
