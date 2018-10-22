package com.example.netactivity;

import java.util.List;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

// TODO 1. - Aktualne funguje aplikace tak, ze se nacte a zobrazi v listu kody men ze souboru http://www.cnb.cz/cs/financni_trhy/devizovy_trh/kurzy_devizoveho_trhu/denni_kurz.xml
// TODO 1. - Cilem je upravit zobrazovani tak aby se zobrazovalo: vlajka statu, nazev meny, kod statu, nazev statu a cena.
// TODO 1. - Kazdy radek listu je reprezentovan instancemi tridy Entry, v tento okamzik je ve tride Entry udaj pouze pro kod zeme
// TODO 1. - V nastaveni aplikace muzete nastavit jestli se maji data stahovat pouze pres wifi
//  |-----------|----------------------------------|
//  | ImageView |          NAZEV MENY              |  
//  |           |Kod                               | 
//  |  vlajka   |Stat                              |
//  |   statu   |Cena                              | 
//  |           |                                  |
//  |----------------------------------------------|
//

public class CNBAdapter extends ArrayAdapter<Entry>{

    Context context;
    int layoutResourceId;   
    List<Entry> data = null;
    
   
    public CNBAdapter(Context context, int layoutResourceId, List<Entry> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        EntryHolder holder = null;
       int imageViewID;

        if(row == null) {
        	LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );      	
            row = inflater.inflate(layoutResourceId, parent, false);
           
            holder = new EntryHolder();
            holder.txtZeme = row.findViewById(R.id.txtZeme);
            holder.txtKod = row.findViewById(R.id.txtKod);
            holder.txtKurz = row.findViewById(R.id.txtKurz);
            holder.txtMena = row.findViewById(R.id.txtMena);
            holder.imageView = row.findViewById(R.id.img);
            
            row.setTag(holder);
        } else {
            holder = (EntryHolder)row.getTag();
        }
       
        Entry entry = data.get(position);
        holder.txtZeme.setText(entry.zeme);
        holder.txtKod.setText(entry.kod);
        holder.txtKurz.setText(entry.kurz);
        holder.txtMena.setText(entry.mena.toUpperCase());

        imageViewID = context.getResources().getIdentifier("@drawable/" + "flag_" + entry.kod.toLowerCase(), null, context.getPackageName());
        holder.imageView.setImageResource(imageViewID);
        
        return row;
    }
   
    static class EntryHolder {
        TextView txtZeme;
        TextView txtKod;
        TextView txtKurz;
        TextView txtMena;
        ImageView imageView;
    }
}
    