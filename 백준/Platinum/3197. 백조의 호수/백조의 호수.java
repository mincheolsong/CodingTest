import java.util.*;
import java.io.*;


public class Main {

    static int R,C;
    static int er,ec; // 백조 위치
    static boolean[][] chk; // 백조 bfs에 사용
    static char[][] map;
    static Queue<int[]> bq; // 백조 bfs에 사용하는 큐
    static Queue<int[]> wq; // 물의 위치를 저장하는 큐
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};


    static boolean move(){
        Queue<int[]> tmp = new LinkedList<>();

        while(!bq.isEmpty()){

            int[] c = bq.poll();
            int cr = c[0], cc=c[1];

            if(cr==er && cc == ec){ // 상대 백조의 위치에 닿으면 return true
                return true;
            }

            for(int d=0;d<4;d++){
                int nr = c[0] + dr[d];
                int nc = c[1] + dc[d];

                if(nr<0 || nr>=R) continue;
                if(nc<0 || nc>=C) continue;
                if(chk[nr][nc]) continue;

                chk[nr][nc]=true;
                if(map[nr][nc]=='.'){
                    bq.add(new int[]{nr,nc});
                }else if (map[nr][nc]=='X'){ // 막혔으면 다음이동 시작위치 큐(tmp)에 저장
                    tmp.add(new int[]{nr,nc});
                }
            }
        }

        bq = tmp; // 큐를 다음위치 큐로 갱신
        return false;
    }

    static void melting(){
        int size = wq.size();

        for(int i=0;i<size;i++){ // 한 턴씩 bfs를 수행하기 위함 (물은 time 당 한번씩 번져야 하니까)
            int[] c = wq.poll();
            for(int d=0;d<4;d++){
                int nr = c[0] + dr[d];
                int nc = c[1] + dc[d];
                if(nr<0 || nr>=R) continue;
                if(nc<0 || nc>=C) continue;
                if(map[nr][nc]=='X'){ // 얼음과 닿아있는 물의 좌표를 큐에 저장
                    map[nr][nc]='.';
                    wq.add(new int[]{nr,nc});
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        bq = new LinkedList<>();
        wq = new LinkedList<>();
        int sr=-1, sc = -1;


        for(int i=0;i<R;i++){
            String s = br.readLine();
            for(int j=0;j<C;j++){
                map[i][j] = s.charAt(j);
                if(map[i][j]=='L'){
                    if(sr==-1 && sc==-1){
                        sr = i;
                        sc = j;
                    }else{
                        er = i;
                        ec = j;
                    }
                    map[i][j]='.'; // 백조의 좌표도 물 (백조도 물 위에 있다)
                }
                if(map[i][j]=='.'){ // 물의 위치 큐 초기화 (처음에는 모든 물의 좌표를 넣음, 나중엔 얼음과 닿아있는 좌표만 저장될 예정)
                    wq.add(new int[]{i,j});
                }
            }
        }
        chk = new boolean[R][C];
        bq.add(new int[]{sr,sc});
        chk[sr][sc]=true;

        int time=0;

        while(true){
            melting();
            if(move()) break;
            time++;
        }

        System.out.println(time+1); // time을 +1 해줘야 함

    }

}