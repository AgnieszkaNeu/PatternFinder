package org.example;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Document {
    String path;
    String name;
    String title;
    String author;
    Map<Integer, Object> indexesOnPage;

    public Document(String path, String title, String author) {
        this.path = path;
        this.title = title;
        this.author = author;
        this.indexesOnPage = new HashMap<>();
        this.name = Paths.get(path).getFileName().toString();
    }

    public Map<Integer, Object> getIndexesOnPage() {
        return indexesOnPage;
    }

    public String getName(){
        return name;
    }

    public void addIndexes(int pageNumber, Object indexes) {
        indexesOnPage.put(pageNumber, indexes);
    }

}
