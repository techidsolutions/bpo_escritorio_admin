/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author TECH ID SOLUTIONS
 */
public class RenderCelda implements TableCellRenderer {
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column){
        JLabel cell = new JLabel();
        cell.setText((String)value);
        if( !hasFocus  ){
            if( column == 1 ){
                String valorCampo = (String)value;
                switch (valorCampo) {
                    case "Pendiente":
                        cell.setForeground(Color.BLACK);
                        break;
                    case "Terminado":
                        cell.setForeground(Color.BLUE);
                        break;
                    case "Guardado":
                        cell.setForeground(Color.RED);
                        break;
                    default:
                        break;
                    }
               cell.setBackground(new Color(255, 255, 255));
               cell.setFont(new Font(cell.getName(), Font.BOLD, 11));
            }
        }
        return cell;
    }
}
