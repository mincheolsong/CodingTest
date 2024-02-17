import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0;i<N;i++){
            pq.offer(arr[i]);
        }

        int ans = 0;
        while(!pq.isEmpty() && pq.size()>1){
            int a,b;
            a = pq.poll();
            b = pq.poll();
            ans += (a+b);
            pq.offer(a+b);
        }
        System.out.println(ans);



    }

}
