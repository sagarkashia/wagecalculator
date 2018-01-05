
package org.mz.wagecalculator.service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.LogManager;
import org.mz.wagecalculator.bean.Employee;
import org.mz.wagecalculator.bean.EmployeeDailyLog;

/**
 * This class is used to generate Pdf
 */
public class PDFService {

    private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(PDFService.class.getName());
    double duration;

    /**
     *
     * @param employee
     * @param logsList
     * @param destinationFolder
     * @return result i.e. pdf file generated or not
     */
    public int generatePDF(Employee employee, List<EmployeeDailyLog> logsList, File destinationFolder) {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(destinationFolder.getAbsolutePath() + "/" + employee.getEmployeeName() + ".pdf"));
            document.open();
            Font font = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLD);
            Chunk chunk = new Chunk(employee.toString(), font);
            Paragraph heading = new Paragraph();
            heading.add(chunk);
            heading.setAlignment(Element.ALIGN_CENTER);
            document.add(heading);
            document.add(Chunk.NEWLINE);
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.addCell("Date");
            table.addCell("In-Time");
            table.addCell("Out-Time");
            table.addCell("Daily hours");
            for (int i = 0; i < logsList.size(); i++) {
                EmployeeDailyLog employeeDailyLogs = logsList.get(i);
                String val1 = employeeDailyLogs.getDate().toString();
                String val2 = " ";
                String val3 = " ";
                String val4;
                if (employeeDailyLogs.getInTime() != null) {
                    val2 = employeeDailyLogs.getInTime();
                }
                if (employeeDailyLogs.getOutTime() != null) {
                    val3 = employeeDailyLogs.getOutTime();
                }
                val4 = getTimeDifference(employeeDailyLogs.getInTime(), employeeDailyLogs.getOutTime());
                table.addCell(val1);
                table.addCell(val2);
                table.addCell(val3);
                table.addCell(val4);
                if (val1.contains("Sat") || val1.contains("Sun")) {
                    PdfPRow satAndSun = table.getRow(i + 1);
                    for (PdfPCell c : satAndSun.getCells()) {
                        c.setBackgroundColor(BaseColor.PINK);
                    }
                }
            }
            document.add(table);
            Paragraph paragraph = new Paragraph();
            paragraph.add("\nTotal hours " + String.format("%.2f", duration));
            paragraph.add("\nWages per day " + employee.getWagesPerHour());
            paragraph.add("\nTotal Amount " + String.format("%.2f", (duration * employee.getWagesPerHour())));
            document.add(paragraph);
            LOGGER.info("Pdf file generated");
            return 1;
        } catch (DocumentException | FileNotFoundException ex) {
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            document.close();
        }
        return 0;
    }

    /**
     * This method is called within generatePdf method.
     *
     * @param inTimeHour
     * @param outTimeHour
     * @return time difference
     */
    private String getTimeDifference(String inTimeHour, String outTimeHour) {
        String value=null;
        Date inTime;
        Date outTime;
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        if (outTimeHour != null && inTimeHour != null) {
            try {
                inTime = dateFormat.parse(inTimeHour);
                outTime = dateFormat.parse(outTimeHour);
                double minDifference = ((outTime.getTime() - inTime.getTime()) / 60000.00);
                if (minDifference < 0) {
                    minDifference = -(minDifference);
                }
                SimpleDateFormat sdf = new SimpleDateFormat("mm");
                Date date = null;
                date = sdf.parse(Double.toString(minDifference));
                sdf = new SimpleDateFormat("HH:mm");
                value = sdf.format(date);
                getDuration(value);
                LOGGER.info("Getting Time Difference");
            } catch (ParseException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
        return value;
    }

    /**
     * This method is called within generatePdf method. It records total
     * duration
     *
     * @param string
     */
    private void getDuration(String string) {
        String[] tokens = string.split(":");
        double hours = Double.parseDouble(tokens[0]);
        double minutes = Double.parseDouble(tokens[1]);
        duration += hours + (minutes / 60);
        LOGGER.info("Getting Duration");
    }
}
