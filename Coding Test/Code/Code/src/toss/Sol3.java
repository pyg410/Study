package toss;

public class Sol3 {
    public static void main(String[] args) {
        System.out.println(new Sol3().isSeparatorRule(",101023"));
    }

    private boolean solution(String amount){
        if(isOnlyNum(amount)){
            return true;
        }
        return isContainsNumAndSeparator(amount) && isZeroRule(amount) && isContainsNumAndSeparator(amount);

    }

    private boolean isContainsNumAndSeparator(String amount){
        for(int i=0; i<amount.length(); i++){
            char c = amount.charAt(i);
            if(c == ',' || ('0' <= c && c<='9')){
                continue;
            }
            return false;
        }
        return true;
    }

    private boolean isZeroRule(String amount){
        if(amount.length()<=1) return true;
        return amount.charAt(0) != '0';
    }

    private boolean isOnlyNum(String amount){
        for(int i=0; i<amount.length(); i++){
            if('0' > amount.charAt(i) || amount.charAt('0') > '9') return false;
        }
        return true;
    }

    private boolean isSeparatorRule(String amount){
        if(amount.length() > 3){
            String[] amountArr = amount.split("");

            int idx = amountArr.length-1;
            int zeroCnt = 0;
            while(idx>=0){
                if(idx == 0){
                    return !amountArr[idx].equals(",");
                }
                if(zeroCnt==3){
                    if(!amountArr[idx].equals(",")) return false;
                    zeroCnt=0;
                }else{
                    if('0'>amountArr[idx].charAt(0) || amountArr[idx].charAt(0) > '9') return false;
                    zeroCnt++;
                }
                idx--;
            }
            return true;

        }else{
            return !amount.contains(",");
        }
    }
}
