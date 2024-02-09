import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[][] schedule;
    static PriorityQueue<Integer> pq;

    static void solve(){
        pq = new PriorityQueue<>((o1,o2)->(o1-o2));

        pq.offer(schedule[0][1]);

        for(int i=1;i<N;i++){
            int end = pq.peek();
            if(end<=schedule[i][0]) {
                pq.poll();
            }
            pq.offer(schedule[i][1]);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        schedule = new int[N][2];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());
            schedule[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(schedule,(o1,o2)->( o1[0]==o2[0] ? (o1[1] - o2[1]) : (o1[0] - o2[0])));

        solve();

        System.out.println(pq.size());


    }

}
