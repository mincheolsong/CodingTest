import java.io.*;
import java.util.*;

public class Main {

    static int N,M;
    static int[] arr;
    static boolean[] visited;
    static int[] tmp;
    static List<int[]> ans;
    static void solve(int n){
        if(n==M){
            int[] copy = new int[M];
            System.arraycopy(tmp,0,copy,0,M);
            ans.add(copy);
            return;
        }

        for(int i=0;i<N;i++){
            if(visited[i]) continue;
            visited[i]=true;
            tmp[n]=arr[i];
            solve(n+1);
            visited[i]=false;
        }
    }
    static List<int[]> removeDuplicates(List<int[]> list){
        Set<String> set = new HashSet<>();
        List<int[]> uniqueList = new ArrayList<>();
        for(int[] array : list){
            if(set.add(Arrays.toString(array))){
                uniqueList.add(array);
            }
        }
        Collections.sort(uniqueList, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                for(int i=0;i<o1.length;i++){
                    if(o1[i]==o2[i]) continue;
                    return (o1[i]-o2[i]);
                }
                return 0;
            }
        });
        return uniqueList;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        visited = new boolean[N];
        tmp = new int[M];
        ans = new ArrayList<>();


        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }


        solve(0);

        List<int[]> uniqueAns = removeDuplicates(ans);
        for(int[] arr : uniqueAns){
            for(int i : arr){
                System.out.print(i + " ");
            }
            System.out.println();
        }







    }

}
