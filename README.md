

This is an application for managing teachers for the school administration, with a graphical interface that allows the management of teachers and subjects. The data is stored in a database through an active connection. The main functions are: 

• Inserting new records (teachers and subjects). 

• Searching and viewing existing teachers.

• Updating teacher details. 

• Deleting teacher records.

The application was developed in Java using JDK 23.0.1 for development, combined with the Eclipse IDE. For the GUI, the WindowBuilder plugin was implemented, which generates automatic code and allows simultaneous viewing of the design and the source code of each window. The database was developed with MySQL Workbench 8.0.41 and MySQL Server of the same version for sending queries and retrieving information.


Skills:

• Java Object-Oriented Programming (OOP): break the application into clear, independent pieces (classes, interfaces).

• Function categorization: separation of UI, business logic, and data access.

• Inheritance: creating common methods for code reuse.

• Encapsulation: hiding database logic behind CRUD methods. 

• Polymorphism: using a common interface for different types of entities (e.g., teachers/courses). Event-Driven Programming User actions (clicking buttons, menu selections) lead to the execution of specific methods. 

• ActionListeners in Swing components for immediate response to clicks.

• Binding callbacks to Insert, Update, Delete buttons. 

• Coordinating UI thread so that database operations do not block the display.

• Exception handling in runtime events (e.g., invalid input data) GUI Development with Swing and WindowBuilder designing the graphical interface has become quick and coherent.

• Use of WindowBuilder for drag-and-drop placement of components.

• Custom JPanel, JFrame, JTable for displaying lists of teachers.

• Layout Managers (BorderLayout, GridBagLayout) for flexible layout.

• Styling: settings for fonts, colors, and icons for a user-friendly environment. Database & JDBC Data storage and retrieval were done through MySQL with network connection. 

• Database schema design with tables for teachers and courses.

• SQL statements: SELECT, INSERT, UPDATE, DELETE with parameters.

• JDBC API for opening/closing Connection, PreparedStatement, ResultSet.

• Proper resource management (calling close() in try-with-resources) CRUD Operations The basic operations of creating, reading, updating, and deleting were fully implemented.

• Insert: validation checks before insertion.

• Read: searching with filters (e.g., name, department) and displaying in a table.

• Update: editing form with pre-filled fields and saving changes.

• Delete: user confirmation before deleting UML & Modeling record. Good design starts with requirements analysis and models. 

• Use case diagrams for mapping use scenarios.

• Class diagrams for capturing relationships (aggregation, association).

• Sequence diagrams for data flow between UI and database.

• Documentation with PlantUML or handwritten sketches. Data Structures & Algorithms Despite their simplicity, proper choice of structures was needed. 

• Use of ArrayList/HashMap for temporary storage of records.

• Simple filter search algorithms (linear search).

• Optimization of SQL queries to reduce latency.

• Management of exceptions and edge-cases (empty result sets).
