import java.util.*;


class Solution {
    
    int R,C;
    int[] comb;
    String[][] relation;
    Set<String> keySet;
    int answer;
    
    boolean check(){
        
        for(String key : keySet){
            int cnt = 0;
            for(int i=0;i<comb.length;i++){
                if(key.contains(String.valueOf(comb[i]))) cnt += 1;
            }   
            if(key.length() == cnt) return false;
        }
        
        Set<String> check = new HashSet<>();
        
		StringBuilder sb;
		
        for(int i=0;i<R;i++){
            sb = new StringBuilder();
            for(int j=0;j<comb.length;j++){
                sb.append(relation[i][comb[j]]).append(",");
            }
            if(check.contains(sb.toString())) {
                return false;
            }
            check.add(sb.toString());
        }
        
		// true인 경우
		sb = new StringBuilder();
        for(int i=0;i<comb.length;i++){
            sb.append(comb[i]);
        }
        keySet.add(sb.toString());
        
        return true;
    }
    
    void solve(int start, int n, int goal){
        if(n == goal){
            if(check()){
                answer+= 1;  
            }
            return;
        }
        
        for(int i=start;i<C;i++){
            comb[n] = i;
            solve(i+1, n+1, goal);
        }
    }
    
    public int solution(String[][] relation) {
        answer = 0;
        R = relation.length;
        C = relation[0].length;
        this.relation = relation;
        keySet = new HashSet<>();
        
        for(int i=1;i<=C;i++){
            comb = new int[i];
            solve(0,0,i);
        }
        
        return answer;
    }
}