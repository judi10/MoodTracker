import java.io.BufferedReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;


public class MoodTracker {

    public static boolean isMoodValid(Mood mood, ArrayList<Mood> moodsList) throws InvalidMoodException {
        for(Mood tempMood: moodsList) {
            if (tempMood.equals(mood)) {
                throw new InvalidMoodException();
            }
        }
        return true;
    }

    public static boolean deleteMoods(LocalDate moodDate, ArrayList<Mood> moodsList) {
        boolean removed = false;
        for(Mood tempMood: moodsList) {
            if (tempMood.getDate().equals(moodDate)) {
                moodsList.remove(tempMood);
                removed = true;
            }
        }
        return removed;
    }

    public static boolean deleteMood(Mood mood, ArrayList<Mood> moodsList) {
        for(Mood tempMood: moodsList) {
            if (tempMood.equals(mood)) {
                moodsList.remove(tempMood);
                return true;
            }
        }
        return false;
    }

   

    public static boolean  editMood(Mood mood, ArrayList<Mood> moodsList, String Notes) {
        for(Mood tempMood: moodsList) {
            if (tempMood.equals(mood)) {
                tempMood.setNotes(Notes);
                return true;
            }
        }
        return false;
        
    }

    public static void searchMood(Mood mood, ArrayList<Mood> moodsList) {
        boolean found = false;

        for(Mood tempMood: moodsList) {
            if (tempMood.equals(mood)) {
                found = true;
                System.out.println(tempMood);
            }
        }
        if(!found) {
            System.out.println("No matching records could be found!");
        }
    }

    public static void searchMoods(LocalDate moodDate, ArrayList<Mood> moodsList) {
        boolean found = false;
        for(Mood tempMood: moodsList) {
            if (tempMood.getDate().equals(moodDate)) {
                found = true;
                System.out.println(tempMood);
            }
        }
        if(!found) {
            System.out.println("No matching records could be found!");
        }
    }

