import java.util.*;

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        Arrays.sort(coins);

        List<Integer> base = new ArrayList<>();
        for (int c : coins) {
            boolean redundant = false;
            for (int b : base) {
                if (c % b == 0) { redundant = true; break; }
            }
            if (!redundant) base.add(c);
        }

        int n = base.size();
        long lo = 1, hi = (long) base.get(0) * k;

        long[] lcms = new long[1 << n];
        int[] signs = new int[1 << n];
        int m = 0;
        for (int mask = 1; mask < (1 << n); mask++) {
            long l = 1;
            int bits = 0;
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                if ((mask >> i & 1) == 1) {
                    long c = base.get(i);
                    l = l / gcd(l, c) * c;
                    bits++;
                    if (l > hi) { ok = false; break; }
                }
            }
            if (ok) {
                lcms[m] = l;
                signs[m] = (bits % 2 == 1) ? 1 : -1;
                m++;
            }
        }

        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (count(mid, lcms, signs, m) >= k) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    private long count(long x, long[] lcms, int[] signs, int m) {
        long total = 0;
        for (int i = 0; i < m; i++) total += signs[i] * (x / lcms[i]);
        return total;
    }

    private long gcd(long a, long b) {
        while (b != 0) { long t = a % b; a = b; b = t; }
        return a;
    }
}