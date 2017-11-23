package com.curtesmalteser.searchitunes.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.curtesmalteser.searchitunes.R;
import com.curtesmalteser.searchitunes.model.ITunesData;

import java.util.List;

/**
 * Created by anton on 23/11/2017.
 */

public class ItemAdapter extends BaseAdapter implements ListAdapter{

    private List<ITunesData> mItems;
    private Context mContext;

    public ItemAdapter(Context context, List<ITunesData> items) {
        this.mItems = items;

        // The context here is use to pass the activity because we are
        // working on a class and not on the activity itself
        this.mContext = context;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.list_items, null);

        ITunesData iTunesItems = mItems.get(position);
        String trackName = iTunesItems.getTrackName();
        String wrapperType = iTunesItems.getWrapperType();
        String kind = iTunesItems.getKind();
        String artistName = iTunesItems.getArtistName();
        String collectionName = iTunesItems.getCollectionName();
        Bitmap artworkUrl100 = iTunesItems.getArtworkUrl100();


        TextView mTextTrackName = (TextView) view.findViewById(R.id.tv_track_name);
        TextView mTextWrapperType = (TextView) view.findViewById(R.id.tv_wrapper_type);
        TextView mTextKind = (TextView) view.findViewById(R.id.tv_kind);
        TextView mTextArtistName = (TextView) view.findViewById(R.id.tv_artist_name);
        TextView mTextCollectionName = (TextView) view.findViewById(R.id.tv_collection_name);
        ImageView mTextArtworkUrl = (ImageView) view.findViewById(R.id.img_artwork);

        mTextTrackName.setText(trackName);
        mTextWrapperType.setText(wrapperType);
        mTextKind.setText(kind);
        mTextArtistName.setText(artistName);
        mTextCollectionName.setText(collectionName);
        mTextArtworkUrl.setImageBitmap(artworkUrl100);

        return view;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mItems.get(position).getId();
    }
}
