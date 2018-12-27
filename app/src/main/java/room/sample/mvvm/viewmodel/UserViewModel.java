package room.sample.mvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

import room.sample.mvvm.model.AppDatabase;
import room.sample.mvvm.model.UserModel;

public class UserViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;
    private LiveData<List<UserModel>> empList ;

    public UserViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getInstance(application);
        empList = appDatabase.getEmpModel().getAllEmp();
    }

    public LiveData<List<UserModel>> getEmpList() {
        return empList;
    }

    public void addEmp(final UserModel userModel) {
        new addAsyncTask(appDatabase).execute(userModel);
    }

    private static class addAsyncTask extends AsyncTask<UserModel, Void, Void> {

        private AppDatabase db;

        addAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final UserModel... params) {
            db.getEmpModel().addEmp(params[0]);
            return null;
        }

    }

    /**
     * A creator is used to inject the product ID into the ViewModel
     * <p>
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the product ID can be passed in a public method.
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;



        public Factory(@NonNull Application application) {
            mApplication = application;

        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new UserViewModel(mApplication);
        }
    }

}
