import java.io.*;
import java.util.*;


public class Main {



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int t = Integer.parseInt(br.readLine());
        int ans = 0;

        for(int i=0;i<n;i++){
            if(arr[i]==t){
                ans+=1;
            }
        }
        System.out.println(ans);

    }



}

