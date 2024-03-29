package sample;

import java.io.*;
import java.util.*;

/**
 * This is the main tester class that will implement both Job_list and Applicant_List.
 * The graphical interface developed in part 2 will substitute this class and it will
 * have user's instructions, to make the application a bit close to those in the real
 * world.
 * @author Alexandro Cipriano da Silva Filho
 * ID: u1818267
 */

public class MainMenu_TESTER {
    private static Job_List jobList;
    private static Applicant_List applicantList;
    private static final Map<String, String> myMatches = new HashMap<>();

    public static void main(String[] args) {

        //Initialize choice
        int choice = 0;

        //Initialize the Lists
        jobList = new Job_List();
        applicantList = new Applicant_List();

        //Read the Job and Applicant lists. These files can be read in a text editor such as Notepad or an IDE
        readJobList(jobList);
        readApplicantList(applicantList);

        System.out.println("Student ID: u1818267");
        System.out.println("\n************************************");
        System.out.println("   WELCOME TO AGENCY APPLICATION   ");
        System.out.println("************************************\n");

        do {
            //This the Main menu of the application
            System.out.println("-------- MAIN MENU ---------\n");
            System.out.println("1. Go to Job Section");
            System.out.println("2. Go to Applicant Section");
            System.out.println("3. Display Matches");
            System.out.println("4. Exit the Application\n");
            try {
                //Get choice
                System.out.print("Enter a choice: ");
                choice = EasyScannerPlus.nextInt();
                System.out.println();

                //Process the user choice
                switch (choice) {
                    case 1 -> option1();
                    case 2 -> option2();
                    case 3 -> option3();
                    case 4 -> {
                        System.out.println("Exiting the application...");
                        Thread.sleep(2000); //Sleep the application for 2 seconds
                        System.out.println("Thank you for testing the Agency Menu!");
                        Thread.sleep(2000); //Sleep the application for 2 seconds
                        //The files will be written and saved when the user chooses option 4
                        writeJobList(jobList);
                        writeApplicantList(applicantList);
                    }
                    // The default will be called if the choice number is not between 1 and 4
                    default -> System.out.println("Enter numbers 1 to 4 only!\n");
                }
                //Catch block is executed if the user enters a value that is not a digit
                //Also this block executes the AgencyException
            } catch (InputMismatchException | InterruptedException e) {
                System.out.println(e);
            }
        } while (choice != 4); //Execute this loop as long as the choice is not equal to 4
    }

    /**
     * The following methods are tailored for the Main Menu
     */

    //----------------------------------------- BEGINNING OF METHODS FROM MAIN MENU ---------------------------------------------------


    // Option 1 - Go to Job Section
    static void option1() {

        int choice = 0; //Initialize the choice

        //Menu
        do {
            System.out.println("\n--------- Job Menu ---------\n");
            System.out.println("\t1. Add Job");
            System.out.println("\t2. Display a job");
            System.out.println("\t3. Update job");
            System.out.println("\t4. Remove a job");
            System.out.println("\t5. Display all jobs");
            System.out.println("\t6. Go Back to Main Menu\n");

            try {
                //Get a choice
                System.out.print("Enter choice: ");
                choice = EasyScannerPlus.nextInt();
                System.out.println();

                //Process the user choice
                switch (choice) {
                    case 1 -> jobOption1();
                    case 2 -> jobOption2();
                    case 3 -> jobOption3();
                    case 4 -> jobOption4();
                    case 5 -> jobOption5();
                    case 6 -> { System.out.println("Going to back Main Menu...\n");
                    Thread.sleep(2000); //Sleep the application for 2 seconds
                    }
                    default -> System.out.println("Enter options 1 to 6 only!\n");
                }
                //Catch block is executed if the user enters a value that is not a digit
                //Also this block executes the AgencyException
            } catch (InputMismatchException | AgencyException | InterruptedException e) {
                System.out.println("\n" + e + "\n");
            }
        } while (choice != 6); //Execute this loop as long as the choice is not equal to 6
    }