    public static void main(String[] args) {

        ArrayList<Mood> moods = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("Press 'a' to add mood\n" +
                                "'d' to delete mood(s)\n" +
                                "'e' to edit mood\n" +
                                "'s' to search for moods\n" +
                                "'M' to get all moods\n" +
                                "'w' to write the moods to a file\n" +
                                "Type 'Exit' to exit");

            String menuOption = scanner.nextLine();

            if(menuOption.equalsIgnoreCase("Exit")) break;

            switch(menuOption) {
                case "a": 	//add code to add mood
                    Mood moodToAdd = null;

                    System.out.println("What is the mood's name?");
                    String moodName = scanner.nextLine();
                    System.out.println("If you wanna to skip entering Date & Time press Enter\n" +
                                        "Answer by Yes or No");

                    if(scanner.nextLine().equalsIgnoreCase("Yes")){
                        System.out.println("Write notes for this mood");
                        String moodNotes = scanner.nextLine();
                        if(moodNotes.strip().equalsIgnoreCase("")) {
                            moodToAdd = new Mood(moodName);
                        } else{
                            moodToAdd = new Mood(moodName, moodNotes);
                        }
                    } 
                    else{
                        try{
                            System.out.println("Input the date in MM/dd/yyyy format:");
                            String moodDateStr = scanner.nextLine();
                            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                            LocalDate moodDate = LocalDate.parse(moodDateStr, dateFormatter);
                            System.out.println("Input the time in HH:mm:ss format:");
                            String moodTimeStr = scanner.nextLine();
                            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                            LocalTime moodTime = LocalTime.parse(moodTimeStr, timeFormatter);
                            System.out.println("Add notes about this mood");
                            String moodNotes = scanner.nextLine();
                            if(moodNotes.strip().equalsIgnoreCase("")) {
                                moodToAdd = new Mood(moodName, moodDate, moodTime);
                            } else {
                                moodToAdd = new Mood(moodName, moodDate, moodTime, moodNotes);
                            }
                        } 
                        catch (DateTimeParseException dfe) {
                            System.out.println("Incorrect format of date or time. Cannot create mood.\n"+dfe);
                            continue;
                        }
                        
                    }

                    try {
                        boolean isValid = isMoodValid(moodToAdd, moods);
                        if(isValid) {
                            moods.add(moodToAdd);
                            System.out.println("The mood has been added to the tracker");
                            continue;
                        }
                    } catch(InvalidMoodException ime) {
                        System.out.println("The mood is not valid");
                    }
                            continue;
                            
                case "d": 	//add code to delete mood
                    System.out.println("Enter '1' to delete all moods by date\n"+
                                        "Enter '2' to delete a specific mood");
                    String deleteVariant = scanner.nextLine();
                    if(deleteVariant.equals("1")) {
                        try {
                            System.out.println("Input the date in MM/dd/yyyy format:");
                            String moodDateStr = scanner.nextLine();
                            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                            LocalDate moodDate = LocalDate.parse(moodDateStr, dateFormatter);
                            boolean areMoodsDeleted = deleteMoods(moodDate, moods);
                            if(areMoodsDeleted) {
                                System.out.println("The moods have been deleted");
                            } else {
                                System.out.println("No matching moods found");
                            }
                        } catch (DateTimeParseException dfe) {
                            System.out.println("Incorrect format of date. Cannot delete mood.");
                            continue;
                        }
                    } else if (deleteVariant.equals("2")) {
                        try {
                            System.out.println("Enter the mood name");
                            moodName = scanner.nextLine();
                            System.out.println("Input the date in MM/dd/yyyy format:");
                            String moodDateStr = scanner.nextLine();
                            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                            LocalDate moodDate = LocalDate.parse(moodDateStr, dateFormatter);
                            System.out.println("Input the time in HH:mm:ss format:");
                            String moodTimeStr = scanner.nextLine();
                            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                            LocalTime moodTime = LocalTime.parse(moodTimeStr, timeFormatter);
                            Mood delMood = new Mood(moodName, moodDate, moodTime);
                            boolean isMoodDeleted = deleteMood(delMood, moods);
                            if(isMoodDeleted) {
                                System.out.println("The mood has been deleted");
                            } else {
                                System.out.println("No matching mood found");
                            }
                        } catch (DateTimeParseException dfe) {
                            System.out.println("Incorrect format of date or time. Cannot delete mood.");
                            continue;
                        }
                    }

                            continue;
                case "e": 	//add code to edit mood
                    System.out.println("You want to edit a mood\n" +
                                        "We need some information");
                    System.out.print("What is the mood name?");
                    moodName = scanner.nextLine();
                    try {
                        System.out.println("Input the date in MM/dd/yyyy format:");
                        String moodDateStr = scanner.nextLine();
                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                        LocalDate moodDate = LocalDate.parse(moodDateStr, dateFormatter);
                        System.out.println("Input the time in HH:mm:ss format:");
                        String moodTimeStr = scanner.nextLine();
                        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                        LocalTime moodTime = LocalTime.parse(moodTimeStr, timeFormatter);
                        Mood edtMood = new Mood(moodName, moodDate, moodTime);
                        
                        System.out.println("Write notes for this mood");
                        String moodNotes = scanner.nextLine();

                        boolean isMoodEdited = editMood(edtMood, moods, moodNotes);
                        if(isMoodEdited) {
                            System.out.println("Mood Edited");
                        } else {
                            System.out.println("Unable to edit Mood");
                        }

                    } catch (DateTimeParseException e) {
                        System.out.println("Incorrect format of date or time. Cannot delete mood.");
                        continue;
                    }


                            continue;
                case "s": 	//add code to search mood
                        System.out.println("Enter '1' to search for all moods by date\n"+
                                            "Enter '2' to search for a specific mood");
                        String searchVariant = scanner.nextLine();
                        if(searchVariant.equals("1")) {
                            try {
                                System.out.println("Input the date in MM/dd/yyyy format:");
                                String moodDateStr = scanner.nextLine();
                                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                                LocalDate moodDate = LocalDate.parse(moodDateStr, dateFormatter);
                                searchMoods(moodDate, moods);
                            } catch (DateTimeParseException dfe) {
                                System.out.println("Incorrect format of date. Cannot search mood.");
                                continue;
                            }
                        } else if (searchVariant.equals("2")) {
                            try {
                                System.out.println("Enter the mood name");
                                moodName = scanner.nextLine();
                                System.out.println("Input the date in MM/dd/yyyy format:");
                                String moodDateStr = scanner.nextLine();
                                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                                LocalDate moodDate = LocalDate.parse(moodDateStr, dateFormatter);
                                System.out.println("Input the time in HH:mm:ss format:");
                                String moodTimeStr = scanner.nextLine();
                                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                                LocalTime moodTime = LocalTime.parse(moodTimeStr, timeFormatter);
                                Mood delMood = new Mood(moodName, moodDate, moodTime);
                                searchMood(delMood, moods);
                            } catch (DateTimeParseException dfe) {
                                System.out.println("Incorrect format of date or time. Cannot search mood.");
                                continue;
                            }
                        }

                            continue;

                case "M": 	//add code to get all moods
                            for(Mood moodObj : moods){
                                System.out.println(moodObj);
                            }
                            continue;
                            
                case "w": 	//add code to write mood to a file
                           try{
                                FileWriter writer = new FileWriter("MoodList.txt");
                                BufferedWriter bufferedwriter = new BufferedWriter(writer);

                                for(Mood moodObj : moods){
                                    String content = moodObj.getName();
                                    content += " " + moodObj.getDate().toString();
                                    content += " " + moodObj.getTime().toString();
                                    content += " " + moodObj.getNotes();
                                    bufferedwriter.write(content);
                                    bufferedwriter.newLine();
                                }

                                bufferedwriter.close();

                            } catch(IOException e){
                                System.out.println("Error..." + e);
                            }

                            continue;

                default: 	System.out.println("Not a valid input!");
                            continue;
            }

        }

    }
}