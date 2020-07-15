package com.example.orion.dia3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Intenciones extends AppCompatActivity {

    @BindView(R.id.imvFoto)
    ImageView imvFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intenciones);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnWeb)
    public void verPagina(){
        Intent intWeb=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.itcelaya.edu.mx"));
        startActivity(intWeb);
    }

    @OnClick(R.id.btnTel)
    public void realizarLlamada(){
        try{
            Intent intTel= new Intent(Intent.ACTION_CALL, Uri.parse("tel:4772684735"));
            startActivity(intTel);
        }catch (SecurityException e){}
    }

    @OnClick({R.id.btnSMS, R.id.btnEmail,R.id.btnFoto})
    public void sendSMS(View v){
        switch (v.getId()){
            case R.id.btnSMS:
                SmsManager sms= SmsManager.getDefault();
                sms.sendTextMessage("4772684735",null,"Hola",null,null);
                break;
            case R.id.btnEmail:
                String emails[]= {"aragon.c08@gmail.com","aragon.c08@gmail.com"};
                Intent intEmail=new Intent(Intent.ACTION_SEND);
                intEmail.setType("text/plain");
                intEmail.putExtra(Intent.EXTRA_SUBJECT,"Asunto");
                intEmail.putExtra(Intent.EXTRA_EMAIL,emails);
                //intEmail.putExtra(Intent.EXTRA_CC,emails);
                //intEmail.putExtra(Intent.EXTRA_BCC,emails);
                intEmail.putExtra(Intent.EXTRA_TEXT,"Aqui va el texto");
                startActivity(intEmail);
                break;
            case R.id.btnFoto:
                Intent intFoto= new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intFoto,10);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==10 && resultCode==RESULT_OK){
            Bitmap foto=(Bitmap) data.getExtras().get("data");
            imvFoto.setImageBitmap(foto);
        }else
            Toast.makeText(this,"No se tomo la foto", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.acercade, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.itmAcerca){
            Intent intent= new Intent(this,Acerca.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