    // Option 2 - Go to Applicant Section
    static void option2() {
        int choice = 0;
        do {
            //Menu
            System.out.println("\n--------- Applicant Menu ---------\n");
            System.out.println("\t1. Add applicant");
            System.out.println("\t2. Remove applicant");
            System.out.println("\t3. Update applicant");
            System.out.println("\t4. Get applicant details");
            System.out.println("\t5. Display  Applicants ");
            System.out.println("\t6. Match a job");
            System.out.println("\t7. Go back to Main Menu\n");

            try {
                //Get choice
                System.out.print("Enter a choice: ");
                choice = EasyScannerPlus.nextInt();
                System.out.println();
                //Process the user choice
                switch (choice) {
                    case 1 -> applicantOption1();
                    case 2 -> applicantOption2();
                    case 3 -> applicantOption3();
                    case 4 -> applicantOption4();
                    case 5 -> applicantOption5();
                    case 6 -> applicantOption6();
                    case 7 -> {
                        System.out.println("Going to back Main Menu...\n");
                        Thread.sleep(2000); //Sleep the application for 2 seconds
                    }
                    default -> System.out.println("Enter choices between 1 and 7 only!\n");
                }
                //Catch block that will be executed if the user enters a character instead a number
                // This catch block executes the Agency Exception
                // We catch the InterruptedException when a thread waits or sleeps, and other threads are interrupted and cannot proceed further

            } catch (InputMismatchException | AgencyException | InterruptedException e) {
                System.out.println("\n"+e +"\n");
            }

        } while (choice != 7); //Execute this loop as long as the choice is not equal to 7
    }

    //Option 3 - Output applicants that match with the jobs on the list by checking the skills and the experience
    static void option3() {

        try {
            if (myMatches.isEmpty()) {
                System.out.println("\nThere are no matches recorded!\n");
            } else {
                System.out.println("---------- Matches ----------\n");

                // For loop that returns a Collection view of the values contained in the map.
                for (String i : myMatches.keySet()) {
                    //Check if the applicant is present and the job is present
                    //Instead of output the job's ID from the map, this block outputs the job's name.
                    if (applicantList.getApplicant(i).isPresent() && jobList.getJob(myMatches.get(i)).isPresent()) {
                        System.out.println("Applicant's name: "+ applicantList.getApplicant(i).get().getName().toUpperCase()+"\n" +
                                "Job matched: "+ jobList.getJob(myMatches.get(i)).get().getJobTitle().toUpperCase()+"\n\n");
                    }
                }
            }
        } catch (NoSuchElementException e){
            System.out.println(e);
        }
    }

    //----------------------------------------- END OF METHODS FROM MAIN MENU ---------------------------------------------------

    /**
     * The following methods will be called in the method option1() - Job section
     */

    // --------------------------------------- BEGINNING OF METHODS FOR JOBS SECTION --------------------------------------------

