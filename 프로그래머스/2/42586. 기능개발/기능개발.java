// 7 3 9
// 5 10 1 1 20 1

import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer;
        Deque<Integer> q = new ArrayDeque<>();
        
        for(int i=0;i<progresses.length;i++){
            int p = 100 - progresses[i];
            int cnt = p / speeds[i];
            if(p % speeds[i]!=0) cnt+=1;
            q.offer(cnt);
        }
        List<Integer> answer_list = new ArrayList();
        int prev = q.pollFirst();
        int ans = 1;
        while(!q.isEmpty()){
            int cur = q.pollFirst();
            if(cur > prev){
                answer_list.add(ans);
                prev = cur;
                ans = 1;
                continue;
            }
            ans+=1;
        }
        answer_list.add(ans);
        
        answer = new int[answer_list.size()];
        for(int i=0;i<answer.length;i++){
            answer[i] = answer_list.get(i);
        }
        
        
        return answer;
    }
}