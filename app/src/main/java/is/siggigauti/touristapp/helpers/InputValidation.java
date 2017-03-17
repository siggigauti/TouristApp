package is.siggigauti.touristapp.helpers;

import android.app.Activity;
import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by odinnhelgason on 17/03/2017.
 */

public class InputValidation {

    private Context context;

    /**
     * constructor
     *
     * @param context
     */
    public InputValidation(Context context) {

        this.context = context;
    }

    /**
     * method to check InputEditText filled .
     *
     * @param message
     * @return
     */
    public boolean isInputEditTextFilled(EditText text, TextView error, String message) {
        String value = text.getText().toString().trim();
        if (value.isEmpty()) {
            error.setError(message);
            hideKeyboardFrom(text);
            return false;
        } else {
        }
        return true;
    }


    /**
     * method to check InputEditText has valid email .
     *
     * @param message
     * @return
     */
    public boolean isInputEditTextEmail(EditText text, TextView error, String message) {
        String value = text.getText().toString().trim();
        if (value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            error.setError(message);
            hideKeyboardFrom(text);
            return false;
        } else {

        }
        return true;
    }

    public boolean isInputEditTextMatches(EditText text1, EditText text2, TextView error, String message) {
        String value1 = text1.getText().toString().trim();
        String value2 = text2.getText().toString().trim();
        if (!value1.contentEquals(value2)) {
            error.setError(message);
            hideKeyboardFrom(text2);
            return false;
        } else {
        }
        return true;
    }

    /**
     * method to Hide keyboard
     *
     * @param view
     */
    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
