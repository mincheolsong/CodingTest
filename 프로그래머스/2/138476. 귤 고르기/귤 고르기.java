import java.util.*;
import java.io.*;

// 1 : 1개, 2 : 2개, 3 : 2개, 4 : 1개, 5 : 2개
// 갯수로 내림차순 정렬

class Solution {
    
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        int[] arr = new int[10_000_001];
        
        for(int i : tangerine){
            arr[i]+=1;
        }
        
        Integer[] arr2 = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        
        Arrays.sort(arr2,Collections.reverseOrder());
        
        
        int cnt = 0;
        for(int i=0;i<10_000_001;i++){
            cnt += arr2[i];
            answer+=1;
            if(cnt>=k) break;
            
        }
        
        return answer;
    }
}
