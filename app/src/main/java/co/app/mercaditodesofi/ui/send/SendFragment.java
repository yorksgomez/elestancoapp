package co.app.mercaditodesofi.ui.send;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import co.app.mercaditodesofi.CatalogActivity;
import co.app.mercaditodesofi.R;
import co.app.mercaditodesofi.controller.SubcategoryController;
import co.app.mercaditodesofi.ui.SubcategoryView;

public class SendFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_send, container, false);

        LinearLayout layout = root.findViewById(R.id.SubcategoryLayout);

        ArrayList<String> subcategories = SubcategoryController.getSubcategories(CatalogActivity.getCategory());

        layout.addView(new SubcategoryView(getContext(), getActivity(), "Ver todos"));

        for(String subcategoria : subcategories)

            if(!subcategoria.equals(""))
                layout.addView(new SubcategoryView(getContext(), getActivity(), subcategoria));


        return root;
    }
}