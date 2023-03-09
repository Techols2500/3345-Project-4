/*
Tyler Echols

CS 3345.002 - Data Structures and Introduction to Algorithmic Analysis

Project #4

Due Dates:  Monday, April 12 at 11:59pm

Submit:    eLearning

Late Policy:  -10 points per hour late

Instructions: This is an individual assignment.  All work should be your own.



Objective:

     Implement an external sort routine.


Description:

     Create a class called ExternalSort.

     Your class should have the following methods.  You may add additional methods as needed.

     You may use code from the textbook, but all other code must be your own.*/

import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ExternalSort
{
    public static void main(String args[])
    {
        int runsize = Integer.parseInt(args[0]);

        Path x = Paths.get("T1.txt");

        Path Sort = extsort(x, runsize);

        display(Sort);
    }

    public static String Sort(String a)
    {

        String[] sArray = a.split(" ");

        int length = sArray.length;

        int[] num = new int[length];
        int[] listSort = new int[length];

        for (int x = 0; x < length; x++)
            num[x] = Integer.parseInt(sArray[x]);

        for (int x = 0; x < length; x++)
        {
            int min = x;
            for (int y = x; y < length; y++)
                if (num[y] < num[min])
                {
                    min = y;
                }
            listSort[x] = num[min];
            num[min] = num[x];
        }

        String output = "";
        for (int x = 0; x < listSort.length; x++)
            output += listSort[x] + " ";

        return output;
    }


    public static Path extsort (Path t1, int runsize)
    {
        Path t2 = Paths.get("T2.txt");
        Path t3 = Paths.get("T3.txt");
        Path t4 = Paths.get("T4.txt");
        String intString = "";
        try
        {
            Scanner in = new Scanner(t1);
            int add = 0;

            PrintWriter tap3 = new PrintWriter(t3.toString());
            PrintWriter tap4 = new PrintWriter(t4.toString());

            while (in.hasNextInt())
            {
                int count = 0;
                intString = "";
                while (in.hasNextInt() && count++ < runsize)
                {
                    intString += in.nextInt() + " ";
                    add++;

                }
                intString = Sort(intString);

                tap3.print(intString);
                intString = "";
                count = 0;

                while (in.hasNextInt() && count++ < runsize)
                {

                    intString += in.nextInt() + " ";
                    add++;

                }

                intString =Sort(intString);
                tap4.print(intString);

            }
            tap3.close();
            tap4.close();

            int runs = add % (runsize * 2) == 0 ? add / (runsize * 2) : add / (runsize * 2) + 1;

            for (int x = 0; x < runs; x++)
            {
                if (x % 2 == 0)
                {

                    Scanner inT3 = new Scanner(t3);
                    Scanner inT4 = new Scanner(t4);
                    boolean printer = false;

                    PrintWriter tap1 = new PrintWriter(t1.toString());
                    PrintWriter tap2 = new PrintWriter(t2.toString());

                    while (inT3.hasNextInt())
                    {
                        intString = "";
                        int count = 0;

                        while (inT3.hasNextInt() && count++ < runsize * (Math.pow(2, x)))
                        {

                            intString += inT3.nextInt() + " ";

                        }
                        int counts = 0;

                        while (inT4.hasNextInt() && counts++ < runsize * (Math.pow(2,  x)))
                        {

                            intString += inT4.nextInt() + " ";

                        }

                        intString = Sort(intString);

                        if (!printer)
                            tap1.print(intString);
                        else
                            tap2.print(intString);

                        printer = !printer;

                    }

                    tap1.close();
                    tap2.close();
                }
                if (x % 2 == 1)
                {
                    intString = "";

                    Scanner inT1 = new Scanner(t1);
                    Scanner inT2 = new Scanner(t2);
                    boolean printer = false;

                    tap3 = new PrintWriter(t3.toString());
                    tap4 = new PrintWriter(t4.toString());

                    while (inT1.hasNextInt())
                    {

                        int count = 0;
                        while (inT1.hasNextInt() && count++ < runsize * (Math.pow(2, x)))
                        {
                            intString += inT1.nextInt() + " ";

                        }
                        int counts = 0;

                        while (inT2.hasNextInt() && counts++ < runsize * (Math.pow(2,  x)))
                        {

                            intString += inT2.nextInt() + " ";

                        }
                        intString = Sort(intString);

                        if (!printer)
                            tap3.print(intString);
                        else
                            tap4.print(intString);
                        printer = !printer;
                    }
                    tap3.close();
                    tap4.close();
                }
            }
            if (runs % 2 == 1)
                return t1;

            return t3;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return t1;
    }



    public static void display (Path fileSort)
    {
        try
        {
            Scanner in = new Scanner(fileSort);

            System.out.print(fileSort.toString() + ": ");

            while ((in.hasNextInt()))
            {
                System.out.print(in.nextInt() + " ");
            }
            System.out.println();

        }

        catch (Exception y)
        {
            System.out.println(y);
        }
    }

}

