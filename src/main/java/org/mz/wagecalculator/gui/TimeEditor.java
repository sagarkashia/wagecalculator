
package org.mz.wagecalculator.gui;

import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.text.DefaultFormatter;
import org.apache.log4j.LogManager;
import org.mz.wagecalculator.service.PDFService;

public class TimeEditor
        extends DefaultCellEditor {

    private JSpinner timeSpinner;
    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
    private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(TimeEditor.class.getName());

    TimeEditor(JFormattedTextField jFormattedTextField) {
        super(jFormattedTextField);
        timeSpinner = new JSpinner();
        timeSpinner.setModel(new SpinnerDateModel());
        timeSpinner.setEditor(new JSpinner.DateEditor(timeSpinner, "hh:mm a"));

        timeSpinner.addChangeListener((ChangeEvent e) -> {
            DefaultFormatter format = (DefaultFormatter) ((JSpinner.DefaultEditor) timeSpinner.getEditor()).getTextField().getFormatter();
            format.setAllowsInvalid(false);
            format.setOverwriteMode(true);
            format.setCommitsOnValidEdit(true);
        });

    }

    @Override
    public Object getCellEditorValue() {
        LOGGER.info("Getting Cell Editor Value");
        return dateFormat.format(this.timeSpinner.getValue());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        LOGGER.info("Returning Table Cell Editor Object");
        if (table.getValueAt(row, column) == null) {
            this.timeSpinner.setValue(new Date());
        } else {
            try {
                this.timeSpinner.setValue(dateFormat.parse((String) table.getValueAt(row, column)));
            } catch (ParseException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
        return this.timeSpinner;
    }

}
