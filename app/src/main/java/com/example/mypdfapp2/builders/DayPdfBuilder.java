package com.example.mypdfapp2.builders;

import android.content.Context;

import com.example.mypdfapp2.models.ModelDay;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class DayPdfBuilder
{
    public void createPdfFile(List<ModelDay> days, Context context)
    {
        FileOutputStream fos = null;
        try
        {
            fos = context.openFileOutput("timetable1.pdf", context.MODE_PRIVATE);
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, fos);
            document.open();
            document.add(createTableTitle());
            for (ModelDay modelDay : days)
            {
                String day = modelDay.getDay();
                String date = modelDay.getDate();
                String holiday = modelDay.getHoliday();
                String service = modelDay.getService();
                String time = modelDay.getTime();
                PdfPTable table = createTable(day, date, holiday, service, time);
                document.add(table);
            }
            document.close();
        } catch (FileNotFoundException | DocumentException e)
        {
            e.printStackTrace();
        } finally
        {
            if (fos != null)
            {
                try
                {
                    fos.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private PdfPTable createTableTitle()
    {
        PdfPTable table = new PdfPTable(5);
        try
        {
            table.setWidths(new int[]{10, 15, 45, 35, 20});
        } catch (DocumentException e)
        {
            e.printStackTrace();
        }
        table.addCell(setNewCell("day"));
        table.addCell(setNewCell("date"));
        table.addCell(setNewCell("holiday"));
        table.addCell(setNewCell("service"));
        table.addCell(setNewCell("time"));
        return table;
    }

    private PdfPTable createTable(String day, String date, String holiday, String service, String time)
    {
        PdfPTable table = new PdfPTable(5);
        try
        {
            table.setWidths(new int[]{10, 15, 45, 35, 20});
        } catch (DocumentException e)
        {
            e.printStackTrace();
        }
        table.addCell(setNewCell(day));
        table.addCell(setNewCell(date));
        table.addCell(setNewCell(holiday));
        table.addCell(setNewCell(service));
        table.addCell(setNewCell(time));
        return table;
    }

    private PdfPCell setNewCell(String param)
    {
        PdfPCell cell = new PdfPCell(new Phrase(param));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingBottom(5f);
        return cell;
    }
}
