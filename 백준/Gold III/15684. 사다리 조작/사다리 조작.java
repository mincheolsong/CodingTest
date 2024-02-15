import java.io.*;
import java.util.*;


public class Main {


    static int N,M,H;
    static int[][] arr;
    static int ans;
    static final int INF = (int)1e9;

    static boolean go(int row, int col,int start){ // (1, num)위치에서 시작

        if(arr[row][col]==9 && start==col){
            return true;
        }

        if(arr[row][col]==0){ // 아래로 내려가기
            if(go(row+1,col,start)) return true;
        }else if(arr[row][col]==1){ // 오른쪽으로 가기
            if(go(row+1,col+1,start)) return true;
        }else if(arr[row][col]==2){ // 왼쪽으로 가기
            if(go(row+1,col-1,start)) return true;
        }

        return false;

    }
    static boolean check(){

        for(int i=1;i<N+1;i++){ // (1행, i열) 에서 출발 시키기
            if(!go(1,i,i)){
                return false;
            }
        }

        return true;
    }
    static void solve(int n, int row){

        if(n>3){
            return;
        }

        if(check()){
            ans = Math.min(ans,n);
            return;
        }

        for(int i=row;i<H+1;i++){
            for(int j=1;j<N;j++){
                if(arr[i][j]==0 && arr[i][j+1]==0){
                    // 다리 놓기
                    arr[i][j]=1;
                    arr[i][j+1]=2;
                    solve(n+1,i);
                    arr[i][j]=0;
                    arr[i][j+1]=0;
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        arr = new int[H+2][N+1];
        ans = INF;
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a,b;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            arr[a][b] = 1;
            arr[a][b+1] = 2;
        }
        Arrays.fill(arr[H+1],9);
        solve(0,1);

        if(ans==INF) ans = -1;

        System.out.println(ans);



    }

}
