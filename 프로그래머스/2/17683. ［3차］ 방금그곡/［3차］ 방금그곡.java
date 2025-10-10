import java.util.*;

// 음악별로 재생시간, 실제 재생된 멜로디, 입력된 순서 저장

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        PriorityQueue<Music> pq = new PriorityQueue<>();
        char[] mArr = m.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<mArr.length;i++){
            if(i+1<mArr.length && mArr[i+1]=='#'){
                sb.append(String.valueOf(mArr[i]).toLowerCase());
                i+=1;
            }else{
                sb.append(String.valueOf(mArr[i]));
            }
        }
        m = sb.toString();
        
        for(int i=0;i<musicinfos.length;i++){
            String[] musicInfo = musicinfos[i].split(",");
            Music music = new Music(musicInfo[0],musicInfo[1],musicInfo[2],musicInfo[3],i);
            if(music.playedMelody.contains(m)) pq.offer(music);                        
        }
        
        if(pq.size()==0) return "(None)";
        
        Music result = pq.poll();
        answer = result.title;
        
        return answer;
    }
}
class Music implements Comparable<Music>{
    String start,end,title,melody;
    int idx;
    
    int runTime; // 재생시간(분)
    String playedMelody; // 재생된 멜로디
    
    public Music(String start,String end, String title, String melody, int idx){
        this.start = start;
        this.end = end;
        this.title = title;
        this.melody = melody;
        this.idx = idx;
        this.update();
    }
    
    private void update(){
        String[] startArr = start.split(":");
        String[] endArr = end.split(":");
        int sh = Integer.parseInt(startArr[0]);
        int sm = Integer.parseInt(startArr[1]);
        int eh = Integer.parseInt(endArr[0]);
        int em = Integer.parseInt(endArr[1]);
        this.runTime = (eh*60 + em) - (sh*60 + sm);
        
        char[] melodyArr = this.melody.toCharArray();
        List<String> melodyList = new ArrayList<>();
        
        for(int i=0;i<melodyArr.length;i++){
            String st = String.valueOf(melodyArr[i]);
            if(i+1 < melodyArr.length && melodyArr[i+1]=='#'){
                st = st.toLowerCase();
                i += 1;
            }
            melodyList.add(st);
        }
        
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<runTime;i++){
           sb.append(melodyList.get(i%melodyList.size()));
        }
        this.playedMelody = sb.toString();
    }
    
    @Override
    public int compareTo(Music other){
        if(this.runTime == other.runTime){
            return (this.idx - other.idx);
        }
        return -(this.runTime - other.runTime);
    }
    
    @Override
    public String toString(){
        return "[ title : " + title + ", melody : " + melody + ", runTime : " + runTime + ", playedMelody : " + playedMelody + " ]";
    }
}