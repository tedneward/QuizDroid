package com.example.QuizDroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ted on 2/21/14.
 */
public class TopicAdapter extends ArrayAdapter<Topic> {

    private Context context;
    private int resId;
    private List<Topic> list;

    static class ViewHolder
    {
        public ImageView imgIcon;
        public TextView txtName;
        public TextView txtDesc;
    }

    public TopicAdapter(Context context, int textViewResourceId, List<Topic> objects) {
        super(context, textViewResourceId, objects);

        this.context = context;
        this.resId = textViewResourceId;
        this.list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        Topic topic = list.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rowView = inflater.inflate(R.layout.quiz_list_item, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.imgIcon = (ImageView) rowView.findViewById(R.id.imgTopicListItemImage);
            viewHolder.txtName = (TextView) rowView.findViewById(R.id.txtTopicListItemName);
            viewHolder.txtDesc = (TextView) rowView.findViewById(R.id.txtTopicListItemDescription);
            rowView.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder) rowView.getTag();
        viewHolder.imgIcon.setImageResource(R.drawable.ic_launcher);
        viewHolder.txtName.setText(topic.getName());
        viewHolder.txtDesc.setText(topic.getDescription());

        return rowView;
    }
}
