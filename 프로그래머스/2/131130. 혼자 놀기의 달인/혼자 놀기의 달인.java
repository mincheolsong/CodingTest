import java.util.*;

class Solution {
    
    int answer;
    boolean[] selected;
    int cnt;
    List<Integer> groupSize;
    int[] group;
    int groupNum;
    
    void makeGroup(int[] cards,int idx,int groupNum,int cnt){
        if(group[idx] == groupNum){
            groupSize.add(cnt-1);
            return;
        }
        selected[idx] = true;
        group[idx] = groupNum;
        makeGroup(cards,cards[idx]-1,groupNum,cnt+1);
    }
    
    public int solution(int[] cards) {
        answer = 0;
        selected = new boolean[cards.length];
        cnt = 0;
        groupSize = new ArrayList<>();
        groupNum = 1;
        group = new int[cards.length];
        
        for(int i=0;i<cards.length;i++){
            if(selected[i]) continue;
            makeGroup(cards,i,groupNum,1);
            groupNum += 1;
        }
        // System.out.println(Arrays.toString(group));
        // for(int i=0;i<groupSize.size();i++){
        //     System.out.print(groupSize.get(i)+ " ");
        // }
        if(groupSize.size()==1) return answer = 0;
        
        Collections.sort(groupSize,Collections.reverseOrder());
        answer = groupSize.get(0) * groupSize.get(1);
        
        return answer;
    }
}