package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon22860 {

    private static int N, M;
    private static ArrayList<Folder> folders;
    private static HashMap<String, Integer> indexFolder;
    private static Queries queries;
    private static StringBuilder output = new StringBuilder();
    private static int many;
    private static HashSet<String> diff;

    public static void main(String[] args) throws IOException {

        // input
        init();

        // main logic
        solution();

        // output
        System.out.println(output);
    }

    private static void solution() {

        for(String query : queries.queries){
            many = 0;
            String[] splitQuery = query.split("/");
            diff = new HashSet<>();
            int index = indexFolder.getOrDefault(splitQuery[splitQuery.length-1], -1);
            if(index == -1){
                continue;
            }else{
                Folder folder = folders.get(index);
                findFolder(folder);
                output.append(diff.size()).append(" ").append(many).append("\n");
            }
        }

    }

    private static void findFolder(Folder folder) {
        many += folder.files.size();
        diff.addAll(folder.files);
        if(folder.underFolders.size()!=0){
            for(Folder f : folder.underFolders){
                findFolder(f);
            }
        }
    }

    private static void init() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        folders = new ArrayList<>();
        indexFolder = new HashMap<>();
        folders.add(new Folder("main"));
        indexFolder.put("main", 0);

        for(int i =0; i<N+M; i++){
            input = br.readLine().split(" ");
            String upperNodeName = input[0];
            String nodeName = input[1];
            int nodeType = Integer.parseInt(input[2]);

            if(nodeType == 1){
                Folder f = null;

                for(int j =0; j<folders.size(); j++){
                    if(folders.get(j).folderName.equals(nodeName)){
                        f = folders.get(j);
                        break;
                    }
                }

                if(f == null){
                    f = new Folder(nodeName);
                    folders.add(f);
                    indexFolder.put(nodeName, folders.size()-1);
                }

                Folder parent = null;

                if(indexFolder.get(upperNodeName) == null){
                    parent = new Folder(upperNodeName);
                    folders.add(parent);
                    indexFolder.put(upperNodeName, folders.size()-1);
                }else{
                    int index = indexFolder.get(upperNodeName);
                    parent = folders.get(index);
                }

                parent.underFolders.add(f);
            } else if (nodeType == 0) {
                Folder parent = null;

                if(indexFolder.get(upperNodeName) == null){
                    parent = new Folder(upperNodeName);
                    folders.add(parent);
                    indexFolder.put(upperNodeName, folders.size()-1);
                }else{
                    int index = indexFolder.get(upperNodeName);
                    parent = folders.get(index);
                }
                parent.files.add(nodeName);
            }


        }

        // 쿼리 입력
        int queryNum = Integer.parseInt(br.readLine());
        queries = new Queries(new ArrayList<>());
        for(int i =0; i<queryNum; i++){
            String query = br.readLine();
            queries.queries.add(query);
        }

    }

    private static class Queries{
        List<String> queries;

        public Queries(List<String> queries){
            this.queries = queries;
        }
    }


    private static class Folder{

        String folderName;
        ArrayList<Folder> underFolders;
        ArrayList<String> files;

        public Folder(String folderName){
            this.folderName = folderName;
            this.underFolders = new ArrayList<>();
            this.files = new ArrayList<>();
        }
    }

}
