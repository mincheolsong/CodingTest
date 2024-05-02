import java.util.*;
// 장르 별 재생 횟수의 총 합 (내림 차순)
// HashMap<String,Integer> genre_cnt;
// Genre {String genre, int plays}
// 각 장르에 속하는 노래의 고유번호와 재생횟수
// HashMap<String,PriorityQueue<Music>> genre_music; (String : 각 장르, PQ<Music> : 해당하는 노래)
// Music {int id, int plays}

class Genre implements Comparable<Genre>{

    String genre;
    int plays;
    
    public Genre(String genre,int plays){
        this.genre = genre;
        this.plays = plays;
    }
    
    @Override
    public int compareTo(Genre other){
        return -(this.plays-other.plays);
    }
}


class Music implements Comparable<Music>{
    int id, plays;
    
    public Music(int id,int plays){
        this.id = id;
        this.plays = plays;
    }
    
    @Override
    public int compareTo(Music other){
        if(this.plays==other.plays){
            return (this.id-other.id);
        }
        return -(this.plays-other.plays);
    }
}

class Solution {
    
    Map<String,PriorityQueue<Music>> genre_music;
    Map<String,Integer> genre_cnt;
    List<Genre> genre_list;
    int[] answer;
    
    void init(String[] genres, int[] plays){
        
        
        genre_cnt = new HashMap<>(); // 장르 별 플레이 횟수 누적하는 map
        genre_list = new ArrayList<>(); // 장르 별 플레이 횟수를 정렬하기 위한 list
        genre_music = new HashMap<>(); // 장르에 해당하는 노래들을 pq로 가지고 있는 map
        List<Integer> answer_list = new ArrayList<>(); // 정답을 저장하는 list
        
        // genre_music 초기화
        for(int i=0;i<plays.length;i++){
            if(!genre_music.containsKey(genres[i])){
                genre_music.put(genres[i],new PriorityQueue<>());
            }
            genre_music.get(genres[i]).offer(new Music(i,plays[i]));
        }
        
        
        // genre_cnt 입력
        for(int i=0;i<genres.length;i++){
            genre_cnt.put(genres[i],genre_cnt.getOrDefault(genres[i],0)+plays[i]);
        }
        // genre_cnt를 genre_list에 입력 (정렬하기 위해서)
        for(String key : genre_cnt.keySet()){
            genre_list.add(new Genre(key,genre_cnt.get(key)));
        }
        // 정렬 수행
        Collections.sort(genre_list);
        
        for(int i=0;i<genre_list.size();i++){ // 정렬된 장르별 플레이 횟수에서 하나씩 꺼내 answer_list에 넣기
            Genre g = genre_list.get(i);
            PriorityQueue<Music> pq = genre_music.get(g.genre);
            for(int j=0;j<2;j++){
                if(pq.isEmpty()) break;
                Music m = pq.poll();
                answer_list.add(m.id);
            }
        }
        
        answer = new int[answer_list.size()];
        for(int i=0;i<answer_list.size();i++){
            answer[i] = answer_list.get(i);
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        
        init(genres,plays);
        
        
        return answer;
    }
}