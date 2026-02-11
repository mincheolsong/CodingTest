import java.util.*;

// 투포인터?

class Solution {
    
    Map<String, Integer> numMap;
    
    boolean isNum(char ch){
        if(ch>='0' && ch<='9') return true;
        return false;
    }
    
    int solve(String s){
        StringBuilder resultSb = new StringBuilder();
        char[] sArr = s.toCharArray();
        
        for(int start=0;start<sArr.length;start++){
            
            if(isNum(sArr[start])){
                resultSb.append(sArr[start]);
                continue;  
            } 
            
            StringBuilder numSb = new StringBuilder();
            
            int end = start;
            
            while(end<sArr.length && !isNum(sArr[end])){

                numSb.append(sArr[end++]);
                
                if(numMap.containsKey(numSb.toString())){
                    
                    resultSb.append(numMap.get(numSb.toString()));
                    numSb.setLength(0);
                }
            }
            
            
            if(numSb.length()>0) resultSb.append(numMap.get(numSb.toString()));
            
            
            start = end-1;
        }       
        
        
        
        return Integer.parseInt(resultSb.toString());
    }
    
    public int solution(String s) {
        int answer = 0;
        numMap = new HashMap(){{
            put("zero",0);
            put("one",1);
            put("two",2);
            put("three",3);
            put("four",4);
            put("five",5);
            put("six",6);
            put("seven",7);
            put("eight",8);
            put("nine",9);
        }};
        
        answer = solve(s);
        return answer;
    }
}