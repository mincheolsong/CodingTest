import java.util.*;

// StringBuilder 사용
// charAt(), deleteCharAt()
// 1. toLowerCase()
// 2. if문
// 3. 투포인터
// 4. append

class Solution {
    
    StringBuilder sb;
    
    String solve(String new_id){
        // 1단계
        sb.append(new_id.toLowerCase());
        
        // System.out.printf("%d 단계 : %s\n",1,sb.toString());
        
        // 2단계
        for(int i=0;i<sb.length();i++){
            char ch = sb.charAt(i);
            
            if(!((ch>='a' && ch<='z') || (ch>='0' && ch<='9') || (ch=='-' || ch=='_' || ch=='.'))){
                sb.deleteCharAt(i);
                i-=1;
            }
        }
        
        // System.out.printf("%d 단계 : %s\n",2,sb.toString());
        
        // 3단계
        int end = 0;
        for(int start=0;start<sb.length()-1;start++){
           
            if(sb.charAt(start)!='.') continue;
            
            if(start+1<sb.length() && sb.charAt(start+1)=='.'){
                sb.deleteCharAt(start+1);
                start-=1;
            }
        
        }
        
        // System.out.printf("%d 단계 : %s\n",3,sb.toString());
        
        // 4단계
        if(sb.charAt(0)=='.'){
            sb.deleteCharAt(0);
        }
        if(sb.length()>0 && sb.charAt(sb.length()-1)=='.'){
            sb.deleteCharAt(sb.length()-1);
        }
        
        // System.out.printf("%d 단계 : %s\n",4,sb.toString());
        
        // 5단계
        if(sb.length()==0) sb.append('a');
        
        // System.out.printf("%d 단계 : %s\n",5,sb.toString());
        
        // 6단계
        if(sb.length()>=16) sb.delete(15,sb.length());
        if(sb.length()>0 && sb.charAt(sb.length()-1)=='.') sb.deleteCharAt(sb.length()-1);
        
        // System.out.printf("%d 단계 : %s\n",6,sb.toString());
        
        // 7단계
        while(sb.length()<=2){
            sb.append(sb.charAt(sb.length()-1));
        }
        // System.out.printf("%d 단계 : %s\n",7,sb.toString());
        
        
        return sb.toString();
        
    }
    
    public String solution(String new_id) {
        sb = new StringBuilder();
        
        
        String ans = solve(new_id);
        
        return ans;
    }
}