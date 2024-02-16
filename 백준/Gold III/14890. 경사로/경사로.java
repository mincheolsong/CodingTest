import java.io.*;
import java.util.*;


public class Main {

    static int N,L;
    static int[][] arr;
    static int ans;

    static void check(int[][] array,int row,int col,int cnt){

        if(col==N){
            ans+=1;
            return;
        }

        int gap;
        if(col-1<0){
            gap = 0;
        }else{
            gap = array[row][col] - array[row][col-1];
        }



        if(col==N-1){
            if(gap==0){

                ans+=1;
            }
            else if(gap==1 && cnt>=L){

                ans+=1;
            }
            else if(gap==-1 && L==1){

                ans+=1;
            }
            return;
        }


        if(gap==0){
            check(array,row,col+1,cnt+1);
        }else if(gap==1){ // 오른쪽 대각선 상승
            if(cnt>=L){
                check(array,row,col+1,1);
            }
        }else if(gap==-1){ // 오른쪽 대각선 하강
            int temp = 0;
            for(int c=col;c<N;c++){
                if(array[row][c]==array[row][col]){
                    if(++temp==L) break;
                }else{
                    break;
                }
            }
            if(temp==L){
                check(array,row,col+L,0);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        ans = 0;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int r=0;r<N;r++){
            check(arr,r,0,0);
        }

        // 배열 90도 회전시키기
        int[][] tmp = new int[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                tmp[i][j] = arr[N-1-j][i];
            }
        }

        for(int r=0;r<N;r++){
            check(tmp,r,0 ,0);
        }

        System.out.println(ans);

    }

}
