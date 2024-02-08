import java.io.*;
import java.util.*;


public class Main {

    static int N,L,R;
    static int[][] arr;
    static boolean[][] collapse;
    static boolean[][] visited; // bfs 수행 시 사용할 visited 배열
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static int ans;
    static Deque<int[]> history;

    static void print_arr(int[][] a){
        for(int i=0;i<a.length;i++){
            System.out.println(Arrays.toString(a[i]));
        }
    }
    static void print_boolean(boolean[][] b){
        for(int i=0;i<b.length;i++){
            System.out.println(Arrays.toString(b[i]));
        }
    }
    static int[] bfs(int r,int c){
        Deque<int[]> q = new ArrayDeque<>();
        history = new ArrayDeque<>();

        q.offer(new int[]{r,c});
        history.offer(new int[]{r,c});

        int cnt = 0;
        int sum = 0;

        while(!q.isEmpty()){
            int[] cur = q.pollFirst();
            int cr = cur[0];
            int cc = cur[1];
            cnt+=1;
            sum += arr[cr][cc];

            for(int d=0;d<4;d++){
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                if(nr<0 || nr>=N) continue;
                if(nc<0 || nc>=N) continue;
                if(visited[nr][nc]) continue;

                int gap = Math.abs(arr[cr][cc] - arr[nr][nc]);
                if(gap<L || gap>R) continue;

                visited[nr][nc]=true;
//                collapse[nr][nc]=true;
                q.offer(new int[]{nr,nc});
                history.offer(new int[]{nr,nc});

            }
        }

        return new int[]{cnt,sum};

    }

    static void spread(int val){

        while(!history.isEmpty()){
            int[] a = history.pollFirst();
            arr[a[0]][a[1]] = val;
        }
        /*boolean[][] chk = new boolean[N][N];

        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r,c});

        chk[r][c]=true;
        arr[r][c] = val; // 시작위치 새로운 값으로 갱신(인구이동)

        while(!q.isEmpty()){
            int[] cur = q.pollFirst();
            int cr = cur[0];
            int cc = cur[1];

            for(int d=0;d<4;d++){
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if(nr<0 || nr>=N) continue;
                if(nc<0 || nc>=N) continue;
                if(chk[nr][nc]) continue;

                if(collapse[nr][nc]){ // collapse값이 true인 부분 값 갱신시키기(인구이동)
                    chk[nr][nc]=true;
                    arr[nr][nc]=val;
                    q.offer(new int[]{nr,nc});
                }
            }
        }*/

    }
    static boolean check(){
        visited = new boolean[N][N];
        int flag = 0;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!visited[i][j]){
                    visited[i][j] = true;
//                    collapse = new boolean[N][N];
//                    collapse[i][j] = true;
                    int[] result = bfs(i,j); // collapse 배열 갱신
//                    System.out.println("visited배열");
//                    print_boolean(visited);
//                    System.out.println("collapse배열");
//                    print_boolean(collapse);
                    if(result[0] > 1){ // 벽을 허문 적이 있으면
//                        System.out.println("벽 허물기!, 값 : " + result[1]/result[0]);
                        spread(result[1]/result[0]); // 인구이동 시키기
                        flag = 1;
                    }
//                    System.out.println("arr배열");
//                    print_arr(arr);
//                    System.out.println("----------------");
                }
            }
        }

        if(flag==1) return true;

        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        ans = 0;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        while(true){

            if(!check()){ // 국경 허물 수 있는지 확인하고 국경 허물기
                break;
            }

            ans+=1;
        }

        System.out.println(ans);

    }

}
