import java.io.*;
import java.util.*;


class Jewel{
    int m,v;

    public Jewel(int m,int v){
        this.m = m;
        this.v = v;
    }


}

public class Main {


    static int N,K;
    static Jewel[] jewel;
    static int[] bag;
    static long answer;

    static void solve(){

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2)->-(o1-o2));

        int j = 0;
        for(int i=0;i<K;i++){
            while(j<N && bag[i] >= jewel[j].m){
                pq.offer(jewel[j].v);
                j+=1;
            }

            if(!pq.isEmpty()){
                answer += pq.poll();
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        jewel = new Jewel[N];
        bag = new int[K];
        answer = 0;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int m,v;
            m = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            jewel[i] = new Jewel(m,v);
        }

        Arrays.sort(jewel,(o1,o2)->(o1.m-o2.m));

        for(int i=0;i<K;i++){
            bag[i]=Integer.parseInt(br.readLine());
        }
        Arrays.sort(bag);

        solve();

        System.out.println(answer);



    }

}
