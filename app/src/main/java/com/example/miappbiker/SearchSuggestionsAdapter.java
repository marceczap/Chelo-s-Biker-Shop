package com.example.miappbiker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchSuggestionsAdapter extends RecyclerView.Adapter<SearchSuggestionsAdapter.ViewHolder> {

    public interface OnProductClickListener {
        void onProductClick(HomeActivity.SearchProduct product);
    }

    private final List<HomeActivity.SearchProduct> items = new ArrayList<>();
    private final OnProductClickListener listener;

    public SearchSuggestionsAdapter(OnProductClickListener listener) {
        this.listener = listener;
    }

    public void updateData(List<HomeActivity.SearchProduct> newItems) {
        items.clear();
        if (newItems != null) {
            items.addAll(newItems);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_suggestion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeActivity.SearchProduct product = items.get(position);
        holder.ivThumb.setImageResource(product.imageRes);
        holder.tvTitle.setText(product.name);
        holder.tvCategory.setText(product.category);
        holder.tvPrice.setText(product.price);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onProductClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumb;
        TextView tvTitle;
        TextView tvCategory;
        TextView tvPrice;

        ViewHolder(View itemView) {
            super(itemView);
            ivThumb = itemView.findViewById(R.id.iv_search_thumb);
            tvTitle = itemView.findViewById(R.id.tv_search_title);
            tvCategory = itemView.findViewById(R.id.tv_search_category);
            tvPrice = itemView.findViewById(R.id.tv_search_price);
        }
    }
}
