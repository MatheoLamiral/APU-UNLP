```Java
public class ReportGenerator{
    private String type;
    
    public ReportGenerator(String type) {
        this.type = type;
    }
    
    public void generateReport(Document document){
        if("PDF".equals(type)){
            //crear documento y configurar metadatos
            DocumentFile docFile = new DocumentFile();
            docFile.setTitle(document.getTitle());
            String authors = "";
            for (String author : document.getAuthors()) {
                authors += ", " + author;
            }
            docFile.setAuthors(authors);
            docFile.contentType("application/pdf");
            docFile.setPageSize("A4");
            
            //crear exportador y setear contenido
            PDFExporter exporter = new PDFExporter();
            byte[] content = exporter.generatePDFFile(document);
            docFile.setContent(content);
            
            //guarda el documento
            this.saveExportedFile(docFile);
        }else if("XSL".equals(type)){
            //crear documento y configurar metadatos
            DocumentFile docFile = new DocumentFile();
            docFile.setTitle(document.getTitle());
            String authors = "";
            for (String author : document.getAuthors()) {
                authors += ", " + author;
            }
            docFile.setAuthors(authors);
            docFile.contentType("application/vnd.ms-excel");
            docFile.setSheetName(document.getSubtitle());
            
            //crear exportador y setear contenido
            ExcelWriter writer = new ExcelWriter();
            byte[] content = writer.generateExcelFile(document);
            docFile.setContent(content);
            
            //guarda el documento
            this.saveExportedFile(docFile);
        }
    }
}

class ReportGeneratorTest {
    ReportGenerator generatorPDF;
    ReportGenerator generatorXLS;
    Document document;
    
    @BeforeEach
    void setUp() {
        document = new Document("Informe");
        document.addAuthor("Carlos");
        document.addAuthor("Ana");
        generatorPDF = new ReportGenerator("PDF");
        generatorXLS = new ReportGenerator("XSL");
    }
    
    @Test
    void testPDF() {
        generatorPDF.generateReport(document);
        // Aca se detallan los asserts
    }
    
    @Test
    void testXLS() {
        generatorXLS.generateReport(document);
        // Aca se detallan los asserts
    }
}
```
## Refactoring ReportGenerator
### Smell: Duplicate code (7-14/26-33)
1. Aplico **extract method**
   2. extraigo el método `DocumentFile createDocumentFile(Document document)`
   3. copio el codigo duplicado en `DocumentFile createDocumentFile(Document document)`
   4. reemplazo el código duplicado por llamadas al método `DocumentFile createDocumentFile(Document document)`
```java
   private DocumentFile createDocumentFile(Document document){
        DocumentFile docFile = new DocumentFile();
        docFile.setTitle(document.getTitle());
        String authors = "";
        for (String author : document.getAuthors()) {
             authors += ", " + author;
        }
        docFile.setAuthors(authors);
   }
   public void generateReport(Document document){
        if("PDF".equals(type)){
            DocumentFile docFile = this.createDocumentFile(document);
            docFile.contentType("application/pdf");
            docFile.setPageSize("A4");
            //crear exportador y setear contenido
            PDFExporter exporter = new PDFExporter();
            byte[] content = exporter.generatePDFFile(document);
            docFile.setContent(content);
            //guarda el documento
            this.saveExportedFile(docFile);
        }else if("XSL".equals(type)){
            DocumentFile docFile = this.createDocumentFile(document);
            docFile.contentType("application/vnd.ms-excel");
            docFile.setSheetName(document.getSubtitle());
            //crear exportador y setear contenido
            ExcelWriter writer = new ExcelWriter();
            byte[] content = writer.generateExcelFile(document);
            docFile.setContent(content);
            //guarda el documento
            this.saveExportedFile(docFile);
        }
    }
   ```
