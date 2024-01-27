# Assignment-1
COP 4520

  In my approach to solve this problem, I decomposed the objective into a few parts. First, we need to approach the demand of using 8 concurrent threads to solve the problems. 
In my approach, when creating the threads, I chose to initialize them through a for-loop and I stored each of the threads in an array so that the division of work/results. 
For each thread, they will have each their own individual properties as each will have access to a counter, a total sum value to store each thread’s total sum of the primes 
they have to find, a total primes value that will keep track of the amount of primes that each thread finds, and finally, each thead will have an array that will be used in 
the method used to check for primes. What will each thread do in this program? Each thread will run a while loop maintaining that it will run if the value j (which starts as 0) 
is less than the upper bound of the desired number range (10^8). Within this loop, j is replaced with a value from the shared counter between the threads, it goes on to check 
if said number is prime through the prime checking method. If the number is prime, the number is added to the total sum of primes and the total amount of primes is increased by 1. 
Now let’s see how the primes are checked for; given the array that each thread will have, the array represents whether the index of the array is a prime number. In the beginning, 
each space in the array is deemed to be prime. Now the next step is to go through the array and adjust the values as not every number from 1 to 10^8 is not prime. 
To adjust this, the process goes like this: 
-value i is equal to 2 and j is equal to 0 
-while i times i results in a value less than or equal to the upper bound of the range of numbers, it is checked if the value in the array at index i is true
-if it is true, then a for-loop is ran with j being i times i, j is increased by i each iteration and it runs while j is less than the upper bound of the range
-within this for-loop, each value of the array at index j is changed so that it indicates that the index is not a prime value
-finally within the while loop, outside of the check for whether the value at index i is true, i is increased by 1

  Now that that is done, each thread will have each an array that they will be able to use to check if the number being examined is prime. Having an already made array for each thread 
to check if the number from the counter is prime, that makes the process of checking for primes much faster, as before I had a method performing tests on each individual number that is 
to be checked. The latter process resulted in the overall execution time to be over 700 seconds. I used what is called the sieve to create the prime checking method, this resulted in 
the execution time going below 30 seconds. I encountered problems with memory at first as I used an integer array to hold the status of primeness for each index, using 0s and 1s to 
denote whether the index value is prime or not prime. Switching this to a boolean array where they hold a value of true or false solved the memory issue. Having a shared counter for 
the 8 concurrent threads also helps with the process as each thread will be able to access the counter and use it individually for their own processes. The lock in place allows the 
threads to use the counter simultaneously and not interrupt each other in this process (mutual exclusion). Overall, my main concerns regarding time were related to the prime checking 
method, as it was causing the program significant delay in completion, the run method for each thread was also of some concern as this is where i was getting some problems with 
accessing some out of bounds element from the array and this is where I called the counter and changed the value which was being examined, both of these concerns were solved. Also the 
Counter implementation was of some concern as I wanted the threads to be able to share a counter. This goal was achieved as a lock was used to divide where the value is changing so 
that the threads don’t interfere with each other’s work and so that the workload can be spread more efficiently. Finally, after all of the threads’ work is done, the sum of primes, the 
total number of primes and the execution time are calculated and printed out. 

To run this program through the command these are the steps (type these exactly in your command line):
-javac Assignment1.java
-java Assignment1

Once those two steps are done check the output in the file primes.txt.
