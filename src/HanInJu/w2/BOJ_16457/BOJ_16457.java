import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16457 {
	// 2N개의 스킬 중 N개 고르기! 중복X : 조합!!
	private static int N, M, K;
	private static int max = -1;
	private static int[][] quests;
	private static int[] keys;

	public static void recur(int cur, int cnt, String skillSet) {
		if(cnt == N) { // N개가 되면 달성한 퀘스트의 수 비교 후 max 갱신
			max = Math.max(max, countPossibleQuestsInString(skillSet));
			return;
		}
		if(cur > 2*N) { // 2N개까지 갔으면 리턴
			return;
		}

		recur(cur+1, cnt+1, skillSet+cur+",");
		recur(cur+1, cnt, skillSet);
	}

	public static void recur2(int cur, int start) {
		if(cur == N) {
			max = Math.max(max, countPossibleQuests());
			return;
		}

		for(int i = start; i <= 2*N; i++) {
			keys[cur] = i;
			recur2(cur+1, i+1);
		}
	}

	public static int countPossibleQuests() {
		int possibleQuests = 0;
		int skillCount;
		for(int i = 0; i<M; i++) {
			skillCount = 0;
			for(int j = 0; j<K; j++) {
				for(int k = 0; k<N; k++) {
					if(keys[k] == quests[i][j]) {
						skillCount++;
					}
				}
			}

			if (skillCount == K) {
				possibleQuests++;
			}
		}

		return possibleQuests;
	}

	public static int countPossibleQuestsInString(String skillSet) {
		int possibleQuests = 0;
		int skillCount;
		String[] skills = skillSet.split(",");
		for(int i = 0; i<M; i++) {
			skillCount = 0;
			for(int j = 0; j<K; j++) {
				for(int k = 0; k<N; k++) {
					if(Integer.parseInt(skills[k]) == quests[i][j]) {
						skillCount++;
					}
				}
			}

			if (skillCount == K) {
				possibleQuests++;
			}
		}

		return possibleQuests;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		keys = new int[N];

		quests = new int[M][K];
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<K; j++) {
				quests[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// recur2(0,1);
		recur(1,0,"");
		System.out.println(max);
	}
}
