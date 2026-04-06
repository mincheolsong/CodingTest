import java.util.*;

// 방문한 경로를 기억해야 함. (출발좌표, 목적좌표) 근데 (목적좌표, 출발좌표)가 되어도 됨
// 위치를 기록할 때, 두 개다 기록하기
// HashSet 사용

class Solution {
    
    HashMap<String,int[]> map = new HashMap<>(){{
        put("L",new int[]{-1,0});
        put("R",new int[]{1,0});
        put("U",new int[]{0,1});
        put("D",new int[]{0,-1});
    }};
    
    public int solution(String dirs) {
        int answer = 0;
        int cr = 0, cc = 0;
        
        HashSet<String> visited = new HashSet<>();
        
        for(char dir : dirs.toCharArray()){
            int[] d = map.get(String.valueOf(dir));
            int nr = cr + d[0];
            int nc = cc + d[1];
            
            if(nr > 5 || nr < -5) continue;
            if(nc > 5 || nc < -5) continue;
        
            
            StringBuilder sb = new StringBuilder();
            String a = sb.append(cr).append(cc).append(nr).append(nc).toString();
            
            if(visited.contains(a)){
                cr = nr;
                cc = nc;
                continue;
            } 
            
            sb.setLength(0);
            String b = sb.append(nr).append(nc).append(cr).append(cc).toString();
            if(visited.contains(b)){
                cr = nr;
                cc = nc;
                continue;
            }
            
            // 새로운 경로라면
           
            cr = nr;
            cc = nc;
            answer += 1;
            visited.add(a);
            visited.add(b);
            
        }
        
        return answer;
    }
}
// class Point{
//     int r, c;
    
//     public Point(int r,int c){
//         this.r = r;
//         this.c = c;
//     } 
    
//     @Override
//     public boolean equals(Object o){
//         if(this == o) return true;
        
//         if(o == null || getClass() != o.getClass()) return false;
        
//         Point point = (Point)o;
//         return this.r == point.r && this.c == point.c;
//     }
    
//     @Override int hashCode(){
//         return Objects.hash(r,c);
//     }
// }