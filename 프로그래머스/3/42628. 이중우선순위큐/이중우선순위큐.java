import java.util.*;

// 우선순위 큐를 두 개 둬야하나?
// 현재 큐에 들어있는 원소의 갯수를 파악하고 있어야 할거 같다.

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> max_pq = new PriorityQueue<>((o1,o2)->-(o1-o2));
        PriorityQueue<Integer> min_pq = new PriorityQueue<>((o1,o2)->(o1-o2));
        
        for(String st : operations){
            String[] cmd = st.split(" ");
            if(cmd[0].equals("I")){
                max_pq.offer(Integer.parseInt(cmd[1]));
                min_pq.offer(Integer.parseInt(cmd[1]));
            }else if(cmd[1].equals("-1")){ // 최솟값 삭제
                if(min_pq.size()>0){
                    max_pq.remove(min_pq.poll());
                }
            }else if(cmd[1].equals("1")){ // 최댓값 삭제
                if(max_pq.size()>0){
                    min_pq.remove(max_pq.poll());
                }   
            }
        }
        
        if(max_pq.size()>0){
            answer[0] = max_pq.poll();
            answer[1] = min_pq.poll();
        }
        
        
        return answer;
    }
}