package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PDFReader {

    /*
        Returns a list of pdf file paths in the given path
     */
    public static List<Path> getPathsList(String path){
        List<Path> pathsList = new ArrayList<>();

        try {
            Path start = Paths.get(path);
            try (Stream<Path> stream = Files.walk(start)) {
                stream
                        .filter(Files::isRegularFile)
                        .filter(p -> {
                            String fileName = p.toString().toLowerCase();
                            return fileName.endsWith(".pdf");
                        })
                        .forEach(pathsList::add);
            } catch (NoSuchFileException e) {
                System.err.println("Error: The path does not exist - " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } catch (InvalidPathException e) {
            System.err.println("Error: Incorrect path syntax " + e.getMessage());
        }

        return pathsList;
    }

    /*
        Returns a list of "String" objects containing the text for each page in the given document
     */
    public static List <String> getListOfPages(PDDocument document) throws IOException {

        PDFTextStripper stripper = new PDFTextStripper();

        int numberOfPages = document.getNumberOfPages();
        List <String> pages = new ArrayList<>();

        for (int i = 1; i<=numberOfPages; i++){

            stripper.setStartPage(i);
            stripper.setEndPage(i);
            pages.add(stripper.getText(document));
        }
        return pages;
    }
}
