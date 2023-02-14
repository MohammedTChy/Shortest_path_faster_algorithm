import java.util.*;

public class Main {
    static int MAXN = 100001;
    static int INF = 1_000_000_001;
    static int n, m, cnt;
    static int[] head = new int[MAXN];
    static int[] vis = new int[MAXN];
    static int[] a = new int[MAXN];
    static int[] sum = new int[MAXN];
    static int[] ans = new int[MAXN];
    static int[] to = new int[MAXN];
    static int[] nxt = new int[MAXN];
    static int[] val = new int[MAXN];
    static Queue<Integer> q = new LinkedList<>();

    static void add(int u, int v, int w) {
        to[++cnt] = v;
        val[cnt] = w;
        nxt[cnt] = head[u];
        head[u] = cnt;
    }

    static boolean shortestPathFasterAlgorithm(int l, int r) {
        Arrays.fill(vis, 0);
        Arrays.fill(sum, 0);
        for (int i = l; i <= r; i++) {
            q.offer(i);
            vis[i] = 1;
            sum[i] = a[i];
        }
        while (!q.isEmpty()) {
            int u = q.poll();
            vis[u] = 0;
            for (int i = head[u]; i != 0; i = nxt[i]) {
                int v = to[i], w = val[i] * sum[u] / 100;
                sum[v] += w;
                if (v < l || v > r) continue;
                if (sum[v] > a[v] || ans[v] == 1) continue;
                if (sum[v] == a[v]) ans[v] = 1;
                if (vis[v] == 0) {
                    q.offer(v);
                    vis[v] = 1;
                }
            }
        }
        for (int i = l; i <= r; i++) {
            if (sum[i] == a[i]) ans[i] = 1;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        for (int i = 1; i <= n; i++) {
            int v = scan.nextInt();
            a[i] = v;
            int m = scan.nextInt();
            for (int j = 1; j <= m; j++) {
                int u = scan.nextInt(), w = scan.nextInt();
                add(i, u, w);
            }
        }
        shortestPathFasterAlgorithm(1, n);
        for (int i = 1; i <= n; i++) {
            if (ans[i] == 1) System.out.print(i + " ");
        }
    }
}
