import java.io.*;
import java.util.*;


// DP
// 점화식
// DP(i,j) = DP(i-1, j-1) + 1,  x(i)==y(j)인 경우
// DP(i,j) = max(DP(i-1,j), DP(i,j-1)), x(i)!=y(i) 인 경우

public class Main {

    static int[][] memo;
    static char[] x, y;

    static String dp(){
        int max = 0;

        for(int c=1; c<x.length+1; c++){
            for(int r=1; r<y.length+1; r++){
                if(x[c-1]==y[r-1]){
                    memo[r][c] = memo[r-1][c-1]+1;
                    if(memo[r][c] > max){
                        max = memo[r][c];
                    }
                }else{
                    memo[r][c] = Math.max(memo[r-1][c],memo[r][c-1]);
                }
            }
        }

        if(memo[y.length][x.length]!=0) {
            StringBuilder sb = new StringBuilder();
            int r = y.length;
            int c = x.length;
            while (true) {
                if (memo[r][c] == 0) {
                    break;
                }

                if (x[c - 1] == y[r - 1]) {
                    sb.append(x[c - 1]);
                    r -= 1;
                    c -= 1;
                    continue;
                }

                if (memo[r][c - 1] > memo[r - 1][c]) {
                    c -= 1;
                } else {
                    r -= 1;
                }
            }
            sb.reverse();
            return sb.toString();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        x = br.readLine().toCharArray();
        y = br.readLine().toCharArray();

        memo = new int[y.length+1][x.length+1];

        String LCS = dp();

        System.out.println(memo[y.length][x.length]);
        if(LCS!=null){
            System.out.println(LCS);
        }

    }

}
