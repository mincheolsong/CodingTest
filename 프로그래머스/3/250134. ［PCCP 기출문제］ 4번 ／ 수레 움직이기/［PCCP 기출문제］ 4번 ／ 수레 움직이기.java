import java.util.*;

// maze의 크기가 작다 -> 백 트래킹? 완전탐색?

class Point{
    int r,c;
    
    
    public Point(int r,int c){
        this.r = r;
        this.c = c;
    }
}
class Solution {
    final int INF = (int)1e9;
    
    int answer;
    int N,M;
    int[] dr = {-1,0,1,0}; // 0 : 상 1 : 우 2 : 하 3 : 좌
    int[] dc = {0,1,0,-1};
    Point red, blue;
    Point red_goal, blue_goal;
    
    boolean[][] r_chk,b_chk;
    
    
    void solve(int[][] maze, int red_r, int red_c, int blue_r, int blue_c,int red_arr, int blue_arr,int cnt){
        
        if(red_r == blue_r && red_c == blue_c){
            return;
        }
        
        if(red_arr == 1 && blue_arr == 1){
            answer = Math.min(answer,cnt);
            return;
        }
        
        if(red_arr == 1){ // blue만 움직여야 함
            for(int bd=0;bd<4;bd++){
                    int b_flag = 0;
                    int blue_nr = blue_r + dr[bd];
                    int blue_nc = blue_c + dc[bd];
                    if(blue_nr<0 || blue_nr>=N) continue;
                    if(blue_nc<0 || blue_nc>=M) continue;
                
                    if(b_chk[blue_nr][blue_nc]) continue; // 이미 방문한 위치
                    if(blue_nr == red.r && blue_nc == red.c) continue; // 빨간수레
                    if(maze[blue_nr][blue_nc]==5) continue; // 벽
                
                    b_chk[blue_nr][blue_nc] = true;
                    blue.r = blue_nr;
                    blue.c = blue_nc;
                
                    if(blue.r == blue_goal.r && blue.c == blue_goal.c){
                        b_flag = 1;
                    }
                
                    solve(maze,red_r,red_c,blue_nr,blue_nc,1,b_flag,cnt+1); // 재귀
                
                    b_chk[blue_nr][blue_nc] = false;
                    blue.r = blue_r;
                    blue.c = blue_c;

                }
        }else if(blue_arr == 1){ // red만 움직여야 함
            for(int rd=0;rd<4;rd++){
                int r_flag = 0;
                int red_nr = red_r + dr[rd];
                int red_nc = red_c + dc[rd];
                if(red_nr<0 || red_nr>=N) continue;
                if(red_nc<0 || red_nc>=M) continue;
            
                if(r_chk[red_nr][red_nc]) continue; // 이미 방문한 위치
                if(red_nr==blue.r && red_nc == blue.c) continue; // 파란수레
                if(maze[red_nr][red_nc]==5) continue; // 벽
            
                r_chk[red_nr][red_nc] = true;
                red.r = red_nr;
                red.c = red_nc;
            
                if(red.r == red_goal.r && red.c == red_goal.c){
                    r_flag = 1;
                }
            
                solve(maze,red_nr,red_nc,blue_r,blue_c,r_flag,1,cnt+1); // 재귀
                
                r_chk[red_nr][red_nc] = false;
                red.r = red_r;
                red.c = red_c;
            } 
        }else{
            for(int rd=0;rd<4;rd++){
                int r_flag = 0;
                int red_nr = red_r + dr[rd];
                int red_nc = red_c + dc[rd];
                if(red_nr<0 || red_nr>=N) continue;
                if(red_nc<0 || red_nc>=M) continue;
            
                if(r_chk[red_nr][red_nc]) continue; // 이미 방문한 위치
                // if(red_nr==blue.r && red_nc == blue.c) continue; // 파란수레
                if(maze[red_nr][red_nc]==5) continue; // 벽
            
                r_chk[red_nr][red_nc] = true;
                red.r = red_nr;
                red.c = red_nc;
            
                if(red.r == red_goal.r && red.c == red_goal.c){
                    r_flag = 1;
                }
            
                for(int bd=0;bd<4;bd++){
                    
                    int b_flag = 0;
                    int blue_nr = blue_r + dr[bd];
                    int blue_nc = blue_c + dc[bd];
                    if(blue_nr<0 || blue_nr>=N) continue;
                    if(blue_nc<0 || blue_nc>=M) continue;
                    
                    if(b_chk[blue_nr][blue_nc]) continue; // 이미 방문한 위치
                    if(red_nr == blue_r && red_nc == blue_c && blue_nr == red_r && blue_nc == red_c) continue; // 스위칭 되는 경우
                        
                    // if(blue_nr == red.r && blue_nc == red.c) continue; // 빨간수레
                    if(maze[blue_nr][blue_nc]==5) continue; // 벽
                
                    b_chk[blue_nr][blue_nc] = true;
                    blue.r = blue_nr;
                    blue.c = blue_nc;
                
                    if(blue.r == blue_goal.r && blue.c == blue_goal.c){
                        b_flag = 1;
                    }
                
                    solve(maze,red_nr,red_nc,blue_nr,blue_nc,r_flag,b_flag,cnt+1); // 재귀
                
                    b_chk[blue_nr][blue_nc] = false;
                    blue.r = blue_r;
                    blue.c = blue_c;

                }
                r_chk[red_nr][red_nc] = false;
                red.r = red_r;
                red.c = red_c;
            } 
        }
        
        
        
    }
    public int solution(int[][] maze) {
        answer = INF;
        
        N = maze.length;
        M = maze[0].length;
        r_chk = new boolean[N][M];
        b_chk = new boolean[N][M];
        
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(maze[i][j]==1){
                    red = new Point(i,j);
                    r_chk[i][j] = true;
                }else if(maze[i][j]==2){
                    blue = new Point(i,j);
                    b_chk[i][j] = true;
                }else if(maze[i][j]==3){
                    red_goal = new Point(i,j);
                }else if(maze[i][j]==4){
                    blue_goal = new Point(i,j);
                }
            }
        }
        
        solve(maze, red.r, red.c, blue.r, blue.c,0, 0,0);
        
        
        return answer==INF ? 0 : answer;
    }
}