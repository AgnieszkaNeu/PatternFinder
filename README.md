# PatternFinder
PatternFinder helps you find a word or expression you are looking for in PDF files located in the given path. Files are searched in all folders within the path.

# Requirements
- Java 8 or higher
- Maven version 3.9.9
  
# How o use
1. Clone repository
```
git clone https://github.com/AgnieszkaNeu/PatternFinder.git
```
2. Build a project
```
mvn clean install
```
3. Navigate to target file and run with arguments
```
java -jar .\SearchDocPdf-1.0-SNAPSHOT "path" "pattern"
```
