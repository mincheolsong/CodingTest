
import java.util.*;
import java.io.*;

public class Main {

    static int N,M;
    static List<Integer> list;
    static Set<Integer> set;
    static StringBuilder sb = new StringBuilder();

    static int[] comb;

    static void solve(int start, int cnt){
        if(cnt == M){
            for(int i=0;i<M;i++){
                sb.append(comb[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=start;i<N;i++){
            comb[cnt] = list.get(i);
            solve(i,cnt+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        comb = new int[M];
        set = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            set.add(Integer.parseInt(st.nextToken()));
        }

        for(Integer i : set){
            list.add(i);
        }

        Collections.sort(list);
        N = list.size();

        solve(0,0);

        System.out.print(sb.toString());
    }
}

