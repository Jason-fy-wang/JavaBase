package com.wk;

import org.junit.Assert;
import org.junit.Test;

public class MergeSortArrayTest {


    @Test
    public void TestMerge(){
        MergeSortArray interview = new MergeSortArray();
        // nums1 = [1,3,4,0,0], m = 3, nums2 = [2,5], n = 2
        int[] nums1 = {1,3,4,0,0};
        int[] nums2 = {2,5};
        int m=3,n=2;
        interview.merge(nums1,m,nums2,n);

        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, nums1);
        Assert.assertEquals(5, nums1[4]);
    }


    @Test
    public void TestMerge2(){
        MergeSortArray interview = new MergeSortArray();
        // nums1 = [1,3,4,0,0], m = 3, nums2 = [2,5], n = 2
        int[] nums1 = {1,3,4,0,0};
        int[] nums2 = {2,5};
        int m=3,n=2;
        interview.merge2(nums1,m,nums2,n);

        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5}, nums1);
        Assert.assertEquals(5, nums1[4]);
    }

}
