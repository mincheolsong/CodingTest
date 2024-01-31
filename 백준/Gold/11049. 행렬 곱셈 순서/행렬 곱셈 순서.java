import java.io.*;
import java.util.*;


class Matrix{
    int r,c;
    public Matrix(int r,int c){
        this.r = r;
        this.c = c;
    }

}
public class Main {

    static int INF = (int)1e9;
    static int N;
    static Matrix[] matrix;
    static long[][] memo;


    static void dp(){

        for(int c=0;c<N;c++){
            for(int r=c;r>=0;r--){
                for(int k=r;k<c;k++){
                    memo[r][c] = Math.min(memo[r][c],memo[r][k]+memo[k+1][c]+matrix[r].r*matrix[k].c*matrix[c].c);
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        matrix = new Matrix[N];
        memo = new long[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            matrix[i] = new Matrix(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        for(int i=0;i<N;i++){
            Arrays.fill(memo[i],INF);
            memo[i][i] = 0;
        }

        dp();

        /*for(int i=0;i<N;i++){
            System.out.println(Arrays.toString(memo[i]));
        }*/
        System.out.println(memo[0][N-1]);

    }

}
