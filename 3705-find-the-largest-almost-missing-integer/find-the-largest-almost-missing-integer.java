class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int[] freq = new int[51];
        for (int v : nums) freq[v]++;

        int best = -1;

        if (k == n) {                       // single window: every value qualifies
            for (int v : nums) best = Math.max(best, v);
        } else if (k == 1) {                // each element is its own window
            for (int v = 0; v <= 50; v++) if (freq[v] == 1) best = v;
        } else {                            // only the two endpoints can qualify
            if (freq[nums[0]] == 1)     best = Math.max(best, nums[0]);
            if (freq[nums[n - 1]] == 1) best = Math.max(best, nums[n - 1]);
        }
        return best;
    }
}