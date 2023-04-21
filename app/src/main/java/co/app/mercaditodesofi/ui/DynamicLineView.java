package co.app.mercaditodesofi.ui;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

public class DynamicLineView<T extends View> extends LinearLayout {

    //Variables
    private int size, width;

    //Constructor
    public DynamicLineView(Context context, int size) {
        super(context);

        this.size = size;

        DisplayMetrics display = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(display);

        this.width = (display.widthPixels - 40) / size;
        setOrientation(LinearLayout.HORIZONTAL);
    }

    //Methods
    public void addContent(T item) {
        item.setMinimumWidth(this.width);

        addView(item);
    }

}
