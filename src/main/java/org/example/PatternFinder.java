package org.example;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class PatternFinder {

    String path;
    String pattern;

    public PatternFinder(String path, String pattern){
        if (pattern == null || pattern.length() < 3) {
            throw new IllegalArgumentException("Pattern must be at least 3 characters long.");
        }
        this.pattern = pattern.toLowerCase();
        this.path = path;
    }

    /*
        Searches for PDF documents in the given path and looks for a pattern in each found document.
        Returns a list of documents in which the pattern was found
     */
    public List <Document> getDocuments() {

        List <Document> documents = new ArrayList<>();
        List<Path> pathsList = PDFReader.getPathsList(path);

        for (Path nestedPath: pathsList){

            try (PDDocument pdDocument = Loader.loadPDF(new File(String.valueOf(nestedPath)))) {

                if(pdDocument.isEncrypted()) continue;

                Document document = examineDocument(pdDocument, String.valueOf(nestedPath));
                if (!document.getIndexesOnPage().isEmpty()){
                    documents.add(document);
                }

            } catch (IOException e) {
                System.err.println("Error loading PDF: " + e.getMessage());
            }
        }
        return documents;
    }

    /*
        Examine given document and search for pattern
     */
    private Document examineDocument(PDDocument pdDocument, String nestedPath) throws IOException {

        PDDocumentInformation information = pdDocument.getDocumentInformation();
        Document document = new Document(nestedPath,information.getTitle(),information.getAuthor());

        Queue<Integer> indexes;
        List <String> pages;

        pages = PDFReader.getListOfPages(pdDocument);
        for (String page: pages){
            indexes = PatternSearch.search(page.toLowerCase(),pattern);
            if (!indexes.isEmpty()){
                document.addIndexes(pages.indexOf(page)+1, indexes);
            }
        }
        return document;
    }
}
