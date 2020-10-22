package controller;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * @Author Albert Lund
 * Abstract controller class that holds logic for checking user input and giving simple feedback to the user
 */
public abstract class AbstractInputErrorController {

    /**
     * Colors the border of the controller to red, signaling an erroneous input from the user
     * @param control the control which border needs to be recoloured
     */
    void errorControlColorChange(Control control){
        controlColorChange(control, Color.RED);
    }

    /**
     * Colours the border of the controller green, signaling correct input from the user
     * @param control the control which border needs to be recoloured
     */
    void confirmedControlColorChange(Control control){
        controlColorChange(control,Color.GREEN);
    }
    private void controlColorChange(Control control, Color color){
        control.setBorder(new Border(new BorderStroke(color, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,new BorderWidths(1))));
    }

    /**
     * Colours the text of a helping label red, signaling an erroneous input from the user
     * @param label the label which texts needs to be recoloured
     */
    void errorLabelColorChange(Label label){
        label.setTextFill(Color.RED);
    }

    /**
     * checks if a textfield has any input from the user and is not left blank, also colours the border of the textfield
     * red if it is left blank and green if not
     * @param textField the textfield which needs to be checked
     * @return true if the textfield is blank, false if otherwise
     */
    boolean emptyTextField(TextField textField){
        if (textField.getText().isEmpty()){
            errorControlColorChange(textField);
            return true;
        }
        confirmedControlColorChange(textField);
        return false;
    }

    /**
     * checks if a combobox has a value selected from its list, also colours the border of the combobox red if no value
     * is selected, and green if not
     * @param comboBox the combobox which needs to be checked
     * @param <T> a generic parameter which allows the comboBox to have different types of values in its list
     * @return true if the combobox has a value selected, false if otherwise
     */
    <T> boolean comboBoxHasSelectedValue(ComboBoxBase<T> comboBox){
        if (comboBox.getValue() == null){
            errorControlColorChange(comboBox);
            return false;
        }
        confirmedControlColorChange(comboBox);
        return true;
    }

    /**
     * checks a textfield which only allows for integers above 0 as input. Also colours the border of the textfield red
     * if the input is not an integer above 0, otherwise green
     * @param textField the textfield which needs to be checked
     * @return true if the input is an integer above 0, otherwise not
     */
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

    /**
     * checks if a textfield which requires an email has a string in the format of an email. If the format is correct,
     * the textfields border turns green, if the format is incorrect, it is coloured red instead and the errorlabel
     * gives a helping explanation.
     * @param textField the email which needs to be checked
     * @param errorLabel the errorlabel linked to the textfield
     * @return true if the email format is correct, otherwise false
     */
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