    //Add a job
    static void jobOption1() {
        try {

            String type;
            System.out.print("Choose a ID for  the Job of length 4: ");
            String ID = EasyScannerPlus.nextString();
            System.out.print("Job Title: ");
            String jobName = EasyScannerPlus.nextString();
            System.out.print("Location of the Job vacancy: ");
            String location = EasyScannerPlus.nextString();
            System.out.println("Contract Types: ");
            System.out.println("\t1. Full-Time");
            System.out.println("\t2. Part-Time" );
            System.out.println("\t3. Placement");
            System.out.println("\t4. Internship");
            System.out.print("Type of contract: ");
            type = EasyScannerPlus.nextString();

            /*
                 While loop to make sure the method does not terminate when the user enters a value between 1 and 4 and
                 all previous information already entered must be entered from the beginning.This while may throw an
                 NumberFormatException if the number is not a digit.At the end of this method there is a catch block
                 to handle this exception.
             */
            while (Integer.parseInt(type) < 1 || Integer.parseInt(type) > 4) {
                System.out.println("No such option!Try again:");
                type = EasyScannerPlus.nextString();
            }

            //Switch statement for the type of contract
            switch (type) {
                    case "1" -> {
                        type = String.valueOf(JobTypeOfContract.FULL_TIME);
                        System.out.println("\nContract: " + JobTypeOfContract.FULL_TIME+ '\n');
                    }
                    case "2" -> {
                        type = String.valueOf(JobTypeOfContract.PART_TIME);
                        System.out.println("\nContract: " + JobTypeOfContract.PART_TIME +'\n');
                    }
                    case "3" -> {
                        type = String.valueOf(JobTypeOfContract.WORK_PLACEMENT);
                        System.out.println("\nContract: " + JobTypeOfContract.WORK_PLACEMENT +'\n');
                    }
                    case "4" -> {
                        type = String.valueOf(JobTypeOfContract.INTERNSHIP);
                        System.out.println("\nContract: " + JobTypeOfContract.INTERNSHIP +'\n');
                    }
                }

                System.out.print("What is the Main Skill required: ");
                String primarySkill = EasyScannerPlus.nextString();
                System.out.print("Job Salary £: ");
                double salary = EasyScannerPlus.nextDouble();
                if (salary < 1) {// If salary smaller than 1 then execute this code
                    System.out.println("\nSalary cannot have a negative value!\n");
                } else {
                    System.out.print("What is the minimum of years of experience for this Job: ");
                    int experience = EasyScannerPlus.nextInt();
                    if (experience < 0) { // If experience smaller than 0 then execute this code
                        System.out.println("\nExperience cannot have a negative value!\n");
                    } else {
                        if (ID.isEmpty() || jobName.isEmpty()|| location.isEmpty()|| type.isEmpty()|| primarySkill.isEmpty()) {
                            System.out.println("\nError: Make sure all field are entered!\n");
                        } else {
                            Job myJob = new Job(ID, jobName, location, JobTypeOfContract.valueOf(type), primarySkill, salary, experience);
                            System.out.println();
                            boolean check = myJob.check();
                            if (!check) { // This block is executed if the ID length is not equal to 4
                                System.out.println("Job failed! ID must have 4 and only characters.Try Again!\n");
                            } else {
                                //Attempt to add job to the Job_List
                                boolean fine = jobList.addJob(myJob);
                                if (fine) {
                                    System.out.println("Job added");
                                } else {
                                    System.out.println("Job already on the system ");
                                }
                            }
                        }
                    }
                }
            /*
             * A catch exception thrown by EasyScannerPlus to indicate that the token retrieved does not match the pattern
             * for the expected type, or that the token is out of range for the expected type.
             */
        } catch (  NumberFormatException e) {
            System.out.println(e);
        }
    }

    //Get a job
    static void jobOption2() {
        System.out.print("Enter job ID: ");
        String ID = EasyScannerPlus.nextString();
        //Retrieve the job using the Optional class
        Optional<Job> job = jobList.getJob(ID);
        System.out.println();
        if (job.isPresent()) { //Check if the job is present in the list
            System.out.println("*** Job Details ***");
            System.out.println(job.get().toString());
        } else {
            System.out.println("Job not found in the List!\n");
        }
    }

