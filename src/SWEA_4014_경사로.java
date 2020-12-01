import java.util.*;
import java.io.*;

public class SWEA_4014_경사로 {
	static int N;
	static int[][] map;
	static int result = 0;
	static int K;
	static boolean maked = true;
	static int[] dr = { -1, 0, 0, 1 }; // 상 좌 우 하
	static int[] dc = { 0, -1, 1, 0 };
	static int a, b;
	static boolean[][] shaved;

	public static void main(String[] args) throws IOException {
		File file = new File("input/SWEA_4014.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			result = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					a = i;
					b = j;
					maked = false;
					if (i == 0) {
						solve(i, j, 3); // 맨위에서 아래쪽 탐색
					}
					if (j == 0) {
						solve(i, j, 2); // 맨 왼쪽에서 오른쪽 탐색
					}

				}
			}

			sb.append("#" + tc + " " + result + '\n');
		}
		System.out.print(sb.toString());

	}

	private static void solve(int x, int y, int dir) {
		boolean pos = true;
		shaved = new boolean[N][N];
		while (pos) {
			int nx = x + dr[dir];
			int ny = y + dc[dir];
			if (!Inrange(nx, ny))
				break;

			if (map[x][y] == map[nx][ny]) {
				; // Nothing Happened;
			} else if (map[x][y] + 1 == map[nx][ny]) {
				// 만약에 나보다 높은 경사로를 만났다면 그 봉우리 지점부터 내가 온길쪽으로 탐색
				pos = check(nx, ny, 3 - dir);

			} else if (map[x][y] - 1 == map[nx][ny]) {
				// 가는 길쪽으로 만들 수 있는지 탐색,//현재 위치 기준에서
				pos = check(x, y, dir);
			} else {
				pos = false; // 그 이외에는 경사로 못만듭니다
			}

			x = nx;
			y = ny; // 증감식
		}
		if (pos) {
			result++;

		}
	}

	static boolean Inrange(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

	static boolean check(int x, int y, int dir) {
		boolean pos = true;
		int tx = 0, ty = 0;
		for (int i = 1; i <= K && pos; i++) {
			tx = x + i * dr[dir];
			ty = y + i * dc[dir];
			if (!Inrange(tx, ty) || map[tx][ty] != map[x][y] - 1 || shaved[tx][ty]) {
				pos = false;
				break;
			}
			shaved[tx][ty] = true;
		}
		return pos;
	}

}
