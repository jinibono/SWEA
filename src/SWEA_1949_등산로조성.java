import java.util.*;
import java.io.*;

public class SWEA_1949_등산로조성 {
	static int N;
	static int[][] map;
	static int result = 0;
	static int K;
	static int max;
	static boolean[][] visited;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
//		File file = new File("input/SWEA_1949.txt");
//		BufferedReader br = new BufferedReader(new FileReader(file));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			max = 0;
			result = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					max = Math.max(max, map[i][j] = Integer.parseInt(st.nextToken()));
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != max)
						continue;
					visited = new boolean[N][N];
					dfs(i, j, 1, map[i][j], false);

				}
			}
			

			sb.append("#" + tc + " " + result + '\n');
		}
		System.out.print(sb.toString());

	}

	private static void dfs(int x, int y, int cnt, int value, boolean cut) {
		visited[x][y] = true;
		
		result = Math.max(result, cnt);
		
		for (int i = 0; i < 4; i++) {
			int nr = x + dr[i];
			int nc = y + dc[i];
			if (!Inrange(nr, nc) || visited[nr][nc])
				continue; // 범위,방문 체크

			// case 1 그냥 내려가는 경우
			if (value > map[nr][nc]) {
				dfs(nr, nc, cnt + 1, map[nr][nc], cut);

			}

			// case 2 깎고 내려가는 경우
			// 얼마 만큼 깎아야 할까?
			if (!cut) {
				int dif = map[nr][nc] - value + 1; // 10 -> 12 (2+1)만큼
				if (dif>0&&dif <= K && map[nr][nc] - dif >= 0) {
				
					dfs(nr, nc, cnt + 1, map[nr][nc] - dif, true);

				}
			}

		}
		visited[x][y] = false;
	}

	static boolean Inrange(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

}
