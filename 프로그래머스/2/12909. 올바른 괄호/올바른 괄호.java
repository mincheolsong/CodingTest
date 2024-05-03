import java.util.*;

class Solution {
    boolean solution(String s) {
        Deque<String> stack = new ArrayDeque<>();
        int cnt = 0;
        
        char[] ch = s.toCharArray();
        for(int i=0;i<ch.length;i++){
            stack.offer(ch[i]+"");
        }
        
        while(!stack.isEmpty()){
            String st = stack.pollLast();
            if(st.equals(")")){
                cnt+=1;
            }else if(st.equals("(")){
                cnt-=1;
            }
            if(cnt<0) return false;
        }
        
        if(cnt==0) return true;
        return false;
        
    }
}