### Smell: Switch statement (6-43)
1. Aplico **Replace Type Code with subclasses** ya que requiero una estructura de herencia para poder aplicar **replace conditional with polymorphism**
   1. convierto la clase `ReportGenerator` en una clase abstracta
   2. creo dos subclases `PDFReportGenerator` y `XLSReportGenerator` que extienden de `ReportGenerator`
   3. se elimina la variable `type` de la clase `ReportGenerator`
   4. modifico el constructor de `ReportGenerator` para que quede vacio
````java
public abstract class ReportGenerator{
    public ReportGenerator(){
        
    }
   ...
}
public class PDFReportGenerator extends ReportGenerator {
    
}
public class XLSReportGenerator extends ReportGenerator {
    
}
````
2. Aplico **replace conditional with polymorphism**
   1. Aplico un **exctract method**(7-24)
      1. creo el método `generateReport(Document document)` en la clase `PDFReportGenerator`
      2. copio el códgio de la línea 7-24 en `PDFReportGenerator.generateReport(Document document)` 
      3. creo el método `generateReport(Document document)` en la clase `XLSReportGenerator`
      4. copio el códgio de la línea 26-43 en `XLSReportGenerator.generateReport(Document document)`
   3. convierto el método `generateReport(Document document)` de la clase `ReportGenerator` en un método abstracto y elimino su contenido
   ```java
   public abstract class ReportGenerator{
      private void createDocumentFile(Document document){  
           DocumentFile docFile = new DocumentFile();
           docFile.setTitle(document.getTitle());
           String authors = "";
           for (String author : document.getAuthors()) {
                authors += ", " + author;
           }
           docFile.setAuthors(authors);
      }
   
      public abstract void generateReport(Document document);
   }
   
   public class PDFReportGenerator extends ReportGenerator {
       @Override
       public void generateReport(Document document) {
           DocumentFile docFile = this.createDocumentFile(document);
           docFile.contentType("application/pdf");
           docFile.setPageSize("A4");
           //crear exportador y setear contenido
           PDFExporter exporter = new PDFExporter();
           byte[] content = exporter.generatePDFFile(document);
           docFile.setContent(content);
           //guarda el documento
           this.saveExportedFile(docFile);
       }
   }
   
    public class XLSReportGenerator extends ReportGenerator {
         @Override
         public void generateReport(Document document) {
           DocumentFile docFile = this.createDocumentFile(document);    
           docFile.contentType("application/vnd.ms-excel");
           docFile.setSheetName(document.getSubtitle());
           //crear exportador y setear contenido
           ExcelWriter writer = new ExcelWriter();
           byte[] content = writer.generateExcelFile(document);
           docFile.setContent(content);
           //guarda el documento
           this.saveExportedFile(docFile);
         }
    }
   ```
