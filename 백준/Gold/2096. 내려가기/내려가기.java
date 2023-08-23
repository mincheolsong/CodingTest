import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// dfs로 다 탐색
public class Main {
    static int N;
    static int[][] arr;


    static void clone(int[][] a){
        for(int i=0;i<N;i++){
            for(int j=0;j<3;j++){
                a[i][j] = arr[i][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];
        int[][] dp = new int[N][3];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                arr[i][j] = tmp;
            }
        }

        clone(dp);

        for(int r=1;r<N;r++){
            int first,second,third;
            first = dp[r-1][0];
            second = dp[r-1][1];
            third = dp[r-1][2];

            for(int c=0;c<3;c++){
                if(c==0){
                    dp[r][c] = Math.max(first,second) + dp[r][c];
                }else if(c==1){
                    dp[r][c] = Math.max(Math.max(first,second),third) + dp[r][c];
                }else if(c==2){
                    dp[r][c] = Math.max(second,third) + dp[r][c];
                }
            }
        }

        int max = Arrays.stream(dp[N-1]).max().getAsInt();

        clone(dp);

        for(int r=1;r<N;r++){
            int first,second,third;
            first = dp[r-1][0];
            second = dp[r-1][1];
            third = dp[r-1][2];

            for(int c=0;c<3;c++){
                if(c==0){
                    dp[r][c] = Math.min(first,second) + dp[r][c];
                }else if(c==1){
                    dp[r][c] = Math.min(Math.min(first,second),third) + dp[r][c];
                }else if(c==2){
                    dp[r][c] = Math.min(second,third) + dp[r][c];
                }
            }
        }



        int min = Arrays.stream(dp[N-1]).min().getAsInt();

        System.out.println(max+" "+min);


    }
}