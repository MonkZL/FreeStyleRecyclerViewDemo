package zl.com.freestylerecyclerviewdemo;

import android.graphics.Bitmap;
import android.graphics.LinearGradient;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import zl.com.freestylerecyclerview.FreeStyleRecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FreeStyleRecyclerView list = (FreeStyleRecyclerView) findViewById(R.id.list);
//        GridLayoutManager layout = new GridLayoutManager(this, 2);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        list.setLayoutManager(layout);
        list.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                if (holder instanceof ItemViewHolder) {
                    Picasso
                            .with(MainActivity.this)
                            .load(R.drawable.image)
                            .noFade()
                            .config(Bitmap.Config.RGB_565)
                            .into(((ItemViewHolder) holder).imageView);
                }
            }

            @Override
            public int getItemCount() {
                return 100;
            }

            class ItemViewHolder extends RecyclerView.ViewHolder {
                private ImageView imageView;

                public ItemViewHolder(View itemView) {
                    super(itemView);
                    imageView = ((ImageView) itemView.findViewById(R.id.image));
                }
            }
        });
    }
}
