import java.util.*;
import java.io.*;

public class Main {

    static int N,M;
    static int[][] arr;
    static List<int[]> virus;
    static List<int[]> selected;
    static int goal;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static boolean[][] visited;
    static final int INF = (int)1e9;
    static int ans;


    static void spread(int r,int c,Deque<int[]> bfs_q,int sec){

        Deque<int[]> q = new ArrayDeque<>();
        bfs_q.offer(new int[]{r,c,sec});
        q.offer(new int[]{r,c});
        visited[r][c]=true;

        while(!q.isEmpty()){
            int[] cur = q.pollFirst();
            for(int d=0;d<4;d++){
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr<0 || nr>= N) continue;
                if(nc<0 || nc>= N) continue;
                if(visited[nr][nc]) continue;
                if(arr[nr][nc]!=-2) continue;

                visited[nr][nc]=true;
                bfs_q.offer(new int[]{nr,nc,sec});
                q.offer(new int[]{nr,nc});
            }
        }

    }

    static int[] bfs(List<int[]> list){
        Deque<int[]> q = new ArrayDeque<>();
        visited = new boolean[N][N];
        int cnt = 0;
        int last_sec=INF;

        for(int i=0;i<list.size();i++){
            int[] p = list.get(i);
            q.offer(new int[]{p[0],p[1],0});
            visited[p[0]][p[1]]=true;
        }


        while(!q.isEmpty()){
            int[] c = q.pollFirst();
            int cr = c[0];
            int cc = c[1];
            int sec = c[2];
            last_sec = sec;

            for(int d=0;d<4;d++){
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                if(nr<0 || nr>=N) continue;
                if(nc<0 || nc>=N) continue;
                if(arr[nr][nc]==-1) continue;

                if(visited[nr][nc]){
                    continue;
                }

//                if(arr[nr][nc]==-2 && ( (nr==0 && nc==N-1) || (nr==0 && nc==0) || (nr==N-1 && nc==0) || (nr==N-1 && nc==N-1))){
//                    visited[nr][nc]=true;
//                    continue;
//                }

                if(arr[nr][nc]==0){
                    cnt+=1;
                }

                visited[nr][nc]=true;
                q.offer(new int[]{nr,nc,sec+1});

                if(cnt==goal){
                    return new int[]{cnt,sec+1};
                }

            }
        }

       /*System.out.println("last_sec : " + last_sec);
        System.out.println("cnt : " + cnt);*/

        return new int[]{-1,-1};
    }



    // 바이러스는 지나갈 수 있지만, 채우지는 않아도 된다.
    static void solve(int n,int start){
        if(n==M){
            int[] result = bfs(selected);

            if(result[0]==goal){
                ans = Math.min(ans,result[1]);
            }

            /*for(int i=0;i<N;i++){
                System.out.println(Arrays.toString(copy_arr[i]));
            }
            System.out.println("================");*/
            return;
        }

        for(int i=start;i<virus.size();i++){
            selected.add(virus.get(i));
            solve(n+1,i+1);
            selected.remove(selected.size()-1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        virus = new ArrayList<>();
        selected = new ArrayList<>();
        goal = 0;
        arr = new int[N][N];
        ans = INF;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                int input = Integer.parseInt(st.nextToken());
                if(input==1){
                    arr[i][j]=-1; // 벽은 -1로 표시
                }else if(input==2){
                    arr[i][j]=-2; // 바이러스는 -2로 표시
                    virus.add(new int[]{i,j});
                }else{ // 0인 곳의 갯수
                    goal+=1;
                }
            }
        }
//        System.out.println("goal : " + goal);
        if(goal==0) ans = 0;
        else solve(0,0);

        System.out.println(ans==INF?-1:ans);
    }
}





