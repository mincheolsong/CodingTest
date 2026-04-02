import java.util.*;

// 1개 ~ n개 까지 다 해보기?

class Solution{ 
    int answer;
    String s;
    
    String solve(int n){ // n 길이로 잘라서 압축
        StringBuilder sb = new StringBuilder();
        
        int start = 0;
        for(start = 0; start <= s.length() - n; start+=n){
            String prevSt = s.substring(start, start + n);    
            
            int nextIdx = start + n;
            int cnt = 1;
            
            while(nextIdx < s.length() && nextIdx + n <= s.length() && prevSt.equals(s.substring(nextIdx, nextIdx + n))){
                nextIdx += n;
                cnt += 1;
            }
            
        
            if(cnt > 1){ // 문자열이 일치
                sb.append(cnt).append(prevSt);
            }else{
                sb.append(prevSt);
            }
            
            if(nextIdx + n > s.length()){
                while(nextIdx < s.length()){
                    sb.append(s.charAt(nextIdx++));
                }
                
                break;
            } 
            start = nextIdx - n;
            
        }
        
        //if(start-n<s.length())sb.append(s.substring(start-n));
        
        return sb.toString();
        
    }

    public int solution(String s) {
        answer = (int)1e9;
        this.s = s;
        
        for(int i=1;i<=s.length();i++){
            // i 길이로 자르기
            answer = Math.min(answer, solve(i).length());
        }
        
        return answer;
    }
}