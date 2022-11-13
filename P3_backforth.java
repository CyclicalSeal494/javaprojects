import java.io.*;
import java.util.*;

public class P3_backforth {
    public static void main(String args[]) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("backforth.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("backforth.out")));
        // PrintWriter out = new PrintWriter(new BufferedWriter(new
        // OutputStreamWriter(System.out)));
        // Use StringTokenizer vs. readLine/split -- lots faster

        List<Integer> bucket1 = new ArrayList<>(); // buckets in Barn 1
        List<Integer> bucket2 = new ArrayList<>(); // buckets in Barn 2

        StringTokenizer st = new StringTokenizer(br.readLine());
        // Get line, break into tokens

        for (int k = 0; k < 10; k++) {
            int bucket = Integer.parseInt(st.nextToken());
            bucket1.add(bucket);
        }

        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < 10; k++) {
            int bucket = Integer.parseInt(st.nextToken());
            bucket2.add(bucket);
        }
        br.close();
        Set<Integer> res = new HashSet<>();
        
        tue(bucket1, bucket2, 1000, 1000, res);
        out.println(res.size());
       
        out.close();
    }

    // on Tue, FJ shifts milk from Barn1 --> Barn2
    static void tue(List<Integer> bucket1, List<Integer> bucket2, int b1_storage, int b2_storage, Set<Integer> res) {
        for(int i = 0; i < bucket1.size(); i++) {
        	int b = bucket1.get(i);
        	bucket1.remove(i);
        	bucket2.add(b);
        	wed(bucket1, bucket2, b1_storage - b, b2_storage + b, res);
        	
        	//restore to previous state
        	bucket2.remove(bucket2.size()-1);
        	bucket1.add(i,b);
        }
    }

    // On Wed, FJ shifts milk from Barn2 --> Barn1
    static void wed(List<Integer> bucket1, List<Integer> bucket2, int b1_storage, int b2_storage, Set<Integer> res) {
    	for(int i = 0; i < bucket2.size(); i++) {
        	int b = bucket1.get(i);
        	bucket2.remove(i);
        	bucket1.add(b);
        	thu(bucket1, bucket2, b1_storage + b, b2_storage - b, res);
        	
        	//restore to previous state
        	bucket1.remove(bucket2.size()-1);
        	bucket2.add(i,b);
        }
    }

    // On Thu, FJ shifts milk from Barn1 --> Barn2
    static void thu(List<Integer> bucket1, List<Integer> bucket2, int b1_storage, int b2_storage, Set<Integer> res) {
    	for(int i = 0; i < bucket1.size(); i++) {
        	int b = bucket1.get(i);
        	bucket1.remove(i);
        	bucket2.add(b);
        	fri(bucket1, bucket2, b1_storage - b, b2_storage + b, res);
        	
        	//restore to previous state
        	bucket2.remove(bucket2.size()-1);
        	bucket1.add(i,b);
        }
    }

    // On Fri, FJ shifts milk from Barn2 --> Barn1
    static void fri(List<Integer> bucket1, List<Integer> bucket2, int b1_storage, int b2_storage, Set<Integer> res) {
    	for (int i = 0; i < bucket2.size(); i++) {
            int b = bucket2.get(i);
            res.add(b1_storage + b);
        }
  
    }
}