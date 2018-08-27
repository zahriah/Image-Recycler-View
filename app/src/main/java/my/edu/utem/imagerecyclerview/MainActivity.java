package my.edu.utem.imagerecyclerview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
RecyclerView imageRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageRecyclerView = findViewById(R.id.imageRecyclerView);
        CustomAdapter adapter = new CustomAdapter(getApplicationContext());
        imageRecyclerView.setAdapter(adapter);
        imageRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView placeTextView, descTextView;
        ImageView imageView;
        public CustomViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.custom_row,parent,false));
            placeTextView = itemView.findViewById(R.id.placeTextView);
            descTextView = itemView.findViewById(R.id.descTextView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder>{
        public final String[] title, subtitle;
        public final Drawable[] imagePlace;
        public CustomAdapter(Context context){
            Resources resources = context.getResources();
            title = resources.getStringArray(R.array.place);
            subtitle = resources.getStringArray(R.array.description);
            TypedArray a = resources.obtainTypedArray(R.array.image);
            imagePlace = new Drawable[a.length()];
            for (int i = 0; i<imagePlace.length; i++){
                imagePlace[i]=a.getDrawable(i);
            }
            a.recycle();
        }


        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new CustomViewHolder(LayoutInflater.from(viewGroup.getContext()), viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
            customViewHolder.imageView.setImageDrawable(imagePlace[i]);
            customViewHolder.placeTextView.setText(title[i]);
            customViewHolder.descTextView.setText(subtitle[i]);

        }

        @Override
        public int getItemCount() {
            return title.length;
        }
    }



}
