import java.io.*;
import java.util.*;


// dp(i) = max(dp(i-1),dp(i-2)) + a(i)

public class Main {

    static int n;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[3][n];

        dp[1][0] = arr[0];
        if(n>=2) dp[0][1] = dp[1][0];

        for(int i=1;i<n;i++){
            int max = dp[0][i];

            dp[1][i] = dp[0][i-1] + arr[i];
            dp[2][i] = dp[1][i-1] + arr[i];
            if(i+1 < n){
                max = Math.max(max,dp[1][i]);
                max = Math.max(max,dp[2][i]);
                dp[0][i+1] = max;
            }
        }

        /*for(int i=0;i<3;i++){
            System.out.println(Arrays.toString(dp[i]));
        }*/

        int ans = dp[0][n-1];
        for(int i=1;i<3;i++){
            ans = Math.max(ans,dp[i][n-1]);
        }
        System.out.println(ans);

    }

}


