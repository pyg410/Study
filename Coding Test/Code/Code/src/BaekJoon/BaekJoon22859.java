package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon22859 {
    private static String htmlDocs;
    private static StringBuilder output;
    private static String parsingDocs;
    public static void main(String[] args) throws IOException {

        // input
        output = new StringBuilder();
        input();

        // main logic
        solution();

        // output

    }

    private static void solution() {
        // main 파싱
        mainParsing();

        // div 파싱
        divParsing();
    }

    private static void divParsing() {
        while(true){
            if(parsingDocs.contains("<div")){
                divFirstParsing();

                pTagParsing();

                divLastParsing();
            }
            else{
                break;
            }
        }
    }

    private static void pTagParsing() {


        while(isLastDivIndex()){
            parsingDocs = parsingDocs.replaceFirst("<p>", "");

            String pTagParsingDocs = parsingDocs;
            int lastPTagIndex = pTagParsingDocs.indexOf("</p>");
            parsingDocs = parsingDocs.substring(lastPTagIndex);
            parsingDocs = parsingDocs.replaceFirst("</p>", "");


            StringBuilder pTagContent = new StringBuilder();
            for(int i =0; i<lastPTagIndex; i++){
                // 태그 지우기
                if(pTagParsingDocs.charAt(0) == '<'){
                    int index = 0;
                    while(pTagParsingDocs.charAt(0) != '>'){
                        pTagParsingDocs = pTagParsingDocs.substring(1);
                        index++;
                    }
                    pTagParsingDocs = pTagParsingDocs.substring(1);
                    lastPTagIndex -= index;
                }else{
                    pTagContent.append(pTagParsingDocs.charAt(0));
                    pTagParsingDocs = pTagParsingDocs.substring(1);
                }
            }
            // 앞 공백 지우기
            while(pTagContent.charAt(0) == ' '){
                pTagContent = new StringBuilder().append(pTagContent.substring(1));
            }
            // 뒷 공백 지우기
            while(pTagContent.charAt(pTagContent.length()-1) == ' '){
                pTagContent = new StringBuilder().append(pTagContent.substring(0, pTagContent.length()-1));
            }

            // 중간 공백 지우기
            String newPTagContent = "";
            for(int i =0; i<pTagContent.length(); i++){
                if( i == 0){
                    newPTagContent += pTagContent.charAt(i);
                }
                else{
                    if(newPTagContent.charAt(newPTagContent.length()-1) == ' ' && pTagContent.charAt(i) == ' '){
                        continue;
                    }
                    newPTagContent += pTagContent.charAt(i);
                }
            }
            output.append(newPTagContent);
        }

    }

    private static boolean isLastDivIndex() {
        return !(parsingDocs.charAt(0) == '<' && parsingDocs.charAt(1) == '/' && parsingDocs.charAt(2) == 'd' && parsingDocs.charAt(3) == 'i' && parsingDocs.charAt(4) == 'v' && parsingDocs.charAt(5) == '>');
    }

    private static void divLastParsing() {
        parsingDocs = parsingDocs.replaceFirst("</div>", "");
    }

    private static void divFirstParsing() {
        parsingDocs = parsingDocs.replaceFirst("<div title=\"", "");
        StringBuilder title = new StringBuilder();
        while(true){
            if(parsingDocs.charAt(0) == '\"') break;
            title.append(parsingDocs.charAt(0));
            parsingDocs = parsingDocs.substring(1);
        }
        output.append("title : ").append(title);
        parsingDocs = parsingDocs.substring(2);
    }

    private static void mainParsing() {
        parsingDocs = htmlDocs;

        parsingDocs = parsingDocs.replaceFirst("<main>", "");
        parsingDocs = parsingDocs.replaceFirst("</main>", "");

    }

    private static void input() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        htmlDocs = br.readLine();

    }
}
