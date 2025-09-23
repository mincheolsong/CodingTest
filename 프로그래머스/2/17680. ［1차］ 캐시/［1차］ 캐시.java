import java.util.*;

class Solution {
    
    PriorityQueue<City> cache;
    HashMap<String, City> map;
    
    void updateCache(int cacheSize, City city){
        // if(city.name.equals("d")){
        //     System.out.println("pq : " + cache.toString());
        // }
        if(cache.size() >= cacheSize){
            
            City polledCity = cache.poll();
            
            // System.out.println(polledCity.name + "이 제거됨");
            polledCity.inCache = false;
            
        }
        cache.offer(city);
    }

    int solve(int cacheSize, String[] cities){
        int runTime = 0;
        int usedTime = 0;
        
        for(int i=0;i<cities.length;i++){
            usedTime += 1;
            // System.out.println("time : " + usedTime);
            // System.out.println("pq : " + cache.toString());
            if(!map.containsKey(cities[i])){ // 새로 들어온 단어
                runTime += 5;
                City city = new City();
                city.name = cities[i];
                city.inCache = true;
                city.lruTime = usedTime;
                map.put(cities[i],city);
                // cache에서 LRU 업데이트
                updateCache(cacheSize, city);
            }else{ // 기존 단어
                City city = map.get(cities[i]);
                if(city.inCache){ // 캐시 hit
                    runTime += 1;  
                    cache.remove(city);
                    city.lruTime = usedTime;
                    cache.offer(city);
                } else{ // 캐시 miss
                    runTime += 5;
                    // cache에서 LRU 업데이트
                    city.inCache = true;
                    city.lruTime = usedTime;
                    updateCache(cacheSize, city);
                }
            }
        }
        
        return runTime;
    }
    
    public int solution(int cacheSize, String[] cities) {
        cache = new PriorityQueue<>((o1,o2)->(o1.lruTime-o2.lruTime));
        map = new HashMap<>();
        
        if(cacheSize == 0) return 5 * cities.length;
        
        for(int i=0;i<cities.length;i++){
            cities[i] = cities[i].toLowerCase();
        }
        
        return solve(cacheSize, cities);
    }
    
}
class City{
    String name;
    int lruTime;
    boolean inCache;
    
    @Override
    public String toString(){
        return "(name : " + name + ", lruTime : " + lruTime + ", inCache : " + inCache + ")";
    }
    
    // public City(String name){
    //     this.name = name;
    // }
} 