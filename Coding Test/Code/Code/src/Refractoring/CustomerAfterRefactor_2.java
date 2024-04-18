package Refractoring;

import java.util.Enumeration;
import java.util.Vector;

public class CustomerAfterRefactor_2 {
    private String _name;
    private Vector _rental = new Vector();

    public CustomerAfterRefactor_2(String _name, Vector _rental) {
        this._name = _name;
        this._rental = _rental;
    }

    public void addRental(Rental arg){
        _rental.addElement(arg);
    }

    public String getName(){
        return _name;
    }

    public String statement(){
        Enumeration rentals = _rental.elements();
        String result = getName() + "고객님의 대여 기록\n";
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();

            // 이번에 대여하는 비디오 정보와 대여료 출력
            result += "\t" + each.getMovie().getTitle()+ "\t" + String.valueOf(each.getCharge()) + "\n";
        }

        result += "누적 대여료: " + String.valueOf(getTotalCharge()) + "\n";
        result += "적립 포인트: " + String.valueOf(getTotalFrequentRenterPoints());
        return result;
    }

    public String htmlStatement(){
        Enumeration rentals = _rental.elements();
        String result = "<H1><EM>" + getName() + " 고객님의 대여 기록</EM></H1><P>\n";
        while(rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();

            result += each.getMovie().getTitle()+ ": " + String.valueOf(each.getCharge()) + "<BR>\n";
        }

        result += "<P>누적 대여료: <EM>" + String.valueOf(getTotalCharge()) + "</EM><P>\n";
        result += "적립 포인트: <EM>" + String.valueOf(getTotalFrequentRenterPoints()) + "</EM><P>";
        return result;
    }

    private double getTotalCharge(){
        double result = 0;
        Enumeration rentals = _rental.elements();
        while(rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            result += each.getCharge();
        }
        return result;
    }

    private int getTotalFrequentRenterPoints(){
        int result =0;
        Enumeration rentals = _rental.elements();
        while(rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();
            result += each.getFrequentRenterPoints();
        }
        return result;
    }
}

