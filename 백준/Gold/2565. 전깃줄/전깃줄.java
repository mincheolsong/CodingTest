import java.io.*;
import java.util.*;

// a(i) < a(i+1) -> b(i) < b(i+1) 이어야 교차 하지 않음
// a(i-1) < a(i) -> b(i-1) < b(i) 이어야 교차 하지 않음

public class Main {

    static int N;
    static int[][] lines;
    static int[] memo;
    static void dp(){

        for(int i=1;i<N+1;i++){
            memo[i] = 1;

            for(int j=1;j<i;j++){
                if(lines[j][1] < lines[i][1]){
                    memo[i] = Math.max(memo[i],memo[j]+1);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        lines = new int[N+1][2];
        memo = new int[N+1];

        for(int i=1;i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<2;j++){
                lines[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(lines,(int[]o1,int[]o2)->(o1[0]-o2[0]));

        dp();

        

        System.out.println(N - Arrays.stream(memo).max().getAsInt());

    }

}


