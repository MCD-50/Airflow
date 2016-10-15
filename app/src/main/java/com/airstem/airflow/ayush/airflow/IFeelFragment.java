package com.airstem.airflow.ayush.airflow;




import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.airstem.airflow.ayush.airflow.adapters.CategoryAdapter;


/**
 * Created by ayush on 09-10-16.
 */
public class IFeelFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.i_feel_fragment, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final CategoryAdapter adapter = new CategoryAdapter(getActivity());
        setListAdapter(adapter);
        getListView().setDividerHeight(0);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity) getActivity()).executeIFeelFragmentListViewOnItemSelected(position, adapter);
            }
        });
    }
}
