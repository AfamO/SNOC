/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
 *
 * @author Charles
 */

import java.awt.*;
import javax.swing.*;
import java.awt.print.*;
/**
 *This defines the logic needed for the application to print the clients’ information.
 * @author Afam
 * @see DetailedInfor.java, GeneReportTable.java
 * @version 1.0 
 */
public class PrintClientInfor implements Printable
{
	private Component componentToBePrinted;
	public PrintClientInfor(Component componentToBePrinted)
	{
		this.componentToBePrinted=componentToBePrinted;
	}
	public void print()
	{
		PrinterJob printJob=PrinterJob.getPrinterJob();
		printJob.setPrintable(this);
		if(printJob.printDialog())
			try
			{
				printJob.print();
			}
			catch(PrinterException pe)
			{
				System.out.println("Error Printing: "+pe);
			}
	}
	public int print(Graphics g, PageFormat pageFormat, int pageIndex)
	{
		if(pageIndex>0)
		return (NO_SUCH_PAGE);
			else
			{
			Graphics2D g2d=(Graphics2D)g;
			g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			disableDoubleBuffering(componentToBePrinted);
			componentToBePrinted.paint(g2d);
			enableDoubleBuffering(componentToBePrinted);
			return(PAGE_EXISTS);	
			}
	}
	public static void printComponent(Component c)
	{
		new PrintClientInfor(c).print();
	}
	public static void  disableDoubleBuffering(Component c)
	{
		RepaintManager currentManager=RepaintManager.currentManager(c);
		currentManager.setDoubleBufferingEnabled(false);
	}
	
		public static void  enableDoubleBuffering(Component c)
	{
		RepaintManager currentManager=RepaintManager.currentManager(c);
		currentManager.setDoubleBufferingEnabled(true);
	}

}