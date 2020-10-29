package com.learning;

import java.util.*;

public class ListProblems {
    static int minChange = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[] input = new int[]{1,2,4,5,6,3,9,7,8};
        List<Integer> input2 = Arrays.asList(1,2,3,4,5,6,7,8,9);
        int[] nums = new int[]{0,-1,1,2,-1,-4};
        List<Integer> list1 = Arrays.asList(1,4,3,2,5);
        List<Integer> list2 = Arrays.asList(-2,4,3,2,-11,5);
        List<Integer> list3 = Arrays.asList(1,8,6,2,5,4,8,3,7);
        int[] list4 = new int[]{24,3,82,22,35,84,19};
        int[][] list5 = new int[][]{{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        Integer[] coins = new Integer[]{3,7,405,436};
        int[] candy = new int[]{-255, 369, 319, 77};

//        System.out.println(maxSubarraySumCircular(new int[]{12,-5,4,-8,11}));
//        System.out.println(longestMountain(new int[]{875,884,239,731,723,685}));
//        dailyTemperatures(new int[]{75, 76, 77});
//        topKFrequent(new int[]{1,1,1,2,2,3}, 2);
//        System.out.println(eraseOverlapIntervals(new int[][]{{1,2},{2,3},{3,4},{1,3}}));
//        System.out.println(findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1}));
//        merge(new int[]{4,5,6,0,0,0}, 3, new int[]{1,2,7}, 3);
//        intersect(new int[]{1,2,2,1}, new int[]{2,2});
//        rotateList(new int[]{1,2,3,4,5,6,7}, 3);
//        System.out.println(thriceSingleNumber(new int[]{3,2,2,2,3,3,1}));
//        System.out.println(twiceSingleNumber(new int[]{1,2,2}));
//        System.out.println(candy(candy));
//        int[] gas = new int[] {5,1,2,3,4};
//        int[] cost = new int[]{4,4,1,5,1};
//        System.out.println(canCompleteCircuit(gas, cost));
//        System.out.println(coinChange(coins, 8839));
//        kthLargest(input2, 4);
//        nthUglyNumber(10);
//        threeSumUsingHashing(input, 9);
//        threeSumZero(nums);
//        findTargetSumUsingHashing(input, 8, 8);
//        pythagoreanTripletFinder(input);
//        gameOfLife(list5);
//        validTriangle(list4);
//        System.out.println(previousGreatestElementInArray(list3));
//        System.out.println(maximumSumSubArray(list2));
//        System.out.println(nextGreaterElement(list1));
    }

    private static List<Integer> nextGreaterElement(List<Integer> list) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        List<Integer> returnList = new ArrayList<>();

        for (int num: list){
            while(!stack.isEmpty() && stack.peek() < num){
                map.put(stack.pop(), num);
            }

            stack.push(num);
        }

        for(int nums: list){
            returnList.add(map.getOrDefault(nums, -1));
        }

        return returnList;
    }

    private static int maximumSumSubArray(List<Integer> list) {
        int currentSum = list.get(0);
        int maxSum = currentSum;

        for (int i=1; i<list.size(); i++){
            currentSum = Math.max(list.get(i), list.get(i) + currentSum);
            maxSum = Math.max(currentSum, maxSum);
        }

        return maxSum;
    }

    private static int maxAreaOfContainer(int[] height){
        int maxArea = 0, l = 0, r = height.length-1;

        while (l<r) {
            maxArea = Math.max(maxArea, Math.min(height[l], height[r]) * (r-l));
            if(height[l] > height[r]) r--;
            else l++;
        }

        return maxArea;
    }

    private static List<Integer> previousGreatestElementInArray(List<Integer> input) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();

        stack.push(0);
        result.add(1);

        for(int i=1; i<input.size(); i++) {
            while (!stack.isEmpty() && input.get(stack.peek()) <= input.get(i))
                stack.pop();

            result.add(stack.isEmpty() ? i+1 : i-stack.peek());

            stack.push(i);
        }

