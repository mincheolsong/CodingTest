import java.io.*;
import java.util.*;

class Node{
    int num;
    String command;

    public Node(int num,String command){
        this.num = num;
        this.command = command;
    }
}
public class Main {

    static int T;
    static int A,B;
    static boolean[] visited;

    static void bfs(int n){

        Deque<Node> q = new ArrayDeque<>();
        q.offer(new Node(n,""));
        visited[n] = true;

        while(!q.isEmpty()){
            Node node = q.pollFirst();
            n = node.num;

            if(n==B){
                System.out.println(node.command);
                return;
            }

            int resultD = (n*2)%10000;
            if(!visited[resultD]){
                visited[resultD] = true;
                q.offer(new Node(resultD,node.command+"D"));
            }

            int resultS = n==0?9999:(n-1);
            if(!visited[resultS]) {
                visited[resultS] = true;
                q.offer(new Node(resultS,node.command+"S"));
            }

            int tmp = n;
            Deque<Integer> lq = new ArrayDeque<>();
            Deque<Integer> rq = new ArrayDeque<>();

            while(tmp!=0){
                lq.offer(tmp%10);
                rq.offer(tmp%10);
                tmp/=10;
            }
            if(lq.size()<4){
                int size = lq.size();
                for(int i=0;i<4-size;i++){
                    lq.offer(0);
                    rq.offer(0);
                }
            }
            // L연산
            if(lq.size()>0){
                int a = lq.pollLast();
                lq.offerFirst(a);
                // 시작하는 0 제거
                while(lq.peekLast()==0){
                    lq.pollLast();
                    break;
                }
                int resultL = 0;
                while(!lq.isEmpty()){
                    resultL *= 10;
                    resultL += lq.pollLast();
                }
                if(!visited[resultL]){
                    visited[resultL] = true;
                    q.offer(new Node(resultL,node.command+"L"));
                }
            }

            if(rq.size()>0){
                // R연산
                int a = rq.pollFirst();
                rq.offerLast(a);
                // 시작하는 0 제거
                while(rq.peekLast()==0){
                    rq.pollLast();
                    break;
                }
                int resultR = 0;
                while(!rq.isEmpty()){
                    resultR *= 10;
                    resultR += rq.pollLast();
                }

                if(!visited[resultR]){
                    visited[resultR] = true;
                    q.offer(new Node(resultR,node.command+"R"));
                }
            }

        }


    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            visited = new boolean[10001];
            bfs(A);
        }


    }

}
