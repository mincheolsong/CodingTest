import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] arr;
    static List<Integer> lis;

    static void solve(){

        for(int i=1;i<N;i++){

            if(lis.get(lis.size()-1) < arr[i]){
                lis.add(arr[i]);
            }else if(lis.get(lis.size()-1)==arr[i]){
            }else{
                int left = 0, right = lis.size()-1;

                while(left<=right){
                    int mid = (left + right)/2;

                    if(lis.get(mid) >= arr[i]){
                        right = mid-1;
                    }else{
                        left = mid+1;
                    }
                }
                lis.set(right+1,arr[i]);
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        lis = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        lis.add(arr[0]);
        solve();
        System.out.println(lis.size());

    }

}
