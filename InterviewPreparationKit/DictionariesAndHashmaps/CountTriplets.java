import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

//https://www.hackerrank.com/challenges/count-triplets-1/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps

public class Solution {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        long countPairs = 0;
         
         Map<Long, Long> rightMap = new HashMap<Long, Long>();
         Map<Long, Long> leftMap = new HashMap<Long, Long>();
         
         
         for(Long temp: arr)
         {
             if(rightMap.containsKey(temp))
             {
                 rightMap.put(temp, rightMap.get(temp) + 1);
             }else {
                 rightMap.put(temp, 1L);
             }
         }
         
         for(Long val : arr)
         {
             rightMap.put(val,rightMap.get(val) - 1);
             
             if(leftMap.containsKey(val/r) && val%r == 0 && rightMap.containsKey(val*r))
             {
                 countPairs = countPairs + leftMap.get(val/r) * rightMap.get(val*r);
             }
             
             if(leftMap.containsKey(val)) {
                 leftMap.put(val, leftMap.get(val) + 1);
             }else {
                 leftMap.put(val, 1L);
             }
         }
         
         return countPairs;
    }


     private static void insertMap(Map<Long,Long> map, Long r)
     {
         if(map.containsKey(r))
         {
             map.put(r, map.get(r) + 1);
         }else {
             map.put(r, 1L);
         }
     }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}