import java.util.*;


class Solution {
    
    int R,C;
	int comb;
    String[][] relation;
    Set<Integer> keySet;
    int answer;
    
	boolean isMinimal(){
		for(Integer key : keySet){
			if((key & comb ) == key) return false;
        }
		return true;
	}
	
	boolean isUnique(){
		Set<String> check = new HashSet<>();
        
		StringBuilder sb;
		
        for(int i=0;i<R;i++){
            sb = new StringBuilder();
            for(int j=0;j<C;j++){
				if((comb & (1 << j)) != 0){
					sb.append(relation[i][j]).append("#^,");
				}
			}
            if(check.contains(sb.toString())) {
                return false;
            }
            check.add(sb.toString());
        }
		
		return true;
	}
	
    boolean check(){
		
		if(isUnique() && isMinimal()){			
			keySet.add(comb);
			return true;	
		}
        
		return false;
    }
    
    void solve(int start, int n, int goal){
        if(n == goal){
            System.out.println(Integer.toBinaryString(comb));
            if(check()){
                answer+= 1;  
            }
            return;
        }
        
        for(int i=start;i<C;i++){
            comb |= 1 << i;
            solve(i+1, n+1, goal);
			comb &= ~(1 << i);
        }
    }
    
    public int solution(String[][] relation) {
        answer = 0;
        R = relation.length;
        C = relation[0].length;
        this.relation = relation;
        keySet = new HashSet<>();
        
        for(int i=1;i<=C;i++){
            comb = 0;
            solve(0,0,i);
        }
        
        return answer;
    }
}