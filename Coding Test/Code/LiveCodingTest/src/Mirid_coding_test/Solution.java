package Mirid_coding_test;


import java.util.*;

public class Solution {
    public String[] solution(String[] companies, String[] applicants){

        StringTokenizer st;
        Applicant[] applicantArr = new Applicant[applicants.length];
        for(int i = 0; i<applicants.length; i++){
            st = new StringTokenizer(applicants[i]);
            String name = st.nextToken();
            LinkedList<String> preferredCompanies = new LinkedList<>(Arrays.asList(st.nextToken().split("")));
            int availableCount = Integer.parseInt(st.nextToken());

            applicantArr[i] = new Applicant(name, preferredCompanies, availableCount);
        }

        Map<String, Company> companyMap = new HashMap<>();
        for(String company : companies){
            st = new StringTokenizer(company);
            companyMap.put(st.nextToken(), new Company(st.nextToken().split(""), Integer.parseInt(st.nextToken())));
        }


        int round =1 ;

        // round
        while(true){
            System.out.println("----라운드 : "+ round+ "----");
            int cnt = 0;
            List<String> cutApp = new ArrayList<>();
            for(Applicant applicant : applicantArr){
                // 1. 지원자가 있는지 판단한다.(기업수가 1이상 && 채용상태가 false 인 지원자.)
                System.out.println("지원자 이름 : " + applicant.getName());
                System.out.println("지원자 지원가능 수 : " + applicant.getAvailableCount());
                System.out.println("지원자 합격 여부 : " + applicant.getEmployedStatus());

                if(applicant.getAvailableCount()>=1 && !applicant.getEmployedStatus()){

                    // 3. 지원자가 선호하는 기업에 지원한다. && 기업은 채용인원에 맞춰 잠정 선택한다.
                    // 지원자가 가장 선호하는 기업을 찾는다.
                    System.out.println("지원하는 회사 명 : " + applicant.getPreferredCompanies().get(0));
                    Company findCompany = companyMap.get(applicant.getPreferredCompanies().get(0));
                    // 지원한다.
                    ApplyDTO dto = findCompany.apply(applicant);
                    boolean isApplySuccessful = dto.getApplyResult();
                    cutApp = dto.getCutApp();


                    // 지원한 내용 저장
                    companyMap.put(applicant.getPreferredCompanies().get(0), findCompany);




                    //지원 성공여부 저장
                    applicant.applyResult(isApplySuccessful);

                }else{
                    cnt++;
                }
                System.out.println("----------");
                if(!cutApp.isEmpty()){
                    for(Applicant applicant1: applicantArr){
                        applicant1.applyResult(false);
                        cutApp.remove(applicant1.getName());
                    }
                }

            }
            if(cnt >=applicantArr.length){
                break;
            }

            round++;
        }



        String[] answer = new String[companyMap.size()];

        // 키 + "_" + passingApplicants의 모든 키값을 문자열로 만들어 배열에 저장
        int index = 0;
        for (String key : companyMap.keySet()) {
            Company company = companyMap.get(key);
            StringBuilder sb = new StringBuilder(key);
            sb.append("_");
            Set<String> keys = company.getPassingApplicants().keySet();
            for (String applicant : keys) {
                sb.append(applicant);
            }
            answer[index++] = sb.toString();
        }

        // 키를 기준으로 오름차순 정렬
        Arrays.sort(answer);

        for (String str : answer) {
            System.out.println(str);
        }



        return answer;
    }
}
