import java.io.*;
import java.util.*;

public class Main {

    // BOJ 1043
    // 서로소 집합? union, findParent

    static int N,M;
    static int tpCnt; // 진실을 아는 사람 수
    static int[] tp; // 진실을 아는 사람
    static List<Integer>[] party; // 파티
    static int[] parent;
    static List<Integer> blackList; // 블랙리스트에 들어있는 번호의 회원 들은 진실을 알고 있다.

    static void unionParent(int a, int b){

        a = findParent(a);
        b = findParent(b);
        if(a < b){
            parent[b]=a;
        }else{
            parent[a]=b;
        }
    }

    static int findParent(int n){
        if(parent[n]==n){
            return n;
        }

        return parent[n] = findParent(parent[n]);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        party = new List[M+1];
        parent = new int[N+1];
        blackList = new ArrayList<>();

        for(int i=1;i<N+1;i++){
            parent[i] = i;
        }

        for(int i=1;i<M+1;i++){
            party[i] = new ArrayList<>();
        }


        st = new StringTokenizer(br.readLine());
        tpCnt = Integer.parseInt(st.nextToken()); // 진실을 아는사람 수
        tp = new int[tpCnt]; // 진실을 아는 사람
        for(int i=0;i<tpCnt;i++){
            tp[i] = Integer.parseInt(st.nextToken());
        }

        // 진실을 아는 사람이 두 명 이상인 경우 unionParent
        if(tpCnt>=2){
            for(int i=1;i<tpCnt;i++){
                unionParent(tp[0],tp[i]);
            }
        }

        for(int i=1;i<M+1;i++){
            st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            for(int j=0;j<tmp;j++){
                party[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i=1;i<M+1;i++){
            if(party[i].size()>=2){ // 파티에 참석하는 인원이 두 명 이상일 경우
                for(int j=1;j<party[i].size();j++){
                    unionParent(party[i].get(0),party[i].get(j));
                }
            }
        }

        // parent[]가 루트 노드를 가질 수 있도록
        // 모든 원소에 대해 findParent를 수행
        for(int i=1;i<N+1;i++){
            findParent(i);
        }

        int blackRoot = -1;
        if(tpCnt>=1){
            blackRoot = parent[tp[0]];
        }

        for(int i=1;i<N+1;i++){
            if(parent[i]==blackRoot){
                blackList.add(i);
            }
        }

        int ans = 0;

        for(int i=1;i<M+1;i++){
            int flag = 0;
            for(int j : party[i]){
                if(blackList.contains(j)){
                    flag = 1;
                    break;
                }
            }
            if(flag==0){
                ans+=1;
            }
        }

        System.out.println(ans);

    }


}



