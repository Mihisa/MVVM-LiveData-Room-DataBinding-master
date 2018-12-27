package room.sample.mvvm.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import room.sample.mvvm.databinding.ActivityMainBinding;
import room.sample.mvvm.R;
import room.sample.mvvm.model.UserModel;
import room.sample.mvvm.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener{

    private UserViewModel viewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(binding.toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });

        recyclerView = binding.contentMain.recyclerView;
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<UserModel>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);

        UserViewModel.Factory factory = new UserViewModel.Factory(getApplication());
        viewModel = ViewModelProviders.of(this, factory).get(UserViewModel.class);

        viewModel.getEmpList().observe(MainActivity.this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(@Nullable List<UserModel> itemAndPeople) {
                recyclerViewAdapter.addItems(itemAndPeople);
            }
        });
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
