import java.io.*;
import java.util.*;


public class Main {

    static int H,W,N,M;
    static int[][] map;

    static void solve(){

        for(int i=0;i<H;i+=(M+1)){
            for(int j=0;j<W;j+=(N+1)){
                map[i][j]=1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        
        int x = W / (M+1);
        int y = H / (N+1);
        if(W%(M+1)!=0){
            x+=1;
        }
        if(H%(N+1)!=0){
            y+=1;
        }

        System.out.println(x*y);

    }
}