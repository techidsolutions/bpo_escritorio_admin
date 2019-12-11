/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author TECH ID SOLUTIONS
 */
public class LimitarNumeros extends PlainDocument{
   /**
    * Método al que llama el editor cada vez que se intenta insertar caracteres.
    * Sólo debemos verificar arg1, que es la cadena que se quiere insertar en el JTextField
     * @param arg0
     * @param arg1
     * @param arg2
     * @throws javax.swing.text.BadLocationException
    */
   @Override
   public void insertString(int arg0, String arg1, AttributeSet arg2) throws BadLocationException {
       for (int i=0;i<arg1.length();i++)
          if (!(Character.isDigit(arg1.charAt(i)) || Character.isISOControl(arg1.charAt(i))))
             return;
       super.insertString(arg0, arg1, arg2);
   }
} 