        return result;
    }

    private static void validTriangle(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-2; i++) {
            int k = i + 2;
            for (int j = i + 1; j < nums.length-1; j++) {
                while(k<nums.length && nums[i] + nums[j] > nums[k])
                    ++k;
                count += k-j-1;
            }
        }

        System.out.println(count);
    }

    public static void gameOfLife(int[][] board) {
        int[][] boardCopy = new int[board.length][board[0].length];

        for(int i=0; i<board.length;i++) {
            System.arraycopy(board[i], 0, boardCopy[i], 0, board[i].length);
        }

        for(int i=0; i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                int aliveCount = countAlive(boardCopy,i,j);

                if(boardCopy[i][j] == 1 && (aliveCount<2 || aliveCount>3))
                    board[i][j] = 0;
                else if(boardCopy[i][j] == 0 && aliveCount==3)
                    board[i][j] = 1;
            }
        }

        for(int i=0; i<board.length;i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int countAlive(int[][] input, int row, int col) {
        int count =0;
        int rowsize = input.length;
        int colsize = input[0].length;

        if(row>0 && input[row-1][col] == 1)
            count+=1;
        if(col>0 && input[row][col-1] ==1)
            count+=1;
        if(row<rowsize-1 && input[row+1][col]==1)
            count+=1;
        if(col<colsize-1 && input[row][col+1]==1)
            count+=1;
        if(row>0 && col>0 && input[row-1][col-1] ==1)
            count+=1;
        if(row>0 && col<colsize-1 && input[row-1][col+1] ==1)
            count+=1;
        if(row<rowsize-1 && col>0 && input[row+1][col-1] ==1)
            count+=1;
        if(row<rowsize-1 && col<colsize-1 && input[row+1][col+1] ==1)
            count+=1;

        return count;
    }

    public static void nthUglyNumber(int n) {
        int[] ugly = new int[n];
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        ugly[0] = 1;

        for (int i = 1; i < n; i++) {
            int min = Math.min(Math.min(factor2, factor3), factor5);
            ugly[i] = min;
            if (factor2 == min)
                factor2 = 2 * ugly[++index2];
            if (factor3 == min)
                factor3 = 3 * ugly[++index3];
            if (factor5 == min)
                factor5 = 5 * ugly[++index5];
        }
    }

    private static void pythagoreanTripletFinder(int[] input) {
        for (int i=0; i<input.length; i++) {
            input[i] = (int) Math.pow(input[i], 2);
        }

        Arrays.sort(input);

        int i = 0;

        for(int t = input.length-1; t >= 2; t--) {
            int target = input[input.length-1-i];
            int r = input.length-2-i;
            findTargetSum(input, target, r);
            findTargetSumUsingHashing(input, target, r);
            i++;
        }
    }

    private static void findTargetSumUsingHashing(int[] input, int target, int r) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<=r; i++){
            if(map.containsKey(target-input[i])){
                System.out.println(input[i] + " " + (target - input[i]) + " " + target);
            } else {
                map.put(input[i], i);
            }
        }
    }

    private static void findTargetSum(int[] input, int target, int r) {
        for (int l = 0; l < r; ) {
            if (input[l] + input[r] > target) r--;
            else if (input[l] + input[r] < target) l++;
            else {
                System.out.println(input[l] + " " + input[r] + " " + target);
                break;
            }
        }
    }

    private static void threeSumUsingHashing(int[] input, int target) {
        for (int i = 0; i < input.length - 1; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < input.length; j++) {
                int midTarget = target - input[i];
                if (map.containsKey(midTarget - input[j])) {
                    System.out.println(i + " " + j + " " + map.get(midTarget - input[j]));
                } else {
                    map.put(input[j], j);
                }
            }
        }
    }

    private static void threeSumZero(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> returnList = new ArrayList<>();

        for (int i=0; i<nums.length-2;i++){
            if(i==0 || (i>0 && nums[i] != nums[i-1])) {
                int low = i+1;
                int high = nums.length-1;
                int sum = -nums[i];

                while(low < high){
                    if(nums[low] + nums[high] == sum){
                        returnList.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        while(low < high && nums[low] == nums[++low]);
                        while(low < high && nums[high] == nums[--high]);
                    } else if(nums[low] + nums[high] > sum) {
                        high--;
                    } else {
                        low++;
                    }
                }
            }
        }

        System.out.println(returnList);
    }

    private static List<Integer> kthLargest(List<Integer> input, int k){
        Integer[] integers = input.toArray(new Integer[0]);
        //Below is Min heap. To make it max heap, new PriorityQueue<>(Collections.reverseOrder())
        PriorityQueue<Integer> arr = new PriorityQueue<>();
        List<Integer> returnList = new ArrayList<>();
        for( int i : input){
            arr.add(i);
            if (arr.size() > k){
                arr.remove();
            }
        }

        for (int i=0; i<k; i++) {
            returnList.add(arr.remove());
        }

        return returnList;
    }

    public static int coinChange(Integer[] coins, int amount) {
        Arrays.sort(coins, (coin1, coin2) -> coin2-coin1);

        minChangeFinder(new ArrayList<>(), coins, amount, 0);
        return minChange == Integer.MAX_VALUE ? -1 : minChange;
    }

    public static void minChangeFinder(List<Integer> holder, Integer[] coins, int amount, int index) {
        if(amount == 0){
            minChange = Math.min(holder.size(), minChange);
        }

        for (int i=index; i<coins.length; i++){
            if(coins[i] > amount)
                break;

            holder.add(coins[i]);
            minChangeFinder(holder, coins, amount - coins[i], i);
            holder.remove(holder.size()-1);
        }
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int tank = 0;
        for(int i=0; i<gas.length; i++)
            tank += gas[i] - cost[i];
        if(tank <0)
            return -1;

        int start = 0;
        int accumulate = 0;
        for(int i=0; i<gas.length; i++){
            int currentGain = gas[i] - cost[i];
            if(accumulate + currentGain < 0) {
                start = i;
                accumulate = 0;
            } else {
                accumulate += currentGain;
            }
        }

        return accumulate >=0 ? start : -1;
    }

    public static int candy(int[] A) {
        int[] candy = new int[A.length];
        Arrays.fill(candy, 1);
        int sum = 0;

        for(int i =1; i<A.length; i++) {
            if (A[i - 1] < A[i])
                candy[i] = candy[i-1] + 1;
        }

        for(int i =A.length-2; i>0; i--) {
            if (A[i + 1] < A[i])
                candy[i] = Math.max(candy[i], candy[i+1] + 1);
        }

        for (int value : candy)
            sum += value;

        return sum;
    }

    private static int twiceSingleNumber(int[] nums){
        int result = 0;
        for (int i : nums){
            result ^= i;
        }
        return result;
    }

    private static int thriceSingleNumber(int[] nums){
        int result = 0;
        for (int i=0; i<nums.length; i+=3){
            result ^= i;
        }
        return result;
    }

    private static void rotateList(int[] nums, int k){
        k = k%nums.length;
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);

        for(int i : nums){
            System.out.print(i + " ");
        }
    }

    private static void reverse(int[] nums, int start, int end) {
        while(start<end){
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    private static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>();
        List<Integer> resultList = new ArrayList<>();

        for(int num: nums1)
            map1.put(num, map1.getOrDefault(num,0)+1);

        for(int num: map1.keySet()){
            if(map1.containsKey(num)){
                int count = Math.min(map1.get(num), map1.get(num));
                for(int i=0; i<count; i++){
                    resultList.add(num);
                }
            }
        }

        int[] result = new int[resultList.size()];
        int i=0;
        for(int num: resultList){
            result[i++] = num;
        }

        return result;
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int j=0;
        for (int i=0; i<nums1.length && j<nums2.length; i++){
            if(nums2[j]<=nums1[i]){
                System.arraycopy(nums1, i, nums1, i+1, nums1.length - i -1);
                nums1[i]=nums2[j++];
            } else if(nums1[i]==0) {
                nums1[i] = nums2[j++];
            }
        }

        for(int num: nums1){
            System.out.print(num + " ");
        }
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();

        for(int num: nums) {
            int desIndex = num - 1;
            nums[desIndex] = -Math.abs(nums[desIndex]);
        }

        for(int i=0; i<nums.length; i++){
            if(nums[i]>0)
                result.add(i+1);
        }

        return result;
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[1]));
        int removals = 0;
        int current = 0;

        for(int i=1; i<intervals.length; i++) {
            if (intervals[current][1] > intervals[i][0]) {
                removals++;
            } else
                current = i;
        }
        return removals;
    }

    public static void topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        List<Integer> result = new ArrayList<>();

        for (int n: nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for (int i: map.keySet()){
            heap.add(i);
            if(heap.size() > k)
                heap.poll();
        }

        while(!heap.isEmpty()){
            result.add(heap.poll());
        }

        for (int num: result.stream().mapToInt(Integer::intValue).toArray())
            System.out.print(num + " ");
        System.out.println();
    }

    public static int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        Stack<Integer> stack = new Stack<>();

        for(int i =T.length-1; i>=0; i--){
            while(!stack.isEmpty() && stack.peek()<=T[i])
                stack.pop();
            result[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return result;
    }

    public static int longestMountain(int[] A) {
        if(A.length<3)
            return 0;

        boolean inc = false, dec = false;
        int max = 0, current = 0;
        for(int i=1; i<A.length; i++){
            if(A[i]>A[i-1] && !dec){
                inc = true;
                current++;
            } else if(A[i]<A[i-1] && inc) {
                dec = true;
                current++;
            } else {
                if(inc && dec)
                    max = Math.max(max, current);
                current = (A[i]>A[i-1]) ? 1 : 0;
                inc = A[i]>A[i-1];
                dec = false;
            }

            if(i==A.length-1 && inc && dec)
                max = Math.max(max, current);
        }

        return max==0 ? 0 : ++max;
    }

    public static int maxSubarraySumCircular(int[] A) {
        int  k = kadane(A);
        int sum = 0;
        for(int i=0; i<A.length; i++) {
            sum += A[i];
            A[i] *= -1;
        }
        int cs = sum + kadane(A);

        if(cs > k && cs!=0) return cs;
        else return k;
    }

    public static int kadane(int[] A) {
        int currentSum = 0, maxSum = Integer.MIN_VALUE;
        for (int value : A) {
            currentSum = Math.max(currentSum + value, value);
            maxSum = Math.max(currentSum, maxSum);
        }

        return maxSum;
    }

    public static int[][] mergeIntersectionInterval(int[][] A, int[][] B){
        List<int[]> result = new ArrayList<>();
        int i=0, j=0;

        while(i<A.length && j <B.length) {
            int lo = Math.max(A[i][0], B[j][0]);
            int hi = Math.min(A[i][1], B[j][1]);

            if(lo<=hi) result.add(new int[]{lo, hi});

            if(A[i][1] <= B[j][1]) i++;
            else j++;
        }
        return result.toArray(new int[result.size()][2]);
    }
}
