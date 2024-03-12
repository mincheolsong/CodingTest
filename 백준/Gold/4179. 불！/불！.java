import java.io.*;
import java.util.*;


public class Main {

    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static int R,C;
    static char[][] maze;
    static boolean[][] visited;
    static Deque<int[]> jq;
    static Deque<int[]> fq;
    static void solve(){

        int cnt=0;

        while(!jq.isEmpty()){

            int jq_size = jq.size();
            int fq_size = fq.size();
            cnt+=1;

            // 지훈이 이동시키기
            for(int s=0;s<jq_size;s++){
                int[] cur = jq.pollFirst();
                int cr = cur[0];
                int cc = cur[1];

                if(maze[cr][cc]=='F') continue; // 현재 지훈이 위치가 불로 체크 되면 다음 큐 탐색

                if(cr==R-1 || cc==C-1 || cr==0 || cc==0){
                    System.out.println(cnt);
                    return;
                }

                for(int d=0;d<4;d++){
                    int nr = cr + dr[d];
                    int nc = cc + dc[d];

                    if(nr<0 || nr>=R) continue;
                    if(nc<0 || nc>=C) continue;
                    if(visited[nr][nc]) continue;
                    if(maze[nr][nc]!='.') continue;

                    visited[nr][nc]=true;
                    jq.offer(new int[]{nr,nc});

                }
            }

            // 불 번지기
            for(int s=0;s<fq_size;s++){
                int[] cur = fq.pollFirst();
                int cr = cur[0];
                int cc = cur[1];
                for(int d=0;d<4;d++){
                    int nr = cr + dr[d];
                    int nc = cc + dc[d];

                    if(nr<0 || nr>=R) continue;
                    if(nc<0 || nc>=C) continue;
                    if(maze[nr][nc]=='#' || maze[nr][nc]=='F') continue;

                    maze[nr][nc]='F';
                    fq.offer(new int[]{nr,nc});
                }
            }

        }

        System.out.println("IMPOSSIBLE");

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        jq = new ArrayDeque<>();
        fq = new ArrayDeque<>();
        maze = new char[R][C];
        visited = new boolean[R][C];


        for(int i=0;i<R;i++){
            char[] ch = br.readLine().toCharArray();
            for(int j=0;j<C;j++){
                maze[i][j] = ch[j];
                if(maze[i][j]=='J'){
                    jq.offer(new int[]{i,j});
                    visited[i][j]=true;
                }else if(maze[i][j]=='F'){
                    fq.offer(new int[]{i,j});
                }
            }
        }

        solve();





    }

}





