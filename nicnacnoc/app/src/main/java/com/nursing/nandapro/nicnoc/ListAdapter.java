package com.nursing.nandapro.nicnoc;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
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

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nursing.nandapro.nicnoc.Activitys.MainActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements Filterable {
    private List<ListElement> mData;
    private LayoutInflater minflater;
    public Boolean esFavorito;
    private Context context;
    private static final int ITEM_VIEW = 0;
    private static final int AD_VIEW = 1;
    final ListAdapter.OnItemClickListener listener;
    String TAG = "gato";
    Context contextoP;

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        //run on bacgorund thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<String> filteredList = new ArrayList<>();
            if (charSequence.toString().isEmpty()) {
                //filteredList.addAll(TextView);
            }

            return null;
        }

        // runs in a ui thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

        }
    };

    public interface OnItemClickListener {
        void onItemClick(ListElement item);
    }

    public ListAdapter(List<ListElement> itemList, Context context, ListAdapter.OnItemClickListener listener) {
        this.minflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
        // context.addALL(itemList);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = minflater.from(parent.getContext()).inflate(R.layout.list_element, parent, false);
        // sin animacion
        //   View view = minflater.inflate(R.layout.list_element, null);
        Activity activity = (Activity) parent.getContext();
        String Panels = activity.getLocalClassName();

        if (Panels.contains("iagnostico") || Panels.contains(("Favoritos")))
            return new ListAdapter.ViewHolder(view, parent.getContext());
        else
            return new ListAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
      //  holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide));
        holder.bindData(mData.get(position));
        Log.e("Posicion:", position + "");
    }

    public void setItems(List<ListElement> items) {
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // ImageView iconImage;
        TextView dominio, titulo, clases, texto, diagnostico;
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

            favorito.setVisibility(View.INVISIBLE);
        }

        ViewHolder(View itemView, Context v) {
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
            contextoP = v;


        }

        void bindData(final ListElement item) {
            dominio.setText(item.getDominio());
            titulo.setText(item.getTitulo());
            clases.setText(item.getClases());
            texto.setText(item.getTexto());
            diagnostico.setText(item.getDiagnostico());
            itemView.setOnClickListener(v -> {
                listener.onItemClick(item);
            });
            favorito.setOnClickListener(v -> {

                Activity activity = (Activity) v.getContext();
                String dbName;
                if (activity.getLocalClassName().contains("2")) dbName = MainActivity.namedb2;
                else if (activity.getLocalClassName().contains("3")) dbName = MainActivity.namedb3;
                else if (activity.getLocalClassName().contains("4")) dbName = MainActivity.namedb5;
                else if (activity.getLocalClassName().contains("10")) dbName = MainActivity.namedb4;

                else dbName = MainActivity.namedb;
                Log.d(TAG, "bindData: "+dbName+" "+ activity.getLocalClassName());
                DatabaseHelper myDbHelper;
                myDbHelper = new DatabaseHelper(v.getContext(), dbName);
                try {
                    myDbHelper.createDataBase();
                    myDbHelper.openDataBase();
                } catch (IOException ioe) {
                }
                Cursor  c = myDbHelper.rawquery("SELECT * FROM Diagnosticos where Titulo like '" + titulo.getText() + "' and DominioN like '" + clases.getText() + "'");
                    int a = c.getColumnIndex("favoritos");
                    Log.d(TAG, "ViewHolder: si se guardo");

                    if (c.moveToFirst()) {
                            String dato = c.getString(a) == null ? "0" : c.getString(a);
                            int favoritoNumber = Integer.parseInt(dato);

                            favoritoNumber = favoritoNumber == 0 ? 1 : 0;

                            myDbHelper.insert("Update Diagnosticos set favoritos = " + favoritoNumber + " where Titulo like '" + titulo.getText() + "' and DominioN like '" + clases.getText() + "'");

                            Drawable setImg = context.getDrawable(favoritoNumber == 0 ? R.drawable.ic_favorite_blank : R.drawable.ic_favorite_black);
                            favorito.setImageDrawable(setImg);

                    }
                    c.close();

            });

           if(contextoP != null){
               Drawable setImg = context.getDrawable(R.drawable.ic_favorite_black);
               Activity activity = (Activity) contextoP;
               String dbName;
               if (activity.getLocalClassName().contains("2")) dbName = MainActivity.namedb2;
               else if (activity.getLocalClassName().contains("3")) dbName = MainActivity.namedb3;
               else if (activity.getLocalClassName().contains("4")) dbName = MainActivity.namedb4;
               else dbName = MainActivity.namedb;

               DatabaseHelper myDbHelper = new DatabaseHelper(contextoP, dbName);
//               Log.d(TAG, "bindData: "+dbName+" "+ contextoP);
               try {
                   myDbHelper.createDataBase();
                   myDbHelper.openDataBase();
                    String tituloRemplace = titulo.getText().toString().replaceAll("'","''");
                   String DominioRemplace = clases.getText().toString().replaceAll("'","''");
//                   Log.d(TAG, "bindData: "+tituloRemplace);
//                   Log.d(TAG, "bindData: "+ "SELECT * FROM Diagnosticos where favoritos like 1 and Titulo like '" + tituloRemplace + "' and DominioN like '" + DominioRemplace + "'");

                   Cursor c = myDbHelper.rawquery("SELECT * FROM Diagnosticos where favoritos like 1 and Titulo like '" + tituloRemplace + "' and DominioN like '" + DominioRemplace + "'");
                   if (c.moveToFirst()) {
                       favorito.setImageDrawable(setImg);
                   }
               } catch (IOException ioe) {
               }

           }

        }


    }
}

