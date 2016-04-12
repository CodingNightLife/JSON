package com.example.t31054.json;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomAdapter extends BaseAdapter implements Filterable {

    Context context;
    List<Company> companyList;
    List<Company> filterList=new ArrayList<>();
    List<Company> mStringFilterList;
	private ArrayList<Company> arraylist;
    List<String> arrayListNames;
    CompanyFilter filterText;
    CompanyFilter1 filterText1;
int count_value=100;
    CustomAdapter(Context context , List<Company> companyList) {
        this.context = context;
        this.companyList = companyList;
        mStringFilterList = companyList;
        this.arraylist = new ArrayList<Company>();
		this.arraylist.addAll(companyList);
    }

    @Override
    public int getCount() {
        if(companyList==null){
            return 0;
        }
        return companyList.size();
    }

    @Override
    public Object getItem(int position) {
        if(companyList==null){
            return 0;
        }
        return companyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        if(companyList==null){
            return 0;
        }
        return companyList.indexOf(getItem(position));
    }

    @Override
    public View getView(int position , View convertView , ViewGroup parent ) {

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        convertView = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);

            TextView companyID = (TextView) convertView.findViewById(R.id.companyID);
            TextView comapnyName = (TextView) convertView.findViewById(R.id.comapnyName);
            TextView companyDepartments = (TextView) convertView.findViewById(R.id.companyDepartments);
            TextView companyDescription = (TextView) convertView.findViewById(R.id.companyDescription);
            TextView companyOwner = (TextView) convertView.findViewById(R.id.companyOwner);
            TextView companyStartDate = (TextView) convertView.findViewById(R.id.companyStartDate);
                Company company = companyList.get(position);

                companyID.setText(company.getCompanyID());
                comapnyName.setText(company.getComapnyName());
            companyDepartments.setText(company.getCompanyDepartments());
                companyDescription.setText(company.getCompanyDescription());
            companyOwner.setText(company.getCompanyOwner());
            companyStartDate.setText(company.getCompanyStartDate());

            
        }
        return convertView;
    }
    public Filter filternew(){
        if (filterText1 == null) {
            filterText1 = new CompanyFilter1();
        }
        return filterText1;
    }



    @Override
    public Filter getFilter() {

        if (filterText == null) {
            filterText = new CompanyFilter();
        }
        return filterText;
    }
    public void resetData() {
        companyList = mStringFilterList;
    }
    private class CompanyFilter extends Filter {



        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                // No filter implemented we return all the list
                results.values = companyList;
                results.count = companyList.size();
            }
            else {
                // We perform filtering operation
                List<Company> nPlanetList = new ArrayList<Company>();

                for (Company p : companyList) {
                    if (p.getCompanyDepartments().toLowerCase().contains(constraint.toString()))
                        nPlanetList.add(p);


                }

                results.values = nPlanetList;
                results.count = nPlanetList.size();

                }




            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            // Now we have to inform the adapter about the new list filtered
            if (results.count == 0) {
                companyList = (List<Company>) results.values;
//                Toast.makeText(context,"No results",1000).show();
                notifyDataSetInvalidated();
            }

            else {

                companyList = (List<Company>) results.values;
                notifyDataSetChanged();
            }

        }



    }

    private class CompanyFilter1 extends Filter {



        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                // No filter implemented we return all the list
                results.values = companyList;

                results.count = companyList.size();
            }
            else {
                // We perform filtering operation
                List<Company> list = new ArrayList<Company>();

                for (Company p : companyList) {
                    if (p.getCompanyID().toLowerCase().contains(constraint.toString())) {
                        list.add(p);

                    }
                    else if (p.getComapnyName().toLowerCase().contains(constraint.toString())) {
                        list.add(p);
                    }
                   else if (p.getCompanyDepartments().toLowerCase().contains(constraint.toString())) {
                        list.add(p);
                    }
                    else if (p.getCompanyDescription().toLowerCase().contains(constraint.toString())) {
                        list.add(p);
                    }
                    else if (p.getCompanyOwner().toLowerCase().contains(constraint.toString())) {
                        list.add(p);
                    }
                    else if (p.getCompanyStartDate().toLowerCase().contains(constraint.toString())) {
                        list.add(p);
                    }



                }
                results.values = list;
                filterList=list;
                results.count = list.size();

            }


            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            // Now we have to inform the adapter about the new list filtered
            if (results.count == 0) {
                companyList = (List<Company>) results.values;
                notifyDataSetInvalidated();

            }
            else {
                companyList = (List<Company>) results.values;
                notifyDataSetChanged();
            }

        }

    }
}

