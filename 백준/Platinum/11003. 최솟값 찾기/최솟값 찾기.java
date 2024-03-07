import java.io.*;
import java.util.*;



public class Main {

    static int N,L;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        Deque<int[]> q = new ArrayDeque<>();

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++){

            while(!q.isEmpty() && q.peekLast()[0] > arr[i]){
                q.pollLast();
            }

            q.offer(new int[]{arr[i],i});

            while(q.peekFirst()[1] < i-L+1){
                q.poll();
            }

            sb.append(q.peekFirst()[0]).append(" ");
        }

        System.out.println(sb.toString());






    }

}


