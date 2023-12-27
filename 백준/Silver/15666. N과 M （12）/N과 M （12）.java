import java.io.*;
import java.util.*;

public class Main {

    // boj 15666 N과 M(12)
    // back tracking
    // 사전 순으로 증가하는 순서로 출력하기 위해 입력을 정렬
    // 중복 방지를 위해 set을 활용



    static int N,M;
    static int[] arr;
    static Set<String> set;
    static int[] result;

    static void solve(int n,int i){
        if(n==M){
            StringBuilder sb = new StringBuilder();
            for(int r : result){
                sb.append(r).append(' ');
            }
            if(!set.contains(sb.toString())){ // 중복 검사
                System.out.println(sb.toString());
                set.add(sb.toString());
            }
            return;
        }

        for(int j=i;j<N;j++){
            result[n]=arr[j];
            solve(n+1,j);
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        set = new HashSet<>();
        result = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr); // 사전 순으로 출력하기 위해 입력을 오름차순 정렬

        solve(0,0);


    }

}