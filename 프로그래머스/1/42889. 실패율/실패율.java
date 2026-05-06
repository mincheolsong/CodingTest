import java.util.*;

class Stage implements Comparable<Stage>{
    int n;
    double failure;
    
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
        int[] countArr = new int[N+1];
        Stage[] stageArr = new Stage[N];
        
        for(int i=0;i<stages.length;i++){
            if(stages[i]<N+1) countArr[stages[i]] += 1;
        }
        
        int leftCnt = stages.length;
        for(int i=1;i<N+1;i++){
            double failure = 0.0;
            
            if(leftCnt > 0) failure = countArr[i] / (double)leftCnt;
            
            stageArr[i-1] = new Stage(i, failure);
            leftCnt -= countArr[i];
        }
        
        Arrays.sort(stageArr);
        
        for(int i=0;i<N;i++){
            answer[i] = stageArr[i].n;
        }
        
        return answer;
    }
}