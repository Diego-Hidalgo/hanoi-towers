import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    private final static int START = 0;
    private final static int AUX = 1;
    private final static int END = 2;

    private static BufferedReader br;
    private static BufferedWriter bw;

    /**
     * main method of the class used by the java virtual machine.
     * the input cases will be located in the folder "files" in the input.txt archive
     * the output will be saved in the folder "files" in the output.txt archive
     */
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader("files/input.txt"));
        bw = new BufferedWriter(new FileWriter("files/output.txt"));
        int n = Integer.parseInt(br.readLine());
        readDiscs(n);
        br.close();
        bw.close();
    }//End main

    /**
     * Reads the amount of discs to solve using recursion and then calls the method that solves them.
     */
    public static void readDiscs(int n) throws IOException{
        if(n == 1) {
            int discs = Integer.parseInt(br.readLine());
            solveTowers(discs);
        } else {
            int discs = Integer.parseInt(br.readLine());
            solveTowers(discs);
            readDiscs(n-1);
        }//End else
    }//End readDiscs

    /**
     * Calls the main solveTowers method that solves the tower according to the amount of discs.
     */
    public static void solveTowers(int discs) throws IOException{
        int[] array = new int[3];
        array[START] = discs;
        bw.write(array[START] + " " + array[AUX] + " " + array[END] + "\n");
        bw.write(solveTowers(discs, array, START, AUX, END) + "\n");
    }//End solveTowers

    /**
     * Solves the towers according to the amount of discs with the next procedure:
     *      if the amount of discs is equal to one, then move the disc int start to end. Otherwise:
     *          solves discs-1 in the aux tower using end as an auxiliar tower
     *          moves the remaining disc in the start tower to the end tower
     *          solves discs-1 in the end tower using start as an auxiliar tower
     */
    public static String solveTowers(int discs, int[] array, int start, int aux, int end) throws IOException{
        String process = "";
        if(discs == 1) {
            --array[start];
            ++array[end];
            process += Arrays.toString(array).replace("[", "").replace(",", "").replace("]", "") + "\n";
            return process;
        } else {
            process += solveTowers(discs-1, array, start, end, aux);
            process += solveTowers(1, array, start, aux, end);
            process += solveTowers(discs-1, array, aux, start, end);
            return process;
        }//End else
    }//End solveTowers

}//End Main