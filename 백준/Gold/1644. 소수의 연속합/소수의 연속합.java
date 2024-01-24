import java.io.*;
import java.util.*;

// 소수 구하기 (에라토스테네스의 체)
// 투 포인터로 경우의 수 구하기


public class Main {


    static boolean[] arr;
    static int N;
    static List<Integer> finded; // 2 ~ N 사이의 소수
    static int answer;
    static void checking(int n){
        int d = 2;
        while(n*d<N+1){
            arr[n*d] = false;
            d+=1;
        }
    }

    static void solve(){
        int end = 0;
        int sum = 0;
        for(int start=0;start<finded.size();start++){
            while(end < finded.size() && sum < N){
                sum += finded.get(end);
                end+=1;
            }

            if(sum==N){
                answer += 1;
            }

            sum -= finded.get(start);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        finded = new ArrayList<>();
        arr = new boolean[N+1];
        Arrays.fill(arr,true);
        arr[1]=false;
        answer = 0;

        for(int i=2;i<N+1;i++){
            if(arr[i]) {
                finded.add(i);
                checking(i);
            }
        }
        solve();
        System.out.println(answer);

    }

}
