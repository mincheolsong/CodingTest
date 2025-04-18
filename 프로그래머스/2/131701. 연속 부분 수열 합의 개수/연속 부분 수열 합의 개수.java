import java.util.*;

// 7 9 1 1 4, 7 9 1 1 
// 1000 * 1000 = 1000000
// 완전탐색
// 탐색범위  
// 1 => i : elements.length - 1 까지.
// 2 => i : elements.length 까지.
// 3 => i : elements.length + 1 까지.
class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        int e_length = elements.length;
        Set<Integer> set = new HashSet<>();
        
        for(int size=1;size<e_length;size++){
            for(int i=0;i<e_length;i++){
                int sum = 0;
                for(int j=i;j<i+size;j++){
                    sum += elements[j%e_length];
                }
                set.add(sum);
            }
        }
        set.add(Arrays.stream(elements).sum());
        
        answer = set.size();
        
        return answer;
    }
}