import java.util.*;
import java.io.*;

public class Main {
    // 시간복잡도 : 250000*256 = 64000000 -> 완전탐색 가능
    // 목표 높이 설정 : 가장 높은 높이 ~ 가장 낮은 높이
    // 목표 높이로 맞추기 (배열 순회) : 목표높이 - 현재높이
    // 양수(추가) : 인벤토리 확인, time += val
    // 음수(삭제) : time += val*(-2), 인벤토리 저장

    static final int INF = (int)1e9;
    static int N,M,B;
    static Integer[] arr;
    static int[] ans;
//    static PriorityQueue<int[]> pq;

    static void calc(int target){
        int inv = B;
        int time = 0;


        for(int i=0; i<arr.length; i++){
            int diff = target - arr[i];

            if(diff > 0){ // 추가
                if(diff > inv) return;
                inv -= diff;
                time += diff;
            }else if(diff < 0){ // 삭제
                time += diff*-2;
                inv += diff*-1;
            }
        }

        if(time<ans[0]){
            ans[0] = time;
            ans[1] = target;
        }else if(time==ans[0] && target > ans[1]){
            ans[1] = target;
        }
    }

    static void solve(){

        for(int i=arr[0];i>=arr[arr.length-1];i--){
            calc(i);
        }

//        int[] answer = pq.poll();
        System.out.println(ans[0]+" "+ans[1]);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
//        pq = new PriorityQueue<>((o1,o2)->{
//            if(o1[0]==o2[0]) return -(o1[1]-o2[1]);
//            return (o1[0]-o2[0]);
//        });
        ans = new int[2];
        ans[0] = INF;
        ans[1] = 0;

        arr = new Integer[N*M];
        int idx = 0;
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                arr[idx++] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(arr,Collections.reverseOrder());
        solve();

    }

}