package practice.ds.collections;

import java.io.*;

import java.util.*;

/**
 * Created by kamrul on 2/27/16.
 * UVa problem # 10107 - What is the Median?
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=24&page=show_problem&problem=1048
 * https://uva.onlinejudge.org/external/101/10107.pdf
 * https://www.udebug.com/UVa/10107
 *
 * Class name must be Main for submission, change it accordingly
 */

public class Main {

    public static void main (String args[]) {
        Main problem = new Main();
        problem.solve();
    }

    /**
     * True entry point of the
     */
    
    private Scanner sc = null;
    void solve() {
        /*
        try {
            System.setIn(new FileInputStream("UVa10107_in.txt"));
            System.setOut(new PrintStream("UVa10107_out.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        
        sc = new Scanner(System.in);        
        ArrayList<Long> numbers = new ArrayList<Long>();        
        while(sc.hasNextLine()) {
            
        	long num = sc.nextLong();
        	sc.nextLine(); // Ignore
        	
            numbers.add(num);

            numbers.sort(new Comparator<Long>() {
                @Override
                public int compare(Long left, Long right) {
                    return left.compareTo(right);
                }
            });

            if(numbers.size() % 2 == 0)
            {
                System.out.println((numbers.get(numbers.size()/2 - 1) + numbers.get(numbers.size()/2))/2);
            }else {
                System.out.println(numbers.get(numbers.size()/2));
            }
        }
    }
}
