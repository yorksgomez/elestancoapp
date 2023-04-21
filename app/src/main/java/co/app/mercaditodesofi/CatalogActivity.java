package co.app.mercaditodesofi;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import co.app.mercaditodesofi.controller.CartController;
import co.app.mercaditodesofi.controller.MessageController;
import co.app.mercaditodesofi.controller.OrderController;
import co.app.mercaditodesofi.controller.SaveSharedPreference;
import co.app.mercaditodesofi.controller.UserUpdateController;
import co.app.mercaditodesofi.data.LoginRepository;
import co.app.mercaditodesofi.model.City;
import co.app.mercaditodesofi.ui.login.LoginActivity;

public class CatalogActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private static String category, subcategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        final CatalogActivity cinstance = this;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: add cart
                NavController navController = Navigation.findNavController(cinstance, R.id.nav_host_fragment);
                navController.navigate(R.id.nav_slideshow);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.catalog, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //Getters
    public static String getCategory() {
        return category;
    }

    public static String getSubcategory() { return subcategory; }

    //Setters
    public static void setSubcategory(String subcategory) { CatalogActivity.subcategory = subcategory; }

    //Method for events
    public boolean closeSession(MenuItem view) {
        SaveSharedPreference.clear(getApplicationContext());

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);

        return true;
    }

    public void hacerPedido(View view) {
        int radio_id = ((RadioGroup) findViewById(R.id.radiogpPago)).getCheckedRadioButtonId();

        int id_usuario = LoginRepository.getInstance(null).getUser().getId();
        String direccion = ((EditText)findViewById(R.id.editDireccion)).getText().toString(),
               forma_pago = ((RadioButton)findViewById(radio_id)).getText().toString();


        OrderController.addOrder(id_usuario, direccion, forma_pago);

        MessageController.showSuccess(getApplicationContext(), "Pedido hecho correctamente!");

        CartController.clean();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.navigate(R.id.nav_home);
    }

    public void actualizarMunicipio(View view) {
        int id = ((City) ( (Spinner) findViewById(R.id.spinMunicipio) ).getSelectedItem()).getId();

        UserUpdateController.updateCity(id);
        MessageController.showSuccess(getApplicationContext(), "Municipio actualizado correctamente!");
    }

    public void setToGalleryBebidas(View view) {
        category = "bebidas";
        setToGallery();
    }

    public void setToGalleryPasabocas(View view) {
        category = "pasabocas";
        setToGallery();
    }

    public void setToGalleryCigarrillos(View view) {
        category = "cigarrillos";
        setToGallery();
    }

    public void setToGalleryPreservativos(View view) {
        category = "preservativos";
        setToGallery();
    }

    public void setToGalleryCombos(View view) {
        category = "combos";
        setToGallery();
    }

    private void setToGallery() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.navigate(R.id.nav_send);
    }

}
