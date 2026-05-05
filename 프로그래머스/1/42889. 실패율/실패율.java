import java.util.*;

class Stage implements Comparable<Stage>{
    int n;
    double failure;
    
    public Stage(int n){
        this.n = n;
    }
    
    public Stage(int n,double failure){
        this.n = n;
        this.failure = failure;
    }
    
    @Override
    public int compareTo(Stage other){
        if(this.failure == other.failure) return (this.n - other.n);
        
        return -Double.compare(this.failure,other.failure);
    }
    
    @Override
    public String toString(){
        return "[ n : " + n + ", failure : " + failure + " ]";
    }
    
}
class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        
        Arrays.sort(stages);
        
        int startIdx = 0;
        PriorityQueue<Stage> pq = new PriorityQueue<>();
        
        for(int i = 1; i <= N; i++){
            Stage stg = new Stage(i);

            if(stages[startIdx] == i){
                int endIdx = startIdx;
                int cnt = 0;
                while(endIdx < stages.length && stages[endIdx] == i){
                    cnt += 1;
                    endIdx += 1;
                }
                stg.failure = (double)cnt / (double)(stages.length - startIdx);
                // System.out.printf("stage : %d, failure : %d / %d \n", i, cnt, stages.length - startIdx);
                
                pq.offer(stg);
                
                if(endIdx == stages.length){
                    for(int j=i+1;j<=N;j++){
                        pq.offer(new Stage(j,0.0));
                    }
                    break;
                }else{
                    startIdx = endIdx;
                }
                
            }else{
                stg.failure = 0.0;
                pq.offer(stg);
            }
        }
        
        
        int answerIdx = 0;
        while(!pq.isEmpty()){
            answer[answerIdx++] = pq.poll().n;
        }
        
        return answer;
    }
}