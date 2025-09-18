
import java.util.*;
import java.io.*;

public class Main {

    static final int INF = (int)1e9;
    static int N,M,H;
    static int[][] ladder;
    static int ans;

    static boolean go(int r,int c,int oc){

        if(r==H+1){
            if(c==oc) return true;
            return false;
        }

        if(ladder[r][c]==0){
            if(go(r+1,c,oc)) return true;
        }else if(ladder[r][c]==1){
            if(go(r+1,c+1,oc)) return true;
        }else if(ladder[r][c]==2){
            if(go(r+1,c-1,oc)) return true;
        }

        return false;
    }
    static boolean check(){

        for(int c=1;c<N+1;c++){
            if(!go(1,c,c)) return false;
        }
        return true;
    }
    static boolean solve(int n,int goal,int row){

        if(n==goal){
            if(check()){
                ans = Math.min(ans,goal);
                return true;
            }
            return false;
        }

        for(int r=row;r<H+1;r++){
            for(int c=1;c<N;c++){
                if(ladder[r][c]==0 && ladder[r][c+1]==0){
                    ladder[r][c] = 1;
                    ladder[r][c+1] = 2;
                    if(solve(n+1,goal,r)) return true;
                    ladder[r][c] = 0;
                    ladder[r][c+1] = 0;
                }

            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        ladder = new int[H+1][N+1];
        ans = INF;

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            ladder[a][b] = 1;
            ladder[a][b+1] = 2;
        }

        for(int i=0;i<4;i++){
            if(solve(0,i,1)) break;
        }

        if(ans==INF) System.out.println(-1);
        else System.out.println(ans);



    }

}