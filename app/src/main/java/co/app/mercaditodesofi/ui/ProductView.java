package co.app.mercaditodesofi.ui;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import co.app.mercaditodesofi.R;
import co.app.mercaditodesofi.controller.AppConstants;
import co.app.mercaditodesofi.controller.CartController;
import co.app.mercaditodesofi.controller.ImageController;
import co.app.mercaditodesofi.controller.MessageController;
import co.app.mercaditodesofi.model.Product;

public class ProductView extends LinearLayout {

    //Variables
    private Product model;
    private ImageView image;
    private TextView name,
            description,
            price;
    private Button buy;
    private boolean buying;

    //Constructor
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public ProductView(Context context, Product model) {
        super(context);

        this.model = model;

        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public ProductView(Context context, Product model, boolean buying) {
        super(context);

        this.model = model;
        this.buying = buying;

        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void init(Context context) {

        setOrientation(LinearLayout.VERTICAL);
        setPadding(15, 15, 15, 15);

        image = new ImageView(context);
        ImageController.getWebImageBitmapUsingCache(AppConstants.REQUEST_SERVER + "images/" + model.getImgUrl(), image);

        name = new TextView(context);
        name.setText(model.getName());
        name.setWidth(getMinimumWidth());
        name.setHeight(70);
        name.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        name.setTextColor(getResources().getColor(R.color.textColorOverPrimary));
        name.setGravity(Gravity.CENTER);

        description = new TextView(context);
        description.setText(model.getDescription());
        description.setGravity(Gravity.CENTER);

        price = new TextView(context);
        price.setText("$" + String.valueOf(model.getPrice()));
        price.setTextColor(getResources().getColor(R.color.colorPrimaryLight));
        price.setGravity(Gravity.CENTER);
        price.setPadding(0, 10, 10, 0);


        buy = new Button(context);
        buy.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        buy.setTextColor(getResources().getColor(R.color.textColorOverPrimary));
        buy.setMaxWidth(getMinimumWidth() - 40);
        buy.setMaxHeight(50);
        buy.setText("Comprar");

        buy.setOnClickListener(v -> {
            CartController.addProduct(model);
            MessageController.showSuccess(context, "Agregado al carrito!");
        });

        addView(name);
        addView(image);
        addView(description);
        addView(price);

        if(!buying)
            addView(buy);

    }

}
