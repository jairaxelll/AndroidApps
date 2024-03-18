package com.example.aplicaciones;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;
import java.util.ArrayList; // Import ArrayList class
import java.util.List;
import android.os.Bundle;
import android.widget.AdapterView;
import android.view.View;
import android.content.Intent;
import android.net.Uri;


public class MainActivity extends AppCompatActivity {


    private ListView listViewProductos;// Correct variable declaration

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the ListView in your layout
        listViewProductos = findViewById(R.id.listViewProductos);

        // Create a list of Producto objects
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Nike Cortez", "Tenis Nike Cortez 23 Premium Leather", "https://www.nike.com/t/cortez-23-premium-leather-womens-shoes-0FZSGV/FB6877-100"));
        productos.add(new Producto("BB Belt", "Kish Dark Silver Clear", "https://bbsimononline.com/product/kish-dark-silver-clear/"));
        // Add more products if needed...

        // Create an instance of ProductoAdapter and pass the context, layout resource, and list of products
        ProductoAdapter adapter = new ProductoAdapter(this, R.layout.item_producto, productos);

        // Set the adapter for your ListView
        listViewProductos.setAdapter(adapter);

        listViewProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked Producto object
                Producto clickedProducto = adapter.getItem(position);

                // Open the web link associated with the clicked item
                String url = clickedProducto.getUrlCompra();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

    }
}