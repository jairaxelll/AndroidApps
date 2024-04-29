package com.example.galery; // Reemplaza "myapplication" con el nombre de tu paquete


import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int[] images = {R.drawable.placeholder_image, R.drawable.placeholder_image2, R.drawable.placeholder_image3};
    private GridView gridView;
    private ImageAdapter adapter;
    private GestureDetector gestureDetector;

    private int currentImageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.grid_view);
        adapter = new ImageAdapter(this, images);
        gridView.setAdapter(adapter);

        gestureDetector = new GestureDetector(this, new MyGestureListener());

        gridView.setOnTouchListener((v, event) -> {
            gestureDetector.onTouchEvent(event);
            return true;
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                // Acción al hacer clic en una imagen
                Toast.makeText(MainActivity.this, "Imagen " + (position + 1), Toast.LENGTH_SHORT).show();
            }
        });
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffX = e2.getX() - e1.getX();
            float diffY = e2.getY() - e1.getY();

            if (Math.abs(diffX) > Math.abs(diffY) &&
                    Math.abs(diffX) > SWIPE_THRESHOLD &&
                    Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) {
                    // Deslizamiento hacia la derecha
                    if (currentImageIndex > 0) {
                        currentImageIndex--;
                        gridView.smoothScrollToPosition(currentImageIndex);
                    }
                } else {
                    // Deslizamiento hacia la izquierda
                    if (currentImageIndex < images.length - 1) {
                        currentImageIndex++;
                        gridView.smoothScrollToPosition(currentImageIndex);
                    }
                }
                return true;
            }
            return false;
        }
    }
}
