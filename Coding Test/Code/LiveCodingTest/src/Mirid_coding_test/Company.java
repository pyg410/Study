package Mirid_coding_test;

import java.util.*;

public class Company {

    private String[] preferredApplicants;
    private int hireCount;

    private Map<String, Integer> passingApplicants;

    public Company(String[] preferredApplicants, int hireCount){
        this.preferredApplicants=preferredApplicants;
        this.hireCount=hireCount;
        this.passingApplicants=new HashMap<>();
    }

    public Map<String, Integer> getPassingApplicants() {
        return passingApplicants;
    }

    private boolean isExceedsHireCnt(){
        return hireCount<passingApplicants.size();
    }

    private Integer findApplicants(String name){
        for(int i=0; i<preferredApplicants.length; i++){
            if(preferredApplicants[i].equals(name)){
                return i;
            }
        }
        return 27;
    }

    public ApplyDTO apply(Applicant applicant){
        System.out.println("지원 회사 가능한 지원자 수 : " + this.hireCount);
        List<String> cutApp = new LinkedList<>();


        // 지원자를 저장한다.
        passingApplicants.put(applicant.getName(), findApplicants(applicant.getName()));
        // 지원자 수가 초과했는지 확인.
        while(isExceedsHireCnt()){
            System.out.println("!!!!지원자 수 초과!");
            // 선호 지원자 순위가 가장 낮은 사람을 삭제한다.
            String maxKey = Collections.max(passingApplicants.entrySet(), Map.Entry.comparingByValue()).getKey();
            passingApplicants.remove(maxKey);
            if(!Objects.equals(maxKey, applicant.getName())){
                cutApp.add(maxKey);
            }
        }

        System.out.print("지원 회사 합격자 : " );
        for(String key : passingApplicants.keySet()){
            System.out.print(key + ", ");
        }
        System.out.println();

        return new ApplyDTO(cutApp ,passingApplicants.containsKey(applicant.getName()));

    }
}
