package com.example.radud.quizapp.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.radud.quizapp.Question;
import com.example.radud.quizapp.R;

import java.util.List;

/**
 * Created by radud on 20/05/2017.
 */

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder> {

    private List<Question> questionList;

    public QuestionsAdapter(List<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public QuestionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QuestionsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(QuestionsViewHolder holder, int position) {
        Question question = questionList.get(position);

        holder.body.setText(question.getBody());
        holder.a.setText(question.getResponseList().get(0));
        holder.b.setText(question.getResponseList().get(1));
        holder.c.setText(question.getResponseList().get(2));
        holder.d.setText(question.getResponseList().get(3));

        holder.a.setTextColor(Color.RED);
        holder.b.setTextColor(Color.RED);
        holder.c.setTextColor(Color.RED);
        holder.d.setTextColor(Color.RED);

        switch (question.getRightResponse()) {
            case 0:
                holder.a.setTextColor(Color.GREEN);
                break;
            case 1:
                holder.b.setTextColor(Color.GREEN);
                break;
            case 2:
                holder.c.setTextColor(Color.GREEN);
                break;
            case 3:
                holder.d.setTextColor(Color.GREEN);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public void updateQuestions(List<Question> questionList) {
        this.questionList = questionList;
        notifyDataSetChanged();
    }

    class QuestionsViewHolder extends RecyclerView.ViewHolder {

        TextView body;
        TextView a;
        TextView b;
        TextView c;
        TextView d;

        public QuestionsViewHolder(View itemView) {
            super(itemView);

            body = (TextView) itemView.findViewById(R.id.body_text_view);
            a = (TextView) itemView.findViewById(R.id.a_text_view);
            b = (TextView) itemView.findViewById(R.id.b_text_view);
            c = (TextView) itemView.findViewById(R.id.c_text_view);
            d = (TextView) itemView.findViewById(R.id.d_text_view);
        }
    }
}
