import java.io.*;
import java.util.*;


public class Main {

    static int N,C;
    static int[] house;
    static int lo,hi;

    static int canInstall(int dist){
        int cnt = 1;
        int lastLocate = house[0];

        for(int i=1;i<N;i++){
            if(house[i] - lastLocate >= dist){
                cnt+=1;
                lastLocate = house[i];
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        house = new int[N];

        for(int i=0;i<N;i++){
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);

        lo = 1;
        hi = house[N-1]-house[0]+1;

        while(lo<hi){
            int mid = (lo + hi)/2;

            if(canInstall(mid) < C){
                hi = mid;
            }else{
                lo = mid+1;
            }
        }

        System.out.println(lo-1);


    }

}


