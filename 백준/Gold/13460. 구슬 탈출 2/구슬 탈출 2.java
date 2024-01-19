import java.io.*;
import java.util.*;


class Marble{
    int rr,rc,br,bc;

    public Marble(){
    }

    public Marble(int rr,int rc,int br,int bc){
        this.rr = rr;
        this.rc = rc;
        this.br = br;
        this.bc = bc;
    }
    public void setRR(int rr){
        this.rr = rr;
    }
    public void setRC(int rc){
        this.rc = rc;
    }
    public void setBR(int br){
        this.br = br;
    }
    public void setBC(int bc){
        this.bc = bc;
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(rr)+Objects.hashCode(rc)+Objects.hashCode(br)+Objects.hashCode(bc);
    }

    @Override
    public boolean equals(Object obj){
        if(obj==null){
            return false;
        }

        if(obj==this){
            return true;
        }

        if(!(obj instanceof Marble)){
            return false;
        }

        Marble other = (Marble)obj;

        if(this.rr==other.rr && this.rc==other.rc && this.br==other.br && this.bc==other.bc){
            return true;
        }
        return false;
    }
}
public class Main {

    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static int N,M;
    static String[][] arr;
    static Set<Marble> visited; // 방문좌표를 기록할 집합
    static Marble marble; // 처음 구슬의 위치를 저장하는 객체

    static void solve(){
        
        // 처음 구슬의 위치로 bfs 시작
        Deque<Marble> q = new ArrayDeque<>();
        q.offer(marble);
        visited.add(marble);
        
        
        int cnt = 0; // 움직인 횟수
        while(!q.isEmpty()){

            if(cnt == 11){ // bfs(처음 목표에 다다른 경우가 최단거리인 특성), cnt가 11이면 실패
                System.out.println(-1);
                return;
            }

            int size = q.size(); // 한 턴씩 bfs수행을 위해서 현재 큐 사이즈 저장

            for(int i=0;i<size;i++){ // 현재 큐 사이즈 만큼 bfs 수행
                Marble current = q.pollFirst();
                int cRedRow = current.rr;
                int cRedCol = current.rc;
                int cBlueRow = current.br;
                int cBlueCol = current.bc;

                if(arr[cRedRow][cRedCol].equals("O")){ // 처음 목표에 다다른 경우가 최단거리(bfs특성), 정답 출력
                    System.out.println(cnt);
                    return;
                }

                for(int d=0;d<4;d++){ // 상,하,좌,우 모든 방향에 대해 이동해보기
                    int nRedRow = cRedRow + dr[d];
                    int nRedCol = cRedCol + dc[d];
                    while(true){ // 끝까지 이동하기 위해서

                        if(arr[nRedRow][nRedCol].equals("#")){ // 벽에 막힌 경우, 직전 위치로 바꿔줘야함
                            nRedRow -= dr[d];
                            nRedCol -= dc[d];
                            break;
                        }

                        if(arr[nRedRow][nRedCol].equals("O")){ // 구멍에 공이 들어갔으면 더 이상 이동시킬 필요가 없음
                            break;
                        }

                        nRedRow += dr[d]; // 계속 이동시키기
                        nRedCol += dc[d];
                    }

                    int nBlueRow = cBlueRow + dr[d];
                    int nBlueCol = cBlueCol + dc[d];
                    while(true){ // 끝까지 이동하기 위해서

                        if(arr[nBlueRow][nBlueCol].equals("#")){ // 벽에 막힌 경우, 직전 위치로 바꿔줘야함
                            nBlueRow -= dr[d];
                            nBlueCol -= dc[d];
                            break;
                        }

                        if(arr[nBlueRow][nBlueCol].equals("O")){ // 구멍에 공이 들어갔으면 더 이상 이동시킬 필요가 없음
                            break;
                        }

                        nBlueRow += dr[d];
                        nBlueCol += dc[d];
                    }

                    if(arr[nBlueRow][nBlueCol].equals("O")){ // 파란 구슬이 구멍에 들어간 경우는, 정답이 될 수 없음
                        continue;
                    }

                    if(nRedRow==nBlueRow && nRedCol==nBlueCol){ // 빨간 구슬과 파란 구슬이 같은 좌표로 됐다면, 더 먼거리를 이동한 구슬이 이전에 출발 한 경우. 직전 위치로 바꾸기
                        int redMoved = Math.abs(cRedRow-nRedRow) + Math.abs(cRedCol-nRedCol);
                        int blueMoved = Math.abs(cBlueRow-nBlueRow) + Math.abs(cBlueCol-nBlueCol);
                        if(redMoved > blueMoved){
                            nRedRow -= dr[d];
                            nRedCol -= dc[d];
                        }else{
                            nBlueRow -= dr[d];
                            nBlueCol -= dc[d];
                        }
                    }

                    Marble nMarble = new Marble(nRedRow,nRedCol,nBlueRow,nBlueCol); 
                    if(!visited.contains(nMarble)){ // 방문체크
                        q.offer(nMarble);
                        visited.add(nMarble);
                    }
                }
            }
            cnt += 1; // 이동횟수 증가
        }

        System.out.println(-1); // 10번의 이동이 불가능한 경우 (벽으로 막혀서 더 이상 움직일 경로가 없음)
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new String[N][M];
        marble = new Marble();
        visited = new HashSet<>();

        for(int i=0;i<N;i++){
            char[] s = br.readLine().toCharArray();
            for(int j=0;j<M;j++){
                arr[i][j] = s[j] + "";
                if(arr[i][j].equals("R")){
                    marble.setRR(i);
                    marble.setRC(j);
                }else if(arr[i][j].equals("B")){
                    marble.setBR(i);
                    marble.setBC(j);
                }
            }
        }

        solve();


    }

}

