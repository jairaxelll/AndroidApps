package com.example.aplicaciones;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;


public class ProductoAdapter extends ArrayAdapter<Producto> {

    private Context context;
    private List<Producto> productos;

    public ProductoAdapter(Context context, int resource, List<Producto> productos) {
        super(context, resource, productos);
        this.context = context;
        this.productos = productos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_producto, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.txtNombre = convertView.findViewById(R.id.txtNombre);
            viewHolder.txtDescripcion = convertView.findViewById(R.id.txtDescripcion);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Producto producto = productos.get(position);
        viewHolder.txtNombre.setText(producto.getNombre());
        viewHolder.txtDescripcion.setText(producto.getDescripcion());

        return convertView;
    }

    @Override
    public Producto getItem(int position) {
        return productos.get(position);
    }

    static class ViewHolder {
        TextView txtNombre;
        TextView txtDescripcion;
    }
}
