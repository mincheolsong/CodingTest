import java.util.*;

class Solution {   
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int current_weight = 0;
        Deque<Integer> q = new ArrayDeque<>();
        int idx = 0;
        
        while(true){
            int w = truck_weights[idx];
            
            answer += 1;
            
            if(q.size()+1 <= bridge_length && current_weight + w <= weight){ //다리에 트럭을 올릴 수 있으면
                q.offer(w);
                current_weight += w;
                idx += 1;
                if(idx==truck_weights.length){
                    return answer += bridge_length;
                }
            }else{ // 트럭을 다리에 올릴 수 없으면
                q.offer(0); // 0 넣어주기
            }       
            
            if(q.size()==bridge_length){ // 큐가 가득 찬 경우 = 제일 앞에 있는 트럭이 건너가는 경우
                current_weight -= q.pollFirst();
            }
            
        }
            
    }
}