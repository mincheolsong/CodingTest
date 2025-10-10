import java.util.*;

// head, number, tail 로 나눠서 저장하기
// head -> 소문자로 통일해서 비교하기
// number -> 앞에 0이 있는 경우 제거하고 int로 바꾸기
// tail -> 입력으로 들어온 순서 저장하기

class Solution {
    
    // File[] fileArray;
    PriorityQueue<File> pq;
    
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        // fileArray = new File[files.length];
        pq = new PriorityQueue<>();
        
        for(int i=0;i<files.length;i++){
            pq.offer(new File(files[i],i));
        }
        
        int idx = 0;
        while(!pq.isEmpty()){
            answer[idx++] = pq.poll().origin;
        }
        
        
        return answer;
    }
}
class File implements Comparable<File>{
    String origin;
    
    String head;
    int number;
    int tail;
    
    public File(String fileName,int order){
        this.origin = fileName;
        // head, number, tail 자르기
        char[] arr = fileName.toCharArray();
        
        StringBuilder sb = new StringBuilder();
        
        int idx = 0;
        // head 탐색
        while(!(arr[idx]>='0' && arr[idx]<='9')){
            sb.append(String.valueOf(arr[idx]));
            idx += 1;
        }
        this.head = sb.toString();
        sb.setLength(0);
        
        // number 탐색
        while(idx < arr.length && (arr[idx]>='0' && arr[idx]<='9')){
            sb.append(String.valueOf(arr[idx]));
            idx += 1;
        }
        
        this.number = Integer.parseInt(sb.toString());
        this.tail = order;
    }
    
    @Override
    public int compareTo(File other){
        if(this.head.toLowerCase().equals(other.head.toLowerCase())){
            if(this.number==other.number){
                return (this.tail-other.tail);
            }
            return (this.number-other.number);
        }
        return (this.head.toLowerCase().compareTo(other.head.toLowerCase()));
        
    }
    
    @Override
    public String toString(){
        return "origin : " + origin + ", head : " + head + ", number : " + number + ", tail : " + tail;
    }
    
    
}