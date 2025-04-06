import java.util.*;

class Solution {
    
    String[][] park;
    boolean[][] visited;
    
    boolean available(int r,int c,int l){
        
        for(int i=r;i<r+l;i++){
            for(int j=c;j<c+l;j++){
                if(!park[i][j].equals("-1")) return false;
            }
        }
        
        for(int i=r;i<r+l;i++){
            for(int j=c;j<c+l;j++){
                visited[i][j] = true;
            }
        }
        
        return true;
    }
    
    public int solution(int[] mats, String[][] park) {
        int answer = 0;
        int max = -1;
        this.park = park;
        visited = new boolean[park.length][park[0].length];
        
        Arrays.sort(mats);
        int idx = 0;
        
        for(int i=0;i<park.length;i++){
            for(int j=0;j<park[i].length;j++){
                
                if(!park[i][j].equals("-1")) continue;
                if(visited[i][j]) continue;
                
                while(idx < mats.length && i + mats[idx] - 1 < park.length && j + mats[idx] - 1 < park[0].length){
                    if(available(i,j,mats[idx])){
                        max = Math.max(max,mats[idx]);                        
                        idx += 1;
                    }else{
                        break;
                    }
                }
            }
        }
        
        answer = max;
        
        return answer;
    }
}