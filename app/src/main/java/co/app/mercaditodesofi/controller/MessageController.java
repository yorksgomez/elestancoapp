package co.app.mercaditodesofi.controller;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import co.app.mercaditodesofi.R;

public class MessageController {

    public static void showError(String error) {
        Log.e("Error", error);
    }

    public static void showSuccess(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        View view = toast.getView();

        view.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));

        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(context.getResources().getColor(R.color.textColorOverPrimary));
        text.setPadding(15, 15, 15, 15);

        toast.show();
    }

}
