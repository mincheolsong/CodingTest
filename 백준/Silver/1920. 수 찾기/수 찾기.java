import java.io.*;
import java.util.*;


public class Main {



    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        HashMap<Integer,Integer> map = new HashMap<>();

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for(int i=0;i<n;i++){
            map.put(Integer.parseInt(st.nextToken()),1);
        }
        int m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++){
            if(map.containsKey(Integer.parseInt(st.nextToken()))){
                System.out.println(1);
            }else{
                System.out.println(0);
            }
        }



    }

}





