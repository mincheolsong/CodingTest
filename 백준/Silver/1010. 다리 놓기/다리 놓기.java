import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		int N,M;
		int[][] memo;
		
		for(int t=0;t<T;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			memo = new int[M+1][N+1];
			
			for(int i=1;i<=N;i++) {
				memo[i][i]=1;
			}
			
			for(int r=2;r<=M;r++) {
				memo[r][1]=r;
			}
			
			
			
			for(int r=3;r<=M;r++) {
				for(int c=2,end = Math.min(r, N);c<=end;c++) {
					if(memo[r][c]==0) {
						memo[r][c]=memo[r-1][c-1] + memo[r-1][c];
					}
				}
			}

//			for(int r=0;r<=M;r++) {
//				System.out.println(Arrays.toString(memo[r]));
//			}
//			
			System.out.println(memo[M][N]);
			
			
			
			
		}
		
	}
}
