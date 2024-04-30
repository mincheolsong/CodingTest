import java.util.*;

class Solution {
    
    int[] dr = {-1,0,1,0};
    int[] dc = {0,1,0,-1};
    int W,H;
    Map<Integer,Integer> map;
    
    int bfs(int r,int c,int[][] land,int area_num){
        Deque<int[]> q = new ArrayDeque<>();
        // boolean[][] visited = new boolean[H][W];
        
        q.offer(new int[]{r,c});
        land[r][c] = area_num;
        // visited[r][c] = true;
        int result = 1;
        
        while(!q.isEmpty()){
            int[] cur = q.pollFirst();
            int cr = cur[0];
            int cc = cur[1];
            
            for(int d=0;d<4;d++){
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                
                if(nr<0 || nr>=H) continue;
                if(nc<0 || nc>=W) continue;
                // if(visited[nr][nc]) continue;
                if(land[nr][nc]==1){
                    result+=1;
                    land[nr][nc] = area_num;
                    // visited[nr][nc]=true;
                    q.offer(new int[]{nr,nc});
                }
            }
        }
        
        map.put(area_num,result);
        
        return result;
    }
   
    
    public int solution(int[][] land) {
        int answer = 0;
        W = land[0].length;
        H = land.length;
        map = new HashMap<>();
        Set<Integer> set;
        int area_num = -1;
        
        for(int c=0;c<W;c++){
            int cnt = 0;
            set = new HashSet<>();
            for(int r=0;r<H;r++){
                if(land[r][c]==1){
                    cnt += bfs(r,c,land,area_num);
                    set.add(land[r][c]);
                    area_num--;
                    // System.out.printf("(%d, %d)는 새로운 영역이고 값은 %d 이다\n",r,c,-land[r][c]);
                }else if(land[r][c]<0 && !set.contains(land[r][c])){ // 이미 확인한 영역이면 그 값 활용
                    // System.out.printf("(%d, %d)는 이미 확인한 영역이고 값은 %d 이다\n",r,c,-land[r][c]);
                    cnt += map.get(land[r][c]);
                    set.add(land[r][c]);
                }
            }
            
            // System.out.printf("최종 결과 cnt : %d\n",cnt);
            answer = Math.max(answer,cnt);
            
            // for(int i=0;i<H;i++){
            //     for(int j=0;j<W;j++){
            //         System.out.print(land[i][j] + "\t");
            //     }
            //     System.out.println();
            // }
            // System.out.println("======================");
        }
        
        
        return answer;
    }
}