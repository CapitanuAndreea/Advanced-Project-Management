import java.sql.Connection;
import java.util.Scanner;
import service.impl.*;

public class Main {
    public static void main(String[] args) {
        DbFunctions db = new DbFunctions();
        Connection connection = db.connect_to_db("ProjectManagementDB", "postgres", "andreea");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Department");
            System.out.println("2. Employee");
            System.out.println("3. Project");
            System.out.println("4. Supply");
            System.out.println("5. Task");
            System.out.println("6. EXIT");

            System.out.println("  Choose an option (number between 1-6): ");
            int option = scanner.nextInt();

            while (option < 1 || option > 7) {
                System.out.println("  Choose a valid option (number between 1-6): ");
                option = scanner.nextInt();
            }

            if (option == 1) {
                while(true) {
                    System.out.println("1. Add a department");
                    System.out.println("2. Show all departments");
                    System.out.println("3. Edit a department");
                    System.out.println("4. Delete a department");
                    System.out.println("5. Return to menu");
                    System.out.println("  Choose a valid option (number between 1-5): ");
                    int choice = scanner.nextInt();
                    while (choice < 1 || choice > 5) {
                        System.out.println("  Choose a valid option (number between 1-5): ");
                        choice = scanner.nextInt();
                    }

                    DepartmentServiceImpl departmentServiceImpl = new DepartmentServiceImpl();
                    if (choice == 1) {
                        departmentServiceImpl.addDepartment(connection);
                    } else if (choice == 2) {
                        departmentServiceImpl.getDepartments(connection);
                    } else if (choice == 3) {
                        departmentServiceImpl.updateDepartmentByName(connection);
                    } else if (choice == 4) {
                        departmentServiceImpl.deleteDepartmentByName(connection);
                    } else {
                        break;
                    }
                }
            }

            if (option == 2) {
                while(true) {
                    System.out.println("1. Add an employee");
                    System.out.println("2. Show all employees");
                    System.out.println("3. Show an employee");
                    System.out.println("4. Edit an employee");
                    System.out.println("5. Delete an employee");
                    System.out.println("6. Show all employees from a department");
                    System.out.println("7. Return to menu");
                    System.out.println("  Choose a valid option (number between 1-7): ");
                    int choice = scanner.nextInt();
                    while (choice < 1 || choice > 7) {
                        System.out.println("  Choose a valid option (number between 1-7): ");
                        choice = scanner.nextInt();
                    }

                    EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
                    if (choice == 1) {
                        employeeServiceImpl.addEmployee(connection);
                    } else if (choice == 2) {
                        employeeServiceImpl.getEmployees(connection);
                    } else if (choice == 3) {
                        employeeServiceImpl.getEmployeeByName(connection);
                    } else if (choice == 4) {
                        employeeServiceImpl.updateEmployeeByName(connection);
                    } else if (choice == 5) {
                        employeeServiceImpl.deleteEmployeeByName(connection);
                    }else if (choice == 6) {
                        employeeServiceImpl.getEmployeeByDepartment(connection);
                    } else {
                        break;
                    }
                }
            }

            if (option == 3) {
                while(true) {
                    System.out.println("1. Add a project");
                    System.out.println("2. Show all projects");
                    System.out.println("3. Show a project");
                    System.out.println("4. Edit a project");
                    System.out.println("5. Delete a project");
                    System.out.println("6. Return to menu");
                    System.out.println("  Choose a valid option (number between 1-6): ");
                    int choice = scanner.nextInt();
                    while (choice < 1 || choice > 6) {
                        System.out.println("  Choose a valid option (number between 1-6): ");
                        choice = scanner.nextInt();
                    }

                    ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
                    if (choice == 1) {
                        projectServiceImpl.addProject(connection);
                    } else if (choice == 2) {
                        projectServiceImpl.getProjects(connection);
                    } else if (choice == 3) {
                        projectServiceImpl.getProjectByTitle(connection);
                    } else if (choice == 4) {
                        projectServiceImpl.updateProjectByTitle(connection);
                    } else if (choice == 5) {
                        projectServiceImpl.deleteProjectByTitle(connection);
                    } else {
                        break;
                    }
                }
            }

            if (option == 4) {
                while(true) {
                    System.out.println("1. Add a supply");
                    System.out.println("2. Show a supply");
                    System.out.println("3. Edit a supply");
                    System.out.println("4. Delete a supply");
                    System.out.println("5. Show supplies for a project");
                    System.out.println("6. Return to menu");
                    System.out.println("  Choose a valid option (number between 1-6): ");
                    int choice = scanner.nextInt();
                    while (choice < 1 || choice > 6) {
                        System.out.println("  Choose a valid option (number between 1-6): ");
                        choice = scanner.nextInt();
                    }

                    SupplyServiceImpl supplyServiceImpl = new SupplyServiceImpl();
                    if (choice == 1) {
                        supplyServiceImpl.addSupply(connection);
                    } else if (choice == 2) {
                        supplyServiceImpl.getSupplyByDescription(connection);
                    } else if (choice == 3) {
                        supplyServiceImpl.updateSupplyByDescription(connection);
                    } else if (choice == 4) {
                        supplyServiceImpl.deleteSupplyByDescription(connection);
                    }else if (choice == 5) {
                        supplyServiceImpl.getSuppliesByProject(connection);
                    } else {
                        break;
                    }
                }
            }

            if (option == 5) {
                while(true) {
                    System.out.println("1. Add a task");
                    System.out.println("2. Show a task");
                    System.out.println("3. Edit a task");
                    System.out.println("4. Delete a task");
                    System.out.println("5. Show tasks for an employee");
                    System.out.println("6. Show tasks for a project");
                    System.out.println("7. Return to menu");
                    System.out.println("  Choose a valid option (number between 1-7): ");
                    int choice = scanner.nextInt();
                    while (choice < 1 || choice > 7) {
                        System.out.println("  Choose a valid option (number between 1-7): ");
                        choice = scanner.nextInt();
                    }

                    TaskServiceImpl taskServiceImpl = new TaskServiceImpl();
                    if (choice == 1) {
                        taskServiceImpl.addTask(connection);
                    } else if (choice == 2) {
                        taskServiceImpl.getTaskByDescription(connection);
                    } else if (choice == 3) {
                        taskServiceImpl.updateTaskByDescription(connection);
                    } else if (choice == 4) {
                        taskServiceImpl.deleteTaskByDescription(connection);
                    } else if (choice == 5) {
                        taskServiceImpl.getTasksByEmployee(connection);
                    }else if (choice == 6) {
                        taskServiceImpl.getTasksByProject(connection);
                    }else {
                        break;
                    }
                }
            }

            if(option == 6){
                break;
            }
        }
    }
}