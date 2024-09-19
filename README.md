### Random Allocator

#### Description:
The `Random Allocator` program is a Java application that takes a list of participants from an Excel file and randomly allocates them into groups. The size of each group is determined by the maximum number of people per group specified by the user. The groups are then outputted to a PDF file, displaying the group members in a table format.

#### Features:
- **Input:**
    - The program reads an Excel file (`.xlsx`) that contains participant data. The first row is assumed to be headers, and the subsequent rows contain participant details.

- **Randomization:**
    - The rows (excluding the header) are shuffled randomly to ensure that participants are distributed into groups without any bias.

- **Group Allocation:**
    - The user is prompted to enter the maximum number of people per group. The program then calculates the number of groups required and allocates the participants accordingly.

- **Output:**
    - The groups are written to a PDF file named `group_allocations.pdf`. Each group is displayed with its members listed in a table, along with the headers from the Excel file.

#### How to Use:
1. **Prepare the Excel File:**
    - Ensure that your Excel file is formatted with headers in the first row and participant data in subsequent rows. Save the file in `.xlsx` format.

2. **Run the Program:**
    - Execute the `RandomAllocator` Java program. When prompted, enter the maximum number of people per group.

3. **View the Output:**
    - The program will generate a PDF file named `group_allocations.pdf` in the working directory. Open the file to see the group allocations.

#### Dependencies:
- Apache POI: For reading Excel files.
- iText 7: For generating PDF files.

#### Example:
If you have 20 participants and specify a maximum group size of 5, the program will create 4 groups, each containing 5 participants.

#### Notes:
- Ensure that the specified file paths are correct.
- The program currently outputs the groups only in PDF format.

This program is ideal for scenarios where you need to organize participants into groups randomly, such as classroom activities, workshops, or team-building exercises.