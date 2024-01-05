import java.io.*;
import java.util.*;

public class Main {

    // 백준 11779
    // 플로이드 워셜 불 N이 최대 500 까지만 플로이드 워셜 가능

    static final int INF = (int)1e9;
    static int N, M;
    static List<Station>[] bus;
    static int[] dist;
    static int from,to;
    static int[] prev;
    static List<Integer> path;

    static void solve(){
        PriorityQueue<Station> pq = new PriorityQueue<>();
        pq.offer(new Station(from,0));
        dist[from] = 0;

        while(!pq.isEmpty()){
            Station c = pq.poll();

            if(c.num==to){ // 목적지 까지 도착한 경우
                System.out.println(c.w);
                return;
            }

            if(c.w > dist[c.num]) continue; // 이미 방문한 위치는 다시 방문하지 않음

            for(Station r : bus[c.num]){
                if(c.w + r.w < dist[r.num]){
                    dist[r.num] = c.w + r.w;
                    pq.offer(new Station(r.num,c.w + r.w));
                    prev[r.num] = c.num; // 최단경로의 직전 정류장 위치 갱신
                }
            }

        }

    }

    static void findPath(int to, int from){
        int p = prev[to]; // 이전노드
        if(p==from){
            path.add(from);
            return;
        }
        path.add(p);
        findPath(p,from);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dist = new int[N+1];
        prev = new int[N+1];
        Arrays.fill(dist,INF);

        bus = new ArrayList[N+1];
        for(int i=1;i<N+1;i++){
            bus[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            bus[from].add(new Station(to,w));
        }
        st = new StringTokenizer(br.readLine());
        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());

        solve();
        path = new ArrayList<>();
        path.add(to);
        findPath(to, from); // to부터 from 까지의 경로 찾기
        System.out.println(path.size());
        StringBuilder sb = new StringBuilder();
        for(int i=path.size()-1;i>=0;i--){
            sb.append(path.get(i) + " ");
        }
        System.out.println(sb.toString());

    }

    static class Station implements Comparable<Station>{
        int num, w;

        public Station(int num, int w){
            this.num = num;
            this.w = w;
        }

        @Override
        public int compareTo(Station o){
            return (this.w - o.w);
        }

        @Override
        public String toString(){
            return "(" + num + ", " + w + ")";
        }
    }

}



