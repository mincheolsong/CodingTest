
import java.io.*;
import java.util.*;

public class Main {

    // 1 5 2 1 4 3 4 5 2 1
    //[1,2,2,1,3,3,4,5,2,1] 우상향
    //[1,5,2,1,4,3,3,3,2,1] 우하향
    // dp문제

    static int N;
    static int[] arr;
    static int[] ru; // 우상향
    static int[] rd; // 우하향

    static int findLowArrMaxRu(int i){ // k < i, arr[k] < arr[i] 인 ru[k] 중 가장 큰 값 return
        int max=0;

        for(int k=0;k<i;k++){
            if(arr[k] < arr[i]){
                max = ru[k] > max ? ru[k] : max;
            }
        }
        return max;
    }

    static int findLowArrMaxRd(int i){ // k > i, arr[k] < arr[i] 인 rd[k] 중 가장 큰 값 return
        int max=0;

        for(int k=N-1;k>i;k--){
            if(arr[k] < arr[i]){
                max = rd[k] > max ? rd[k] : max;
            }
        }

        return max;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        ru = new int[N];
        rd = new int[N];
        int ans = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ru[0]=1;
        rd[N-1]=1;

        for(int i=1;i<N;i++){
            ru[i] = findLowArrMaxRu(i) + 1; // k < i, arr[k] < arr[i] 인 ru[k] 중 가장 큰 값 return
        }
        for(int i=N-2;i>=0;i--){
            rd[i] = findLowArrMaxRd(i) + 1; // k > i, arr[k] < arr[i] 인 rd[k] 중 가장 큰 값 return
        }


        for(int i=0;i<N;i++){
            int tmp = ru[i] + rd[i];
            ans = tmp > ans ? tmp : ans;
        }

        System.out.println(ans-1);

    }

}
