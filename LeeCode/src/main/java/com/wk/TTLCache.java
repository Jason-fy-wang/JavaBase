package com.wk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class TTLCache {


    /*
    1. TTL cache:
    Implement a prototype of a time-to-live cache.  You are given a 2D array of intergers data with dimensions nX2, where each
    data[i] represents that the 1th data point was added to the cache at time data[i][0] and it remains in the cache for
    data[i][1] time units. For an array of queries,  determine the number of data items present in the cache at each query time.

    example:
    n = 3, data=[[105231, 183], [105334, 34],[105198, 543], q=2, queries = [105338, 105410] output: [3,2]
     */

    public static List<Integer> getCacheStatus(List<List<Integer>> dates, List<Integer> queries) {
        List<Integer> res = new ArrayList<>();

        for (Integer query : queries) {
            int count = 0;
            for (List<Integer> date : dates) {
                int start = date.get(0);
                int ttl = date.get(1);
                if (query >= start && query < start + ttl) {
                    count++;
                }
            }
            res.add(count);
        }

        return res;
    }

    public static List<Integer> getCacheStatus2(List<List<Integer>> dates, List<Integer> queries) {
        TreeMap<Integer, Integer> cacheCount = new TreeMap<>();
        for (List<Integer> date : dates) {
            int start = date.get(0);
            int ttl = date.get(1);
            int end = start + ttl-1;
            cacheCount.put(start, cacheCount.getOrDefault(start, 0) + 1);
            cacheCount.put(end+1, cacheCount.getOrDefault(end+1, 0) - 1);
        }

        TreeMap<Integer, Integer> cumulative = new TreeMap<>();
        int count = 0;
        for (Integer temp : cacheCount.keySet()) {
            count += cacheCount.get(temp);
            cumulative.put(temp, count);
        }
        List<Integer> res = new ArrayList<>();

        for (Integer query : queries) {
            Integer key = cumulative.floorKey(query);
            int result = key != null ? cumulative.get(key):0;
            res.add(result);
        }

        return res;
    }


    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(105231, 183);
        List<Integer> list1 = Arrays.asList(105334, 34);
        List<Integer> list2 = Arrays.asList(105198, 543);

        List<List<Integer>> dates = Arrays.asList(list1, list2, list);

        List<Integer> query = Arrays.asList(105338, 105410);
        List<Integer> status = getCacheStatus(dates, query);
        System.out.println(status);
        status = getCacheStatus2(dates, query);
        System.out.println(status);

    }
}
