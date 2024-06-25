import java.util.*;

// 1. 1~n-1 의 숫자 중 k-1개의 조합을 뽑기
// 1-1. 뽑아진 조합 숫자들의 차이 값을 통해서 유형 별 멘토의 숫자를 구하기
// 2. 그 결과 값을 바탕으로 PQ의 초기 원소 갯수 설정
// 3. PQ를 활용해서 지연시간 누적하기
// 4. 최솟값 갱신하기
class Solution {
    
    static final int INF = (int)1e9;
    int[] comb;
    int answer;
    int k,n;
    int[][] reqs;
    PriorityQueue<Integer>[] pq;
    
    
    int calc(){
        int result = 0;
        
        for(int[] req : reqs){
            int a = req[0]; // a분에
            int b = req[1]; // b분 동안
            int c = req[2]-1; // c유형의 상담
            
            int d = pq[c].poll(); // 현재 pq에 가장 빠르게 끝나는 상담 시간
            
            if(d - a > 0){ // 대기해야 하면
                result += (d - a);
                pq[c].offer(d + b);
            }else{ // 대기 하지 않아도 되면
                pq[c].offer(a + b);
            }
        }
        
        return result;
            
    }
    
    void solve(int cnt, int start){
        if(cnt==k-1){
            
            for(int i=0;i<k;i++){
                pq[i] = new PriorityQueue<>();
            }
            
            // comb[0], comb[1], ... , comb[k-2]
            for(int i=0;i<comb[0];i++){
                pq[0].offer(0);
            }
            
            for(int i=1;i<k-1;i++){
                for(int j=comb[i-1];j<comb[i];j++){
                    pq[i].offer(0);
                }
            }
            
            for(int i=comb[k-2];i<n;i++){
                pq[k-1].offer(0);
            }
            
            answer = Math.min(answer,calc()); // 최솟갑 갱신
            
            return;
        }
        
        for(int i=start;i<n;i++){
            comb[cnt] = i;
            solve(cnt+1,i+1);
        }
        
    }
    
    
    public int solution(int k, int n, int[][] reqs) {
        
        answer = INF;
        this.k = k;
        this.n = n;
        this.reqs = reqs;
        pq = new PriorityQueue[k];
        
        if(k==1){ // k==1 인 경우 예외처리
            pq[0] = new PriorityQueue<>();
            
            for(int i=0;i<n;i++){
                pq[0].offer(0);
            }
            answer = Math.min(answer,calc());
            return answer;
        }
        
        comb = new int[k-1];
        
        solve(0,1);
        
        return answer;
    }
}