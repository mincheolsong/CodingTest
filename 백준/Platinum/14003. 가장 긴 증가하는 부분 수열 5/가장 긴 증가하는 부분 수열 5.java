import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] arr;
    static int[] ans;
    static int cnt;
    static int[] memo;

    static int binary_search(int num){
        int start=0, end=cnt;

        while(start<end){
            int mid = (start+end)/2;

            if(ans[mid]>=num){
                end=mid;
            }else{
                start=mid+1;
            }
        }

        if(start==cnt) return -1;
        return start;
    }

    static void solve(){

        for(int i=1;i<N;i++){
            // 0 ~ cnt 에 대해서 binarySearch(큰 값), 이때 lower bound 를 찾아야함
            int idx = binary_search(arr[i]);
            if(idx==-1){
                ans[cnt++]=arr[i];
                memo[i]=cnt;
            }else{
                ans[idx]=arr[i];
                memo[i]=idx+1;
            }

        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        ans = new int[N];
        memo = new int[N];
        Arrays.fill(memo,1);

        st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ans[0] = arr[0];
        cnt=1;

        solve();

        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append("\n");
        Deque<Integer> q = new ArrayDeque<>();

        /*System.out.println(Arrays.toString(ans));
        System.out.println(Arrays.toString(memo));*/

        for(int i=N-1;i>=0;i--){
            if(memo[i]==cnt){
                q.offer(arr[i]);
                cnt-=1;
            }
            if(cnt==0) break;
        }
        while(!q.isEmpty()){
            sb.append(q.pollLast()).append(" ");
        }

        System.out.println(sb.toString());
    }

}





