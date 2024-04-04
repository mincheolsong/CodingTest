import java.util.*;

// N을 1개 ~ 8개 사용했을 때의 경우 구하기 (DP)

class Solution {
    
    List<Set<Integer>> l;
    
    void dp(){
        
        for(int i=2;i<=8;i++){ // i개 사용했을 때 나올 수 있는 경우
            for(int j=1;j<i;j++){ // j와 k의 원소들을 조합
                int k = i-j;
                for(Integer a : l.get(j)){
                    for(Integer b : l.get(k)){
                        if(a+b<=32000) l.get(i).add(a+b);
                        if(a-b>0) l.get(i).add(a-b);
                        if(b-a>0) l.get(i).add(b-a);
                        if(a*b<=32000) l.get(i).add(a*b);
                        if(a/b>0) l.get(i).add(a/b);
                        if(b/a>0) l.get(i).add(b/a);
                    }
                }
            }
        }
    }
    
    public int solution(int N, int number) {
        int answer = -1;
    
        l = new ArrayList<>();
        
        for(int i=0;i<9;i++){
            l.add(new HashSet<>());
        }
        
        int n = N;
        for(int i=1;i<9;i++){
            l.get(i).add(n);
            n = n*10 + N;
        }
        
        dp();
        
        for(int i=1;i<9;i++){
            if(l.get(i).contains(number)){
                answer = i;
                break;
            }
        }
        
        return answer;
    }
}