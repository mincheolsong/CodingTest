import java.util.*;

class Solution {
    
    boolean isNum(char ch){
        return ch >= '0' && ch<= '9';
    }
    
    public int solution(String dartResult) {
        int answer = 0;
        
        List<String> list = new ArrayList<>();
        Deque<Integer> q = new ArrayDeque<>();
        
        char[] arr = dartResult.toCharArray();
        
        for(int i=0;i<arr.length;i++){
            if(isNum(arr[i]) && isNum(arr[i+1])){
                list.add("10");
                i+=1;
            }else{
                list.add(String.valueOf(arr[i]));
            }
        }
        
        for(int i=0;i<list.size();i++){
            int num = Integer.parseInt(list.get(i));
            System.out.printf("num : %d \n",num);
            
            if(list.get(i+1).equals("D")) q.offer((int)Math.pow(num,2));
            else if(list.get(i+1).equals("T")) q.offer((int)Math.pow(num,3));
            else if(list.get(i+1).equals("S")) q.offer(num);
            
            if(i+2 < list.size() && list.get(i+2).equals("*")){
                int a = q.pollLast();
                int b = 0;
                
                if(q.size()>0){
                    b = q.pollLast();
                }
                if(b!=0)q.offer(b*2);
                q.offer(a*2);
                
                i += 2;
            }else if(i+2 < list.size() && list.get(i+2).equals("#")){
                q.offer(q.pollLast()*-1);
                i += 2;
            }else{
                i += 1;
            }
        }
        
        while(!q.isEmpty()){
            answer += q.poll();
        }
        
        
        
        return answer;
    }
}