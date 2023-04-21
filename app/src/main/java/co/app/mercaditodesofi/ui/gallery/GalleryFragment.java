package co.app.mercaditodesofi.ui.gallery;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;

import co.app.mercaditodesofi.CatalogActivity;
import co.app.mercaditodesofi.R;
import co.app.mercaditodesofi.controller.ImageController;
import co.app.mercaditodesofi.controller.ProductController;
import co.app.mercaditodesofi.model.Product;
import co.app.mercaditodesofi.ui.DynamicLineView;
import co.app.mercaditodesofi.ui.ProductView;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ImageController.clearLooking();

        String category = CatalogActivity.getCategory(),
               subcategory = CatalogActivity.getSubcategory();

        ArrayList<Product> products = ProductController.getProductsByCategory(category, subcategory);
        ArrayList<DynamicLineView<ProductView>> dynamicProductLines = new ArrayList<>();

        for(int i = 0; i < products.size() - 1; i += 2) {
            Product product1 = products.get(i),
                    product2 = products.get(i + 1);

            DynamicLineView<ProductView> dynamicLine = new DynamicLineView<>(getContext(), 2);
            dynamicLine.addContent(new ProductView(getContext(), product1));
            dynamicLine.addContent(new ProductView(getContext(), product2));

            dynamicProductLines.add(dynamicLine);
        }

        if(products.size() % 2 == 1) {
            Product product = products.get(products.size() - 1);

            DynamicLineView<ProductView> dynamicLine = new DynamicLineView<>(getContext(), 2);
            dynamicLine.addContent(new ProductView(getContext(), product));

            dynamicProductLines.add(dynamicLine);
        }

        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        LinearLayout mainLayout = root.findViewById(R.id.mainLinearGallery);

        for(DynamicLineView<ProductView> line : dynamicProductLines)
            mainLayout.addView(line);

        return root;
    }

}