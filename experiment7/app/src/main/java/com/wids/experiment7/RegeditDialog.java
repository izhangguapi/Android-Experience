package com.wids.experiment7;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegeditDialog extends Dialog implements View.OnClickListener {

    String usrname;
    Button cancleBtn, regeditBtn;
    Context contextactivity;
    EditText usrnameEdt, passwdEdt, passwdaginEdt;
    public DBAdapter dbAdepter;
    public UsrInfo usrinfo;

    public RegeditDialog(Context context, DBAdapter dbAdepter) {
        super(context);
        this.dbAdepter = dbAdepter;
        contextactivity = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regeditdialog_layout);
        initView();
    }

    private void initView() {
        cancleBtn = (Button) findViewById(R.id.canclebutton);
        regeditBtn = (Button) findViewById(R.id.regitbutton);
        usrnameEdt = (EditText) findViewById(R.id.usrnameEditText);
        passwdEdt = (EditText) findViewById(R.id.passwordeditText);
        passwdaginEdt = (EditText) findViewById(R.id.passwordagaineditText);
        usrinfo = new UsrInfo();
        cancleBtn.setOnClickListener(this);
        regeditBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regitbutton:
                String psd, psdagin;
                psd = passwdEdt.getText().toString();
                psdagin = passwdaginEdt.getText().toString();
                if (psd.equals(psdagin) != true) {
                    Toast.makeText(contextactivity, "regit failed,password not same", Toast.LENGTH_SHORT).show();
                } else {
                    usrinfo.Number = usrnameEdt.getText().toString();
                    usrinfo.Passwd = psd;
                    dbAdepter.open();
                    dbAdepter.insert(usrinfo);
                    dbAdepter.close();
                    Toast.makeText(contextactivity, "insert success", Toast.LENGTH_SHORT).show();
                    dismiss();
                }
                break;
            case R.id.canclebutton:
                dismiss();
                break;
            default:
                break;
        }
    }
}

