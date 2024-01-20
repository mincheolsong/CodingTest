import java.io.*;
import java.util.*;


public class Main {

    static int N,M;
    static int[][] map;
    static boolean[] visited;
    static int[] ladder;
    static int[] snake;

    static void solve(){

        Deque<Integer> q = new ArrayDeque<>();
        q.offer(1);
        visited[1]=true;
        int cnt = 0;

        while(!q.isEmpty()){
            int size = q.size();

            for(int i=0;i<size;i++){
                int cur = q.pollFirst();

                if(cur==100){
                    System.out.println(cnt);
                    return;
                }

                for(int j=1;j<=6;j++){
                    int next = cur + j;
                    if(next>100) break;
                    if(visited[next]) continue;
                    if(ladder[next]!=0){
                        q.offer(ladder[next]);
                        visited[next]=true;
                        visited[ladder[next]]=true;
                    }
                    else if(snake[next]!=0){
                        q.offer(snake[next]);
                        visited[next]=true;
                        visited[snake[next]]=true;
                    }else{
                        q.offer(next);
                        visited[next]=true;
                    }
                }

            }
            cnt+=1;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        map = new int[101][101];
        visited = new boolean[101];
        ladder = new int[101];
        snake = new int[101];

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            ladder[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            snake[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }


        solve();



    }

}

