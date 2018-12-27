package room.sample.mvvm.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import room.sample.mvvm.R;
import room.sample.mvvm.databinding.ActivityAddBinding;
import room.sample.mvvm.model.UserModel;
import room.sample.mvvm.viewmodel.UserViewModel;

public class AddActivity extends AppCompatActivity {

    private UserViewModel viewModel;
    ActivityAddBinding binding ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add);
        UserViewModel.Factory factory = new UserViewModel.Factory(getApplication());
        viewModel = ViewModelProviders.of(this, factory).get(UserViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_save){
            saveEmp();
        }
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void saveEmp(){
        UserModel model = new UserModel(binding.enterId.getText().toString(), binding.enterName.getText().toString());
        viewModel.addEmp(model);
    }
}
