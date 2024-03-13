import java.io.*;
import java.util.*;


public class Main {



    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] memo = new int[31];

        memo[2] = 3;
        memo[4] = 11;

        for(int i=6;i<31;i+=2){
            memo[i] = memo[i-2]*3;
            for(int j=2; i-j>2; j+=2){
                memo[i] += memo[j]*2;
            }
            memo[i] += 2;
        }

        System.out.println(memo[N]);



    }

}





