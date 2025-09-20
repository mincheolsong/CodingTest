
import java.util.*;
import java.io.*;

public class Main {

    static int N,D;
    static int[] dp;
    static int[][] shortcut;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        dp = new int[D+1];
        shortcut = new int[N][3];

        for(int i=0;i<D+1;i++){
            dp[i] = i;
        }

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++){
                shortcut[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1;i<D+1;i++){
            dp[i] = dp[i-1] + 1;
            for(int j=0;j<N;j++){
                if(shortcut[j][1]==i){
                    dp[i] = Math.min(dp[i],dp[shortcut[j][0]]+shortcut[j][2]);
                }
            }
        }

        System.out.println(dp[D]);

    }

}