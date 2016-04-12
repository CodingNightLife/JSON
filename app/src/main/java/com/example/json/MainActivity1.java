package com.example.t31054.json;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MainActivity1 extends Activity {
    private static String url = "https://api.myjson.com/bins/2ggcs";
    CustomAdapter adapter;
    String companyID, comapnyName;
    ArrayList<HashMap<String, String>> jsonlist = new ArrayList<HashMap<String, String>>();
    EditText search;
    ListView lv;
    List<Company> countrylist = new ArrayList<Company>();
    SearchView searchView, search_view;
    ArrayList<Company> mStringFilterList;
    HashMap<String, String> map = new HashMap<String, String>();
    TextView empty;
    private Context context;
    private EditText edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getActionBar().setDisplayUseLogoEnabled(false);
        lv = (ListView) findViewById(R.id.list);
        search = (EditText) findViewById(R.id.search);
        empty = (TextView) findViewById(android.R.id.empty);

        search_view = (SearchView) findViewById(R.id.search_view);

        search.setVisibility(View.GONE);
        search_view.setVisibility(View.GONE);

        new ProgressTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                if (adapter != null) {
                    adapter.resetData();
                    search.setVisibility(View.GONE);
                    search_view.setVisibility(View.VISIBLE);
                    search_view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            adapter.resetData();
                            String query = search_view.getQuery().toString();
                            if (query.length() != 0) {

                                adapter.filternew().filter(query);

                            }
                            if(lv.getCount()==0){
                                empty.setVisibility(View.VISIBLE);
                                lv.setEmptyView(empty);
                            }


                        }
                    });


                    SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextChange(String newText) {
                            // this is your adapter that will be filtered
                            if (newText.length() == 0) {
                                adapter.resetData();
                            }


                            adapter.filternew().filter(newText);


                            return true;
                        }

                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            // this is your adapter that will be filtered

                            adapter.filternew().filter(query);


                            return true;
                        }
                    };
                    search_view.setOnQueryTextListener(textChangeListener);
                    search_view.setOnCloseListener(new SearchView.OnCloseListener() {
                        @Override
                        public boolean onClose() {
                            adapter.filternew().filter("");

                            return true;
                        }
                    });

                }


                return true;
            case R.id.action_filter:
                if (adapter != null) {
                    adapter.resetData();
                    search_view.setVisibility(View.GONE);
                    search.setVisibility(View.VISIBLE);
                    search.addTextChangedListener(new TextWatcher() {

                        @Override
                        public void afterTextChanged(Editable arg0) {
                            // TODO Auto-generated method stub
                            String text = search.getText().toString().toLowerCase(Locale.getDefault());
                            adapter.getFilter().filter(text);

                            if(lv.getCount()==0){
                                empty.setVisibility(View.VISIBLE);
                                lv.setEmptyView(empty);
//                                Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void beforeTextChanged(CharSequence arg0, int arg1,
                                                      int arg2, int arg3) {
                            // TODO Auto-generated method stub
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before,
                                                  int count) {
                            // TODO Auto-generated method stub
                            if (count < before) {
                                // We're deleting char so we need to reset the adapter data
                                adapter.resetData();
                            }
                            MainActivity1.this.adapter.getFilter().filter(s.toString());

                            if(lv.getCount()==0){
                                empty.setVisibility(View.VISIBLE);
                                lv.setEmptyView(empty);
//                                Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                }

                if (adapter == null) {
                    empty.setVisibility(View.VISIBLE);
                    lv.setEmptyView(empty);
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private class ProgressTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog dialog;

        private Activity activity;

        public ProgressTask() {
            dialog = new ProgressDialog(MainActivity1.this);
        }


        protected void onPreExecute() {
            this.dialog.setMessage("Loading...");
            this.dialog.show();
        }


        protected Boolean doInBackground(final String... args) {

            JSONParser jParser = new JSONParser();

            // get JSON data from URL
            JSONArray json = jParser.getJSONFromUrl("https://api.myjson.com/bins/2ggcs");
            if (json.length() != 0) {
                for (int i = 0; i < json.length(); i++) {

                    try {
                        JSONObject c = json.getJSONObject(i);
                        String companyOwner = c.getString("companyOwner");
                        String companyStartDate = c.getString("companyStartDate");
                        String companyDescription = c.getString("companyDescription");
                        String companyDepartments = c.getString("companyDepartments");

                        companyID = c.getString("companyID");

                        comapnyName = c.getString("comapnyName");


                        Company country = new Company(companyID, comapnyName, companyOwner, companyStartDate, companyDescription, companyDepartments);
                        countrylist.add(country);
                        jsonlist.add(map);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            adapter = new CustomAdapter(getApplicationContext(), countrylist);
            lv.setAdapter(adapter);
            lv.setTextFilterEnabled(true);

        }
    }
}


