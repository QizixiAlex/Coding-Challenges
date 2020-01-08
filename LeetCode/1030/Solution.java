import java.util.*;

class Solution {

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] result = new int[R*C][2];
        int resultIdx = 0;
        //max distance from r0, c0 is R+C-2
        for (int d = 0; d < R+C; d++) {
            boolean flag = false;
            for (int dr = 0; dr <= d; dr++) {
                int dc = d - dr;
                List<List<Integer>> directions = new ArrayList<>();
                directions.add(Arrays.asList(new Integer[]{dr, dc}));
                if (dr > 0) {
                    directions.add(Arrays.asList(new Integer[]{-dr, dc}));
                }
                if (dc > 0) {
                    directions.add(Arrays.asList(new Integer[]{dr, -dc}));
                }
                if (dr > 0 && dc > 0) {
                    directions.add(Arrays.asList(new Integer[]{-dr, -dc}));
                }
                for (List<Integer> direction : directions) {
                    int r = r0 + direction.get(0);
                    int c = c0 + direction.get(1);
                    if (isValid(r, c, R, C)) {
                        flag = true;
                        result[resultIdx][0] = r;
                        result[resultIdx][1] = c;
                        resultIdx++;
                    }
                }
            }
            if (!flag) {
                break;
            }
        }
        return result;    
    }
    
    //check if r,c is a valid point in the matrix
    public boolean isValid(int r, int c, int R, int C) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}