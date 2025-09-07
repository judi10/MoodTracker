
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;


public class Mood {

    // Attributes
    public String name;
    public String notes;
    public LocalDate date;
    public LocalTime time;


    //Constructors
    public Mood(String name){
        this.name = name;
        this.date = LocalDate.now();
        this.time = LocalTime.MIDNIGHT;

    }

    public Mood(String name, LocalDate date){
        this.name = name;
        this.date = date;
        this.time = LocalTime.MIDNIGHT;

    }

    public Mood(String name, LocalDate date, LocalTime time) {
        this.name = name;
        this.date = date;
        this.time = time;

    }

    public Mood(String name, String notes) {
        this.name = name;
        this.date = LocalDate.now();
        this.time = LocalTime.MIDNIGHT;
        this.notes = notes;

    }

    public Mood(String name, LocalDate date, String notes) {
        this.name = name;
        this.date = date;
        this.time = LocalTime.MIDNIGHT;
        this.notes = notes;

    }

    public Mood(String name, LocalDate date, LocalTime time, String notes) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.notes = notes;

    }


    //ToString
    public String toString(){
        return name + " - " + date + " " + time + "\n" +notes;
    }


    //Getter
    public String getName(){
        return name;
    }

    public LocalDate getDate(){
        return date;
    }

    public LocalTime getTime(){
        return time;
    }

    public String getNotes(){
        return notes;
    }


    //Setter
    public void setName(String name){
        this.name = name;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setTime(LocalTime time){
        this.time = time;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }


    public boolean equals(Mood mood) {
        if(mood.name.equalsIgnoreCase(this.name) &&
            mood.getDate().equals(this.date) &&
            mood.getTime().equals(this.time)) {
            return true;
        } else {
            return false;
        }
    }


    

}