    //Update Job
    static void jobOption3() {

        String ID;
        System.out.print("Enter job ID: ");
        ID = EasyScannerPlus.nextString();
        //Retrieve the job using the Optional class
        Optional<Job> job = jobList.getJob(ID);
        System.out.println();
        if (job.isEmpty()) { //If the job is not found in the list execute this block of code
            System.out.println("This job isn't registered!\n");
        } else {
            char answer;
            int choice ;

            try {
                //Update Menu.The user chooses what part of the job has to be updated.
                System.out.print("WARNING: Updating this job may affect the matches for this job.\nProceed? (y/n) ");
                answer = EasyScannerPlus.nextChar();
                if (answer == 'y' || answer == 'Y') {
                    do {
                        System.out.println("\n\t\t1. Update Title");
                        System.out.println("\t\t2. Update Location");
                        System.out.println("\t\t3. Update Type of Contract");
                        System.out.println("\t\t4. Update Skill");
                        System.out.println("\t\t5. Update Salary");
                        System.out.println("\t\t6. Update Experience");
                        System.out.println("\t\t7. Go back to Job Menu\n");
                        System.out.print("Enter option: (1-7) ");
                        choice = EasyScannerPlus.nextInt();
                        //An enhanced switch statement
                        switch (choice) {
                            case 1 -> {
                                System.out.println("Current Title: " + jobList.getJob(ID).get().getJobTitle());
                                System.out.print("Enter new Title: ");
                                String newTitle = EasyScannerPlus.nextString();
                                String newJobTitle = jobList.getJob(ID).get().setJobTitle(newTitle);
                                System.out.println("New Title: " + newJobTitle);
                            }
                            case 2 -> {
                                System.out.println("Current Location: " + jobList.getJob(ID).get().getLocation());
                                System.out.print("Enter new Location: ");
                                String newLocation = EasyScannerPlus.nextString();
                                String newJobLocation = jobList.getJob(ID).get().setLocation(newLocation);
                                System.out.println("New Location: " + newJobLocation);
                            }
                            case 3 -> {
                                String type;
                                System.out.println("Current Contract : " + jobList.getJob(ID).get().getType());
                                System.out.println("Contract Types: ");
                                System.out.println("\t\t\t1. Full-Time");
                                System.out.println("\t\t\t2. Part-Time");
                                System.out.println("\t\t\t3. Placement");
                                System.out.println("\t\t\t4. Internship");
                                System.out.println("\t\t\t5. Keep Contract");
                                System.out.print("Enter  new type of contract: ");
                                type = EasyScannerPlus.nextString();
                                while (Integer.parseInt(type) < 1 || Integer.parseInt(type) > 5) {
                                    System.out.print("Enter values 1 to 5 only: ");
                                    type = EasyScannerPlus.nextString();
                                }

                                // An enhanced switch to update the contract of the job
                                switch (type) {
                                    case "1" -> {
                                        type = String.valueOf(JobTypeOfContract.FULL_TIME);
                                        jobList.getJob(ID).get().setType(JobTypeOfContract.valueOf(type));
                                        System.out.println("New Contract: " + JobTypeOfContract.FULL_TIME);
                                    }
                                    case "2" -> {
                                        type = String.valueOf(JobTypeOfContract.PART_TIME);
                                        jobList.getJob(ID).get().setType(JobTypeOfContract.valueOf(type));
                                        System.out.println("New Contract: " + JobTypeOfContract.PART_TIME + '\n');
                                    }
                                    case "3" -> {
                                        type = String.valueOf(JobTypeOfContract.WORK_PLACEMENT);
                                        jobList.getJob(ID).get().setType(JobTypeOfContract.valueOf(type));
                                        System.out.println("New Contract: " + JobTypeOfContract.WORK_PLACEMENT + '\n');
                                    }
                                    case "4" -> {
                                        type = String.valueOf(JobTypeOfContract.INTERNSHIP);
                                        jobList.getJob(ID).get().setType(JobTypeOfContract.valueOf(type));
                                        System.out.println("New Contract: " + JobTypeOfContract.INTERNSHIP + '\n');
                                    }
                                    case "5" -> System.out.println("Job contract kept!");
                                }

                            }
                            case 4 -> {
                                System.out.println("Current Skill: " + jobList.getJob(ID).get().getPrimarySkill());
                                System.out.print("Enter new Skill: ");
                                String newSkill = EasyScannerPlus.nextString();
                                String newJobSkill = jobList.getJob(ID).get().setPrimarySkill(newSkill);
                                System.out.println("New Type: " + newJobSkill);
                            }
                            case 5 -> {
                                System.out.println("Current Salary: " + jobList.getJob(ID).get().getSalary());
                                System.out.print("Enter new Salary: ");
                                double newSalary = EasyScannerPlus.nextDouble();
                                double newJobSalary = jobList.getJob(ID).get().setSalary(newSalary);
                                System.out.println("New Salary: " + newJobSalary);
                            }
                            case 6 -> {
                                System.out.println("Current Experience: " + jobList.getJob(ID).get().getExperience());
                                System.out.print("Enter new Experience: ");
                                int newExperience = EasyScannerPlus.nextInt();
                                int newJobExperience = jobList.getJob(ID).get().setExperience(newExperience);
                                System.out.println("New Experience: " + newJobExperience);
                            }
                            case 7 -> {
                                System.out.println("Going to back Job Menu...\n");
                                Thread.sleep(2000); // Make the application to sleep for 2 seconds
                            }
                            default -> System.out.println("Choices 1 and 7 only!");
                        }


                    } while (choice != 7);
                } else {
                    System.out.println("Going back to Job Menu...\n");
                    Thread.sleep(2000); // Make the application to sleep for 2 seconds
                }
                 /*
                  * Catch block to be executed if choice is not a digit. Also, we catch the InterruptedException
                  * when a thread waits or sleeps, and other threads are interrupted and cannot proceed further.
                  * The block also catches the user choice is not a digit.
                  */
            } catch (InputMismatchException | NumberFormatException| InterruptedException e) {
            System.out.println(e);
            }
        }
    }

