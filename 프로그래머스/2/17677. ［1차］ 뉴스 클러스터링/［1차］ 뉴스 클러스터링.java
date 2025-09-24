import java.util.*;

// 원소 갯수 저장하는 HashMap
// HashMap<String, Integer> map1, map2;

// 원소 저장하는 HashSet
// HashSet<String> set1, set2;

// set 의 addAll(합집합), retainAll(교집합)

// 교집합 하고, 그 결과 set에 대해서 map1, map2에 Math.min 하면서 갯수 +=
// 합집합 하고, 그 결과 set에 대해서 map1, map2에 Math.max 하면서 갯수 +=

class Solution {
    
    static final double VAL = 65536*1.0;
    
    char[] arr1, arr2;
    HashMap<String,Integer> map1,map2;
    HashSet<String> set1, set2;
    
    void debug(){
        System.out.println("map1");
        for(String k1 : map1.keySet()){
            System.out.println(k1 + " : " + map1.get(k1));
        }
        
        System.out.println("set1");
        for(String s : set1){
            System.out.printf("%s ",s);
        }
        System.out.println();
        
        System.out.println("map2");
        for(String k2 : map2.keySet()){
            System.out.println(k2 + " : " + map2.get(k2));
        }
        
        System.out.println("set2");
        for(String s : set2){
            System.out.printf("%s ",s);
        }
        System.out.println();
    }
    
    
    int solve(){
        if(set1.size()==0 && set2.size()==0) return (int)VAL;
        
        int interNum = 0, unionNum = 0;
        
        Set<String> inter = new HashSet<>(set1);
        Set<String> union = new HashSet<>(set1);
        inter.retainAll(set2); // 교집합
        union.addAll(set2); // 합집합
        
        for(String s : inter){
            interNum += Math.min(map1.get(s),map2.get(s));
        }                
        
        for(String s : union){
            unionNum += Math.max(map1.getOrDefault(s,0),map2.getOrDefault(s,0));
        }
        
        return (int)((interNum*1.0 / unionNum*1.0) * VAL);
        
    }
    
    void make(){
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<arr1.length-1;i++){
            sb.setLength(0);
            for(int j=i;j<i+2;j++){
                if(arr1[j] >= 'a' && arr1[j] <= 'z'){
                    sb.append(arr1[j]);
                }
            }
            if(sb.length()==2){
                String result = sb.toString();
                map1.put(result,map1.getOrDefault(result,0)+1);
                set1.add(result);
            }
        }
          
        
        
        for(int i=0;i<arr2.length-1;i++){
            sb.setLength(0);
            for(int j=i;j<i+2;j++){
                if(arr2[j] >= 'a' && arr2[j] <= 'z'){
                    sb.append(arr2[j]);
                }
            }
            if(sb.length()==2){
                String result = sb.toString();
                map2.put(result,map2.getOrDefault(result,0)+1);
                set2.add(result);
            }
        }
        
    }
    
    public int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        arr1 = str1.toCharArray();
        arr2 = str2.toCharArray();
        
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        set1 = new HashSet<>();
        set2 = new HashSet<>();
        
        make();
        
        return solve();
        
    }
}