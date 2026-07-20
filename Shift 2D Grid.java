class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
    
        int numrows = grid.length;
        int numCols = grid[0].length;
    
        List<List<Integer>> result = new ArrayList<>();
        for (int row = 0; row < numrows; row++) {
            List<Integer> currentRow = new ArrayList<>();
            for (int col = 0; col < numCols; col++) {
                currentRow.add(0);
            }
            result.add(currentRow);
        }
      
      
        for (int row = 0; row < numrows; row++) {
            for (int col = 0; col < numCols; col++) {
                
                int currentIndex = row * numCols + col;
              
               
                int newIndex = (currentIndex + k) % (numrows * numCols);
              
                
                int newrow = newIndex / numCols;
                int newCol = newIndex % numCols;
              
                
                result.get(newrow).set(newCol, grid[row][col]);
            }
        }
      
        return result;
    }
}
