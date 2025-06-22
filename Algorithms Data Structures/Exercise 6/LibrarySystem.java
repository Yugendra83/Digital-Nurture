import java.util.*;

public class LibrarySystem {

    // Book class
    static class Book {
        int bookId;
        String title;
        String author;

        Book(int bookId, String title, String author) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
        }

        @Override
        public String toString() {
            return bookId + " | " + title + " | " + author;
        }
    }

    // Linear search by title
    public static int linearSearch(Book[] books, String key) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].title.equalsIgnoreCase(key)) {
                return i;
            }
        }
        return -1;
    }

    // Binary search by title
    public static int binarySearch(Book[] books, String key) {
        int left = 0, right = books.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int cmp = books[mid].title.compareToIgnoreCase(key);
            if (cmp == 0)
                return mid;
            else if (cmp < 0)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    // Sort books by title
    public static void sortBooksByTitle(Book[] books) {
        Arrays.sort(books, Comparator.comparing(b -> b.title.toLowerCase()));
    }

    // Display books
    public static void displayBooks(Book[] books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Book[] books = {
            new Book(101, "The Alchemist", "Paulo Coelho"),
            new Book(102, "Clean Code", "Robert C. Martin"),
            new Book(103, "1984", "George Orwell"),
            new Book(104, "The Pragmatic Programmer", "Andrew Hunt"),
            new Book(105, "Atomic Habits", "James Clear")
        };

        System.out.println("📚 Library Catalog:");
        displayBooks(books);

        System.out.print("\n🔍 Enter book title to search: ");
        String key = sc.nextLine();

        // Linear Search
        int linResult = linearSearch(books, key);
        System.out.println("\n📘 Linear Search Result:");
        if (linResult != -1)
            System.out.println("Found: " + books[linResult]);
        else
            System.out.println("Book not found.");

        // Binary Search (sorted)
        sortBooksByTitle(books);
        int binResult = binarySearch(books, key);
        System.out.println("\n📗 Binary Search Result (after sorting):");
        if (binResult != -1)
            System.out.println("Found: " + books[binResult]);
        else
            System.out.println("Book not found.");

        sc.close();
    }
}
