import java.io.*;
import java.util.*;

public class Main {

    // BOJ 5639번 이진 검색 트리
    // 전위 순회 : root - 왼쪽 - 오른쪽
    // 후위 순회 : 왼쪽 - 오른쪽 - root
    // 전위 순회가 입력으로 주어진다.
    // 후위 순회한 결과를 출력
    static class Node{
        int num;
        Node left, right;
        public Node(int num){
            this.num = num;
        }

        public void insert(Node input){
            if(this.num > input.num){ // 왼쪽
                if(this.left == null){
                    this.left = input;
                }else{
                    this.left.insert(input);
                }
            }else { // 오른쪽
                if(this.right == null){
                    this.right = input;
                }else{
                    this.right.insert(input);
                }
            }
        }

    }

    static void solve(Node node){

        if(node.left!=null)
            solve(node.left);

        if(node.right!=null)
            solve(node.right);

        System.out.println(node.num);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node(Integer.parseInt(br.readLine()));

        String input = "";
        while((input = br.readLine()) != null){
            int n = Integer.parseInt(input);
            root.insert(new Node(n));
        }

        solve(root);

    }

}



