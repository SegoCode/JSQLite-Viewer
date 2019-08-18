import javax.swing.UIManager;
import mdlaf.MaterialLookAndFeel;

/**
 *
 * @author Segocode
 */
public class main {

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(new MaterialLookAndFeel());
        View ui = new View();
        ui.setVisible(true);

    }

}
