import java.io.*;
import java.util.*;

public class Main {

    // 백준 11444
    //  | f(n+1), f(n) |     |1 1| 의 n승과 같다
    //  | f(n),  f(n-1)|     |1 0|
    // 분할 정복

    static long n;

    static long[][] calc(long[][] a, long[][] b){ // 행렬 곱 계산함수
        long[][] result = new long[2][2];

        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                for(int k=0;k<2;k++){
                    result[i][j] = ((result[i][j]%1_000_000_007) +  ((a[i][k] %1_000_000_007)* (b[k][j]%1_000_000_007))%1_000_000_007)%1_000_000_007;
                }
            }
        }
        return result;
    }

    static long[][] solve(long n){
        if(n==1){
            return new long[][]{{1,1},{1,0}};
        }

        long d = n / 2;
        long[][] tmp = solve(d);
        long[][] ans = calc(tmp,tmp);
        if(n%2==0){
            return ans;
        }

        return calc(ans,new long[][]{{1,1},{1,0}});

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(br.readLine());

        long[][] ans = solve(n);

        System.out.println(ans[0][1]);


    }


}



