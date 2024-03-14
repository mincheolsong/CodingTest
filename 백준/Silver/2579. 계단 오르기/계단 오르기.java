import java.io.*;
import java.util.*;


public class Main {

    static int n;
    static int[] arr;
    static int[][] memo;

    static void dp(){
        for(int i=1;i<n;i++){
            memo[0][i] = Math.max(memo[1][i-1],memo[2][i-1]);

            memo[1][i] = memo[0][i-1] + arr[i];
            memo[2][i] = memo[1][i-1] + arr[i];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        memo = new int[3][n];

        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        memo[1][0] = arr[0];

        dp();

        System.out.println(Math.max(memo[1][n-1],memo[2][n-1]));

        /*for(int i=0;i<3;i++){
            System.out.println(Arrays.toString(memo[i]));
        }*/





    }

}





