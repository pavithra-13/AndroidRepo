package com.example.devuser4.examresultlist.view;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.devuser4.examresultlist.R;
import com.example.devuser4.examresultlist.remote.ApiClient;
import com.example.devuser4.examresultlist.remote.ApiService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionFrg extends Fragment implements QuestionAdapter.OnClickListener {

    View view;
    ApiService apiService;
    private List<ResultModel.Data.Question> quesList;
    public Integer crt_optn,wrng_optn;
    private Integer position;
    private int left = 0, right = 0;
    private QuestionAdapter quesAdapter;

    @BindView(R.id.ques_no)
    TextView ques_no;

    @BindView(R.id.tv_quesiton)
    TextView ques;

    @BindView(R.id.tv_optn0)
    TextView option0;

    @BindView(R.id.tv_optn1)
    TextView option1;

    @BindView(R.id.tv_optn2)
    TextView option2;

    @BindView(R.id.tv_optn3)
    TextView option3;

    @BindView(R.id.rv_queslist)
    RecyclerView rv_ques_list;

    private ImageButton rightBtn;
    private ImageButton leftBtn;
    public QuestionFrg() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null){
            position = bundle.getInt("Position");
        }
        else{
            System.out.println("Bundle empty...");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ButterKnife.bind(getActivity());

        view = inflater.inflate(R.layout.fragment_question_frg, container,
                false);
        viewCreation();
        return view;
    }

    public void callView(Integer pos){

        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.popBackStack();

        Fragment newFragment = new QuestionFrg();
        String backStackFrg = newFragment.getClass().getName();

        Bundle bundle = new Bundle();
        bundle.putInt("Position", pos);
        newFragment.setArguments(bundle);

        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.rootlayout,newFragment);
        ft.addToBackStack(backStackFrg);

        ft.commit();
    }
    private void viewCreation() {

        apiService = ApiClient.getClient(getActivity()).create(ApiService.class);

        LinearLayout bottom_sheet = view.findViewById(R.id.view_item_question);
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(bottom_sheet);
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    behavior.setState(behavior.STATE_COLLAPSED);
                    behavior.setPeekHeight(500);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                behavior.setPeekHeight(100);
            }
        });
        rightBtn = view.findViewById(R.id.right_btn);
        leftBtn = view.findViewById(R.id.left_btn);

        rightBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (position < quesList.size()-1) {
                    position = position + 1;
                    System.out.println("Position cal on click ..... " + position);
                    callView(position);
                }
                else{}
            }
        });

        leftBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(position > 0) {
                    position = position - 1;
                    callView(position);
                }
                else {}
            }
        });


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager
                (getActivity(), 5);

        rv_ques_list = view.findViewById(R.id.rv_queslist);
        rv_ques_list.setLayoutManager(layoutManager);

        quesAdapter = new QuestionAdapter(getActivity());
        quesAdapter.setListener(this);
        rv_ques_list.setAdapter(quesAdapter);

        Call<ResultModel> call = apiService.getResults();
        call.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                if(response.body().getStatus()){
                    if(response.body().getData()!= null) {
                        if (response.body().getData().getQuestions() != null &&
                                response.body().getData().getQuestions().size() > 0) {
                            quesList = response.body().getData().getQuestions();
                            final ResultModel.Data.Question question = quesList.get(position);
                            String str = ""+(position+1)+")";
                            ques_no = view.findViewById(R.id.ques_no);
                            ques_no.setText(str);
                            ques = view.findViewById(R.id.tv_quesiton);
                            ques.setText(Html.fromHtml(Html.fromHtml(question.getQuestion()).
                                    toString()));
                            option0 = view.findViewById(R.id.tv_optn0);
                            option0.setText(Html.fromHtml(Html.fromHtml(question.getOptions().get(0).
                                    getOption()).toString()));
                            option1 = view.findViewById(R.id.tv_optn1);
                            option1.setText(Html.fromHtml(Html.fromHtml(question.getOptions().get(1).
                                    getOption()).toString()));
                            option2 = view.findViewById(R.id.tv_optn2);
                            option2.setText(Html.fromHtml(Html.fromHtml(question.getOptions().get(2).
                                    getOption()).toString()));
                            option3 = view.findViewById(R.id.tv_optn3);
                            option3.setText(Html.fromHtml(Html.fromHtml(question.getOptions().get(3).
                                    getOption()).toString()));
                            if(question.getUserData().get(0).getAnswerStatus().equals("0")) {
                            }
                            else if(question.getUserData().get(0).getAnswerStatus().equals("1")) {
                                for(int i = 0; i < 4; i++) {
                                    if (question.getUserData().get(0).getUserAnswer().equals(
                                            question.getOptions().get(i).getOptionId())) {
                                        crt_optn = i;
                                    }
                                }

                                switch(crt_optn){
                                    case 0:
                                        option0.setBackgroundResource(R.drawable.crt_option);
                                        break;
                                    case 1:
                                        option1.setBackgroundResource(R.drawable.crt_option);
                                        break;
                                    case 2:
                                        option2.setBackgroundResource(R.drawable.crt_option);
                                        break;
                                    case 3:
                                        option3.setBackgroundResource(R.drawable.crt_option);
                                        break;
                                }
                            }

                            else if(question.getUserData().get(0).getAnswerStatus().equals("2")) {
                                for(int i = 0; i < 4; i++){
                                    if(question.getUserData().get(0).getUserAnswer().equals(
                                            question.getOptions().get(i).getOptionId())){
                                        wrng_optn = i;
                                    }
                                    else if(question.getUserData().get(0).getCorrectAnswer().equals(
                                            question.getOptions().get(i).getOptionId())){
                                        crt_optn = i;
                                    }
                                }
                                switch(wrng_optn){
                                    case 0:
                                        option0.setBackgroundResource(R.drawable.wrng_option);
                                        break;
                                    case 1:
                                        option1.setBackgroundResource(R.drawable.wrng_option);
                                        break;
                                    case 2:
                                        option2.setBackgroundResource(R.drawable.wrng_option);
                                        break;
                                    case 3:
                                        option3.setBackgroundResource(R.drawable.wrng_option);
                                        break;
                                }
                                switch(crt_optn){
                                    case 0:
                                        option0.setBackgroundResource(R.drawable.crt_option);
                                        break;
                                    case 1:
                                        option1.setBackgroundResource(R.drawable.crt_option);
                                        break;
                                    case 2:
                                        option2.setBackgroundResource(R.drawable.crt_option);
                                        break;
                                    case 3:
                                        option3.setBackgroundResource(R.drawable.crt_option);
                                        break;
                                }
                            }
                        }
                    }

                    System.out.println("Call adapter  ");
                    setAdapter();
                }

            }

            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {

            }
        });


    }

    private void setAdapter() {
        if (quesList.size() > 0)
            System.out.println("list in call adapter");
        quesAdapter.setQuestionList(quesList);
    }

}
