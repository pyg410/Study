package Mirid_coding_test;

import java.util.List;

public class ApplyDTO {
    private List<String> cutApp;
    private boolean applyResult;

    public ApplyDTO(List<String> cutApp, boolean applyResult){
        this.cutApp=cutApp;
        this.applyResult=applyResult;
    }

    public List<String> getCutApp() {
        return cutApp;
    }
    public boolean getApplyResult(){
        return applyResult;
    }
}
