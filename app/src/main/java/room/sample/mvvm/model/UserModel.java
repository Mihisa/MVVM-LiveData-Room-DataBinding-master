package room.sample.mvvm.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class UserModel {
    @PrimaryKey(autoGenerate = true)
    public int id;

    String name;
    String empId;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserModel(String id, String name){
        this.empId = id ;
        this.name = name ;
    }

    public UserModel(){

    }
}
