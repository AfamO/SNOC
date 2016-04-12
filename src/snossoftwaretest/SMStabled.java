/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

/**
 *
 * @author Charles
 */
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class SMStabled extends JPanel {
  private boolean DEBUG = false;

  public SMStabled() {
    super(new GridLayout(1, 0));

    JTable table = new JTable(new MyTableModeld());
    table.setPreferredScrollableViewportSize(new Dimension(500, 70));

    // Create the scroll pane and add the table to it.
    JScrollPane scrollPane = new JScrollPane(table);

    // Set up column sizes.
    initColumnSizes(table);

    // Fiddle with the Sport column's cell editors/renderers.
    //setUpSportColumn(table, table.getColumnModel().getColumn(2));

    // Add the scroll pane to this panel.
    add(scrollPane);
  }

  /*
   * This method picks good column sizes. If all column heads are wider than the
   * column's cells' contents, then you can just use column.sizeWidthToFit().
   */
  private void initColumnSizes(JTable table) {
    MyTableModeld model = (MyTableModeld) table.getModel();
    TableColumn column = null;
    Component comp = null;
    int headerWidth = 0;
    int cellWidth = 0;
    Object[] longValues = model.longValues;
    TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();

    for (int i = 0; i < 3; i++) {
      column = table.getColumnModel().getColumn(i);

      comp = headerRenderer.getTableCellRendererComponent(null, column.getHeaderValue(), false,
          false, 0, 0);
      headerWidth = comp.getPreferredSize().width;

      comp = table.getDefaultRenderer(model.getColumnClass(i)).getTableCellRendererComponent(table,
          longValues[i], false, false, 0, i);
      cellWidth = comp.getPreferredSize().width;
      table.setValueAt("read", 0, 3);

      if (DEBUG) {
        System.out.println("Initializing width of column " + i + ". " + "headerWidth = "
            + headerWidth + "; cellWidth = " + cellWidth);
      }

      // XXX: Before Swing 1.1 Beta 2, use setMinWidth instead.
      column.setPreferredWidth(Math.max(headerWidth, cellWidth));
    }
  }

 
  
}