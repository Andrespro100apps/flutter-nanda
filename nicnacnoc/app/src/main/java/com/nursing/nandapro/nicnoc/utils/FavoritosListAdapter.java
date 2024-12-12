package com.nursing.nandapro.nicnoc.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nursing.nandapro.nicnoc.ListAdapter;
import com.nursing.nandapro.nicnoc.ListElement;
import com.nursing.nandapro.nicnoc.R;

import java.util.ArrayList;
import java.util.List;

public class FavoritosListAdapter extends RecyclerView.Adapter<FavoritosListAdapter.ViewHolder> implements Filterable {
    private List<ListElement> mData;
    private LayoutInflater minflater;
    public Boolean esFavorito;
    private Context context;
    private static final int ITEM_VIEW = 0;
    private static final int AD_VIEW = 1;
    final ListAdapter.OnItemClickListener listener;

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        //run on bacgorund thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<String> filteredList = new ArrayList<>();
            if (charSequence.toString().isEmpty()){
                //filteredList.addAll(TextView);
            }

            return null;
        }
        // runs in a ui thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

        }
    };

    public  interface OnItemClickListener {
        void onItemClick(ListElement item);
    }

    public FavoritosListAdapter(List<ListElement> itemList, Context context, ListAdapter.OnItemClickListener listener){
        this.minflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
        // context.addALL(itemList);
    }



    @Override
    public int getItemCount() { return mData.size();}

    @Override
    public FavoritosListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = minflater.from(parent.getContext()).inflate(R.layout.list_element, parent, false);
        // sin animacion
        //   View view = minflater.inflate(R.layout.list_element, null);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide));
        holder.bindData(mData.get(position));
        Log.e("Posicion:",position +"");
    }


    public void setItems(List<ListElement> items) { mData =items; }

    public class  ViewHolder extends RecyclerView.ViewHolder {
        // ImageView iconImage;
        TextView dominio, titulo, clases, texto,diagnostico;
        CardView cv;
        ImageView favorito;
        ViewHolder(View itemView) {
            super(itemView);
            //click si funciona

            //   itemView.setOnClickListener(context);
            //   iconImage = itemView.findViewById(R.id.imageView2);
            dominio = itemView.findViewById(R.id.dominiooooo);
            titulo = itemView.findViewById(R.id.titulo);
            clases = itemView.findViewById(R.id.clases);
            texto = itemView.findViewById(R.id.descripcion);
            favorito = itemView.findViewById(R.id.btnFavorito);
            cv = itemView.findViewById(R.id.cv);
            diagnostico = itemView.findViewById(R.id.diagnostico);

        }

        void bindData(final ListElement item){
            // iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            dominio.setText(item.getDominio());
            titulo.setText(item.getTitulo());
            clases.setText(item.getClases());
            @SuppressLint("UseCompatLoadingForDrawables") Drawable imagen_favorito = context.getDrawable(item.getEsFavorito() ? R.drawable.ic_favorite_black :R.drawable.ic_favorite_blank);
            favorito.setImageDrawable(imagen_favorito);
            texto.setText(item.getTexto());
            diagnostico.setText(item.getDiagnostico());
            itemView.setOnClickListener(v -> {
                listener.onItemClick(item);

                //  Intent i = new Intent(context,);

            });
        }
    }
}
