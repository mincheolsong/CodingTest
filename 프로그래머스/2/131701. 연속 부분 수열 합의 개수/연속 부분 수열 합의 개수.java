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
        int[] copy = new int[elements.length*2-1];
        Set<Integer> set = new HashSet<>();
        
        for(int i=0;i<copy.length;i++){
            copy[i] = elements[i%elements.length];            
        }
        
        for(int size=1;size<elements.length;size++){
            for(int i=0;i<elements.length;i++){
                int sum = 0;
                for(int j=i;j<i+size;j++){
                    sum += copy[j];
                }
                set.add(sum);
            }
        }
        set.add(Arrays.stream(elements).sum());
        
        answer = set.size();
        
        return answer;
    }
}