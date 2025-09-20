import java.util.*;

// bfs
class Solution {
    
    boolean isPossible(String[] words,String target){
        for(int i=0;i<words.length;i++){
            if(words[i].equals(target)) return true;
        }
        return false;
    }
    
    boolean check(String st1, String st2){
        char[] ch1 = st1.toCharArray();
        char[] ch2 = st2.toCharArray();
        int diffCnt = 0;
        
        for(int i=0;i<ch1.length;i++){
            if(ch1[i]!=ch2[i]) diffCnt += 1;
            if(diffCnt > 1) return false;            
        }
        return true;
    }
    
    int solve(String begin, String target,String[] words){
        boolean[] visited = new boolean[words.length];
        Deque<String> q = new ArrayDeque<>();
        q.offer(begin);
        int cnt = 0;
        
        while(!q.isEmpty()){
            int size = q.size();
            
            for(int s=0;s<size;s++){
                String st = q.pollFirst();       
                if(st.equals(target)) return cnt;
                    
                for(int i=0;i<words.length;i++){
                    if(visited[i]) continue;
                
                    if(check(words[i],st)){ // 하나의 문자만 다른지 체크
                        visited[i] = true;
                        q.offer(words[i]);
                    }
                }
            }
            cnt += 1;
        }
        
        return 0;
    }
    
    public int solution(String begin, String target, String[] words) {
        if(!isPossible(words,target)) return 0;
        
        return solve(begin,target,words);
    }
   
}