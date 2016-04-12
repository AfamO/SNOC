/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snossoftwaretest;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Charles
 */
class MyTableModeld extends AbstractTableModel {
    private boolean DEBUG = false;
    private String[] columnNames = { "Sender", "Messages", "Date_Received","message Status " };
   
  

    public Object[][] getData() {
        return data;
    }
    private Object[][] data = {
        { "Mary", "Campione", "Snowboarding","unread" },
        { "Mary", "Campione", "Snowboarding","unread" },
        { "Alison", "Huml", "Rowing","unread" },
        { "Kathy", "Walrath", "Knitting","unread" },
        { "Sharon", "Zakhour", "Speed reading","unread" },
        { "Philip", "Milne", "Pool","unread" }, 
        { "Chuks", "Caint", "Football","unread" }};
   
    /*
    ReadMessages b = new ReadMessages();
    Reconsoft dis = new Reconsoft();
    
  
    public Object[][] data=dis.getDatabaseArray();

     */
    public final Object[] longValues = { " ", " ", " " };

    public int getColumnCount() {
      return columnNames.length;
    }

    public int getRowCount() {
      return data.length;
    }

    public String getColumnName(int col) {
      return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
      return data[row][col];
    }

    /*
     * JTable uses this method to determine the default renderer/ editor for
     * each cell. If we didn't implement this method, then the last column would
     * contain text ("true"/"false"), rather than a check box.
     */
    public Class getColumnClass(int c) {
      return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's editable.
     */
    public boolean isCellEditable(int row, int col) {
      // Note that the data/cell address is constant,
      // no matter where the cell appears onscreen.
      if (col < 2) {
        return false;
      } else {
        return true;
      }
    }

    /*
     * Don't need to implement this method unless your table's data can change.
     */
    public void setValueAt(Object value, int row, int col) {
      if (DEBUG) {
        System.out.println("Setting value at " + row + "," + col + " to " + value
            + " (an instance of " + value.getClass() + ")");
      }

      data[row][col] = value;
      fireTableCellUpdated(row, col);

      if (DEBUG) {
        System.out.println("New value of data:");
        printDebugData();
      }
    }

    private void printDebugData() {
      int numRows = getRowCount();
      int numCols = getColumnCount();

      for (int i = 0; i < numRows; i++) {
        System.out.print("    row " + i + ":");
        for (int j = 0; j < numCols; j++) {
          System.out.print("  " + data[i][j]);
        }
        System.out.println();
      }
    }
  }
