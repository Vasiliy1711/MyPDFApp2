package com.example.mypdfapp2.builders;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mypdfapp2.R;
import com.itextpdf.text.Font;

import com.example.mypdfapp2.models.ModelDay;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class TimetablePdfBuilder
{
    private Font font;
    private FileOutputStream fos = null;
    private String pdfPath;

    public void createPdfFile(String newMonth, List<ModelDay> days, Context context)
    {
        setFont();
        saveToExternalStorage(newMonth, days, context);
    }

    private void setFont()
    {
        try
        {
            font = new Font(BaseFont.createFont("assets/segreg.ttf", "Cp1251"
                    , BaseFont.EMBEDDED), 12f, Font.NORMAL, BaseColor.BLACK);

        } catch (DocumentException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private PdfPTable createTable(List<ModelDay> days, Context context)
    {
        PdfPTable table = new PdfPTable(5);
        try
        {
            table.setWidths(new int[]{10, 15, 50, 40, 15});
            table.setSpacingBefore(10f);
            for (PdfPCell cell : setColumnCells(context))
            {
                table.addCell(cell);
            }
            for (ModelDay day : days)
            {
                table.addCell(setNewCell(day.getDay()));
                table.addCell(setNewCell(day.getDate()));
                table.addCell(setNewCell(day.getHoliday()));
                table.addCell(setNewCell(day.getService()));
                table.addCell(setNewCell(day.getTime()));
            }

        } catch (DocumentException e)
        {
            e.printStackTrace();
        }

        return table;
    }

    private List<PdfPCell> setColumnCells(Context context)
    {
        List<PdfPCell> columnCells = new ArrayList<>();
        columnCells.add(setNewCell(context.getString(R.string.day)));
        columnCells.add(setNewCell(context.getString(R.string.date)));
        columnCells.add(setNewCell(context.getString(R.string.holiday)));
        columnCells.add(setNewCell(context.getString(R.string.service)));
        columnCells.add(setNewCell(context.getString(R.string.time)));
        return columnCells;
    }

    private PdfPCell setNewCell(String param)
    {
        PdfPCell cell = new PdfPCell(new Phrase(param, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingBottom(5f);
        return cell;
    }

    private Paragraph createFileTitle(String newMonth, Context context)
    {
        Paragraph fileTitle = new Paragraph();
        Paragraph first_row = new Paragraph(context.getString(R.string.first_row), font);
        Paragraph second_row = new Paragraph(context.getString(R.string.second_row), font);
        Paragraph third_row = new Paragraph(context.getString(R.string.third_row), font);
        Paragraph fourth_row = new Paragraph(context.getString(R.string.fourth_row) + " " + newMonth, font);
        first_row.setAlignment(Element.ALIGN_CENTER);
        second_row.setAlignment(Element.ALIGN_CENTER);
        third_row.setAlignment(Element.ALIGN_CENTER);
        third_row.setSpacingAfter(15f);
        fourth_row.setAlignment(Element.ALIGN_CENTER);


        fileTitle.add(first_row);
        fileTitle.add(second_row);
        fileTitle.add(third_row);
        fileTitle.add(fourth_row);
        return fileTitle;
    }

    private void writeToFile(String newMonth, List<ModelDay> days, Context context)
    {
        try
        {

            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, fos);
            document.open();
            document.add(createFileTitle(newMonth, context));
            document.add(createTable(days, context));
            document.close();
        } catch (DocumentException e)
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

    private void saveToInternalStorage(String newMonth, List<ModelDay> days,Context context)
    {
        try
        {
            getPdfPath(newMonth);
            fos = context.openFileOutput(pdfPath, context.MODE_APPEND);
            writeToFile(newMonth, days, context);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private void saveToExternalStorage(String newMonth, List<ModelDay> days, Context context)
    {

        if (isExternalStorageWritable())
        {
            getPdfPath(newMonth);
            File dir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(dir, pdfPath);
            try
            {
                fos = new FileOutputStream(file);
                writeToFile(newMonth, days, context);
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        } else
        {
            Toast.makeText(context.getApplicationContext(), "External Media is Unavailable", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean isExternalStorageWritable()
    {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state))
        {
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable()
    {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
        {
            return true;
        }
        return false;
    }

    private void getPdfPath(String newMonth)
    {
        pdfPath = newMonth + ".pdf";
    }

}
