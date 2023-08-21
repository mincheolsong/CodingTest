
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

class Point{
	int row,col;
	
	Point(int row,int col){
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	@Override
	public String toString() {
		return "Pair [row=" + row + ", col=" + col + "]";
	}
}
public class Main {
	
	static int N,M;
	static int[][] city;
	static List<Point> chicken; 
	static List<Point> house;
	static boolean chicken_chk[];
	static int answer=Integer.MAX_VALUE;
	
	static void backTracking(int start,int cnt) {
		
		if(cnt==M) { // M개의 치킨 집을 선택하면 도시의 치킨 거리 계산 시작
			int result=0;
			for(int p=0;p<house.size();p++) {
				int min_distance = Integer.MAX_VALUE;
				for(int q=0;q<chicken.size();q++) {
					if(chicken_chk[q]) {
						int now_distance = Math.abs(house.get(p).getRow()-chicken.get(q).getRow()) + Math.abs(house.get(p).getCol()-chicken.get(q).getCol());
						
						min_distance = Math.min(min_distance, now_distance);
						
						if(min_distance==1)
							break;
					}
				}
				result+=min_distance;
			}
			
			answer = Math.min(answer, result);
			
			return;
		}
		
		for(int i=start;i<chicken.size();i++) { // 치킨집의 모든 경우를 백트래킹으로 탐색함
			chicken_chk[i]=true;
			backTracking(i+1,cnt+1);
			chicken_chk[i]=false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		chicken = new ArrayList<>();
		house = new ArrayList<>();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		city = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				if(city[i][j]==2) { // 치킨 집 이면 chicken List에 좌표값을 저장
					chicken.add(new Point(i,j));
				}else if(city[i][j]==1) {
					house.add(new Point(i,j));
				}
			}
		}
		chicken_chk = new boolean[chicken.size()];
		
		backTracking(0, 0);
		
		System.out.println(answer);
		
		
	}

}
