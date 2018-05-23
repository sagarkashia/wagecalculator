package org.mz.wagecalculator;

import org.apache.log4j.LogManager;
import org.mz.wagecalculator.gui.WageCalculator;
import org.mz.wagecalculator.service.EmployeeDAO;
public class App {
    private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(App.class.getName());
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
           LOGGER.error(ex.getMessage(), ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() ->{ 
        new WageCalculator(new EmployeeDAO().getEmployees()).setVisible(true);
        LOGGER.info("Application Starting...");
        });
                
    }
}
