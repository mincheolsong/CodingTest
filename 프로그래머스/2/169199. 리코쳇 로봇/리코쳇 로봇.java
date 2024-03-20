// 내가 멈춘 위치에 G가 있어야 함
// G의 상하좌우에 D가 존재하거나
// G의 행 인덱스 = 0 or 열 인덱스 = 0 or 행 인덱스 = R-1 or 열 인덱스 = C-1
// 이동 : 
import java.util.*;
import java.io.*;

class Solution {
    
    int[] dr = {-1,0,1,0};
    int[] dc = {0,1,0,-1};
    int[] robot, goal;
    int[][] map;
    boolean[][] visited;
    int R,C; // 행, 열
    final int INF = (int)1e9;
    
    void print(int n){
        System.out.println(n);
    }
    
    int solve(){
        // G가 도달할 수 있는 위치인지 확인
        int canReach = 0;
        
        if(goal[0]==0 || goal[1]==0 || goal[0]==R-1 || goal[1]==C-1){
            canReach = 1;
        }
        
        if(canReach==0){
            for(int d=0;d<4;d++){
                int nr = goal[0]+dr[d];
                int nc = goal[1]+dc[d];
                if(nr<0 || nr==R) continue;
                if(nc<0 || nc==C) continue;
                if(map[nr][nc]=='D'){
                    canReach = 1;
                    break;
                }
            }
        }
        
        if(canReach==0){ // 도달할 수 없음
            return -1;
        }
        
        
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{robot[0],robot[1],0});
        visited[robot[0]][robot[1]]=true;
        int min = INF;
        
        while(!q.isEmpty()){
            int[] cur = q.pollFirst();
            int cr = cur[0];
            int cc = cur[1];
            int cnt = cur[2];
            
            if(cr==goal[0] && cc==goal[1]){ // 목표위치에 도달한 경우
                return cnt;
            }
            
            for(int d=0;d<4;d++){
                int nr = cr;
                int nc = cc;
                
                while(nr>=0 && nr<R && nc>=0 && nc<C && map[nr][nc]=='.'){ // 끝까지 이동
                    nr += dr[d];
                    nc += dc[d];
                }
                
                if(nr<0 || nr==R || nc<0 || nc==C){ // 배열 끝에 도달해서 멈춘 경우 한 칸뒤로 이동
                    nr -= dr[d];
                    nc -= dc[d];
                }else if(map[nr][nc]=='D'){ // 장애물에 도달한 경우 한 칸 뒤로 이동
                    nr -= dr[d];
                    nc -= dc[d];
                }
                
                if(visited[nr][nc]) continue; // 방문한 곳은 다시 방문하지 않음
                
                visited[nr][nc]=true;
                q.offer(new int[]{nr,nc,cnt+1});
            }
            
        }
        return -1;
    }
        
    public int solution(String[] board) {
        int answer = 0;
        R = board.length;
        C = board[0].length();
        map = new int[R][C];
        robot = new int[2];
        goal = new int[2];
        visited = new boolean[R][C];
        
        for(int i=0;i<R;i++){
            char[] ch = board[i].toCharArray();
            for(int j=0;j<C;j++){
                map[i][j] = ch[j];
                if(ch[j]=='R'){
                    robot[0] = i;
                    robot[1] = j;
                    map[i][j] = '.'; // 초기 로봇위치 .으로 표시
                }else if(ch[j]=='G'){
                    goal[0] = i;
                    goal[1] = j;
                    map[i][j] = '.'; // 목표위치 .으로 표시
                }
            }
        }
        
        answer = solve();
        
        
        return answer;
    }
}