package co.app.mercaditodesofi.ui;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import co.app.mercaditodesofi.CatalogActivity;
import co.app.mercaditodesofi.R;

public class SubcategoryView extends LinearLayout {

    //Variables
    private String name;
    private TextView innerText;

    public SubcategoryView(Context context, Activity activity, String name) {
        super(context);

        innerText = new TextView(context);

        if(name.length() > 1)
            name = String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1);
        else
            name = name.toUpperCase();

        this.name = name;

        init(context, activity);
    }

    private void init(Context context, Activity activity) {
        setPadding(15, 15, 15, 15);

        DisplayMetrics display = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(display);
        setMinimumWidth(display.widthPixels - 20);
        setMinimumHeight(120);

        innerText.setText(name);
        innerText.setTextSize(20);
        innerText.setPadding(0,10, 10, 0);
        innerText.setWidth(getMinimumWidth());
        innerText.setHeight(100);
        innerText.setGravity(Gravity.CENTER);
        innerText.setTextColor(getResources().getColor(R.color.textColorOverPrimary));
        innerText.setBackgroundResource(R.color.colorPrimary);

        innerText.setOnClickListener(v -> {
            CatalogActivity.setSubcategory(name.toLowerCase());
            NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
            navController.navigate(R.id.nav_gallery);
        });

        addView(innerText);
    }

}
