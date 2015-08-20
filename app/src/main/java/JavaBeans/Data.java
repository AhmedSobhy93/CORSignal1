package JavaBeans;

/**
 * Created by Ahmed on 6/23/2015.
 */
public class Data {

    private String day,checkIn,checkOut;


    public Data(String dayCon,String checkinCon,String checkOutCon){
        this.day=dayCon;
        this.checkIn=checkinCon;
        this.checkOut=checkOutCon;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
}
