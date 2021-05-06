
/*
    TwoSum问题：数组无序，如何查找问题
    解决思路：先排序，再考虑双指针问题
    HashMap 或者 HashSet 也可以帮助我们处理⽆序数组相关的简单问题。

    Given nums = [2, 7, 11, 15], target = 9,
    Because nums[0] + nums[1] = 2 + 7 = 9,
    return [0, 1].
 */


import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2,7,11,15};

        //int[] res = baoli(nums, 9);
        int[] res = twoSum(nums, 9);

        System.out.println("res[0] : " + res[0] + ", res[1] : " + res[1]);
    }


    /*
        时间复杂度：两层 for 循环，O（n²）
        空间复杂度：O（1）
     */
    public static int[] baoli(int[] nums, int target ){
        int []ans = new int[2];
        for (int i = 0; i < nums.length; i++){
            for (int j = (i+1); j < nums.length; j++) {
                if (nums[i] + nums[j] == target){
                    ans[0] = i;
                    ans[1] = j;
                    return ans;
                }
            }
        }
        return ans;
    }

    /*
        哈希表的查询时间为 O(1)，算法的时间复杂度降低到 O(N)，
        但是需要 O(N) 的空间复杂度来存储哈希表。
     */
    public static int[] twoSum(int[] nums, int target){
        int n = nums.length;
        Map<Integer,Integer> index = new HashMap<>();
        //构造一个哈希表，元素映射到相应的索引
        for (int i = 0; i < n; i++){
            index.put(nums[i], i);
        }

        for (int i = 0; i < n; i++){
            int other = target - nums[i];
            // 若other存在且不是nums[i]本身
            if (index.containsKey(other) && index.get(other) != i){
                return new int[] {i, index.get(other)};
            }
        }
        return new int[]{-1, -1};
    }

}

class TwoSumTool {
    Map<Integer,Integer> freq = new HashMap<>();

    public static TwoSumTool getInstance() {
        return new TwoSumTool();
    }

    public void add(int number){
        //记录number出现的次数
        freq.put(number, freq.getOrDefault(number, 0) + 1);
    }

    public boolean find(int value){
        for (Integer key : freq.keySet()){
            int other = value - key;
            //情况⼀： add 了 [3,3,2,5] 之后，执⾏ find(6) ，
            // 由于 3 出现了两次，3 + 3 = 6，所以返回 true。
            if (other == key && freq.get(key) > 1)
                return true;
            //情况⼆： add 了 [3,3,2,5] 之后，执⾏ find(7) ，
            // 那么 key 为2， other 为 5 时算法可以返回 true。
            if (other != key && freq.containsKey(other))
                return true;
        }
        return false;
    }


}

