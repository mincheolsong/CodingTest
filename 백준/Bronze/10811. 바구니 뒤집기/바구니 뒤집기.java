import java.io.*;
import java.util.*;


// 위상정렬

public class Main {

    static int N,M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        for(int i=1;i<N+1;i++){
            arr[i]=i;
        }

        StringBuilder sb;

        for(int i=0;i<M;i++){

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int[] tmp = new int[b-a+1];
            for(int j=a;j<=b;j++){
                tmp[j-a] = arr[j];
            }


            for(int j=a;j<=b;j++){
                arr[j] = tmp[b-j];
            }


        }

        for(int j=1;j<N+1;j++){
            System.out.print(arr[j] + " ");
        }






    }

}
