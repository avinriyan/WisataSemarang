package com.larapin.wisatasemarang;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

    public static final String EXTRA_WISATA_KEY = "wisata_key";

    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;

    private String wisataKey;

    private TextView tvKey;
    private TextView tvNama;
    private TextView tvAlamat;
    private TextView tvTelpon;
    private TextView tvTiket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        wisataKey = getIntent().getStringExtra(EXTRA_WISATA_KEY);
        if (wisataKey == null){
            throw new IllegalArgumentException("Must pass EXTRA");
        }

        databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("wisata-semarang").child(wisataKey);

        tvKey = findViewById(R.id.tv_key);
        tvNama = findViewById(R.id.tv_nama_detail);
        tvAlamat = findViewById(R.id.tv_alamat_detail);
        tvTelpon = findViewById(R.id.tv_telp_detail);
        tvTiket = findViewById(R.id.tv_tiket_detail);

        tvKey.setText(wisataKey);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                WisataModel wisataModel = dataSnapshot.getValue(WisataModel.class);
                tvNama.setText(wisataModel.strNamaWisata);
                tvAlamat.setText(wisataModel.strAlamat);
                tvTelpon.setText(wisataModel.strTelepon);
                tvTiket.setText(wisataModel.strTiket);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        databaseReference.addValueEventListener(eventListener);
        valueEventListener = eventListener;

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (valueEventListener != null){
            databaseReference.removeEventListener(valueEventListener);
        }
    }
}
