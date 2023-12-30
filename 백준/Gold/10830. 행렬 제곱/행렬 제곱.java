import java.io.*;
import java.util.*;

public class Main {


    static int N;
    static long B;
    static int[][] arr;

    static int[][] solve(long n){ // n제곱
        if(n==1){
            int[][] tmp = new int[N][N];
            for(int r=0;r<N;r++){
                for(int c=0;c<N;c++){
                    tmp[r][c]=arr[r][c]%1000;
                }
            }
            return tmp;
        }

        if(n==2){
            int[][] tmp = new int[N][N];
            int result;
            for(int r=0;r<N;r++){
                for(int c=0;c<N;c++){
                    result = 0;
                    for(int i=0;i<N;i++){
                        result = ((result%1000) + ((arr[r][i]%1000)*(arr[i][c]%1000)%1000))%1000;
                    }
                    tmp[r][c]=result;
                }
            }
            return tmp;
        }

        // 분할정복
        long m = n/2;
        int[][] result = solve(m);

        int[][] powerOfResult = new int[N][N];

        // result * result를 계산하는 과정
        for(int r=0;r<N;r++){
            for(int c=0;c<N;c++){
                int sum = 0;
                for(int i=0;i<N;i++){
                    sum = (sum%1000 + ((result[r][i]%1000)*(result[i][c]%1000))%1000)%1000;
                }
                powerOfResult[r][c]=sum;
            }
        }



        if(n%2==0){ // 짝수이면 result * result; 한 행렬이 결과값
            return powerOfResult;
        }else{ // 홀수이면 result * result * arr 한 행렬이 결과값
            int[][] tmp = new int[N][N];
            for(int r=0;r<N;r++){
                for(int c=0;c<N;c++){
                    int sum = 0;
                    for(int i=0;i<N;i++){
                        sum = (sum%1000 + (powerOfResult[r][i]%1000)*(arr[i][c]%1000))%1000;
                    }
                    tmp[r][c]=sum;
                }
            }
            return tmp;
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        arr = new int[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 입력 끝
        int[][] ans = solve(B);

        for(int[] a : ans){
            StringBuilder sb = new StringBuilder();
            for(int n : a){
                sb.append(n).append(" ");
            }
            System.out.println(sb.toString());
        }




    }

}



