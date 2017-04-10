package ldq.app.com.qmqpots;

/**
 * Created by Mayank on 30-03-2017.
 */

public class Details {

    int id;
    String name;
    String pots;

    public Details(String name, String pots){
        this.name = name;
        this.pots = pots;
    }

    public Details(){

    }

    public int getID(){
        return id;
    }

    public void setID(){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPots(){
        return pots;
    }

    public void setPots(){
        this.pots = pots;
    }
}
