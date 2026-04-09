import java.util.Scanner;
// Scanner is a built-in Java class used to read input from the user

// Bed class - stores all details about one hospital bed
class Bed {

    int bedNumber;        // the number of the bed (example: 101)
    String wardType;      // the ward name (example: General, ICU)
    boolean isOccupied;   // true means bed is taken, false means it is free
    String patientName;   // name of the patient in this bed
    int patientId;        // ID number of the patient

    // Constructor: this runs automatically when we create a new Bed object
    // It sets up the bed with a number and ward, and marks it as free
    Bed(int bedNumber, String wardType) {
        this.bedNumber = bedNumber;
        this.wardType = wardType;
        this.isOccupied = false;   // bed is free when first added
        this.patientName = "";     // no patient yet
        this.patientId = 0;         // no patient ID yet
    }

    // displayStatus() method - prints the bed details on screen
    void displayStatus() {
        String status;
        if (isOccupied == true) {
            status = "Occupied by: " + patientName;
        } else {
            status = "Available";
        }
        System.out.println("Bed No: " + bedNumber +
                           " | Ward: " + wardType +
                           " | Status: " + status);
    }
}

// Main class - this is where the program starts
public class HospitalBedSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Array to hold up to 20 Bed objects
        Bed[] beds = new Bed[20];

        int bedCount = 0;  // counts how many beds are added
        int choice;        // stores the menu number chosen by user

        // do-while loop keeps repeating the menu until user types 6
        do {
            System.out.println("");
            System.out.println("--- Hospital Bed Allocation System ---");
            System.out.println("1. Register Bed");
            System.out.println("2. Admit Patient");
            System.out.println("3. Discharge Patient");
            System.out.println("4. View Bed Availability");
            System.out.println("5. Search Patient by ID");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            // switch checks what number the user typed
            switch (choice) {

                case 1:  // Register a new bed
                    System.out.print("Enter Bed Number: ");
                    int bedNo = sc.nextInt();
                    sc.nextLine();  // clears the Enter key from memory
                    System.out.print("Enter Ward Type (General/ICU/Maternity/Paediatric): ");
                    String ward = sc.nextLine();
                    beds[bedCount] = new Bed(bedNo, ward);  // create new Bed object
                    bedCount++;  // increase the count
                    System.out.println("Bed registered successfully.");
                    break;

                case 2:  // Admit a new patient
                    System.out.print("Enter Patient ID: ");
                    int pid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Patient Name: ");
                    String pname = sc.nextLine();
                    boolean allocated = false;
                    // Loop through all beds to find a free one
                    for (int i = 0; i < bedCount; i++) {
                        if (beds[i].isOccupied == false) {  // if bed is free
                            beds[i].isOccupied = true;       // mark as occupied
                            beds[i].patientName = pname;     // save patient name
                            beds[i].patientId = pid;          // save patient ID
                            System.out.println("Patient admitted. Bed " + beds[i].bedNumber + " assigned.");
                            allocated = true;
                            break;  // stop searching once a bed is found
                        }
                    }
                    if (allocated == false) {
                        System.out.println("Sorry, no beds are available right now.");
                    }
                    break;

                case 3:  // Discharge a patient
                    System.out.print("Enter Patient ID to discharge: ");
                    int dId = sc.nextInt();
                    boolean discharged = false;
                    for (int i = 0; i < bedCount; i++) {
                        if (beds[i].patientId == dId) {      // find the patient
                            beds[i].isOccupied = false;       // mark bed as free
                            beds[i].patientName = "";         // clear patient name
                            beds[i].patientId = 0;             // reset patient ID
                            System.out.println("Patient discharged. Bed " + beds[i].bedNumber + " is now Available.");
                            discharged = true;
                            break;
                        }
                    }
                    if (discharged == false) {
                        System.out.println("Patient ID not found.");
                    }
                    break;

                case 4:  // View all bed statuses
                    if (bedCount == 0) {
                        System.out.println("No beds have been registered yet.");
                    } else {
                        System.out.println("\nBed Availability Status:");
                        for (int i = 0; i < bedCount; i++) {
                            beds[i].displayStatus();  // call the display method
                        }
                    }
                    break;

                case 5:  // Search for a patient by their ID
                    System.out.print("Enter Patient ID to search: ");
                    int sId = sc.nextInt();
                    boolean found = false;
                    for (int i = 0; i < bedCount; i++) {
                        if (beds[i].patientId == sId) {
                            beds[i].displayStatus();
                            found = true;
                        }
                    }
                    if (found == false) {
                        System.out.println("No patient found with that ID.");
                    }
                    break;

                case 6:  // Exit the program
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:  // If user types a number not in the menu
                    System.out.println("Invalid choice. Please enter a number from 1 to 6.");
            }

        } while (choice != 6);  // loop stops when user types 6

        sc.close();  // always close Scanner when done
    }
}