import java.util.Arrays; 
import java.util.List; 
import java.util.concurrent.locks.Lock;
import java.io.PrintWriter; 
import java.io.IOException;

class Counter {
    private long value;
    private long temp;
    private final Object lock = new Object();

    public long getAndIncrement() {
        synchronized (lock) {
            temp = value;
            value = temp + 1;
        }        
        return temp;
    }
}

class Prime extends Thread {

    private Counter counter;
    private long sum;
    private boolean [] isPrime;
    private int nprimes;

    public Prime(Counter counter, int range) {
        this.counter = counter;
        this.sum = 0;
        this.isPrime = new boolean[range + 1];
        this.nprimes=0;
        Arrays.fill(isPrime, true);
        isPrime[0] = false; 
        isPrime[1] = false;
        checkPrime(range);
    }

    private void checkPrime(int range) {
        
        int i=2, j=0;

        while ((i*i)<=range){
            if (isPrime[i]){
                for (j=i*i; j<range; j+=i){
                    isPrime[j]=false;

                }
            }
            i+=1;
        }
    }
    
    public void run() {
        long j = 0;
        int r = (int) Math.pow(10, 8);
        while (j < r) {
            j = counter.getAndIncrement();
            if (j < isPrime.length && isPrime[ (int) j]) {
                sum += j;
                nprimes+=1;
            }
        }
    }

    public long getSum() {
        return sum;
    }

    public int getN(){
        return nprimes;
    }
}

// Main Class
public class Assignment1 {
	public static void main(String[] args) throws IOException
	{
        PrintWriter output = new PrintWriter("primes.txt");

        long start = System.nanoTime();

		int n = 8;
        Counter counter = new Counter();
		int range = (int) Math.pow(10, 8);        
        Prime[] threads = new Prime[n]; 

        for (int i = 0; i < n; i++) {
            threads[i] = new Prime(counter, range);
            threads[i].start();
        }

        //final result
        long total = 0;
        for (int j = 0; j < n; j++) {
            total += threads[j].getSum();
        }

        int total_primes = 0;
        for (int z = 0; z < n; z++) {
            total_primes += threads[z].getN();
        }

        long end = System.nanoTime();

        long total_time = end-start;

        double final_time = (double) total_time/1000000000;

        output.println(final_time + " " + total_primes + " " + total);

        output.close();
	}    

}
