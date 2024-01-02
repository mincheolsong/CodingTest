import java.io.*;
import java.util.*;

public class Main {

    // BOJ 12851
    // 다익스트라

    static final int INF = (int)1e9;
    static int N,K;
    static int[] dist;
    static PriorityQueue<Node> pq;
    static int count;

    static void solve(){

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(cur.idx==K && cur.w==dist[K]){ // 목표지점에 도달한 경우
                count+=1;
            }

            if(dist[cur.idx] < cur.w) continue; // 방문체크


            if(cur.idx-1 >= 0 && cur.w + 1 <= dist[cur.idx-1]){ // 기존 다익스트라는 < 이지만, 모든 경우를 보기 위해서 <=
                dist[cur.idx-1] = cur.w + 1;
                pq.offer(new Node(cur.idx-1,cur.w+1));
            }
            if(cur.idx+1 <= 100000 && cur.w + 1 <= dist[cur.idx+1]){ // 기존 다익스트라는 < 이지만, 모든 경우를 보기 위해서 <=
                dist[cur.idx+1] = cur.w + 1;
                pq.offer(new Node(cur.idx+1,cur.w+1));
            }
            if(cur.idx*2 <= 100000 && cur.w + 1 <= dist[cur.idx*2]){ // 기존 다익스트라는 < 이지만, 모든 경우를 보기 위해서 <=
                dist[cur.idx*2] = cur.w + 1;
                pq.offer(new Node(cur.idx*2,cur.w+1));
            }

        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        count = 0;
        if(N>=K){
            System.out.println(N-K);
            System.out.println(1);
            return;
        }

        dist = new int[100001];
        Arrays.fill(dist,INF);

        pq = new PriorityQueue<>();
        pq.offer(new Node(N,0));
        dist[N]=0;

        solve();

        System.out.println(dist[K]);
        System.out.println(count);

    }

    static class Node implements Comparable<Node>{
        int idx,w;

        public Node(int idx, int w){
            this.idx = idx;
            this.w = w;
        }

        @Override
        public int compareTo(Node o){
            return this.w - o .w;
        }

    }

}



