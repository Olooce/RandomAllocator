package ac.ku.oloo;

/**
 * RandomAllocator (ac.ku.oloo)
 * Created by: oloo
 * On: 19/09/2024. 16:10
 * Description: 
 **/

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class RandomAllocator {
    public static void main(String[] args) {
        try {
            // Load the Excel file
            File excelFile = new File("/home/oloo/Documents/KU/responses.xlsx");
            FileInputStream fis = new FileInputStream(excelFile);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);

            // Read headers (assumed to be in the first row)
            Row headerRow = sheet.getRow(0);
            int numColumns = headerRow.getLastCellNum();

            // Read all rows into a list (excluding the header row)
            List<Row> rows = new ArrayList<>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                rows.add(sheet.getRow(i));
            }

            // Shuffle the rows randomly
            Collections.shuffle(rows);

            // Ask for the maximum number of people per group
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the maximum number of people per group:");
            int maxPeoplePerGroup = scanner.nextInt();

            // Calculate the number of groups needed
            int numberOfGroups = (int) Math.ceil((double) rows.size() / maxPeoplePerGroup);

            // Split the rows into groups with a maximum number of people per group
            List<List<Row>> groups = new ArrayList<>();
            for (int i = 0; i < numberOfGroups; i++) {
                groups.add(new ArrayList<>());
            }

            for (int i = 0; i < rows.size(); i++) {
                groups.get(i / maxPeoplePerGroup).add(rows.get(i));
            }

            // Output the groups to a PDF file
            String pdfFileName = "group_allocations.pdf";
            PdfWriter writer = new PdfWriter(pdfFileName);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            for (int i = 0; i < groups.size(); i++) {
                document.add(new Paragraph("Group " + (i + 1) + ":"));

                Table pdfTable = new Table(numColumns);
                pdfTable.setWidth(500); // Set table width in points (e.g., 500 points)

                // Add headers to the table
                for (int j = 0; j < numColumns; j++) {
                    String header = headerRow.getCell(j).getStringCellValue();
                    pdfTable.addCell(new Cell().add(new Paragraph(header)));
                }

                // Add rows to the table
                for (Row row : groups.get(i)) {
                    for (int j = 0; j < numColumns; j++) {
                        Cell pdfCell = new Cell().add(new Paragraph(row.getCell(j).toString()));
                        pdfTable.addCell(pdfCell);
                    }
                }

                document.add(pdfTable);
                document.add(new Paragraph("\n"));
            }

            document.close();
            workbook.close();
            System.out.println("Group allocations have been written to " + pdfFileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
