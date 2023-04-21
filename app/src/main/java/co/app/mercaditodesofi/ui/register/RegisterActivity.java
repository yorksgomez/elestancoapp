package co.app.mercaditodesofi.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import co.app.mercaditodesofi.R;
import co.app.mercaditodesofi.controller.CityController;
import co.app.mercaditodesofi.controller.DepartmentController;
import co.app.mercaditodesofi.controller.MessageController;
import co.app.mercaditodesofi.controller.RegisterController;
import co.app.mercaditodesofi.model.City;
import co.app.mercaditodesofi.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ArrayList<String> departments = DepartmentController.getDepartments();

        Spinner dep_spin = findViewById(R.id.editDepartamento);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departments);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dep_spin.setAdapter(adapter);
        dep_spin.setOnItemSelectedListener(this);

    }

    public void registerClicked(View view) {
        String nombre = ((EditText) findViewById(R.id.nameEdit)).getText().toString(),
               telefono = ((EditText) findViewById(R.id.TelEdit)).getText().toString(),
               usuario = ((EditText) findViewById(R.id.mailEdit)).getText().toString(),
               contrasena = ((EditText) findViewById(R.id.passwordEdit)).getText().toString(),
               nacimiento = "";
        int id_ciudad = ((City) ((Spinner) findViewById(R.id.editMunicipio)).getSelectedItem()).getId();

        DatePicker picker = findViewById(R.id.calendarNacimiento);

        String dia = String.valueOf(picker.getDayOfMonth()),
               mes = String.valueOf(picker.getMonth()),
               anio = String.valueOf(picker.getYear());

        if(Integer.valueOf(dia) <= 9)
            dia = "0" + dia;

        if(Integer.valueOf(mes) <= 9)
            mes = "0" + mes;

        nacimiento = anio + "-" + mes + "-" + dia;

        RegisterController.register(nombre, nacimiento, telefono, usuario, contrasena, id_ciudad);

        MessageController.showSuccess(getApplicationContext(), "Registrado correctamente");

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String department = parent.getItemAtPosition(position).toString();

        ArrayList<City> cities = CityController.getCityFromDepartment(department);

        Spinner mun_spin = findViewById(R.id.editMunicipio);

        ArrayAdapter<City> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mun_spin.setAdapter(adapter);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
