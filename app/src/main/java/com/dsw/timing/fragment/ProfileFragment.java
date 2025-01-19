package com.dsw.timing.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dsw.timing.Constant;
import com.dsw.timing.R;
import com.dsw.timing.adapter.SimpleAdapter;
import com.dsw.timing.bean.Student;
import com.dsw.timing.network.NetworkService;
import com.dsw.timing.network.StudentListConvertFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileFragment";
    private RecyclerView mRecyclerView;
    private Retrofit mRetrofit;

    private List<Student> studentList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profilefragment_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.simple_recyclerview);

        //List<String> list = Arrays.asList("Tom", "Jerry", "Lily", "Ben", "Maria");
        //List<String> list2 = new ArrayList<>(list);


//        for(int i=0; i < 10; i++) {
//            list2.add(String.valueOf(i));
//        }
        initNetwork();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new SimpleAdapter(studentList));

    }

    private void initNetwork() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(new StudentListConvertFactory())
                //.addConverterFactory(GsonConverterFactory.create())
                .build();
        NetworkService networkService = mRetrofit.create(NetworkService.class);
        try {
            Call<List<Student>> call = networkService.getStudentInfo();
            call.enqueue(new Callback<List<Student>>() {
                @Override
                public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), "请求成功!", Toast.LENGTH_SHORT).show();
                        studentList = response.body();
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        mRecyclerView.setAdapter(new SimpleAdapter(studentList));
                    }
                }

                @Override
                public void onFailure(Call<List<Student>> call, Throwable t) {
                    Toast.makeText(getContext(), "网络请求失败!", Toast.LENGTH_LONG).show();

                    Log.e(TAG, t.getMessage());
                }
            });
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
