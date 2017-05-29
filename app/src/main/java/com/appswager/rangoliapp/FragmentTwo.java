package com.appswager.rangoliapp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment implements View.OnClickListener{
    ImageView cartoon,lord,peocock,freehand,round,square;

    public FragmentTwo() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_two, container, false);

    cartoon = (ImageView)viewRoot.findViewById(R.id.cartoon);
      lord = (ImageView)viewRoot.findViewById(R.id.lord);
        peocock = (ImageView)viewRoot.findViewById(R.id.peocock);
        freehand = (ImageView)viewRoot.findViewById(R.id.freehand);
        round = (ImageView)viewRoot.findViewById(R.id.round);
        square = (ImageView)viewRoot.findViewById(R.id.square);

        cartoon.setOnClickListener(this);
        lord.setOnClickListener(this);
        freehand.setOnClickListener(this);
        round.setOnClickListener(this);
        square.setOnClickListener(this);
        peocock.setOnClickListener(this);
        return viewRoot;
    }

  /*  @Override
    public void onClick(View v) {
        Intent i = new Intent(getActivity(),CartoonActivity.class);
        startActivity(i);
    }
*/

@Override
    public void onClick(View v) {

switch (v.getId())
{
    case R.id.cartoon:

        Intent i = new Intent(getActivity(),CartoonActivity.class);
        startActivity(i);
        break;
    case R.id.lord:

        Intent i2 = new Intent(getActivity(),LordActivity.class);
        startActivity(i2);
        break;
    case R.id.peocock:

        Intent i3 = new Intent(getActivity(),PeocockActivity.class);
        startActivity(i3);
        break;
    case R.id.freehand:

        Intent i4 = new Intent(getActivity(),FreeHandActivity.class);
        startActivity(i4);
        break;
    case R.id.round:

        Intent i5 = new Intent(getActivity(),RoundActivity.class);
        startActivity(i5);
        break;
    case R.id.square:

        Intent i6 = new Intent(getActivity(),SquareActivity.class);
        startActivity(i6);
        break;

}
    }
}