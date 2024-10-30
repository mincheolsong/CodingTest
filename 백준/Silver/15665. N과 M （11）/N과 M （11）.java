
import java.util.*;
import java.io.*;

public class Main {

    static int N,M;
    static List<Integer> list;
    static Set<String> set_comb;
    static Set<Integer> set_input;
    static StringBuilder sb_comb, sb_answer;

    static int[] comb;

    static void solve(int cnt){
        if(cnt == M){
            sb_comb = new StringBuilder();

            for(int i=0;i<M;i++){
                sb_comb.append(comb[i]).append(" ");
            }
            String result = sb_comb.toString();
            if(!set_comb.contains(result)){
                set_comb.add(result);
                sb_answer.append(result).append("\n");
            }

            return;
        }


        for(int i=0;i<N;i++){
            comb[cnt] = list.get(i);
            solve(cnt+1);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        comb = new int[M];
        set_comb = new HashSet<>();
        set_input = new HashSet<>();
        sb_answer = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            set_input.add(Integer.parseInt(st.nextToken()));
        }

        for(Integer i : set_input){
            list.add(i);
        }

        Collections.sort(list);
        N = list.size();

        solve(0);

        System.out.print(sb_answer.toString());
    }
}

