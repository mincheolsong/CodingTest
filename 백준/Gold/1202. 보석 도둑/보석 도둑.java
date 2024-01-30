import java.io.*;
import java.util.*;


class Jewel implements Comparable<Jewel>{

    int m,v;

    public Jewel(int m,int v){
        this.m = m;
        this.v = v;
    }

    @Override
    public String toString(){
        return "[ m : " + m + ", v : " + v + " ]";
    }

    @Override
    public int compareTo(Jewel o){
        if(this.m==o.m){
            return -(this.v-o.v);
        }
        return this.m-o.m;
    }

}


public class Main {



    static int N,K;
    static long ans;
    static Integer[] backpack;
    static Jewel[] jewels;


    static void solve(){

        int j = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2)->-(o1-o2));

        for(int i=0;i<K;i++){

            while(j<N && jewels[j].m <= backpack[i]){
                pq.offer(jewels[j].v);
                j+=1;
            }

            if(!pq.isEmpty()){
                ans += pq.poll();
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        jewels = new Jewel[N];
        backpack = new Integer[K];

        ans = 0;

        for(int i=0;i<N;i++){
            int m,v;
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(m,v);
        }

        for(int i=0;i<K;i++){
            backpack[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewels);
        Arrays.sort(backpack);

        solve();

        System.out.println(ans);


    }

}
