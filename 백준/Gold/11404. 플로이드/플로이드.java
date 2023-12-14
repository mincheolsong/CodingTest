import java.io.*;
import java.util.*;

public class Main {


    static int n,m;
    static final int INF = (int)1e9;
    static int[][] arr;

    static void print(){
        for(int i=1;i<n+1;i++){
            for(int j=1; j<n+1;j++){
                if(arr[i][j]==INF){
                    System.out.print(0 + " ");
                }else{
                    System.out.print(arr[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        arr = new int[n+1][n+1];

        for(int i=0;i<n+1;i++){
            Arrays.fill(arr[i],INF);
        }

        for(int i=1;i<n+1;i++){
            arr[i][i]=0;
        }


        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if(w < arr[from][to]){
                arr[from][to]=w;
            }
        }

        // 플로이드 워셜
        for(int k=1;k<n+1;k++){
            for(int a=1;a<n+1;a++){
                for(int b=1;b<n+1;b++){
                    arr[a][b] = Math.min(arr[a][k]+arr[k][b],arr[a][b]);
                }
            }
        }

        print();

    }

}
