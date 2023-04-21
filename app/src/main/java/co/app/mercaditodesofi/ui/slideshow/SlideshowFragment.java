package co.app.mercaditodesofi.ui.slideshow;

import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;

import co.app.mercaditodesofi.R;
import co.app.mercaditodesofi.controller.CartController;
import co.app.mercaditodesofi.controller.ImageController;
import co.app.mercaditodesofi.model.Product;
import co.app.mercaditodesofi.ui.DynamicLineView;
import co.app.mercaditodesofi.ui.ProductView;

public class SlideshowFragment extends Fragment implements View.OnKeyListener {

    private SlideshowViewModel slideshowViewModel;
    private int suma;
    private ArrayList<EditText> campos;
    private View root;
    private ArrayList<Product> products;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        CartController.clearCantidades();
        ImageController.clearLooking();

        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        this.root = root;

        campos = new ArrayList<>();

        products = CartController.getProducts();
        ArrayList<DynamicLineView<View>> lines = new ArrayList<>();

        for(Product product : products) {
            DynamicLineView line = new DynamicLineView(getContext(), 2);

            EditText edit = new EditText(getContext());
            edit.setOnKeyListener(this);
            edit.setHint("Cantidad");
            edit.setInputType(InputType.TYPE_CLASS_NUMBER);
            edit.setGravity(Gravity.CENTER);

            campos.add(edit);
            CartController.addCantidad(0);

            line.addContent(edit);
            line.addContent(new ProductView(getContext(), product, true));

            lines.add(line);
        }

        LinearLayout mainLayout = root.findViewById(R.id.mainCartLayout);

        for(DynamicLineView<View> line : lines)
            mainLayout.addView(line);

        return root;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if(event.getAction() == KeyEvent.ACTION_UP) {
            EditText edit = (EditText) v;

            for (int i = 0; i < campos.size(); i++) {

                if (campos.get(i) == edit) {
                    String cantidadt = edit.getText().toString();
                    cantidadt = cantidadt.replace(' ', '\0');
                    cantidadt = cantidadt.equals("") ? "0" : cantidadt;

                    CartController.getCantidades().set(i, Integer.valueOf(cantidadt) * products.get(i).getPrice());
                    break;
                }

            }

            fireUpdatedCamp();
        }

        return false;
    }

    private void fireUpdatedCamp() {

        suma = 0;

        for(Integer dato : CartController.getCantidades())
            suma += dato;

        TextView txt = root.findViewById(R.id.txtCosto);

        txt.setText("COSTO TOTAL: $" + String.valueOf(suma));
    }
}