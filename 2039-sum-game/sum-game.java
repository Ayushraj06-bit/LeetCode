class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int s1 = 0, s2 = 0, q1 = 0, q2 = 0;

        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            if (i < n / 2) {
                if (c == '?') q1++;
                else          s1 += c - '0';
            } else {
                if (c == '?') q2++;
                else          s2 += c - '0';
            }
        }

        // Odd number of '?' -> Alice moves last and can always break the tie.
        if (((q1 + q2) & 1) == 1) return true;

        // Bob survives only if the gap exactly equals what mirroring forces.
        return 2 * (s1 - s2) != 9 * (q2 - q1);
    }
}
