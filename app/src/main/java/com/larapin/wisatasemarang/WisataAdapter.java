package com.larapin.wisatasemarang;

import android.annotation.SuppressLint;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

/**
 * Created by Avin on 07/10/2018.
 */

public class WisataAdapter extends RecyclerView.ViewHolder {
    private ImageView ivGambar;
    public TextView tvNama;
    public TextView tvAlamat;
    public TextView tvJarak;

    public WisataAdapter(View itemView){
        super(itemView);
        ivGambar = itemView.findViewById(R.id.iv_gambar);
        tvNama = itemView.findViewById(R.id.tv_nama);
        tvAlamat = itemView.findViewById(R.id.tv_alamat);
        tvJarak = itemView.findViewById(R.id.tv_jarak);


    }

    @SuppressLint("SetTextI18n")
    public void bindTo(WisataModel wisata, double longitude, double latitude, View.OnClickListener onClickListener){
        Location loc1 = new Location("");
        loc1.setLatitude(latitude);
        loc1.setLongitude(longitude);

        Location loc2 = new Location("");
        loc2.setLatitude(wisata.dblLat);
        loc2.setLongitude(wisata.dblLng);

        float jarakMeter = loc1.distanceTo(loc2);
        float jarakKM = jarakMeter/1000;

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        Picasso.with(itemView.getContext()).load(wisata.gambarWisata).into(ivGambar);
        tvNama.setText(wisata.strNamaWisata);
        tvAlamat.setText(wisata.strAlamat);
        tvJarak.setText(""+df.format(jarakKM)+" KM");
        itemView.setOnClickListener(onClickListener);
    }
}
