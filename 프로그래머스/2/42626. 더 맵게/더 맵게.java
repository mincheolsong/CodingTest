import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1,Integer o2){
                return (o1-o2);
            }
        });
        int flag = 0;

        for(int i : scoville){
            pq.offer(i);
        }
        
        while(!pq.isEmpty()){
            if(pq.peek()>=K){
                flag = 1;
                break;
            }            
            // 두 개 꺼내기
            if(pq.size()<2) break;
            int a = pq.poll();
            int b = pq.poll();
            pq.offer(a+b*2);
            answer++;
        }
        
        if(flag==0) answer = -1;
        
        return answer;
    }
}