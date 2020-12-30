package com.ahao.practise.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ahao.practise.DetailActivity;
import com.ahao.practise.MainActivity;
import com.ahao.practise.R;
import com.ahao.practise.dao.GridImageViewItem;
import com.squareup.picasso.Picasso;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    /*
    * LayoutInflater是一个抽象类，他的实现用于加载布局
    * */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        GridView gridView = (GridView) root.findViewById(R.id.dashboard_grid);
        gridView.setOnItemClickListener(mOnItemClickListener);
        GridAdapter adapter = new GridAdapter();
        gridView.setAdapter(adapter);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //gridView
            }
        });

        return root;
    }

    private final AdapterView.OnItemClickListener mOnItemClickListener
            = new AdapterView.OnItemClickListener() {

        /**
         * Called when an item in the {@link android.widget.GridView} is clicked. Here will launch
         * the {@link DetailActivity}, using the Scene Transition animation functionality.
         */
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            GridImageViewItem item = (GridImageViewItem) adapterView.getItemAtPosition(position);

            // Construct an Intent as normal
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_PARAM_ID, item.getId());

            // BEGIN_INCLUDE(start_activity)
            /*
             * Now create an {@link android.app.ActivityOptions} instance using the
             * {@link ActivityOptionsCompat#makeSceneTransitionAnimation(Activity, Pair[])} factory
             * method.
             */
            @SuppressWarnings("unchecked")
            ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    getActivity(),

                    // Now we provide a list of Pair items which contain the view we can transitioning
                    // from, and the name of the view it is transitioning to, in the launched activity
                    new Pair<>(view.findViewById(R.id.imageview_item),
                            DetailActivity.VIEW_NAME_HEADER_IMAGE),
                    new Pair<>(view.findViewById(R.id.textview_name),
                            DetailActivity.VIEW_NAME_HEADER_TITLE));

            // Now we can start the Activity, providing the activity options as a bundle
            ActivityCompat.startActivity(getActivity(), intent, activityOptions.toBundle());
            // END_INCLUDE(start_activity)
        }
    };

    /**
     * {@link android.widget.BaseAdapter} which displays items.
     */
    private class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return GridImageViewItem.ITEMS.length;
        }

        @Override
        public GridImageViewItem getItem(int position) {
            return GridImageViewItem.ITEMS[position];
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getId();
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.dashboard_grid_item, viewGroup, false);
            }

            final GridImageViewItem item = getItem(position);

            // Load the thumbnail image
            ImageView image = view.findViewById(R.id.imageview_item);
            String url = item.getThumbnailUrl();
            Picasso.with(image.getContext()).load(url).into(image);

            // Set the TextView's contents
            TextView name = view.findViewById(R.id.textview_name);
            name.setText(item.getName());

            return view;
        }
    }
}