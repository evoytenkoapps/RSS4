package ru.evoytenkoapps.rss;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evv on 21.03.2017.
 */

public class SecondRecyclerAdapter extends RecyclerView.Adapter<SecondRecyclerAdapter.ViewHolder>
{
    private List<String> mDataset = new ArrayList<String>();
    private Context context;


    // класс view holder-а с помощью которого мы получаем ссылку на каждый элемент
    // отдельного пункта списка
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        // наш пункт состоит только из одного TextView
        public TextView mTextView;
        public ViewHolder( View v)
        {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.tv_recycler_item);
        }
    }

    // Конструктор
    public SecondRecyclerAdapter(List list)
    {
        mDataset = list;
    }

    // Создает новые views (вызывается layout manager-ом)
    @Override
    public SecondRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)
        ViewHolder vh = new ViewHolder(v);
        // Берем контекст для тоаст
        context = parent.getContext();
        return vh;
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.mTextView.setText(mDataset.get(position).toString());
        final int p = position;

        // Вызов WebActivity
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Context context = v.getContext();
                TextView tv = (TextView) v;
                String str = tv.getText().toString();
                Intent intent = new Intent(context, ThirdActivity.class);
                //Получаем ссыдку на RSS канал и вызываем новое активити
                intent.putExtra(SecondActivity.EXTRA_NAME, str);
                context.startActivity(intent);
            }
        });
    }

    // Возвращает размер данных (вызывается layout manager-ом)
    @Override
    public int getItemCount()
    {
        return mDataset.size();
    }
}
