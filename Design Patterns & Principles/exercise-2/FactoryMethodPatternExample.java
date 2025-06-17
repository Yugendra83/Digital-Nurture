interface Document {
    void open();
    void save();
    void close();
    String getType();
}

class WordDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Word document...");
    }

    @Override
    public void save() {
        System.out.println("Saving Word document...");
    }

    @Override
    public void close() {
        System.out.println("Closing Word document...");
    }

    @Override
    public String getType() {
        return "Word Document (.docx)";
    }
}

class PdfDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening PDF document...");
    }

    @Override
    public void save() {
        System.out.println("Saving PDF document...");
    }

    @Override
    public void close() {
        System.out.println("Closing PDF document...");
    }

    @Override
    public String getType() {
        return "PDF Document (.pdf)";
    }
}

class ExcelDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Excel document...");
    }

    @Override
    public void save() {
        System.out.println("Saving Excel document...");
    }

    @Override
    public void close() {
        System.out.println("Closing Excel document...");
    }

    @Override
    public String getType() {
        return "Excel Document (.xlsx)";
    }
}

abstract class DocumentFactory {
    public abstract Document createDocument();

    public Document processDocument() {
        Document doc = createDocument();
        doc.open();
        return doc;
    }
}

class WordDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}

class PdfDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}

class ExcelDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}

class DocumentManager {
    public static DocumentFactory getFactory(String documentType) {
        switch (documentType.toLowerCase()) {
            case "word":
                return new WordDocumentFactory();
            case "pdf":
                return new PdfDocumentFactory();
            case "excel":
                return new ExcelDocumentFactory();
            default:
                throw new IllegalArgumentException("Unknown document type: " + documentType);
        }
    }
}

public class FactoryMethodPatternExample {
    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern Demo ===\n");

        String[] documentTypes = {"word", "pdf", "excel"};

        for (String type : documentTypes) {
            System.out.println("Creating " + type.toUpperCase() + " document:");
            System.out.println("----------------------------------------");

            try {
                DocumentFactory factory = DocumentManager.getFactory(type);
                Document document = factory.processDocument();
                System.out.println("Document type: " + document.getType());
                document.save();
                document.close();
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }

            System.out.println();
        }

        System.out.println("=== Direct Factory Usage ===");

        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDoc = wordFactory.createDocument();
        System.out.println("Created: " + wordDoc.getType());

        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDoc = pdfFactory.createDocument();
        System.out.println("Created: " + pdfDoc.getType());

        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDoc = excelFactory.createDocument();
        System.out.println("Created: " + excelDoc.getType());

        System.out.println("\n=== Error Handling Demo ===");
        try {
            DocumentFactory unknownFactory = DocumentManager.getFactory("powerpoint");
        } catch (IllegalArgumentException e) {
            System.err.println("Expected error: " + e.getMessage());
        }
    }
}