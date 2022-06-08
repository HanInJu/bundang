package HanInJu.w3.BOJ_1497;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1497 {
	// N개의 기타 중 최소 개수 뽑기 - 조합!
	private static int N, M, guitar = -1, songs = 0;
	private static String[][] guitars;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String songList = "";
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		guitars = new String[N][2];
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			guitars[i][0] = st.nextToken();
			guitars[i][1] = st.nextToken();
		}

		for(int i = 0; i<M; i++) {
			songList += "N";
		}

		recur(0, 0, songList);
		System.out.println(guitar);
	}

	public static void recur(int cur, int cnt, String songList) {
		if(cur == N) { // N번 기타를 뽑든 말든의 선택이 왔을 때
			// 현재까지 연주 가능한 곡 계산해서 Max 갱신
			int numberOfSongPerform = 0;
			for(int i = 0; i<M; i++) {
				if(songList.charAt(i) == 'Y') {
					numberOfSongPerform++;
				}
			}

			if(numberOfSongPerform > songs) {
				songs = numberOfSongPerform;
				guitar = cnt;
			}
			else if(numberOfSongPerform == songs && guitar > cnt) {
				guitar = cnt;
			}

			return;
		}

		// cur번 기타를 뽑았을 때 곡을 연주할 수 있는지 없는지
		recur(cur+1, cnt+1, makeSongList(cur, songList));
		recur(cur+1, cnt, songList);
	}

	private static String makeSongList(int cur, String songList) {
		String newSongList = "";

		for(int i = 0; i<M; i++) {
			if(songList.charAt(i) == 'Y' || guitars[cur][1].charAt(i) == 'Y') {
				newSongList += "Y";
			}
			else {
				newSongList += "N";
			}
		}

		return newSongList;
	}
}
