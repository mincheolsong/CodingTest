import java.util.*;
import java.io.*;

public class Main{
    
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N,M;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1];
        for(int i=1;i<N+1;i++){
            arr[i] = i;
        }
        
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a,b;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            int tmp = arr[a];
            arr[a] = arr[b];
            arr[b] = tmp;
        }
        
        for(int i=1;i<N+1;i++){
            System.out.print(arr[i]+" ");
        }
        
        
    }
}