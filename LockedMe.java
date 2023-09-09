package project1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class LockedMe {
    private static final String ROOT_DIRECTORY = "E:\\ruvel" ; // Replace with your desired root directory
    private static List<String> directoryList = new ArrayList<>();
    public static boolean keeplooping = true;

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("WELCOME");
        System.out.println("The name of the application is LockedMe and it is developed by Niranjan Desai ");
        System.out.println("this application is made as a demo prototype , it has total 3 options \n"
        		+ "of which the 1st option one is to \"list all the files which is asked and that to in ascending order\" ,\n "
        		+ "2nd option is \"user interface\" which again has total 4 choices in them:-\n"
        		+ " a.to add a file in existing file or directory \n"
        		+ " b.Delete a user specified file from the existing directory list \n"
        		+ " c. Search a user specified file from the main directory and \n"
        		+ " d. to exit from user interface \n"
        		+ "3rd option is to exit the main  program  \n ");
        addFileToList(ROOT_DIRECTORY);
        
        	while(true) {
        	System.out.println("\n");
        System.out.println("Options:");
        System.out.println("1. List files in ascending order");
        System.out.println("2. User interface");
        System.out.println("3. Exit");
         System.out.println("\n");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        //scanner.nextLine(); // Consume the newline character after reading the choice

        if (choice == 1) {
            listFilesInAscendingOrder(ROOT_DIRECTORY);
        } else if (choice == 2) {
        	keeplooping = true;
        	while (keeplooping) {
        		userInterface(scanner);
        	}
        } else if (choice == 3) {
        	break ;
        } else {
            System.out.println("Invalid choice");
        }}

        scanner.close();
    }

    private static void addFileToList(String directoryPath) {
    	File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            String[] fileNames = directory.list();
            for (String s: fileNames) {           
            	directoryList.add(s); 
            }

        } else {
            System.out.println("Directory not found");
        }
    }
    
    private static void listFilesInAscendingOrder(String directoryPath) {
    	
    	File directory = new File(directoryPath);
        
        if (!directoryList.isEmpty()) {
            directoryList.sort(String::compareToIgnoreCase);

            System.out.println("Files in ascending order:");
            for (String fileName : directoryList) {
                System.out.println(fileName);
            }
        } else {
            System.out.println("Directory is empty");
        }
    }

    private static void userInterface(Scanner scanner) {
    	System.out.println("Options:");
        System.out.println("1. Add a file to the existing directory list");
        System.out.println("2. Delete a user specified file from the existing directory list");
        System.out.println("3. Search a user specified file from the main directory");
        System.out.println("4. Exit");


        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        //scanner.nextLine(); // Consume the newline character after reading the choice

        switch (choice) {
            case 1:
                System.out.print("Enter the file name to add: ");
                String fileNameToAdd = scanner.nextLine();
                addFile(fileNameToAdd);
                break;
            case 2:
                System.out.print("Enter the file name to delete: ");
                String fileNameToDelete = scanner.nextLine();
                deleteFile(fileNameToDelete);
                break;
            case 3:
                System.out.print("Enter the file name to search: ");
                String fileNameToSearch = scanner.nextLine();
                searchFile(fileNameToSearch);
                break;
            case 4:
	           	 System.out.print("exit: ");
	           	 keeplooping = false;
	           	 break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    private static void addFile(String fileName) {
        if (directoryList.contains(fileName.toLowerCase())) {
            System.out.println("File already exists");
        } else {
            directoryList.add(fileName.toLowerCase());
            File file = new File(ROOT_DIRECTORY, fileName);

            try {
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("File added successfully");
                } else {
                    System.out.println("File already exists");
                }
            } catch (IOException e) {
                System.out.println("Error occurred while adding the file: " + e.getMessage());
            }
        }
    }

    private static void deleteFile(String fileName) {
        if (directoryList.remove(fileName.toLowerCase())) {
            File file = new File(ROOT_DIRECTORY, fileName);

            if (file.exists() && file.isFile()) {
                boolean deleted = file.delete();
                if (deleted) {
                    System.out.println("File deleted successfully");
                } else {
                    System.out.println("Unable to delete the file");
                }
            } else {
                System.out.println("File not found");
            }
        } else {
            System.out.println("File not found");
        }
    }

    private static void searchFile(String fileName) {
        if (directoryList.contains(fileName.toLowerCase())) {
            File file = new File(ROOT_DIRECTORY, fileName);

            if (file.exists() && file.isFile()) {
                System.out.println("File found");
            } else {
                System.out.println("File not found");
            }
        } else {
            System.out.println("File not found");
        }
    }
}