### Smell: Identifico pasos similares en las subclases en el método `generateReport(Document document)` y duplicate code
1. Aplico **Form template method**
   1. extraigo los pasos similares que varian en implementación en dos métodos en ámbas subclases
      1. uno para setear metadatos `setMetadata(DocumentFile docFile, Document document)` 
      2. otro para exportar `byte[] export(Document document)`
   2. creo dos métodos abstractos en la clase `ReportGenerator` para poder usar un template method
      1. `setMetadata(DocumentFile docFile, Document document)`
      2. `byte[] export(Document document)`
   3. convierto el método `generateReport(Document document)` de la clase `ReportGenerator` en un template method
      1. muevo la línea `DocumentFile docFile = this.createDocumentFile(document);` al inicio del método
      2. luego la llamada a `setMetadata(docFile, document);`
      3. luego la llamada a `export(document);`
      4. muevo la línea `docFile.setContent(content);`
      5. finalmente la llamada a `this.saveExportedFile(docFile);`
   4. elimino el método `reportGenerator` de las subclases 
   ```java
   public abstract class ReportGenerator{
      private void createDocumentFile(Document document){  
           DocumentFile docFile = new DocumentFile();
           docFile.setTitle(document.getTitle());
           String authors = "";
           for (String author : document.getAuthors()) {
                authors += ", " + author;
           }
           docFile.setAuthors(authors);
      }
      
      public abstract void setMetadata(DocumentFile docFile, Document document);
      public abstract byte[] export(Document document);
   
      public void generateReport(Document document){
        DocumentFile docFile = this.createDocumentFile(document);
        this.setMetadata(docFile, document);
        byte[] content = this.export(document);
        docFile.setContent(content);
        this.saveExportedFile(docFile);
      }
   }
   
   public class PDFReportGenerator extends ReportGenerator {
         @Override
         public void setMetadata(DocumentFile docFile, Document document) {
              docFile.contentType("application/pdf");
              docFile.setPageSize("A4");
         }
         
         @Override
         public byte[] createExporter(Document document) {
              PDFExporter exporter = new PDFExporter();
              byte[] content = exporter.generatePDFFile(document);
              return content;  
         }
   }
   
    public class XLSReportGenerator extends ReportGenerator {
         @Override
         public void setMetadata(DocumentFile docFile, Document document) {
              docFile.contentType("application/vnd.ms-excel");
              docFile.setSheetName(document.getSubtitle());
         }
            
         @Override
         public byte[] createExporter(Document document) {
              ExcelWriter writer = new ExcelWriter();
              byte[] content = writer.generateExcelFile(document);
              return content;
         }
    }
   ```
### Smell: Reinventando la rueda en el for y temporary field en el método `createDocumentFile(Document document)`
1. Aplico **Replace loop with pipeline**
   1. reemplazo el for por un stream
   2. uso `stream.join(", ")` para unir los autores en una sola cadena
2. Aplico **Replace temporary fiel with query**
   1. Elimino la variable temporal authors y paso directamente el stream como parametro al método `setAuthors`
```java
private void createDocumentFile(Document document){  
     DocumentFile docFile = new DocumentFile();
     docFile.setTitle(document.getTitle());
     docFile.setAuthors(document.getAuthors()
                                .stream()
                                .join(", "));
}
```
### Smell: temporary field en el método `generateReport(Document document)`
1. Aplico **Replace temporary field with query**
   1. Elimino la variable `content` y llamo al método `docFile.setContent(this.export(document));` directamente
```Java
public void generateReport(Document document){
     DocumentFile docFile = this.createDocumentFile(document);
     this.setMetadata(docFile, document);
     docFile.setContent(this.export(document));
     this.saveExportedFile(docFile);
}
```
## Código final
```java
public abstract class ReportGenerator{
   private void createDocumentFile(Document document){
      DocumentFile docFile = new DocumentFile();
      docFile.setTitle(document.getTitle());
      docFile.setAuthors(document.getAuthors()
              .stream()
              .join(", "));
   }
   
   public abstract void setMetadata(DocumentFile docFile, Document document);
   public abstract byte[] export(Document document);

   public void generateReport(Document document){
     DocumentFile docFile = this.createDocumentFile(document);
     this.setMetadata(docFile, document);
     docFile.setContent(this.export(document));
     this.saveExportedFile(docFile);
   }
}

public class PDFReportGenerator extends ReportGenerator {
      @Override
      public void setMetadata(DocumentFile docFile, Document document) {
           docFile.contentType("application/pdf");
           docFile.setPageSize("A4");
      }
      
      @Override
      public byte[] createExporter(Document document) {
           PDFExporter exporter = new PDFExporter();
           byte[] content = exporter.generatePDFFile(document);
           return content;
      }
}

 public class XLSReportGenerator extends ReportGenerator {
      @Override
      public void setMetadata(DocumentFile docFile, Document document) {
           docFile.contentType("application/vnd.ms-excel");
           docFile.setSheetName(document.getSubtitle());
      }
         
      @Override
      public byte[] createExporter(Document document) {
           ExcelWriter writer = new ExcelWriter();
           byte[] content = writer.generateExcelFile(document);
           return content;
      }
 }
   ```
