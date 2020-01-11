import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            result.add(new ArrayList<>());
            return result;
        }
        for (int i=0; i<nums.length; i++) {
            int[] otherNums = new int[nums.length-1];
            int k = 0;
            for (int j=0; j<nums.length; j++) {
                if (j == i) {
                    continue;
                }
                otherNums[k++] = nums[j];
            }
            List<List<Integer>> otherNumsLists = permute(otherNums);
            for (List<Integer> otherNumsList : otherNumsLists) {
                otherNumsList.add(0, nums[i]);
                result.add(otherNumsList);
            }
        }
        return result;
    }
}