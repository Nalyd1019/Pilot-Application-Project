package controller;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * @Author Albert Lund
 * Abstract controller class that holds logic for checking user input and giving simple feedback to the user
 */
public class AbstractInputErrorController {

    void errorControlColorChange(Control control){
        controlColorChange(control, Color.RED);
    }
    void confirmedControlColorChange(Control control){
        controlColorChange(control,Color.GREEN);
    }
    private void controlColorChange(Control control, Color color){
        control.setBorder(new Border(new BorderStroke(color, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,new BorderWidths(1))));
    }
    void errorLabelColorChange(Label label){
        label.setTextFill(Color.RED);
    }
    boolean emptyTextField(TextField textField){
        if (textField.getText().isEmpty()){
            errorControlColorChange(textField);
            return true;
        }
        confirmedControlColorChange(textField);
        return false;
    }
    <T> boolean comboBoxHasSelectedValue(ComboBoxBase<T> comboBox){
        if (comboBox.getValue() == null){
            errorControlColorChange(comboBox);
            return false;
        }
        confirmedControlColorChange(comboBox);
        return true;
    }
    boolean validIntegerInTextField(TextField textField){
        try {
            if (Integer.parseInt(textField.getText())<0){
                errorControlColorChange(textField);
                return false;
            }
        }        //Totalt wack
        catch (NumberFormatException e){
            errorControlColorChange(textField);
            return false;
        }
        return true;
    }
    boolean validEmail(TextField textField, Label errorLabel){
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if (textField.getText().matches(regex)){
            confirmedControlColorChange(textField);
            return true;
        }
        errorControlColorChange(textField);
        errorLabel.setText("Ej giltlig e-postadress");
        errorControlColorChange(errorLabel);
        return false;
    }
}
