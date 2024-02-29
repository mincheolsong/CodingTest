import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] graph;

    static long solve(int start,int end){

        if(start==end){
            return graph[start];
        }

        int mid = (start+end)/2;

        long left = solve(start,mid);
        long right = solve(mid+1,end);

        long ans = Math.max(left,right);

        int ld = mid, rd = mid;
        long h = graph[mid];

        while(start < ld && rd < end){
            if(graph[ld-1] < graph[rd+1]){
                rd++;
                h = Math.min(h,graph[rd]);
            }else{
                ld--;
                h = Math.min(h,graph[ld]);
            }
            ans = Math.max(ans,(rd-ld+1)*h);
        }

        while(rd < end){
            rd++;
            h = Math.min(h,graph[rd]);
            ans = Math.max(ans,(rd-ld+1)*h);
        }

        while(start < ld){
            ld--;
            h = Math.min(h,graph[ld]);
            ans = Math.max(ans,(rd-ld+1)*h);
        }

        return ans;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = Integer.parseInt(br.readLine());
        graph = new int[n];
        
        for(int i=0;i<n;i++){
            graph[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(solve(0,n-1));



    }

}


