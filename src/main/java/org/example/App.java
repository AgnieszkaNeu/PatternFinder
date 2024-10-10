package org.example;

import java.util.List;
import java.util.Map;

public class App
{
    public static void main( String[] args ){

        if(args.length > 1) {
            String path = args[0];
            String pattern = args[1];

            PatternFinder patternFinder = new PatternFinder(path, pattern);
            List<Document> documents = patternFinder.getDocuments();

            printDocuments(documents, pattern);
        }
        else{
            System.out.println("Insufficient number of arguments");
        }
    }

    public static void printDocuments(List<Document> documents, String pattern){

        if(documents.isEmpty()){
            System.out.println("No pattern found.");
        }
        else {
            for (Document document : documents) {
                System.out.println("Pattern: \"" + pattern + "\" founded in file \"" + document.getName() + "\"");
                for (Map.Entry<Integer, Object> indexesList : document.getIndexesOnPage().entrySet()) {
                    System.out.println("Page number: " + indexesList.getKey() + ", at indexes: " + indexesList.getValue());
                }
                System.out.println();
            }
        }
    }
}