    //Remove a job
    static void jobOption4() {
        System.out.print("Enter job ID: "); //Retrieve the Job ID
        String jobID = EasyScannerPlus.nextString();
        Optional<Job> job = jobList.getJob(jobID);
        if (job.isPresent()) {

            System.out.print("WARNING: Deleting this job may affect the matches with this job.\nProceed? (y/n) ");
            char choice = EasyScannerPlus.nextChar();
            //Execute this block of code if the user enters 'y' or 'Y'
            if (choice == 'y'|| choice =='Y') {
                jobList.removeJob(jobID);
                System.out.println("\nJob removed!\n");
                if (!jobList.isEmpty()) {
                    System.out.println("\nJobs left: " + jobList.getTotalOfJobs() + '\n');
                } else {
                    System.out.println("\nList is empty\n");
                }
            } else {
                System.out.println("\nJob has been kept in the recordings!\n");
            }
        } else {
            System.out.println("\nJob not found!\n");
        }
    }

    //Method to display 5 records from the list. Throws an AgencyException
    static void jobOption5() throws AgencyException {
        int fromWhere;
        System.out.print("Where would you like to start seeing the records from? ");
        fromWhere = EasyScannerPlus.nextInt();
        jobList.displayJobRecords(fromWhere);
    }

    // -------------------------------------------- END OF METHODS FOR JOBS SECTION ------------------------------------------------------



    /**
     * The following methods will be called in the method option2() - Applicant Section
     */

    //----------------------------------------- BEGINNING OF METHODS FOR APPLICANTS SECTION ---------------------------------------------------

