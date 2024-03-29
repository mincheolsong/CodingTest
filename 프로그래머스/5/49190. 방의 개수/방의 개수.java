import java.util.*;

class Node{
    int r,c;
    
    public Node(int r,int c){
        this.r = r;
        this.c = c;
    }
    
    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null) return false;
        if(!(o instanceof Node)) return false;
        Node no = (Node)o;
        if(no.r==this.r && no.c==this.c) return true;
        
        return false;
    }
    
    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = result*prime + r;
        result = result*prime + c;
        return result;
    }
}

class Solution {
    int[] dr = {-1,-1,0,1,1,1,0,-1};
    int[] dc = {0,1,1,1,0,-1,-1,-1};
    int answer;
    Map<Node,List<Node>> map;
    
    void solve(int[] arrows){
        int cr = 0;
        int cc = 0;
        Node node = new Node(cr, cc);
        map.put(node,new ArrayList<>());
        
        for(int a : arrows){
            for(int i=0;i<2;i++){
                int nr = cr + dr[a];
                int nc = cc + dc[a];
                Node cur_node = new Node(cr,cc);
                Node next_node = new Node(nr,nc);
                if(!map.containsKey(next_node)){ // 처음 방문하는 지점
                    map.put(next_node,new ArrayList<>());
                    map.get(next_node).add(cur_node);
                    map.get(cur_node).add(next_node);
                }else if(!map.get(next_node).contains(cur_node)){ // 재방문 지점이고 새로 만들어진 선분이면 answer+1
                    map.get(next_node).add(cur_node);
                    map.get(cur_node).add(next_node);
                    answer+=1;
                }
                cr = nr;
                cc = nc;
            }
            
        }
        
    }
    
    public int solution(int[] arrows) {
        answer = 0;
        map = new HashMap<>();
        
        solve(arrows);
        
        return answer;
    }
}