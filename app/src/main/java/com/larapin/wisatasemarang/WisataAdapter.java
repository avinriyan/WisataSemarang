package com.larapin.wisatasemarang;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Avin on 07/10/2018.
 */

public class WisataAdapter extends RecyclerView.ViewHolder {
    public TextView tvNama;
    public TextView tvAlamat;
    public TextView tvTelp;
    public TextView tvTiket;

    public WisataAdapter(View itemView){
        super(itemView);
        tvNama = itemView.findViewById(R.id.tv_nama);
        tvAlamat = itemView.findViewById(R.id.tv_alamat);
        tvTelp = itemView.findViewById(R.id.tv_telp);
        tvTiket = itemView.findViewById(R.id.tv_tiket);
    }

    public void bindTo(WisataModel wisata, View.OnClickListener onClickListener){
        tvNama.setText(wisata.strNamaWisata);
        tvAlamat.setText(wisata.strAlamat);
        tvTelp.setText(wisata.strTelepon);
        tvTiket.setText(wisata.strTiket);
        itemView.setOnClickListener(onClickListener);
    }
}
