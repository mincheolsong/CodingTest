import java.util.*;

class Solution {
    int answer;
    int[][] jobs;
    PriorityQueue<int[]> pq;
    
    void solve(){
        int end = 0;
        int idx = 0;
        int cnt = 0;
        answer = 0;
        
        while(cnt < jobs.length){
            int flag = 0;
            
            while(idx < jobs.length && jobs[idx][0] <= end){ // 현재 작업 중 들어온 요청 (없을 수도 있음)
                pq.offer(jobs[idx]);
                idx+=1;
                flag = 1;
            }
            
            if(pq.isEmpty()){
                end = jobs[idx][0];
                continue;
            }
        
            int[] job = pq.poll();
            // System.out.println("end : " + end);
            // System.out.println(Arrays.toString(job));
            // System.out.println(end-job[0]+job[1]);
            answer += (end-job[0]+job[1]); // 지연시간
            end += job[1];
            cnt+=1;
        }
        
        answer = answer / jobs.length;
    }
    
    public int solution(int[][] jobs) {
        answer = 0;
        this.jobs = jobs;
        pq = new PriorityQueue<>((o1,o2)->(o1[1]-o2[1]));
        Arrays.sort(jobs,(o1,o2)->(o1[0]-o2[0]));
        
        solve();
        
        return answer;
    }
}