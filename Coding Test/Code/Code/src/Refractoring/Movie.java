package Refractoring;

public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE =1;

    private String _title;
    private Price _price;

    public Movie(String title, int priceCode){
        _title=title;
        setPriceCode(priceCode);
    }

    public String getTitle() {
        return _title;
    }

    public int getPriceCode() {
        return _price.getPriceCode();
    }

    public void setPriceCode(int arg) {
        switch (arg){
            case Movie.REGULAR:
                _price = new RegularPrice();
                break;
            case Movie.NEW_RELEASE:
                _price = new NewReleasePrice();
                break;
            case Movie.CHILDRENS:
                _price = new ChildrenPrice();
                break;
            default:
                throw new IllegalArgumentException("가격 코드가 잘못되었습니다.");
        }
    }

    double getCharge(int daysRented) {
        double result = 0;
        switch (getPriceCode()){
            case Movie.REGULAR:
                result += 2;
                if(daysRented > 2)
                    result += (daysRented - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                result += daysRented * 3;
                break;
            case Movie.CHILDRENS:
                result += 1.5;
                if(daysRented > 3)
                    result += (daysRented -3) * 1.5;
                break;
        }
        return result;
    }

    public int getFrequentRenterPoints(int dayRented){
        // 최신물을 이틀 이상 대여하면 보너스 포인트 지급
        if((getPriceCode() == Movie.NEW_RELEASE) &&
                dayRented > 1){
            return 2;
        }
        return 1;
    }
}
