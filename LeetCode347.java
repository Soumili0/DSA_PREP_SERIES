import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 1. Build frequency map using standard indexed for loop
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
        }

        // 2. Create buckets where the index represents frequency
        List<Integer>[] bucket = new List[nums.length + 1];

        // Convert keySet to an array to use an index-based for loop
        Integer[] keys = countMap.keySet().toArray(new Integer[0]);
        for (int i = 0; i < keys.length; i++) {
            int key = keys[i];
            int frequency = countMap.get(key);
            
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        // 3. Collect the top k elements using standard indexed for loops
        int[] result = new int[k];
        int counter = 0;

        for (int pos = bucket.length - 1; pos >= 0 && counter < k; pos--) {
            if (bucket[pos] != null) {
                List<Integer> currentBucket = bucket[pos];
                for (int j = 0; j < currentBucket.size(); j++) {
                    result[counter++] = currentBucket.get(j);
                    if (counter == k) {
                        break;
                    }
                }
            }
        }

        return result;
    }
}
