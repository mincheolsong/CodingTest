import java.util.*;
import java.io.*;

public class Robot{
    int r,c;
    int[] goal; // 목표
    int goal_idx; // 목표(goal[]) 인덱스
    int dir; // 이동해야 하는 방향
  
    public Robot(int r,int c,int goal_idx, int[] goal){
        this.r = r;
        this.c = c;
        this.goal_idx = goal_idx;
        this.goal = goal;
    }
}
    
class Solution {
    
    // 최단경로는 6가지 유형으로 정해져 있음
    // 1. x개의 로봇들을 1초씩 최단경로로 이동(로봇의 좌표 갱신)
    // 2. map[][] 배열 갱신 (기존 경로 -1, 이동한 경로 +1)
    // 2-1. 이때 이동한 경로의 map[][] >= 2 라면 충돌이 발생한 것. 
    // 이때 충돌 여부 확인 배열 갱신, 충돌 갯수 누적
    
    Robot[] robots; // i번째 로봇의 (r,c) 좌표값
    int[][] map; // 로봇들의 존재여부를 저장하는 2차원 배열 map[r][c] 는 (r,c) 좌표에 존재하는 로봇의 갯수
    int[] dr = {1,-1,0,0};
    int[] dc = {0,0,1,-1};

    
    boolean find_direct(Robot robot,int[][] points){
        
        if(robot.r == points[robot.goal[robot.goal_idx]][0] && robot.c == points[robot.goal[robot.goal_idx]][1] ){ // 현재 goal에 도달한 경우
            robot.goal_idx += 1;
        }
        
        if(robot.goal_idx == robot.goal.length){ // 최종 목적지에 도달한 경우 (ArrayIndexOutofBound 에러를 해결한 코드)
            map[robot.r][robot.c]-=1; // map을 갱신하는 move가 실행되지 않기 때문에 여기서 갱신
            return false;
        }
        
        if(robot.r < points[robot.goal[robot.goal_idx]][0]){ // 아래방향
            robot.dir = 0;
        }else if(robot.r > points[robot.goal[robot.goal_idx]][0]){ // 위 방향
            robot.dir = 1;
        }else if(robot.c < points[robot.goal[robot.goal_idx]][1]){ // 오른쪽 방향
            robot.dir = 2;
        }else if(robot.c > points[robot.goal[robot.goal_idx]][1]){ // 왼쪽 방향
            robot.dir = 3;
        }
        
        return true;
        
    }
    
    void move(Robot robot){
        // robot의 방향으로 움직이기
        // map배열 갱신
        map[robot.r][robot.c] -= 1; // 이전위치 -1
        robot.r += dr[robot.dir];
        robot.c += dc[robot.dir];
        map[robot.r][robot.c] += 1;
    }
    
   
    
    int solve(int[][] points){
        
        int collapse_cnt = 0;
        Deque<Robot> q = new ArrayDeque<>();
        
        for(Robot robot : robots){
            q.offer(robot);
        }
        
        while(!q.isEmpty()){ // 충돌확인할 때 100 x 100 배열을 다 확인하는 것이 아닌, queue를 활용해서 robot만큼만 확인
            int q_size = q.size();
            
            
            // 1. 충돌 확인
            boolean[][] visited = new boolean[100][100]; // 충돌여부 체크 배열
            
            for(int i=0;i<q_size;i++){ 
                Robot robot = q.pollFirst();
                if(!visited[robot.r][robot.c] && map[robot.r][robot.c] > 1){
                    collapse_cnt += 1;
                    visited[robot.r][robot.c] = true;
                }
                q.offer(robot); // 원상복구
            }
            
            // 2. 로봇 이동
            for(int i=0;i<q_size;i++){ // 로봇 이동시키기
                Robot robot = q.pollFirst(); 
                
                if(find_direct(robot,points)){ // 방향 찾기 + 끝까지 도달한 로봇인지 판단 (ArrayIndexOutOfBound 에러를 위해서)
                    move(robot); // 이동시키기
                    q.offer(robot);
                }; 
        
            }
        }
        return collapse_cnt;
    }
    
  
    
    public int solution(int[][] points, int[][] routes){
        int answer = 0;
        robots = new Robot[routes.length];
        map = new int[100][100];
        
        for(int i=0;i<points.length;i++){
            for(int j=0;j<2;j++){
                points[i][j] -= 1;
            }
        }
        
        for(int i=0;i<routes.length;i++){
            for(int j=0;j<routes[i].length;j++){
                routes[i][j] -= 1;
            }
            int start_point = routes[i][0]; // i번째 로봇의 시작 point 번호
            int r = points[start_point][0];
            int c = points[start_point][1];
            robots[i] = new Robot(r,c,0,routes[i]);
            map[r][c] += 1;
        }
        
        answer = solve(points);

        return answer;
    }
}