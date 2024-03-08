package Mirid_coding_test;

import java.util.LinkedList;
import java.util.List;

public class Applicant {

    private String name;
    private LinkedList<String> preferredCompanies;
    private int availableCount;
    private boolean employedStatus;

    public Applicant(String name, LinkedList<String> preferredCompanies, int availableCount){
        this.name = name;
        this.preferredCompanies = preferredCompanies;
        this.availableCount = availableCount;
        this.employedStatus=false;
    }
    public void applyResult(boolean isApplySuccessful){
        if(isApplySuccessful){
            applySuccess();
        }else{
            applyFail();
        }
    }
    private void applySuccess(){
        availableCountMinus();
        setEmployedStatus(true);
    }

    private void applyFail(){
        availableCountMinus();
        setEmployedStatus(false);
        // 거절당한 인원들의 선호기업 삭제.
        removeFirstCompany();
    }

    public String getName() {
        return name;
    }
    public List<String> getPreferredCompanies(){
        return preferredCompanies;
    }

    public int getAvailableCount(){
        return availableCount;
    }

    public boolean getEmployedStatus() {
        return employedStatus;
    }

    private void availableCountMinus() {
        this.availableCount--;
    }

    public void removeFirstCompany() {
        if(!preferredCompanies.isEmpty()){
            this.preferredCompanies.removeFirst();
        }

    }

    private void setEmployedStatus(boolean employedStatus) {
        this.employedStatus = employedStatus;
    }
}
