package com.example.mypdfapp2.builders;

import android.content.Context;

import com.example.mypdfapp2.models.ModelDay;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class TimetablePdfBuilder1
{
    public static final String DAY = "day";
    public static final String DATE = "date";
    public static final String HOLIDAY = "holiday";
    public static final String SERVICE = "service";
    public static final String TIME = "time";
    public static final String FIRST_ROW = "ПАТРИАРШЕЕ ПОДВОРЬЕ";
    public static final String SECOND_ROW = "ХРАМ СВЯТОГО БЛАГОВЕРНОГО КНЯЗЯ АЛЕКСАНДРА НЕВСКОГО";
    public static final String THIRD_ROW = "ПРИ БЫВШЕМ КОМИССАРОВСКОМ УЧИЛИЩЕ Г. МОСКВЫ";
    public static final String FOURTH_ROW = "РАСПИСАНИЕ БОГОСЛУЖЕНИЙ НА";
    private Font font;

    public void createPdfFile(String newMonth, List<ModelDay> days, Context context)
    {
        FileOutputStream fos = null;
        try
        {
            fos = context.openFileOutput(newMonth, context.MODE_PRIVATE);
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, fos);
            document.open();
            font = new Font(BaseFont.createFont("assets/roboto_reg.ttf", "Cp1251", BaseFont.EMBEDDED), 12f, Font.NORMAL, BaseColor.BLACK);
            document.add(createFileTitle(newMonth));
            document.add(createTable(DAY, DATE, HOLIDAY, SERVICE, TIME));
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
        } catch (IOException e)
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
        PdfPCell cell = new PdfPCell(new Phrase(param, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingBottom(5f);
        return cell;
    }

    private Paragraph createFileTitle(String newMonth)
    {
        Paragraph fileTitle = new Paragraph();
        fileTitle.add(new Phrase(TimetablePdfBuilder1.FIRST_ROW, font));
        fileTitle.add(new Phrase(TimetablePdfBuilder1.SECOND_ROW, font));
        fileTitle.add(new Phrase(TimetablePdfBuilder1.THIRD_ROW, font));
        fileTitle.add(new Phrase(TimetablePdfBuilder1.FOURTH_ROW + " " + newMonth, font));
        return fileTitle;
    }
}
