package ClassProjects;
import java.sql.SQLOutput;
import java.util.Scanner;

/** 1. Create Java Class
 * Start by creating a new Java class named BandOfTheHour. This class will contain all your methods and the main program logic.
 */
//=================================================================================================
public class BandOfTheHour {
//=================================================================================================

//---------------------------------------------------------------------------------------------------------------------

    /** 2. Import Necessary Packages/Declare Global Variables
     * At the beginning of your class file, import the Scanner class from the java.util package for reading inputs from the console.
     * Declare constants for the maximum array size and maximum row size, and initialize a Scanner object for input.
     */

    private static final Scanner keyboard = new Scanner(System.in);

    private static final int MAX_ARRAY_SIZE = 10;

    private static final int MAX_ROW_SIZE = 8;

//---------------------------------------------------------------------------------------------------------------------

    /** 3. Implement Main Method
     *This method will serve as the entry point for your program. It should handle the initial user prompts to set up the band array and enter the main loop for adding, removing, printing, or exiting.
     */

    public static void main(String[] args) {
        int rows;
        int index;
        int position;
        char selection;

        System.out.print("Please enter number of rows : ");
        rows= keyboard.nextInt();

        while(rows <= 0 || rows > MAX_ARRAY_SIZE){
            System.out.print("ERROR: Out of range, try again: ");
            rows= keyboard.nextInt();
        }

        /** 4. Initialize Band Array
         * Prompt the user for the number of rows and validate the input. Then, for each row, ask for the number of positions and create a jagged array representing the band's formation.
          */

        double bandarray[][] = new double[rows][];

        for (index = 0; index < rows; index++) {
            System.out.print("Please enter number of positions in row "+ (char)(index+65)+" :");
            position = keyboard.nextInt();
            while(position <= 0 || position > MAX_ROW_SIZE){
                System.out.print("ERROR: Out of range, try again: ");
//            System.out.print("Please enter number of rows : ");
                position= keyboard.nextInt();
            }
            bandarray[index] = new double[position];
        }// End of Band array

        /** 5. Implement Menu Options
         *Within the main loop, offer the user options to Add, Remove, Print, or Exit. Use a switch statement or if-else blocks to handle the selection.
         */

        while (true){
            System.out.print("(A)dd, (R)emove, (P)rint, e(X)it : ");
            selection = keyboard.next().charAt(0);

            if (selection == 'P' || selection == 'p'){
                printbandarray(bandarray, rows);
            } else if (selection =='x' || selection == 'X') {
                break;
            } else if (selection == 'a'|| selection == 'A') {
                RowStatistics(bandarray,rows);
            } else if (selection == 'r'|| selection == 'R') {
                RemoveFunction(bandarray,rows);
            } else {
                System.out.println("ERROR: Invalid option, try again: ");
            }//end of menu if statement
        }// end of while loop
        //printbandarray(bandarray,rows);
        //RowStatistics(bandarray,rows);
        //printbandarray(bandarray,rows);


    }// End of the Main Method

    /** 6. Printing Band Array
     *Loop through the band array and print each musician's weight in the formation, organized by rows and positions.
     */

    public static void printbandarray (double bandarray[][], int rows){

        int index;
        int position;

        for(index = 0; index < rows; index++){
            System.out.print((char)(index+65));
            for(position = 0; position < bandarray[index].length; position++){
                System.out.print(" :" + bandarray[index][position]);
            }//End of For Loop 1
            System.out.println();
        }//End of For Loop 2

    }// End of the printbandarray method

    /** 7. Adding a Musician
     *For the "Add" functionality, prompt for the row, position, and weight. Check if the position is available and whether adding the new weight exceeds the average weight limit for the row.
     */
    public static void RowStatistics (double bandarray[][], int rows){

        int index;
        char rowletter;
        int positionnumber;
        double weight;

        System.out.print("Please enter row letter: ");
        rowletter = keyboard.next().charAt(0);
        rowletter = Character.toUpperCase(rowletter);
        while (rowletter - 65 < 0 || rowletter - 65 > bandarray.length - 1){
            System.out.print("ERROR: Out of range, try again: ");
            rowletter = keyboard.next().charAt(0);
            rowletter = Character.toUpperCase(rowletter);
        }//End of While Loop

        System.out.print("Please enter position number (1 to "+bandarray[rowletter-65].length+") : ");
        positionnumber = keyboard.nextInt();
        while (positionnumber < 0 || positionnumber > bandarray[rowletter-65].length) {
            System.out.print("ERROR: Out of range, try again: ");
            positionnumber = keyboard.nextInt();
        }//End of While Loop

        int rownumber = Character.toUpperCase(rowletter) - 'A';

        if (bandarray[rownumber][positionnumber] != 0){
            System.out.println("ERROR: There is already a musician there.");
        }//End of error

    /** 8. Validating Inputs
    *Throughout your program, ensure you validate all user inputs (e.g., row numbers, positions, weights) to be within expected ranges. Display error messages for invalid inputs and prompt the user to try again.
     */

        System.out.print("Please enter weight (45.0 to 200.0): ");
        weight = keyboard.nextDouble();
        while (weight < 45.0 || weight > 200.0) {
            System.out.print("ERROR: Out of range, try again: ");
            weight = keyboard.nextInt();
        }//End of While Loop

        if (ExceedWeight(bandarray,rows, rownumber) + weight > bandarray[rownumber].length * 100){
            System.out.println("ERROR: That would exceed the average weight limit.");
            return;
        }


        if (bandarray[rownumber][positionnumber - 1] == 0) {
            bandarray[rownumber][positionnumber - 1] = weight;
            System.out.println("****** Musician added.");
        }else{
            System.out.print("ERROR: There is already a musician there.");
        }//end of if/else


    }//End of RowStatistics Method

    /** 8. Validating Inputs Continued...
     * Weight Function
     */
    public static double ExceedWeight (double bandarray[][], int rows, int rowon){
        int index;
        double sum = 0;
                for (index = 0; index < bandarray[rowon].length; index +=1 ){
                    sum += bandarray[rowon][index];
                }return sum;

    }// end of exceedweight method

    /** 9. Remove a Musician
     *For "Remove", ask for the row and position and then set the weight at that position to 0, effectively removing the musician.
     */

    public static void RemoveFunction (double bandarray[][], int rows){

        char rowletter;
        int positionnumber;

        System.out.print("Please enter row letter: ");
        rowletter = keyboard.next().charAt(0);
        rowletter = Character.toUpperCase(rowletter);
        while (rowletter - 65 < 0 || rowletter - 65 > bandarray.length - 1){
            System.out.print("ERROR: Out of range, try again: ");
            rowletter = keyboard.next().charAt(0);
            rowletter = Character.toUpperCase(rowletter);
        }//End of While Loop

        System.out.print("Please enter position number (1 to "+bandarray[rowletter-65].length+") : ");
        positionnumber = keyboard.nextInt();
        while (positionnumber < 0 || positionnumber > bandarray[rowletter-65].length) {
            System.out.print("ERROR: Out of range, try again: ");
            positionnumber = keyboard.nextInt();
        }//End of While Loop

        int rownumber = Character.toUpperCase(rowletter) - 'A';

        if (bandarray[rownumber][positionnumber] == 0) {
            System.out.println("ERROR: That position is vacant.");
            return;
        }//end


        bandarray[rownumber][positionnumber] = 0;
        System.out.println("****** Musician removed.");

    }//end of removefunction method

//=================================================================================================
}// End of BandOfTheHour Class
//=================================================================================================