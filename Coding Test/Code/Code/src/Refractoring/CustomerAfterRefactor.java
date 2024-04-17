package Refractoring;

import java.util.Enumeration;
import java.util.Vector;

public class CustomerAfterRefactor {
    private String _name;
    private Vector _rental = new Vector();

    public CustomerAfterRefactor(String _name, Vector _rental) {
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
        double totalAmount=0;
        int frequentRenterPoints=0;
        Enumeration rentals = _rental.elements();
        String result = getName() + "고객님의 대여 기록\n";
        while (rentals.hasMoreElements()){
            Rental each = (Rental) rentals.nextElement();

            // 비디오 종류별 대여료 계산

            // 적립 포인트를 1 포인트 증가
            frequentRenterPoints++;
            // 최신물을 이틀 이상 대여하면 보너스 포인트 지급
            if((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
                    each.getDaysRented() > 1){
                frequentRenterPoints++;
            }
            // 이번에 대여하는 비디오 정보와 대여료 출력
            result += "\t" + each.getMovie().getTitle()+ "\t" + String.valueOf(each.getCharge()) + "\n";
            // 현재까지 누적된 총 대여료
            totalAmount += each.getCharge();
        }

        result += "누적 대여료: " + String.valueOf(totalAmount) + "\n";
        result += "적립 포인트: " + String.valueOf(frequentRenterPoints);
        return result;
    }

    private double amountFor(Rental aRental) {
        return aRental.getCharge();
    }
}