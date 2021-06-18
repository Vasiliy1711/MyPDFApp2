package com.example.mypdfapp2.converters;

import android.util.Log;

public class DateConverter
{
    private static String sMonth, sYear;
    private static int iMonth, iYear;

    public static int monthToInt(String month)
    {
        String lMonth = month.toLowerCase();
        Log.e("CONVERTER", lMonth + " " + month);
        if (lMonth.equals("january") || lMonth.equals("январь"))
        {
            iMonth = 1;
        } else if (lMonth.equals("february") || lMonth.equals("февраль"))
        {
            iMonth = 2;
        } else if (lMonth.equals("march") || lMonth.equals("март"))
        {
            iMonth = 3;
        } else if (lMonth.equals("april") || lMonth.equals("апрель"))
        {
            iMonth = 4;
        } else if (lMonth.equals("may") || lMonth.equals("май"))
        {
            iMonth = 5;
        } else if (lMonth.equals("june") || lMonth.equals("июнь"))
        {
            iMonth = 6;
        } else if (lMonth.equals("july") || lMonth.equals("июль"))
        {
            iMonth = 7;
        } else if (lMonth.equals("august") || lMonth.equals("август"))
        {
            iMonth = 8;
        } else if (lMonth.equals("september") || lMonth.equals("сентябрь"))
        {
            iMonth = 9;
        } else if (lMonth.equals("october") || lMonth.equals("октябрь"))
        {
            iMonth = 10;
        } else if (lMonth.equals("november") || lMonth.equals("ноябрь"))
        {
            iMonth = 11;
        } else if (lMonth.equals("december") || lMonth.equals("декабрь"))
        {
            iMonth = 12;
        }
        else
        {
            return 0;
        }
        return iMonth;
    }

    public static int yearToInt(String year)
    {
        if (year.equals("2020"))
        {
            iYear = 2020;
        } else if (year.equals("2021"))
        {
            iYear = 2021;
        } else if (year.equals("2022"))
        {
            iYear = 2022;
        } else if (year.equals("2023"))
        {
            iYear = 2023;
        } else if (year.equals("2024"))
        {
            iYear = 2024;
        } else if (year.equals("2025"))
        {
            iYear = 2025;
        }
        else
        {
            return 0;
        }
        return iYear;
    }

    public static String monthToString(int month)
    {
        switch (month)
        {
            case 1:
                sMonth = "Январь";
                break;
            case 2:
                sMonth = "Февраль";
                break;
            case 3:
                sMonth = "Март";
                break;
            case 4:
                sMonth = "Апрель";
                break;
            case 5:
                sMonth = "Май";
                break;
            case 6:
                sMonth = "Июнь";
                break;
            case 7:
                sMonth = "Июль";
                break;
            case 8:
                sMonth = "Август";
                break;
            case 9:
                sMonth = "Сентябрь";
                break;
            case 10:
                sMonth = "Октябрь";
                break;
            case 11:
                sMonth = "Ноябрь";
                break;
            case 12:
                sMonth = "Декабрь";
                break;
            default:
                sMonth = null;
        }
        return sMonth;
    }
    public static String yearToString(int year)
    {
        switch (year)
        {
            case 2020 :
                sYear = "2020";
                break;
            case 2021 :
                sYear = "2021";
                break;
            case 2022 :
                sYear = "2022";
                break;
            case 2023 :
                sYear = "2023";
                break;
            case 2024 :
                sYear = "2024";
                break;
            case 2025 :
                sYear = "2025";
                break;
            default:
                sYear = null;
        }
        return sYear;
    }
}
