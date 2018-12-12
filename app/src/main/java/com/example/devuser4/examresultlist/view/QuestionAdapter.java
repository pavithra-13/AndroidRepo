package com.example.devuser4.examresultlist.view;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.devuser4.examresultlist.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private Context context;
    private List<ResultModel.Data.Question> quesList = new ArrayList<>();
    private OnClickListener clickListener;

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_number)
        TextView ques_list_no;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public QuestionAdapter(Context context) {
        this.context = context;
    }

    public void setQuestionList(List<ResultModel.Data.Question> quesList){
        this.quesList = quesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.rv_queslist
                ,parent,false);

        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        List<ResultModel.Data.Question> questions = (List<ResultModel.Data.Question>) quesList.get(position);
        String str = String.valueOf(position + 1);
        holder.ques_list_no.setText(str);
        holder.ques_list_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.callView(position);
                System.out.println("Position in clicklistener....." + position);
            }
        });
    }

    public void setListener(OnClickListener onClickListener){
        clickListener = onClickListener;
    }

    public interface OnClickListener{
        void callView(Integer pos);
    }

    @Override
    public int getItemCount() {

        if (quesList.size() > 0)
            return quesList.size();
        else
            return 0;
    }


}
