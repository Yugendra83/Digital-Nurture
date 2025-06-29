import java.util.Scanner;

public class TaskManagement {

    // Task class (node)
    static class Task {
        int taskId;
        String taskName;
        String status;
        Task next;

        Task(int taskId, String taskName, String status) {
            this.taskId = taskId;
            this.taskName = taskName;
            this.status = status;
            this.next = null;
        }

        @Override
        public String toString() {
            return taskId + " | " + taskName + " | " + status;
        }
    }

    // Singly Linked List Manager
    static class TaskManager {
        Task head = null;

        // Add task at end
        public void addTask(int id, String name, String status) {
            Task newTask = new Task(id, name, status);
            if (head == null) {
                head = newTask;
            } else {
                Task temp = head;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = newTask;
            }
            System.out.println("✅ Task added.");
        }

        // Search task by ID
        public void searchTask(int id) {
            Task temp = head;
            while (temp != null) {
                if (temp.taskId == id) {
                    System.out.println("🔍 Found: " + temp);
                    return;
                }
                temp = temp.next;
            }
            System.out.println("❌ Task not found.");
        }

        // Display all tasks
        public void displayTasks() {
            if (head == null) {
                System.out.println("📭 No tasks.");
                return;
            }
            System.out.println("📋 Task List:");
            Task temp = head;
            while (temp != null) {
                System.out.println(temp);
                temp = temp.next;
            }
        }

        // Delete task by ID
        public void deleteTask(int id) {
            if (head == null) {
                System.out.println("❌ No tasks to delete.");
                return;
            }
            if (head.taskId == id) {
                head = head.next;
                System.out.println("🗑️ Task deleted.");
                return;
            }

            Task prev = null, curr = head;
            while (curr != null && curr.taskId != id) {
                prev = curr;
                curr = curr.next;
            }

            if (curr == null) {
                System.out.println("❌ Task not found.");
            } else {
                prev.next = curr.next;
                System.out.println("🗑️ Task deleted.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        while (true) {
            System.out.println("\n===== Task Menu =====");
            System.out.println("1. Add Task");
            System.out.println("2. Search Task by ID");
            System.out.println("3. Display All Tasks");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter Task ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Task Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Status: ");
                    String status = sc.nextLine();
                    manager.addTask(id, name, status);
                    break;
                case 2:
                    System.out.print("Enter ID to search: ");
                    int sid = sc.nextInt();
                    manager.searchTask(sid);
                    break;
                case 3:
                    manager.displayTasks();
                    break;
                case 4:
                    System.out.print("Enter ID to delete: ");
                    int did = sc.nextInt();
                    manager.deleteTask(did);
                    break;
                case 5:
                    System.out.println("👋 Exiting Task System.");
                    sc.close();
                    return;
                default:
                    System.out.println("❗ Invalid choice.");
            }
        }
    }
}
