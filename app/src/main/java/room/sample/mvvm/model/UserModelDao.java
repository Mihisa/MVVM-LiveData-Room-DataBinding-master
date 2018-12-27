package room.sample.mvvm.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserModelDao {

    @Query("select * from UserModel")
    LiveData<List<UserModel>> getAllEmp();

    @Insert
    void addEmp(UserModel userModel);

    @Delete
    void deleteEmp(UserModel userModel);

}
