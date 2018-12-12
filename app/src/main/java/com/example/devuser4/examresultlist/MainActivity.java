package com.example.devuser4.examresultlist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.devuser4.examresultlist.remote.ApiClient;
import com.example.devuser4.examresultlist.remote.ApiService;
import com.example.devuser4.examresultlist.view.QuestionFrg;
import com.example.devuser4.examresultlist.view.ResultModel;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<ResultModel.Data.Question> quesList;
    private List<ResultModel.Data.Exam> examList;
    Integer position =0;
    Date date;
    Integer crt = 0, wrng =0, skip = 0;
    private ApiService apiService;

    @BindView(R.id.strt_time)
    TextView start_time;

    @BindView(R.id.end_time)
    TextView end_time;

    @BindView(R.id.duration)
    TextView duration;

    @BindView(R.id.crt_ans)
    TextView crt_ans;

    @BindView(R.id.skipped_ans)
    TextView skipped;

    @BindView(R.id.wrng_ans)
    TextView wrng_ans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        viewcreation();

    }

    private void viewcreation(){

        apiService = ApiClient.getClient(getApplicationContext()).create(ApiService.class);

        Call<ResultModel> call = apiService.getResults();
        call.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                if(response.body().getStatus()){
                    if(response.body().getData()!= null) {
                        if (response.body().getData().getExams() != null &&
                                response.body().getData().getExams().size() >0){
                            examList = response.body().getData().getExams();
                            ResultModel.Data.Exam exam = examList.get(position);
                            start_time.setText(dateConversion(exam.getStartTime()));
                            end_time.setText(dateConversion(exam.getEndTime()));
                            duration.setText(exam.getDuration());
                        }
                        if (response.body().getData().getQuestions() != null &&
                                response.body().getData().getQuestions().size() > 0) {
                            System.out.println("In question list    ");
                            quesList = response.body().getData().getQuestions();
                            System.out.println("quesListtt     ");
                            for (int i = 0; i < quesList.size(); i++) {
                                ResultModel.Data.Question ques = quesList.get(i);
                                ResultModel.Data.Question.UserDatum xxx = ques.getUserData().get(0);
                                String str = xxx.getAnswerStatus();
                                if (str.equals("0")) {
                                    skip = skip + 1;
                                } else if (str.equals("1")) {
                                    crt = crt + 1;
                                } else if (str.equals("2")) {
                                    wrng = wrng + 1;
                                }
                            }
                        }
                        crt_ans.setText("" + crt);
                        wrng_ans.setText("" + wrng);
                        skipped.setText("" + skip);
                    }
                }
                callFrg();
            }

            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {

            }
        });
    }

    public String dateConversion(String date)
    {
        String[] arrOfStr = date.split(" ", 2);
        String time = arrOfStr[1];
        String[] arrOfStr1 = time.split(":", 3);
        String str = arrOfStr1[0];
        for (int i = 1; i < 3; i++)
            str = str + "/" + arrOfStr1[i];
        System.out.println("Time : " + str);
        return str;
    }

    public void callFrg(){
        Fragment fragment = new QuestionFrg();
        String backStack = fragment.getClass().getName();
        Bundle bundle = new Bundle();
        bundle.putInt("Position" , 0);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.rootlayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onBackPressed(){
        finish();
    }

}