    //Add applicant
    static void applicantOption1() {

        try {
            System.out.print("What is your email: ");
            String email = EasyScannerPlus.nextString();
            if (applicantList.getApplicant(email).isPresent()) {
                System.out.println("This email is already registered!");
            } else {
                System.out.print("What is your name:");
                String appName = EasyScannerPlus.nextString();
                System.out.print("What is your skill 1: ");
                String skill_1 = EasyScannerPlus.nextString();
                System.out.print("What is your skill_2: ");
                String skill_2 = EasyScannerPlus.nextString();
                System.out.print("How many years of experience do you have: ");
                int appExperience = EasyScannerPlus.nextInt();
                if (appExperience < 0) {
                    System.out.println("\nYour experience cannot smaller than 0");
                } else {
                    if (email.isEmpty() || appName.isEmpty() || skill_1.isEmpty() || skill_2.isEmpty() ) {
                        System.out.println("\nError: Make sure all field are entered!\n");
                    } else {
                        Applicant applicant = new Applicant(email,appName, skill_1, skill_2, appExperience);
                        boolean checkApplicant = applicant.check();// call the check method from the Applicant class
                        if (checkApplicant) { // This block is executed if the applicant name and skills does not contain digits
                            boolean fine = applicantList.addApplicant(applicant);
                            if (fine) {
                                //Attempt to add applicant to the Applicant_List
                                System.out.println("\nApplicant added!");
                            } else {
                                System.out.println("\nERROR: Applicant already registered!");
                            }
                        } else {
                            System.out.println("\nWARNING: Your name and/or skills must not have digits!");
                        }
                    }
                }
            }

            /*
             * A catch exception thrown by EasyScannerPlus to indicate that the token retrieved does not match the pattern
             * for the expected type, or that the token is out of range for the expected type.
             */
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        System.out.println();
    }

    //Remove an Applicant
    static void applicantOption2()  {

        String email;
        System.out.print("Enter your email: "); //Retrieve the applicant's email
        email = EasyScannerPlus.nextString();
        Optional<Applicant> applicant = applicantList.getApplicant(email);
        if (applicant.isPresent()) {

            System.out.print("WARNING: Deleting this applicant of the application may also be deleted from the list of matches ,\n" +
                    "if the applicant has a match. Proceed? (y/n) ");
            char choice = EasyScannerPlus.nextChar();

            //Execute this block of code if the user enters a 'y' or 'Y'
            if (choice == 'y'|| choice == 'Y') {
                applicantList.removeApplicant(email);
                System.out.println("\nApplicant removed!\n");
                if (!applicantList.isEmpty()) {
                    System.out.println("\nApplicants on the list: " + applicantList.getTotalOfApplicants() + '\n');
                } else {
                    System.out.println("\nList is empty\n");
                }
            } else {
                System.out.println("\nApplicant has been kept in the recordings!\n");
            }
        } else {
            System.out.println("\nApplicant not found!\n");
        }
    }

    //Update Applicant
    static void applicantOption3() {
        String email;
        System.out.print("Enter your email: ");
        email = EasyScannerPlus.nextString();
        //Retrieve the job using the Optional class
        Optional<Applicant> applicant = applicantList.getApplicant(email);
        System.out.println();
        if (applicant.isEmpty()) { //Check if the applicant is in the list
            System.out.println("This applicant is not registered\n");
        } else {
            char answer;
            int choice ;

            try {
                System.out.print("WARNING: Updating the applicant details may affect the matches list.\nProceed? (y/n) ");
                answer = EasyScannerPlus.nextChar();
                if (answer == 'y' || answer == 'Y') {
                    do {
                        System.out.println("\t\t1. Update Name.");
                        System.out.println("\t\t2. Update Skill 1.");
                        System.out.println("\t\t3. Update Skill 2.");
                        System.out.println("\t\t4. Update Experience.");
                        System.out.println("\t\t5. Go back to Applicant Menu.\n");
                        System.out.print("Enter option: (1-5) ");
                        choice = EasyScannerPlus.nextInt();

                        //An enhanced switch statement
                        switch (choice) {
                            case 1 -> {
                                System.out.println("Current Name: " + applicantList.getApplicant(email).get().getName());
                                System.out.print("Enter new Name: ");
                                String newName = EasyScannerPlus.nextString();
                                String newApplicantName = applicantList.getApplicant(email).get().setName(newName);
                                System.out.println("New Name: " + newApplicantName);
                            }
                            case 2 -> {
                                System.out.println("Current Skill 1: " + applicantList.getApplicant(email).get().getSkill_1());
                                System.out.print("Enter new Skill 1: ");
                                String newSkill1 = EasyScannerPlus.nextString();
                                String newApplicantSkill1 = applicantList.getApplicant(email).get().setSkill_1(newSkill1);
                                System.out.println("New Skill 1: " + newApplicantSkill1);
                            }
                            case 3 -> {
                                System.out.println("Current Skill 2: " + applicantList.getApplicant(email).get().getSkill_1());
                                System.out.print("Enter new Skill 2: ");
                                String newSkill2 = EasyScannerPlus.nextString();
                                String newApplicantSkill2 = applicantList.getApplicant(email).get().setSkill_1(newSkill2);
                                System.out.println("New Skill 1: " + newApplicantSkill2);
                            }
                            case 4 -> {
                                System.out.println("Current Experience: " + applicantList.getApplicant(email).get().getYourExperience());
                                System.out.print("Enter new Experience: ");
                                int experience = EasyScannerPlus.nextInt();
                                int newJobSkill = applicantList.getApplicant(email).get().setYourExperience(experience);
                                System.out.println("New Experience: " + newJobSkill);
                            }

                            case 5 -> {
                                System.out.println("Going to Applicant Menu...");
                                Thread.sleep(2000);// Make the application sleep for 2 seconds
                            }

                            default -> System.out.println("\nChoices 1 and 7 only!\n");
                        }

                    } while (choice != 5);
                } else {
                    System.out.println("Going back to Applicant Menu...\n");
                    Thread.sleep(2000); // Make the application to sleep for 2 seconds
                }
                /*
                 * Catch block to be executed if choice is not a digit. Also, we catch the InterruptedException
                 * when a thread waits or sleeps, and other threads are interrupted and cannot proceed further.
                 * The block also catches the user choice is not a digit.
                 */
            } catch (InputMismatchException | InterruptedException e) {
            System.out.println(e);
            }
        }
    }

    //Get applicant details
    static void applicantOption4() {
        System.out.print("Enter your email: ");
        String email = EasyScannerPlus.nextString();

        //Retrieve the applicant using the Optional class
        Optional<Applicant> applicant = applicantList.getApplicant(email);

        if (applicant.isPresent()) {  //Check if the applicant is present in the list
            System.out.println("*** Applicant Details ***");
            System.out.println(applicant.get().toString());
        } else {
            System.out.println("Applicant not found in the list!\n");
        }
    }

    //Method to display 5 records from the list. Throws an AgencyException
    static void applicantOption5() throws AgencyException {

        int fromWhere;
        System.out.print("Where would you like to start seeing the records? ");
        fromWhere = EasyScannerPlus.nextInt();

        applicantList.displayApplicantRecords(fromWhere);
    }

    //Match an applicant with a job
    static void applicantOption6() {

        if (jobList.isEmpty()) {
            System.out.println("Job Match not available as there are no jobs registered!");
        } else {
            System.out.print("Enter your Email: ");
            String email = EasyScannerPlus.nextString();
            Optional<Applicant> findApplicant = applicantList.getApplicant(email);

            if (findApplicant.isEmpty()) {
                System.out.println("Applicant not Found!\n");
            } else {

                //Check if the user already has a job match in the Map
                String checkApplicant = myMatches.get(email);
                if (checkApplicant == null) {
                    //For Loop to show the user the available jobs
                    System.out.println("\t\t\t\t*** Available Jobs ***");
                    for (Job job : jobList.jList) {
                        System.out.println("Job ID = " + job.getJobId() + "\t\t Job Title = " + job.getJobTitle() + "\t\tLocation = " + job.getLocation() + "\n");
                    }

                    //Get the job id from the job list shown above
                    System.out.print("Enter job ID: ");
                    String jobID = EasyScannerPlus.nextString();

                    //Check if the user enters a valid job ID from the list above
                    Optional<Job> findJob = jobList.getJob(jobID);
                    if (findJob.isEmpty()) {
                        System.out.println("Error: Check the List above to see the available jobs.\n");
                    } else {
                        // Execute this block of code if one of the applicant's skills match with the chosen job
                        if ((findApplicant.get().getSkill_1().equalsIgnoreCase(findJob.get().getPrimarySkill()) ||
                                findApplicant.get().getSkill_2().equalsIgnoreCase(findJob.get().getPrimarySkill())) &&
                                findApplicant.get().getYourExperience() >= findJob.get().getExperience()) {

                            myMatches.put(email, jobID);
                            System.out.println("Congratulations: You have the skills necessary for this job!\n");

                        } else {
                            System.out.println("\nI m sorry! This job does not match you!");
                            System.out.println("REASON: You must have the skill required for the job AND the minimum experience required!\n");
                        }
                    }
                } else {
                    System.out.println("\nERROR: You already have a job match! You can only match one job!\n");
                }
            }
        }
    }
    //----------------------------------------- END OF METHODS FOR APPLICANTS SECTION ---------------------------------------------------

    // -----------------------------------------METHODS TO WRITE AND READ THE APPLICANTS AND JOBS FILES ----------------------------------------------------
    //Method for writing the file
    public static void writeJobList(Job_List jobList) {

        //Usage of the try-with-resources to close the file safely
        try (FileWriter jobFile = new FileWriter("Jobs.txt");
             PrintWriter jobWriter = new PrintWriter(jobFile)) {

            //Write each element of the list to the file
            for (Job item : jobList.jList) {

                jobWriter.println(item.getJobId());
                jobWriter.println(item.getJobTitle());
                jobWriter.println(item.getLocation());
                jobWriter.println(item.getType());
                jobWriter.println(item.getPrimarySkill());
                jobWriter.println(item.getSalary());
                jobWriter.println(item.getExperience());

            }
            //Handle the exception thrown by the FileWriter methods
        } catch (IOException e) {
            System.out.println("There is a problem with the file!");
        }
    }

    //Method for reading the Job file
    public static void readJobList(Job_List jobList) {
        String ID, jobName, location, type, primarySkill, strSalary, strExperience;
        int experience;
        double salary;

        //Usage of the try-with-resources to close the file safely
        try (FileReader jobFile = new FileReader("Jobs.txt");
             BufferedReader jobStream = new BufferedReader(jobFile)
        ) {
            ID = jobStream.readLine(); //To read the first line of the file
            while (ID != null) {

                //Read the remaining of the first record, then all the rest of records until the end of the file
                jobName = jobStream.readLine();
                location = jobStream.readLine();
                type = jobStream.readLine();
                primarySkill = jobStream.readLine();
                strSalary = jobStream.readLine();

                //Convert the salary from String to Double
                salary = Double.parseDouble(strSalary);
                strExperience = jobStream.readLine();

                //Convert Experience from String to Integer
                experience = Integer.parseInt(strExperience);
                Job myJob = new Job(ID, jobName, location, JobTypeOfContract.valueOf(type), primarySkill, salary, experience);
                jobList.addJob(myJob);
                ID = jobStream.readLine();
            }
        }
        //Handle the exception if the file is not found
        catch (FileNotFoundException e) {
            System.out.println("Jobs.txt not found!");
        } catch (NumberFormatException e) {
            System.out.print("");
        }
        //Handle the exception thrown by the FileReader methods
        catch (IOException exception) {
            System.out.println("Problem with the file\n");
        }
        //Handle the exception the type of contract of the job. (Contract is of type 'ENUM' )
        catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
    //Method for writing the Applicant file
    static void writeApplicantList(Applicant_List applicantList) {

        //Usage of the try-with-resources to close the file safely
        try (FileWriter applicantFile = new FileWriter("Applicants.txt");
             PrintWriter applicantWriter = new PrintWriter(applicantFile)) {

            //Write each element of the list to the file
            for (Applicant item : applicantList.aList) {

                applicantWriter.println(item.getEmail());
                applicantWriter.println(item.getName());
                applicantWriter.println(item.getSkill_1());
                applicantWriter.println(item.getSkill_2());
                applicantWriter.println(item.getYourExperience());
            }

            //Handle the exception thrown by the FileWriter methods
        } catch (IOException e) {
            System.out.println("There is a problem with the file!");
        }
    }

    //Method for reading the  Applicant file
    static void readApplicantList(Applicant_List applicantList) {
        String email,name, skillOne, skillTwo;
        String stringExperience;
        int experience;

        //Usage of the try-with-resources to close the file safely
        try (FileReader applicantFile = new FileReader("Applicants.txt");
             BufferedReader applicantStream = new BufferedReader(applicantFile)) {
            email = applicantStream.readLine(); //To read the first line of the file
            while (email != null) {

                //Read the remaining of the first record, then all the rest of records until the end of the file
                name = applicantStream.readLine();
                skillOne = applicantStream.readLine();
                skillTwo = applicantStream.readLine();
                stringExperience = applicantStream.readLine();

                //Convert the salary from String to Integer
                experience = Integer.parseInt(stringExperience);
                Applicant myApplicant = new Applicant( email,name, skillOne, skillTwo, experience);
                applicantList.addApplicant(myApplicant);
                email = applicantStream.readLine();
            }
            //Handle the exception if the file is not found
        } catch (FileNotFoundException e) {
            System.out.println("Applicants.txt  not found!\n");
        } catch (NumberFormatException e) {
            System.out.print("");
        } catch (IOException e) { //Handle the exception thrown by the FileReader methods
            System.out.println("Problem with the file");
        }
    }
}
