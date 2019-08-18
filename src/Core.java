
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Segocode
 */
public class Core {

    View ui;
    connectDB con;

    public Core(View ui) {
        this.ui = ui;
    }

    public void init(String file) {
        ui.tablesCombo.removeAllItems();
        DefaultTableModel auxdm = (DefaultTableModel) ui.tableView.getModel();
        auxdm.getDataVector().removeAllElements();
        auxdm.fireTableDataChanged();
        ui.sqlField.setText("");
        try {
            con = new connectDB(file);
            con.setConexion();
            con.setSentencia("SELECT name FROM sqlite_master WHERE type ='table' AND name NOT LIKE 'sqlite_%';");
            ResultSet rs = con.ejecutarConsulta();
            while (rs.next()) {
                ui.tablesCombo.addItem(rs.getString(1));
            }
            ui.status.setText("File loaded");
            ui.status.setForeground(new Color(255, 116, 0));
        } catch (Exception ex) {
            ui.status.setForeground(Color.red);
            ui.status.setText("File Error");
        }
    }

    public void execute() {
        try {
            con.setSentencia(ui.sqlField.getText());
            ResultSet rs = con.ejecutarConsulta();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            DefaultTableModel tableModel = new DefaultTableModel();
            for (int i = 1; i < columnsNumber + 1; i++) {
                tableModel.addColumn(rsmd.getColumnName(i));

            }
            ui.tableView.setModel(tableModel);

            tableModel = (DefaultTableModel) ui.tableView.getModel();
            int row = 0;
            while (rs.next()) {
                tableModel.addRow(new Object[]{});
                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = rs.getString(i);
                    tableModel.setValueAt(columnValue, row, i - 1);

                }
                row++;
            }

            ui.tableView.setModel(tableModel);
            ui.status.setForeground(Color.blue);
            ui.status.setText("OK");
        } catch (Exception ex) {
            ui.status.setForeground(Color.red);
            ui.status.setText("SQLException Error");
        }

    }

}
